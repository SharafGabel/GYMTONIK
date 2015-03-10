package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AMuscle{
    //region Property    
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;
    
    @Column(name="name",nullable = false)
    protected String name;

    @ManyToMany(cascade={CascadeType.ALL})
    private List<ATraining> exercices;
    //endregion

    //region Constructor
    public AMuscle(){
        exercices = new ArrayList<ATraining>();
    }
    //endregion

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ATraining> getExercices() {
        return exercices;
    }

    public void setExercices(List<ATraining> exercices) {

        this.exercices = exercices;

    }

    public void addExercice(ATraining exercise)
    {
        this.exercices.add(exercise);
    }
    //endregion
}