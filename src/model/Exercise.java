package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Exercise")
public class Exercise extends ATraining implements Serializable {
    //region Property

    //endregion

    //region Constructor
    public Exercise()
    {}
    public Exercise(AUser user,int length, String name, String explanation,int niveau)
    {
        super(user,length, name, explanation,niveau);
    }
    //endregion

    //region Getter/Setter
    //endregion

}