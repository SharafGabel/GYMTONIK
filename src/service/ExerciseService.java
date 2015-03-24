package service;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseService {

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

    //region create
    public static ATraining createExercise(AUser user, String description, String name, int length,int nbRep,int niveau, List<AMuscle> aMuscles){
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            if(nbRep==0)
                nbRep=5;
            if(length==0)
                length=1;
            ATraining exercise = new Exercise(user,length,nbRep,name,description,niveau);
            exercise.setBodyParts(aMuscles);
            Serializable idEx = session.save(exercise);
            tx.commit();
            exercise = (Exercise) session.get(Exercise.class, idEx);
            return exercise;
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    public static ATraining createExercise(AUser user, String description, String name, int length,int nbRep,int niveau){
        Session session = getSession();
        Transaction tx = null;

        List<AMuscle> aMuscles = new ArrayList<AMuscle>();

        try{
            tx = session.beginTransaction();
            if(nbRep==0)
                nbRep=5;
            if(length==0)
                length=1;
            Exercise exercise = new Exercise(user,length,nbRep,name,description,niveau);
            exercise.setBodyParts(aMuscles);
            Serializable idEx = session.save(exercise);
            tx.commit();
            exercise = (Exercise) session.get(Exercise.class, idEx);
            return exercise;
        }catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

//endregion

    //region update
    public static List<ATraining> getExercisesWithDifferentLevelFromExercise(ATraining exercise)
    {
        Session session = getSession();
        List<ATraining> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            //TODO : lorsque le nom est composé de plus d'un mot, il y a une erreur dans la requête SQL ( We have to find how to fix this problem )
            Query query = session.createQuery("Select e from Exercise e where e.name=(:exercice) and e.explanation=(:description)");
            query.setParameter("exercice",exercise.getName());
            query.setParameter("description",exercise.getExplanation());
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }
        finally {
            session.close();
        }
        return exercises;
    }

    public static boolean updateExercise(int idEx,String nameExo,int length,int nbRepet,String description,List<AMuscle> aMuscles)
    {
        Session session = getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            ATraining exercise = getExerciseById(idEx);

            List<ATraining> exerciseList = getExercisesWithDifferentLevelFromExercise(exercise);

            for(ATraining a:exerciseList) {

                int l=length*a.getNiveau()/exercise.getNiveau();
                int r=nbRepet*a.getNiveau()/exercise.getNiveau();

                if(exercise.getNiveau()== a.getNiveau()){
                    a.setDureeExo(length);
                    a.setNbRepetition(nbRepet);
                }
                else{
                    if(l==0)
                        a.setDureeExo(1);
                    if(r==0)
                        a.setNbRepetition(5);
                    a.setDureeExo(l);
                    a.setNbRepetition(r);
                }
                a.setName(nameExo);
                a.setExplanation(description);
                if (aMuscles.size() > 0) {
                    a.setBodyParts(new ArrayList<AMuscle>());
                    a.setBodyParts(aMuscles);
                }
                session.update(a);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    public static boolean updateExercise(ATraining exercise, List<AMuscle> muscles) {
        return updateExercise(exercise.getId(), exercise.getName(), exercise.getDureeExo(), exercise.getNbRepetition(), exercise.getExplanation(), muscles);
    }
//endregion

    //region exercices à partir de muscles
    public static List<ATraining> getExercisesFromMuscles(List<AMuscle> muscles,int niveau) {
        Session session = getSession();
        Transaction tx = null;

        List<ATraining> exercises;
        try {
            tx = session.beginTransaction();
            String str=" b.id="+muscles.get(0).getId();
            for(int i=1;i<muscles.size();i++)
            {
                str+=" or b.id="+muscles.get(i).getId();
            }
            Query query = session.createQuery("Select distinct e from Exercise e join e.bodyParts b where e.niveau=:niveaux and"+str);
            //Query query = session.createQuery("Select e from Exercise e join e.bodyParts b where e.niveau=:niveaux and b.name in (:tags) group by e having count(b)=:tag_count");
            query.setParameter("niveaux",niveau);
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
//endregion

    //region delete
    public static boolean deleteExercise(AUser user,ATraining exercise){
        //Car un Utilisateur ne peut supprimer que des exercices qu'il a lui même créé
        if(exercise == null || user.getId()!=exercise.getUser().getId()){
            return false;
        }

        Session session = getSession();
        List<ATraining> exercises = getExercisesWithDifferentLevelFromExercise(exercise);
        try{
            Transaction tx = session.beginTransaction();
            for(ATraining e:exercises)
                session.delete(e);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
    //endregion

    public static List<ATraining> getExercises() {
        Session session = getSession();
        Transaction tx = null;

        List<ATraining> exercises;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select e from Exercise e");
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

    public static List<ATraining> getAllExercises() {
        return getExercises();
    }

    public static ATraining getExercise(int idEx,int test) {
        Session session = getSession();
        Transaction tx = null;

        Exercise exercise;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise where id="+ idEx+" and niveau="+test);
            exercise = (Exercise) query.uniqueResult();
            tx.commit();
            return exercise;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static ATraining getExerciseById(int idEx) {
        Session session = getSession();
        Transaction tx = null;

        Exercise exercise;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Exercise where id="+ idEx);
            exercise = (Exercise) query.uniqueResult();
            tx.commit();
            return exercise;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public static ATraining getExercise(String idEx) {
        return getExerciseById(Integer.parseInt(idEx));
    }

    public static List<ATraining> getExercises(SessionUser sessionUser) {
        return getExercises(sessionUser.getIdS());
    }


    public static List<ATraining> getExercises(int idsessionUser) {
        Session session = getSession();
        List<ATraining> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("Select distinct e from Exercise e, ExerciceSession h where h.sessionUser.idS=" + idsessionUser + " and h.training.id=e.id");
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }
        finally {
            session.close();
        }

        return exercises;
    }

    public static List<ATraining> getUserExercises(AUser user){
        Session session = getSession();
        List<ATraining> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Exercise where user.id="+user.getId());
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }

        return exercises;
    }

    public static List<ATraining> getExercisesByLevel(int level){
        Session session = getSession();
        List<ATraining> exercises = null;

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from Exercise e where e.niveau="+level+"");
            exercises = query.list();
            tx.commit();
        } catch (Exception e) {
        }

        return exercises;
    }

}
