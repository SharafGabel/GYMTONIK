package model;

import java.util.ArrayList;
import java.util.List;

public abstract class ATraining implements  ITraining{
    private Integer idTraining;
    public int length;
    public String name;
    public String explanation;
    public List<IBodyPart> bodyPart;
    public ATraining()
    {
        bodyPart = new ArrayList<IBodyPart>();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<IBodyPart> getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(List<IBodyPart> bodyPart) {
        this.bodyPart = bodyPart;
    }
}