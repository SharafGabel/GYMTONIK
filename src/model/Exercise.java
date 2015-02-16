package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Exercise")
public class Exercise extends ATraining{
    @Id
    @GeneratedValue
    @Column(name="idExercise",nullable = false)
    private int idExercise;

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public int getIdExercice() {
        return idExercise;
    }

    public void setIdExercice(int idExercice) {
        this.idExercise = idExercice;
    }
    
    public void setBodyPart(List<IBodyPart> bodyPart){ this.bodyPart = bodyPart;}

    public Exercise()
    {
        super();
    }

}