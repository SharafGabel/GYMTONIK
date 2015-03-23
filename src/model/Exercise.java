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
    public Exercise(AUser user,int duree,int nbRepet, String name, String explanation,int niveau)
    {
        super(user,duree,nbRepet, name, explanation,niveau);
    }
    //endregion

    //region Getter/Setter
    //endregion

}