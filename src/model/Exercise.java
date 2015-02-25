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
    {
        super();
    }
    public Exercise(int length, String name, String explanation)
    {
        super(length, name, explanation);
    }
    //endregion

    //region Getter/Setter
    //endregion
}