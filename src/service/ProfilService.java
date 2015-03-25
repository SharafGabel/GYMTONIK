package service;

import model.AUser;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ProfilService {

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

    public static void changeUsername(AUser user, String username) {
        user.setUsername(username);
        updateUser(user);
    }

    public static void changePassword(AUser user, String password) {
        user.setPassword(password);
        updateUser(user);
    }

    public static void changeEmail(AUser user, String email) {
        user.setEmail(email);
        updateUser(user);
    }

    public static void changeHeight(AUser user, int height) {
        user.setHeight(height);
        updateUser(user);
    }

    public static void changeWeight(AUser user, int weight) {
        user.setWeight(weight);
        updateUser(user);
    }

    public static void updateUser(AUser user){
        Session session = getSession();
        try{
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void deleteUser(AUser user) {
        Session session = getSession();
        try{
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
