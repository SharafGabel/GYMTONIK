package model;

import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ExerciseService;
import service.HistoriqueService;
import util.HibernateUtil;

import static org.testng.Assert.*;

public class ExerciceSessionTest {

    ExerciceSession exerciceSession;
    ExerciceSession exerciceSessionNoDb;
    ExerciceSession exerciceSessionFaible;
    SessionUser sessionUser;
    Exercise exercise;
    User user;
    @BeforeMethod
    public void setUp() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        exerciceSession = (ExerciceSession)session.get(ExerciceSession.class,2);
        exerciceSessionFaible =(ExerciceSession)session.get(ExerciceSession.class,4);
        exercise = (Exercise)session.get(Exercise.class,4);
        sessionUser = (SessionUser)session.get(SessionUser.class,4);
        user = (User)session.get(User.class,2);
        exerciceSessionNoDb = new ExerciceSession(sessionUser,exercise);

    }

    @Test
    public void testRatioExercise(){
        Exercise exerciseTest;

        int nbRepetReussi = exerciceSessionFaible.getNbRepetEffectue();
        int dureeEff = exerciceSessionFaible.getDureeEffectue();
        int niveau = exercise.getNiveau();

        int idExercise=4;
        Integer numSeance = 2;


        int result = dureeEff+nbRepetReussi/2;
        if(result >70 && niveau!=3){
            exerciseTest = ExerciseService.getExercise(idExercise + 1, niveau + 1);//retourne l'exercice du niveau supérieur
            assertTrue(HistoriqueService.updateHistorique(numSeance, exerciseTest, user));
        }
        else if(result <30 && niveau!=1){
            exerciseTest = ExerciseService.getExercise(idExercise - 1,niveau-1);//retourne l'exercice du niveau inférieur
            assertTrue(HistoriqueService.updateHistorique(numSeance,exerciseTest,user));
        }
        else if(result<30 && niveau ==1){
            exerciseTest = ExerciseService.getExercise(idExercise ,niveau);//retourne l'exercice du niveau inférieur
            assertTrue(HistoriqueService.updateHistorique(numSeance,exerciseTest,user));
        }
    }

}