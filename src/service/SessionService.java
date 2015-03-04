package service;

import model.Exercise;
import model.SessionUser;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

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
            if(!sommeil.trim().isEmpty())
                sessionUser.setTimeSleep(Integer.parseInt(sommeil));
            sessionUser.setUser(user);
            sessionUser.setName("seance "+sessionUser.getIdS());
            System.out.println(sessionUser.toString());
            session.save(sessionUser);
           sessionUser.setName("seance "+sessionUser.getIdS());
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

    public static boolean addExToSession(SessionUser sessionUser, Exercise exercise)
    {
        System.out.println("SHIT");
        /*if(sessionUser == null || sessionUser.getTrainings().contains(exercise)){
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");
            return false;
        }*/
        System.out.println("1");
        Session session = getSession();
        System.out.println("1.2");
        Transaction tx = null;
        System.out.println("1.3");
        try{
            tx = session.beginTransaction();
            System.out.println("123");
            System.out.println("125");
            sessionUser.addTraining(exercise);
            System.out.println("126");
            session.saveOrUpdate(sessionUser);
            System.out.println("127");
            /*System.out.println("2");
            sessionUser.addTraining(exercise);
            System.out.println("3");
            System.out.println("4");*/
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            System.out.println("PB");
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

    public static List getSessionList(User user1)
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from SessionUser where user.id="+user1.getId());
            list = query.list();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static SessionUser getSessionById(int idS)
    {
        Session session = getSession();
        List<SessionUser> list = null;

        try{
            Query query=session.createQuery("from SessionUser where id="+idS);
            list = query.list();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        session.close();
        return list.get(0);
    }

}
