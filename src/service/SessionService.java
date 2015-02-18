package service;

import model.SessionUser;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by kukugath on 18/02/2015.
 */
public class SessionService {

    public boolean addSession(User user) {
        Session session = HibernateUtil.openSession();
        int id;
        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUser(user);
            id = (Integer) session.save(sessionUser);
            tx.commit();
            HibernateUtil.closeSession();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
            HibernateUtil.closeSession();
        }
        
    }
    
    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        Session session = HibernateUtil.openSession();
        try{
            Transaction tx = session.getTransaction();
            tx.begin();
            session.delete(sessionUser);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
            HibernateUtil.closeSession();
        }
    }
    
    public boolean updateSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        Session session = HibernateUtil.openSession();
        try{
            Transaction tx = session.getTransaction();
            tx.begin();
            session.update(sessionUser);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
            HibernateUtil.closeSession();
        }
        
    }
    
    
    
    
    
    
}
