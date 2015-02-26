package service;

import model.Exercise;
import model.SessionUser;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class GetList {
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
        return list.get(0);
    }

    public static List getExerciseList()
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from Exercise");
            list = query.list();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static List getPerformanceList(SessionUser sessionUser1)
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from Performance where session="+sessionUser1);
            list = query.list();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static List getMuscleList()
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from Muscle");
            list = query.list();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
}
