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

    public static List getExerciseList(SessionUser sessionUser1)
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from Exercise");
            //Query query=session.createQuery("from Exercise where sessionUser="+sessionUser1); //On ne peut pas matcher avec sessionUser, pb au niveau du mapping
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

    public static List getMuscleList(Exercise exercise1)
    {
        Session session = getSession();
        List list = null;

        try{
            Query query=session.createQuery("from Muscle where training="+exercise1);
            list = query.list();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
}
