package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.Serializable;
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

    public static SessionUser createSession(User user, SessionUser sessionUser){
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            sessionUser.setUser(user);
            Serializable id = session.save(sessionUser);
            SessionUser sessionUser1 = (SessionUser)session.get(SessionUser.class, id);
            tx.commit();
            return sessionUser1;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static boolean addSession(User user) {

        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUser(user);
            sessionUser.setName("seance " + sessionUser.getIdS());
            System.out.println(sessionUser.toString());
            session.save(sessionUser);
           sessionUser.setName("seance " + sessionUser.getIdS());
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
    public static SessionUser addSessionUser(User user) {

        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUser(user);
            sessionUser.setName("seance "+sessionUser.getIdS());
            System.out.println(sessionUser.toString());
            session.save(sessionUser);
            sessionUser.setName("seance "+sessionUser.getIdS());
            session.update(sessionUser);
            tx.commit();
            return sessionUser;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }

    }

    public static boolean addOrUpdateExToSession(int idS,  int idEx)
    {
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            SessionUser sessionUser = SessionService.getSessionById(idS);
            ATraining exercise = ExerciseService.getExercise(idEx);
            ExerciceSession exerciceSession = new ExerciceSession(sessionUser, exercise);
            session.saveOrUpdate(exerciceSession);
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

    public static boolean addOrUpdateExToSession(SessionUser sessionUser,  ATraining exo) {
        return addOrUpdateExToSession(sessionUser.getIdS(), exo.getId());
    }

    public static void deleteSession(SessionUser sessionUser){
        Session session = getSession();
        Transaction tx = null;
        try{
            for(ExerciceSession es: sessionUser.getExerciceSessions()){
                HistoriqueService.deleteExerciceSession(es);
            }
            tx  = session.beginTransaction();
            session.delete(sessionUser);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
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

    public static List<SessionUser> getSessionList(User user1)
    {
        Session session = getSession();
        List<SessionUser> list = null;

        try{
            Query query=session.createQuery("from SessionUser where user.id="+user1.getId());
            list = (List<SessionUser>) query.list();
        }
        catch(Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
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
        } finally {
            session.close();
        }
        return list.get(0);
    }

}
