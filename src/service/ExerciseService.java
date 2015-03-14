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

    public static void createExercise(AUser user, String name, String description, int length,int niveau, int nbRep){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,description,niveau);
            session.save(exercise);
            tx.commit();
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void createExerciseWithMuscle(AUser user, String descritpion, String name, int length,int nbRep,int niveau,List<AMuscle> aMuscles){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,descritpion,niveau);
            exercise.setBodyParts(aMuscles);
            session.save(exercise);
            tx.commit();
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static boolean addExercise(AUser user,SessionUser sessionUser,int length,int nbRep,String name,String explanation,int niveau) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,explanation,niveau);
            session.save(exercise);
            session.saveOrUpdate(sessionUser);
            Historique historique = new Historique(sessionUser.getIdS(),exercise.getId(),user.getId());
            session.save(historique);
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

    public static boolean addExerciseWithMuscle(AUser user,SessionUser sessionUser,int length,int nbRep,String name,String explanation,int niveau,List<AMuscle> aMuscles) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,explanation,niveau);
            exercise.setBodyParts(aMuscles);
            session.save(exercise);
            session.saveOrUpdate(sessionUser);
            Historique historique = new Historique(sessionUser.getIdS(),exercise.getId(),user.getId());
            session.save(historique);
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

    public static boolean addExerciseWithMuscleWithoutSession(AUser user,int length,int nbRep,String name,String explanation,int niveau,List<AMuscle> aMuscles) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,explanation,niveau);
            exercise.setBodyParts(aMuscles);
            session.save(exercise);
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

    public static boolean addExerciseToSession(AUser user,SessionUser sessionUser,int length,int nbRep,String name,String explanation,int niveau) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Exercise exercise = new Exercise(user,length,nbRep,name,explanation,niveau);
            session.save(exercise);
            session.saveOrUpdate(sessionUser);
            Historique historique = new Historique(sessionUser.getIdS(),exercise.getId(),user.getId());
            session.save(historique);
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
        if(exercise == null /*|| user.getId()!=exercise.getUser().getId()*/){
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
            Query query = session.createQuery("Select e from Exercise e, Historique h where h.idS=" + sessionUser.getIdS() + " and h.idEx=e.id");
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
            Query query = session.createQuery("Select distinct e from Exercise e, Historique h where h.idS=" + idsessionUser + " and h.idEx=e.id");
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


}
