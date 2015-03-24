package model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.ExerciseService;
import service.LoginService;
import service.MuscleService;

import java.util.List;

public class ExerciseTest {
    private final static String NAME_MOD = "Exercice de test BIS";
    private final static String DESCR_MOD ="Description de test BIS";
    private final static int LENGHT_MOD = 11;
    private final static int NB_REP_MOD = 26;
    private final static int LEVEL_MOD = 2;

    private boolean testDelete;
    private ATraining exercise;
    private AUser user;
    private List<AMuscle> muscles;

    @DataProvider(name = "create")
    public static Object[][] baseExercise() {
        return new Object[][] {{"Exercice de test", "Description de test", 10, 25, 1}};
    }


    @BeforeMethod
    public void setUp() throws Exception {
        testDelete = false;
        user = LoginService.getUserByUsername("axel");
        muscles = MuscleService.getAllMuscles();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if(!testDelete)
            ExerciseService.deleteExercise(user, exercise);
    }

    @Test(dataProvider = "create")
    public void testCreateExercise(String name, String descr, int lenght, int nbRep, int niveau) {
        exercise = ExerciseService.createExercise(user, name, descr, lenght, nbRep, niveau, muscles);
        assert (exercise != null);
    }

    @Test(dataProvider = "create")
    public void testUpdateExercise(String name, String descr, int lenght, int nbRep, int niveau) {
        exercise = ExerciseService.createExercise(user, name, descr, lenght, nbRep, niveau, muscles);

        exercise.setName(NAME_MOD);
        exercise.setExplanation(DESCR_MOD);
        exercise.setDureeExo(LENGHT_MOD);
        exercise.setNbRepetition(NB_REP_MOD);
        exercise.setNiveau(LEVEL_MOD);

        assert (ExerciseService.updateExercise(exercise, muscles));
    }

    @Test(dataProvider = "create")
    public void testUpdateExerciseParams(String name, String descr, int lenght, int nbRep, int niveau) {
        exercise = ExerciseService.createExercise(user, name, descr, lenght, nbRep, niveau, muscles);

        assert (ExerciseService.updateExercise(exercise.getId(), name, lenght, nbRep, descr, muscles));
        exercise = ExerciseService.getExercise(exercise.getId());
    }

    @Test(dataProvider = "create")
    public void testDeleteExercise(String name, String descr, int lenght, int nbRep, int niveau) {
        testDelete = true;
        exercise = ExerciseService.createExercise(user, name, descr, lenght, nbRep, niveau, muscles);
        assert (ExerciseService.deleteExercise(user, exercise));
    }
}