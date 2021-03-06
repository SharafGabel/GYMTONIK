package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.Serializable;
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

    //region update ExerciceSession
    public static boolean updateHistorique(int idS, ATraining exercise,ATraining newExercise) {
        SessionUser sessionUser = SessionService.getSessionById(idS);
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select h from ExerciceSession h where h.sessionUser.idS=(:idSession) and h.training.id=(:idEx)");
            query.setParameter("idSession",idS);
            query.setParameter("idEx",exercise.getId());

            List<ExerciceSession> exerciceSession1 = query.list();
            ExerciceSession exerciceSession = (ExerciceSession)session.get(ExerciceSession.class,exerciceSession1.get(0).getId());
            exerciceSession.setTraining(null);
            exerciceSession.setTraining(newExercise);
            session.update(exerciceSession);
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
    //endregion

    public static List<SessionUser> getSessionUserNotHaveThisExercise(ATraining exercise, User user) {
        Session session = getSession();
        Transaction tx = null;

        List<SessionUser> seances;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select distinct s from SessionUser s,ExerciceSession h where h.sessionUser.idS=s.idS and h.training.id!="+exercise.getId()+" and s.user.id="+user.getId());
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
            Query query = session.createQuery("select e from ExerciceSession h,Exercise e where h.sessionUser.id="+sessionUser.getIdS());
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

    public static List<ExerciceSession> getExercises(int idSessionUser) {
        Session session = getSession();
        Transaction tx = null;
        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select distinct h from ExerciceSession h where h.sessionUser.idS="+idSessionUser);
            exerciceSessions = query.list();
            tx.commit();
            return exerciceSessions;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static List<Object> getExercisesAndHistoriqueObject(int idSeance) {
        Session session = getSession();
        Transaction tx = null;

        List<Object> historiques;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select e.id,e.name,e.niveau,h.ratioDuree,h.ratioRepet,h.timeSleep,h.dateProgEffectue from ExerciceSession h,Exercise e where h.training.id=e.id and h.sessionUser.idS="+idSeance);
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

    public static List<ExerciceSession> getExercisesAndHistorique(int idSeance) {
        Session session = getSession();
        Transaction tx = null;

        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select e.name,e.niveau,h.dureeEffectue,h.nbRepetEffectue,h.timeSleep from ExerciceSession h,Exercise e where h.training.id=e.id and h.sessionUser.idS="+idSeance);
            exerciceSessions = query.list();
            System.out.println(exerciceSessions.toString());
            return exerciceSessions;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static boolean addExerciseDone(int idS, int idEx, int dureeEff, int nbRepEff, int timeSleep, int nbRepRequis, int dureeRequis) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SessionUser sessionUser = SessionService.getSessionById(idS);
            ATraining exercise = ExerciseService.getExercise(idEx);
            ExerciceSession exerciceSession = new ExerciceSession(sessionUser,exercise);
            exerciceSession.setTimeSleep(timeSleep);
            exerciceSession.setDureeEffectue(dureeEff);
            exerciceSession.setNbRepetEffectue(nbRepEff);
            exerciceSession.setDateProgEffectue(new Date());
            exerciceSession.calculRatioDuree(dureeEff,dureeRequis);
            exerciceSession.calculRatioRepet(nbRepEff,nbRepRequis);
            session.save(exerciceSession);
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

    public static boolean addExerciseDone(SessionUser sessionUser, ATraining exercise, int dureeEff, int nbRepEff, int timeSleep) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ExerciceSession exerciceSession = new ExerciceSession(sessionUser,exercise);
            exerciceSession.setTimeSleep(timeSleep);
            exerciceSession.setDureeEffectue(dureeEff);
            exerciceSession.setNbRepetEffectue(nbRepEff);
            exerciceSession.setDateProgEffectue(new Date());
            exerciceSession.calculRatioDuree(dureeEff,exercise.getDureeExo());
            exerciceSession.calculRatioRepet(nbRepEff,exercise.getNbRepetition());
            session.save(exerciceSession);
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

    public static ExerciceSession getHistoriqueByIdExerciseIdSeance(int idExercise,int idSeance){
        Session session = getSession();
        List<ExerciceSession> exerciceSession = null;
        try{
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select h from ExerciceSession h where h.training.id="+idExercise+" and h.sessionUser.id="+idSeance);
            exerciceSession = query.list();
            tx.commit();
            return exerciceSession.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return exerciceSession.get(0);
    }


    public static SessionUser deleteExerciceSession(ExerciceSession es, SessionUser su) {
        Session session = getSession();
        Transaction tx = null;
        SessionUser sessionUser = null;
        try{
            ExerciceSession exerciceSession = (ExerciceSession)session.get(ExerciceSession.class,es.getId());
            sessionUser = (SessionUser)session.get(SessionUser.class, su.getIdS());
            tx  = session.beginTransaction();
            sessionUser.getExerciceSessions().clear();
            session.saveOrUpdate(sessionUser);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                System.out.println("rollback");
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return sessionUser;
    }

    public static SessionUser deleteAllExerciceSessions(SessionUser su){
        Session session = getSession();
        Transaction tx = null;
        SessionUser sessionUser = null;
        try {
            sessionUser = (SessionUser) session.get(SessionUser.class, su.getIdS());
            sessionUser.getExerciceSessions().clear();
            session.update(sessionUser);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                System.out.println("rollback");
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return sessionUser;
    }

}
