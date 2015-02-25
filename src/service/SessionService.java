package service;

import model.SessionUser;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import util.Util;

public class SessionService {

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

    public static boolean addSession(User user,String sommeil) {

        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            SessionUser sessionUser = new SessionUser();
            if(!sommeil.equals(null))
                sessionUser.setTimeSleep(Integer.parseInt(sommeil));
            sessionUser.setUser(user);
            sessionUser.setName("seance "+sessionUser.getId());
            session.save(sessionUser);
            sessionUser.setName("seance "+sessionUser.getId());
            session.update(sessionUser);
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
    
    public static boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }

        Session session = getSession();
        Transaction tx = null;
        try{
            tx  = session.beginTransaction();
            session.delete(sessionUser);
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
    
    public static boolean updateSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }

        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(sessionUser);
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

    //return a sessionUser
    //@param sessionUser
    public static SessionUser getSessionUserByidS(String idS) {
        Session session = getSession();
        Transaction tx = null;
        SessionUser sessionUserObj = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from SessionUser where id="+ idS);
            sessionUserObj = (SessionUser)query.uniqueResult();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sessionUserObj;
    }

}
