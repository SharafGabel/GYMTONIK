package service;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by kuga on 15/02/2015.
 */
public class RegisterService {

    Session session;

    public RegisterService(Session session) {
        this.session = session;
    }

    public boolean register(User user){
        if(isExistUser(user)) return false;

        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSessionFactory();
        }
        return true;
    }

    private boolean isExistUser(User user) {
        boolean result = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where username_canonical='"+user.getUsername_canonical()+"'");
            User u = (User)query.uniqueResult();
            tx.commit();
            if(u != null) result = true;
        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            HibernateUtil.closeSessionFactory();
        }
        return result;
    }
}
