package model;

import java.util.List;

public interface ITraining{
    public int getLength();
    public void setLength(int length);
    public String getName();
    public void setName(String name);
    public String getExplanation();
    public void setExplanation(String explanation);
    public List<IBodyPart> getBodyPart();
    public void setBodyPart(List<IBodyPart> bodyPart);
}