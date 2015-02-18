package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AMuscle{
    //region Property    
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected int id;
    
    @Column(name="name",nullable = false)
    protected String name;

    @Column(name="description")
    protected String description;

    @ManyToOne
    @JoinColumn(name="idTraining",insertable=false, updatable=false,nullable=false)
    protected ATraining training;
    //endregion

    //region Constructor
    public AMuscle()
    {
        training=new Exercise();
    }
    //endregion

    //region Getter/Setter
    public int getId() {
        return id;
    }
    
    public ATraining getTraining() {
        return training;
    }

    public void setTraining(ATraining training) {
        this.training = training;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}