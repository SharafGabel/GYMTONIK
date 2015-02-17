package model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public interface ITraining{
    //region Method
    public int getLength();
    public void setLength(int length);
    public String getName();
    public void setName(String name);
    public String getExplanation();
    public void setExplanation(String explanation);
    public List<IBodyPart> getBodyPart();
    public void setBodyPart(List<IBodyPart> bodyParts);
    public void addBodyPart(IBodyPart bodyPart);
    //endregion
}