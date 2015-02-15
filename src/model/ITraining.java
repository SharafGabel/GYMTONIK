package model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public interface ITraining{
    public int getLength();
    public void setLength(int length);
    public String getName();
    public void setName(String name);
    public String getExplanation();
    public void setExplanation(String explanation);
    public List<IBodyPart> getBodyPart();
    @ManyToOne
    @JoinColumn(name="idSession",
            insertable=false, updatable=false,
            nullable=false)
    public SessionUser sessionUser=new SessionUser();
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="idTraining")
    @IndexColumn(name="idx")
    public List<IBodyPart> bodyPart=new ArrayList<IBodyPart>();
}