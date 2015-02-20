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

    Session session;

    public SessionService(Session session) {
        this.session = session;
    }

    public boolean addSession(User user) {
        int id;

        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUser(user);
            id = (Integer) session.save(sessionUser);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            HibernateUtil.closeSessionFactory();
        }
        
    }
    
    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
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
            HibernateUtil.closeSessionFactory();
        }
    }
    
    public boolean updateSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }

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
            HibernateUtil.closeSessionFactory();
        }
        
    }
}
