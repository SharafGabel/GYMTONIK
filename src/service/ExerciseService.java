package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
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

    //region create,add
    public static Exercise createExercise(AUser user, String description, String name, int length,int nbRep,int niveau,List<AMuscle> aMuscles){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            if(nbRep==0)
                nbRep=5;
            if(length==0)
                length=1;
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
//endregion

    //region update
    public static List<Exercise> getExercisesWithDifferentLevelFromExercise(Exercise exercise)
    {
        Session session = getSession();
        List<Exercise> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("Select e from Exercise e where e.name="+exercise.getName()+" and e.explanation="+exercise.getExplanation());
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }
        finally {
            session.close();
        }
        return exercises;
    }

    public static boolean updateExercise(int idEx,String nameExo,int length,int nbRepet,String description,List<AMuscle> aMuscles)
    {
        Session session = getSession();

        try{
            Transaction tx = session.beginTransaction();
            Exercise exercise = (Exercise)session.get(Exercise.class, idEx);
            tx.commit();
            List<Exercise> exerciseList = getExercisesWithDifferentLevelFromExercise(exercise);
            tx = session.beginTransaction();
            for(Exercise a:exerciseList) {

                int l=length*a.getNiveau()/exercise.getNiveau();
                int r=nbRepet*a.getNiveau()/exercise.getNiveau();

                if(exercise.getNiveau()==a.getNiveau()){
                    a.setDureeExo(length);
                    a.setNbRepetition(nbRepet);
                }
                else{
                    if(l==0)
                        a.setDureeExo(1);
                    if(r==0)
                        a.setNbRepetition(5);
                    a.setDureeExo(l);
                    a.setNbRepetition(r);
                }
                a.setName(nameExo);
                a.setExplanation(description);
                a.setBodyParts(aMuscles);

                session.update(a);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
//endregion

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

}
