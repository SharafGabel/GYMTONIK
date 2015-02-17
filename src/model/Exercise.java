package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Exercise")
public class Exercise extends ATraining{
    //region Property
    @Id @GeneratedValue
    @Column(name="id")
    private int id;
    //endregion

    //region Constructor
    public Exercise()
    {
        super();
    }
    //endregion

    //region Getter/Setter
    public int getId() {
        return id;
    }
    //endregion
}