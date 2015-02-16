package model;

import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

public abstract class ATraining implements  ITraining{

    @Id @GeneratedValue
    @Column(name="idTraining",nullable = false)
    private Integer idTraining;
    @Column(name="length",nullable = false)
    public int length;
    @Column(name="name",nullable = false)
    protected String name;
    @Column(name="explanation",nullable = false)
    public String explanation;
    public List<IBodyPart> bodyPart;

    public ATraining()
    {
        this.bodyPart = new ArrayList<IBodyPart>();
    }

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getExplanation() {
        return explanation;
    }
    @Override
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    @Override
    public List<IBodyPart> getBodyPart() {
        return bodyPart;
    }
}