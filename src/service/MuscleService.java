package service;

import model.AMuscle;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class MuscleService {

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

    public static List<AMuscle> getAllMuscles() {
        Session session = getSession();
        Transaction tx = null;

        List<AMuscle> aMuscles;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select m from Muscle m");
            aMuscles = query.list();
            tx.commit();
            return aMuscles;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static AMuscle getMuscleById(int idMuscle) {
        Session session = getSession();
        Transaction tx = null;
        List<AMuscle> aMuscles;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select m from Muscle m where m.id="+idMuscle);
            aMuscles = query.list();
            tx.commit();
            return aMuscles.get(0);
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
