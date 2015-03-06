package service;

import model.ATraining;
import model.Exercise;
import model.SessionUser;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class HistoriqueService {


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

    public static List<SessionUser> getSessionUserNotHaveThisExercise(ATraining exercise, User user) {
        Session session = getSession();
        Transaction tx = null;

        List<SessionUser> seances;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select distinct s from SessionUser s,Historique h where h.idS=s.idS and h.idEx!="+exercise.getId()+" and s.user.id="+user.getId());
            seances = query.list();
            tx.commit();
            return seances;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Exercise> getExercises(SessionUser sessionUser) {
        Session session = getSession();
        Transaction tx = null;

        List<Exercise> exercises;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select e from Historique h,Exercise e where h.idS="+sessionUser.getIdS());
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


}
