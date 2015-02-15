package service;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by kuga on 15/02/2015.
 */
public class LoginService {
    
    public boolean authendificate(String userId,String password) {
        User user = getUserById(userId);
        if(user !=null && user.getUser_id().equals(userId) && user.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }

    private User getUserById(String userId) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;

        try{
            tx=session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from User where user_id="+userId+"");
            user = (User)query.uniqueResult();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }
}
