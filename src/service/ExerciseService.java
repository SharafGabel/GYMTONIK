package service;

import model.Exercise;
import model.SessionUser;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by kukugath on 18/02/2015.
 */
public class ExerciseService {
    public boolean addExercise(SessionUser sessionUser) {
        Session session = HibernateUtil.openSession();
        int id;
        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Exercise exercise = new Exercise();

            id = (Integer) session.save(sessionUser);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
            HibernateUtil.closeSessionFactory();
        }

    }

    public boolean deleteExercise(Exercise exercise){
        if(exercise == null){
            return false;
        }
        Session session = HibernateUtil.openSession();
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
            HibernateUtil.closeSessionFactory();
        }
    }

    public boolean updateExercise(Exercise exercise){
        if(exercise == null){
            return false;
        }
        Session session = HibernateUtil.openSession();
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
            HibernateUtil.closeSessionFactory();
        }

    }


}
