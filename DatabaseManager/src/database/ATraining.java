package database;

import java.util.ArrayList;
import java.util.List;

public abstract class ATraining implements  ITraining{

    public int length;
    public String name;
    public String explanation;
    public List<IBodyPart> bodyPart;
    public ATraining()
    {
        bodyPart = new ArrayList<IBodyPart>();
    }

}