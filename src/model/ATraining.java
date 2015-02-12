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
    @Override
    public void setBodyPart(List<IBodyPart> bodyPart) {
        this.bodyPart = bodyPart;
    }
}