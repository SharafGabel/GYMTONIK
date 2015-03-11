package service;

import model.AUser;
import model.Exercise;
import model.Historique;
import model.Performance;
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


    public static void createPerformance(String name, Historique historique) {
        Session session = getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction( );
            Performance perf = new Performance(name, historique);
            session.save(perf);
            tx.commit();
            }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
                e.printStackTrace();
        }

    }
    public static List<Integer> calculPerfrepet(List<Historique> historiques) {
        Session session = getSession();
        Transaction tx = null;
        List<Integer> ratioRepet = new ArrayList<Integer>();
        List<Exercise> extemp;

        for (Historique h : historiques) {
            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("Select e from Exercise e where e.id =" + h.getIdEx());
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

    public static List<Integer> calculPerfduree(List<Historique> historiques) {
        Session session = getSession();
        Transaction tx = null;
        List<Integer> ratioDuree = new ArrayList<Integer>();
        List<Exercise> extemp;

        for (Historique h : historiques) {
            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("Select e from Exercise e where e.id =" + h.getIdEx());
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

    public static void recupcalculPerformance(AUser user){
        Session session = getSession();
        Transaction tx = null;
        List<Historique> historiques = null;
        try {
            tx = session.beginTransaction( );

            Query query = session.createQuery("Select h from Historique h where h.idUser="+user.getId());
            historiques = query.list();

            tx.commit();
        }   catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }

    }






}
