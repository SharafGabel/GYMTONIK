package model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ExerciseService;
import service.LoginService;

public class ExerciseTest {
    static final String NAME_EXERCISE = "Exercice de test";
    static final String DESC_EXERCISE = "Description de test";
    static final int LENGHT_EXERCISE = 10;
    static final int NB_REP_EXERCISE = 25;
    static final int NIVEAU_EXERCISE = 1;

    boolean testDelete;
    ATraining exercise;
    AUser user;

    @BeforeMethod
    public void setUp() throws Exception {
        testDelete = false;
        user = LoginService.getUserByUsername("axel");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if(!testDelete)
            ExerciseService.deleteExercise(user, exercise);
    }

    /*@Test
    public void testCreateExerciseSansMuscle() {
        exercise = ExerciseService.createExercise(user, DESC_EXERCISE, NAME_EXERCISE, LENGHT_EXERCISE, NB_REP_EXERCISE, NIVEAU_EXERCISE);
        assert (exercise != null);
    }*/

    // TODO :
    /*@Test
    public void testCreateExerciseAvecMuscle() {
        exercise = ExerciseService.createExercise(user, DESC_EXERCISE, NAME_EXERCISE, LENGHT_EXERCISE, NB_REP_EXERCISE, NIVEAU_EXERCISE);
        assert (exercise != null);
    }*/

    /*@Test
    public void testDeleteExercise() {
        testDelete = true;
        exercise = ExerciseService.createExercise(user, DESC_EXERCISE, NAME_EXERCISE, LENGHT_EXERCISE, NB_REP_EXERCISE, NIVEAU_EXERCISE);
        assert (ExerciseService.deleteExercise(user, exercise));
    }*/
}