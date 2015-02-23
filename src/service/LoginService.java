package service;

import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class LoginService {

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

    public static boolean authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.validatePassword(password)) {

//            sessionService.addSession(user);
            return true;
        } else return false;
    }



    public static User getUserByUsername(String username) {
        Session session = getSession();
        Transaction tx = null;
        User user = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where username_canonical='"+username.toLowerCase()+"'");
            user = (User)query.uniqueResult();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getUserList(){
        Session session = getSession();
        List<User> list = new ArrayList<User>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("from User ").list();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
