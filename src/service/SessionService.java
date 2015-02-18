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
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUser(user);
            id = (Integer) session.save(sessionUser);
            tx.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        Session session = HibernateUtil.openSession();
        try{
            Transaction tx = session.getTransaction();
            session.delete(sessionUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
