package model;

import javax.persistence.*;

@Entity
@Table(name="Muscle")
public class Muscle extends AMuscle{

    @Id
    @GeneratedValue
    @Column(name="idMuscle",nullable = false)
    protected int idMuscle;

    public Muscle()
    {
        super();
    }

    public int getIdMuscle() {
        return idMuscle;
    }

    public void setIdMuscle(int idMuscle) {
        this.idMuscle = idMuscle;
    }

}