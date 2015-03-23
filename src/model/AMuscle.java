package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AMuscle implements Serializable{
    //region Property    
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;
    
    @Column(name="name",nullable = false)
    protected String name;

    @ManyToMany(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
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


    @Override
    public String toString() {
        return name;
    }

    public static String []toStringFromList(List<AMuscle> muscles) {
        String[] str=new String[muscles.size()];
        for(int i=0;i<muscles.size();i++)
        {
            str[i]=muscles.get(i).toString();
        }
        System.out.println("str toString : "+str.toString());
        return str;
    }
}