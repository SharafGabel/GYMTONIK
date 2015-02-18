package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Muscle")
public class Muscle extends AMuscle implements Serializable {
    //region Property

    //endregion

    //region Constructor
    public Muscle()
    {
        super();
    }
    //endregion

    //region Getter/Setter
    //endregion
}