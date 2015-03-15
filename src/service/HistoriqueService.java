package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class HistoriqueService {

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

    public static List<SessionUser> getSessionUserNotHaveThisExercise(ATraining exercise, User user) {
        Session session = getSession();
        Transaction tx = null;

        List<SessionUser> seances;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select distinct s from SessionUser s,Historique h where h.idS=s.idS and h.idEx!="+exercise.getId()+" and s.user.id="+user.getId());
            seances = query.list();
            tx.commit();
            return seances;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Exercise> getExercises(SessionUser sessionUser) {
        Session session = getSession();
        Transaction tx = null;

        List<Exercise> exercises;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select e from Historique h,Exercise e where h.idS="+sessionUser.getIdS());
            exercises = query.list();
            tx.commit();
            return exercises;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Historique> getExercises(int idSessionUser) {
        Session session = getSession();
        Transaction tx = null;
        List<Historique> historiques;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select distinct h from Historique h where h.idS="+idSessionUser);
            historiques = query.list();
            tx.commit();
            return historiques;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Historique> getExercisesAndHistorique(int idSeance) {
        Session session = getSession();
        Transaction tx = null;

        List<Historique> historiques;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select e.name,e.niveau,h.dureeEffectue,h.nbRepetEffectue,h.timeSleep from Historique h,Exercise e where h.idEx=e.id and h.idS="+idSeance);
            historiques = query.list();
            System.out.println(historiques.toString());
            return historiques;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static boolean addExerciseDone(int idS,int idEx,AUser user,int dureeEff,int nbRepEff,int timeSleep,int nbRepRequis,int dureeRequis) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Historique historique = new Historique(idS,idEx,user.getId());
            historique.setTimeSleep(timeSleep);
            historique.setDureeEffectue(dureeEff);
            historique.setNbRepetEffectue(nbRepEff);
            historique.setDateProgEffectue(new Date());
            historique.calculRatioDuree(dureeEff,dureeRequis);
            historique.calculRatioRepet(nbRepEff,nbRepRequis);
            session.save(historique);
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
}
