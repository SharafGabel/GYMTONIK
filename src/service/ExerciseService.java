package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class ExerciseService {

    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static Exercise createExercise(AUser user, String description, String name, int length,int nbRep,int niveau,List<AMuscle> aMuscles){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,description,niveau);
            exercise.setBodyParts(aMuscles);
            session.save(exercise);
            tx.commit();
            return exercise;
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    public static boolean addExerciseToSession(ATraining exercise,SessionUser sessionUser) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ExerciceSession exerciceSession = new ExerciceSession(sessionUser,exercise);
            session.save(exerciceSession);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }

    }


    public static List<Exercise> getExercises() {
        Session session = getSession();
        Transaction tx = null;

        List<Exercise> exercises;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select e from Exercise e");
            exercises = query.list();
            tx.commit();
            return exercises;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Exercise> getAllExercises() {
        Session session = getSession();
        Transaction tx = null;

        List<Exercise> exercises;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise");
            exercises = query.list();
            tx.commit();
            return exercises;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static Exercise getExercise(String idEx) {
        Session session = getSession();
        Transaction tx = null;

        Exercise exercise;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise where id="+ idEx);
            exercise = (Exercise) query.uniqueResult();
            tx.commit();
            return exercise;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static Exercise getExercise(int idEx,int test) {
        Session session = getSession();
        Transaction tx = null;

        Exercise exercise;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise where id="+ idEx+" and niveau="+test);
            exercise = (Exercise) query.uniqueResult();
            tx.commit();
            return exercise;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static Exercise getExerciseById(int idEx) {
        Session session = getSession();
        Transaction tx = null;

        Exercise exercise;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise where id="+ idEx);
            exercise = (Exercise) query.uniqueResult();
            tx.commit();
            return exercise;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static boolean deleteExercise(AUser user,Exercise exercise){
        //Car un Utilisateur ne peut supprimer que des exercices qu'il a lui même créé
        if(exercise == null || user.getId()!=exercise.getUser().getId()){
            return false;
        }

        Session session = getSession();
        try{
            Transaction tx = session.beginTransaction();
            session.delete(exercise);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    public static boolean updateExercise(AUser user,Exercise exercise){
        if(exercise == null || user.getId()!=exercise.getUser().getId()){
            return false;
        }
        Session session = getSession();

        try{
            Transaction tx = session.beginTransaction();
            session.update(exercise);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    public static List<Exercise> getExercises(SessionUser sessionUser) {
        Session session = getSession();
        List<Exercise> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("Select e from Exercise e, ExerciceSession h where h.sessionUser=" + sessionUser.getIdS() + " and h.training=+e.id");
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }
        finally {
            session.close();
        }

        return exercises;
    }


    public static List<Exercise> getExercises(int idsessionUser) {
        Session session = getSession();
        List<Exercise> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("Select distinct e from Exercise e, ExerciceSession h where h.sessionUser=" + idsessionUser + " and h.training.id=e.id");
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }
        finally {
            session.close();
        }

        return exercises;
    }

    public static List<Exercise> getUserExercises(AUser user){
        Session session = getSession();
        List<Exercise> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Exercise where user.id="+user.getId());
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }

        return exercises;
    }

    public static List<Exercise> getExercisesByLevel(int level){
        Session session = getSession();
        List<Exercise> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Exercise e where e.niveau="+level+"");
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }

        return exercises;
    }

    public static Integer getIdExerciseByExercise(String exerciseName) {
        Session session = getSession();
        Transaction tx = null;
        Integer exerciseId = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select e.id from Exercise e where e.name='"+exerciseName +"'");
            exerciseId = (Integer)query.uniqueResult();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return exerciseId;
    }


}
