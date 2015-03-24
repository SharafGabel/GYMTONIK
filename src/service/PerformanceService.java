package service;

import model.AUser;
import model.ExerciceSession;
import model.Exercise;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by driss on 04/03/2015.
 */
public class PerformanceService {
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


    public static List<Integer> calculPerfrepet(List<ExerciceSession> exerciceSessions) {
        Session session = getSession();
        Transaction tx = null;
        List<Integer> ratioRepet = new ArrayList<Integer>();
        List<Exercise> extemp;

        for (ExerciceSession h : exerciceSessions) {
            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("Select e from Exercise e where e.id =" + h.getTraining());
                extemp = query.list();
                h.setRatioRepet(h.getNbRepetEffectue()*100 / extemp.get(0).getNbRepetition());
                ratioRepet.add(h.getNbRepetEffectue()*100 / extemp.get(0).getNbRepetition());
                session.saveOrUpdate(h);
                tx.commit();

            } catch (Exception e) {
                if (tx != null)
                    tx.rollback();
                e.printStackTrace();

            }

        }
        return ratioRepet;
    }

    public static List<Integer> calculPerfduree(List<ExerciceSession> exerciceSessions) {
        Session session = getSession();
        Transaction tx = null;
        List<Integer> ratioDuree = new ArrayList<Integer>();
        List<Exercise> extemp;

        for (ExerciceSession h : exerciceSessions) {
            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("Select e from Exercise e where e.id =" + h.getTraining());
                extemp = query.list();
                h.setRatioDuree(h.getDureeEffectue()*100 / extemp.get(0).getDureeExo());
                ratioDuree.add(h.getDureeEffectue()*100 / extemp.get(0).getDureeExo());
                session.saveOrUpdate(h);
                tx.commit();

            } catch (Exception e) {
                if (tx != null)
                    tx.rollback();
                e.printStackTrace();

            }

        }
        return ratioDuree;
    }

    public static List<ExerciceSession> recupcalculPerformance(AUser user){
        Session session = getSession();
        Transaction tx = null;
        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select h from ExerciceSession h where  h.sessionUser.user.id="+user.getId()+" order by h.dateProgEffectue");
            exerciceSessions = query.list();
            tx.commit();
            return exerciceSessions;
        }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public static List<ExerciceSession> getUserPerformanceFromExerciseId(AUser user,int idExercise){
        Session session = getSession();
        Transaction tx = null;
        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select h from ExerciceSession h where  h.sessionUser.user.id="+user.getId()+" and h.training.id="+idExercise+ " order by h.dateProgEffectue");
            exerciceSessions = query.list();
            tx.commit();
            return exerciceSessions;
        }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public static List<ExerciceSession> getPerfFromExerciseId(int idExercise){
        Session session = getSession();
        Transaction tx = null;
        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select h,avg(ratioRepet) from ExerciceSession h where h.training.id="+idExercise+ " order by h.dateProgEffectue");
            exerciceSessions = query.list();
            tx.commit();
            return exerciceSessions;
        }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public static List<ExerciceSession> getAveragePerfFromExerciseId(int idExercise){
        Session session = getSession();
        Transaction tx = null;
        List<ExerciceSession> exerciceSessions;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select  avg(h.ratioRepet) from ExerciceSession h where h.training.id="+idExercise+ "");
            exerciceSessions = query.list();
            tx.commit();
            return exerciceSessions;
        }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return null;
    }






}
