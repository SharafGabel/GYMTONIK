package service;

import model.ATraining;
import model.AUser;
import model.Exercise;
import model.SessionUser;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import util.Util;

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

    public static void createExercise(AUser user, String descritpion, String name, int length){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.getTransaction();
            tx.begin();
            Exercise exercise = new Exercise(user,length,name,descritpion);
            session.save(exercise);
            tx.commit();
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }
    public static boolean addExercise(AUser user,SessionUser sessionUser,String length,String name,String explanation) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.getTransaction();
            tx.begin();
            Exercise exercise = new Exercise(user,Integer.parseInt(length),name,explanation);
            List<ATraining> exerciseList = new ArrayList<ATraining>();
            exerciseList.add(exercise);
            sessionUser.setTrainings(exerciseList);
            session.save(exercise);
            session.saveOrUpdate(sessionUser);
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

    public static List<Exercise> getExercises(AUser aUser) {
        Session session = getSession();
        Transaction tx = null;

        List<Exercise> exercises;
        try {
            tx = session.getTransaction();
            tx.begin();
            //Query query = session.createQuery("from Exercise where idUser="+aUser.getId());
            Query query = session.createQuery("from Exercise");
            exercises = (List<Exercise>) query.list();
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
            tx = session.getTransaction();
            tx.begin();
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
            Transaction tx = session.getTransaction();
            tx.begin();
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
        if(exercise == null|| user.getId()!=exercise.getUser().getId()){
            return false;
        }
        Session session = getSession();

        try{
            Transaction tx = session.getTransaction();
            tx.begin();
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

}
