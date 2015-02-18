package model;

import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

public abstract class ATraining implements  ITraining{
    //region Property
    @Column(name="length",nullable = false)
    public int length;

    @Column(name="name",nullable = false)
    protected String name;

    @Column(name="explanation",nullable = false)
    public String explanation;

    @ManyToOne
    @JoinColumn(name="idSession",insertable=false, updatable=false,nullable=false)
    public SessionUser sessionUser;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="idTraining")
    @IndexColumn(name="idx")
    public List<IBodyPart> bodyParts;
    //endregion

    //region Constructor
    public ATraining()
    {
        this.bodyParts = new ArrayList<IBodyPart>();
    }

    public ATraining(int length, String name, String explanation) {
        this.length = length;
        this.explanation = explanation;
        this.name = name;
        this.bodyParts = new ArrayList<IBodyPart>();
    }
    //endregion

    //region Getter/Setter
    @Override
    public int getLength() {
        return this.length;
    }
    @Override
    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getExplanation() {
        return this.explanation;
    }
    @Override
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    @Override
    public List<IBodyPart> getBodyPart() {
        return this.bodyParts;
    }
    @Override
    public void setBodyPart(List<IBodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }
    @Override
    public void addBodyPart(IBodyPart bodyPart) {
        this.bodyParts.add(bodyPart);
    }

    //endregion
}