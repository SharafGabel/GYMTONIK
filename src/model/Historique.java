package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Historique")
public class Historique {

    //region Property
    @Id
    @Column(name = "idH", unique = true, nullable = false)
    private int idH;

    @Column(name="idS",nullable = false)
    private int idS;

    @Column(name="idEx",nullable = false)
    private int idEx;

    @Column(name="idUser",nullable = false)
    private int idUser;

    @Column(name="nbRepetEffectue")
    private int nbRepetEffectue;

    @Column(name="dureeEffectue")
    private int dureeEffectue;

    @Column(name = "timeSleep")
    private int timeSleep;

    @Column(name="dateProgEffectue")
    @Type(type="date")
    private Date dateProgEffectue;

    @OneToOne(cascade={CascadeType.ALL},mappedBy = "historique")
    private Performance performance;
    //endregion

    //region Constructor
    public Historique() {
    }

    public Historique(int idS, int idEx,int idUser) {
        this.idS = idS;
        this.idEx = idEx;
        String str = Integer.toString(idS) + Integer.toString(idEx);
        this.idH=Integer.parseInt(str);
        this.idUser = idUser;
    }
    //endregion

    //region Getters/Setters

    public Performance getPerformance() {
        return performance;
    }

    public int getDureeEffectue() {
        return dureeEffectue;
    }

    public int getNbRepetEffectue() {
        return nbRepetEffectue;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdEx() {
        return idEx;
    }

    public int getIdS() {
        return idS;
    }

    public int getIdH() {
        return idH;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(int timeSleep) {
        this.timeSleep = timeSleep;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
        String str = Integer.toString(idS) + Integer.toString(idEx);
        this.idH=Integer.parseInt(str);
    }

    public void setNbRepetEffectue(int nbRepetEffectue) {
        this.nbRepetEffectue = nbRepetEffectue;
        this.dateProgEffectue = new Date();
    }

    public void setDureeEffectue(int dureeEffectue) {
        this.dureeEffectue = dureeEffectue;
        this.dateProgEffectue = new Date();
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    //endregion
}
