package service;

import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class RegisterService {

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

    public static boolean register(User user){
        if(userExists(user))
            return false;

        Session session = getSession();

        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }

    private static boolean userExists(User user) {
        Session session = getSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where username_canonical='" + user.getUsername_canonical() + "'");
            User u = (User)query.uniqueResult();
            tx.commit();
            if(u != null)
                return true;
        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        return false;
    }
}
