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
    SessionService sessionService;
    Session session;

    public LoginService(Session session) {
        this.session = session;
        sessionService = new SessionService(session);
    }

    public boolean authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.validatePassword(password)) {
// TODO : corriger ce "truc"
//            sessionService.addSession(user);
            return true;
        } else return false;
    }



    public User getUserByUsername(String username) {
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
            HibernateUtil.closeSessionFactory();
        }
        return user;
    }

    public List<User> getUserList(){
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
            HibernateUtil.closeSessionFactory();
        }
        return list;
    }
}
