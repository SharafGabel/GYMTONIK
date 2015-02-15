package model;

import javax.persistence.*;

public abstract class AMuscle implements IBodyPart{

    @Column(name="name",nullable = false)
    protected String name;
    @Column(name="description")
    protected String description;
    @ManyToOne
    @JoinColumn(name="idTraining",
            insertable=false, updatable=false,
            nullable=false)
    protected ITraining training;

    public ITraining getTraining() {
        return training;
    }

    public void setTraining(ITraining training) {
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

    public AMuscle()
    {
        training=new Exercise();
    }
}