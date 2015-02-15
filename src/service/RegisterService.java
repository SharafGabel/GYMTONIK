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

    public boolean register(User user){
        Session session = HibernateUtil.openSession();
        if(isExistUser(user)) return false;

        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(user);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        HibernateUtil.closeSession();
        return true;
    }

    private boolean isExistUser(User user) {
        Session session = HibernateUtil.openSession();
        boolean result = false;
        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from User where user_id="+user.getUser_id()+"");
            User u = (User)query.uniqueResult();
            tx.commit();
            if(u != null) result = true;
        }catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        HibernateUtil.closeSession();
        return result;
    }
}
