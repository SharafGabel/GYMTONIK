package service;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuga on 15/02/2015.
 */
public class LoginService {
    SessionService sessionService = new SessionService();

    public boolean authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.validatePassword(password)) {
            sessionService.addSession(user);
            return true;
        } else return false;
    }



    private User getUserByUsername(String username) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;

        try{
            tx=session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from User where username_canonical="+username.toLowerCase()+"");
            user = (User)query.uniqueResult();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.closeSessionFactory();
        }
        return user;
    }

    public List<User> getUserList(){
        List<User> list = new ArrayList<User>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        try{
            tx=session.getTransaction();
            tx.begin();
            list = session.createQuery("from User ").list();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.closeSessionFactory();
        }
        return list;
    }
}
