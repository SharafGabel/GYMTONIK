package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Performance")
public class Performance implements Serializable {
    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name="id")
    private Integer idP;

    @Column(name="name",nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name="historique",nullable = false)
    private Historique historique;
    //endregion

    //region Constructor
    public Performance(String name, Historique historique)
    {
        this.name=name;
        this.historique= historique;
    }
    //endregion

    //region Getter/Setter
    public Integer getIdP() {
        return idP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Historique getHistorique() {
        return historique;
    }

    public void setSession(Historique historique) {
        this.historique = historique;
        historique.setPerformance(this);
    }



    //endregion
}
