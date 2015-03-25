package util;

import model.ExerciceSession;

import java.util.List;

/**
 * Created by kukugath on 25/03/2015.
 */
public class ObjectForGson {

    private List<ExerciceSession> exerciceSessions; 
    private Double moyenneGenerale;

    public ObjectForGson(List<ExerciceSession> exerciceSessions, Double moyenneGenerale) {
        this.exerciceSessions = exerciceSessions;
        this.moyenneGenerale = moyenneGenerale;
    }
    
    
}
