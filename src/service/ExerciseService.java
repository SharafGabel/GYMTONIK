package service;

import model.Exercise;
import model.SessionUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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

    public static boolean addExercise(SessionUser sessionUser,String length,String name,String explanation) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.getTransaction();
            tx.begin();
            Exercise exercise = new Exercise(Integer.parseInt(length),name,explanation);
            exercise.setSessionUser(sessionUser);
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


    public static boolean deleteExercise(Exercise exercise){
        if(exercise == null){
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

    public static boolean updateExercise(Exercise exercise){
        if(exercise == null){
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
