package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Historique")
public class Historique {

    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

    @Column(name = "idH", nullable = false)
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

    @Column(name="ratioRepet")
    private float ratioRepet;

    @Column(name="ratioDuree")
    private float ratioDuree;

    @OneToOne(cascade={CascadeType.ALL},mappedBy = "historique")
    private Performance performance;
    //endregion

    //region Constructor
    public Historique() {
        this.ratioRepet = 0;
        this.ratioDuree = 0;
    }

    public Historique(int idS, int idEx,int idUser) {
        this.idS = idS;
        this.idEx = idEx;
        String str = Integer.toString(idS) + Integer.toString(idEx);
        this.idH=Integer.parseInt(str);
        this.idUser = idUser;
        this.ratioRepet = 0;
        this.ratioDuree = 0;
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

    public void setRatioRepet(float ratioRepet) {
        this.ratioRepet = ratioRepet;
    }

    public void setRatioDuree(float ratioDuree) {
        this.ratioDuree = ratioDuree;
    }

    public Date getDateProgEffectue() {
        return dateProgEffectue;
    }

    public void setDateProgEffectue(Date dateProgEffectue) {
        this.dateProgEffectue = dateProgEffectue;
    }

    public float getRatioRepet() {
        return ratioRepet;
    }

    public float getRatioDuree() {
        return ratioDuree;
    }

    public float calculRatioDuree(int a,int b)
    {
        this.ratioDuree = (a*100)/b;
        return ratioDuree;
    }

    public float calculRatioRepet(int a,int b)
    {
        this.ratioRepet = (a*100)/b;
        return ratioRepet;
    }

    public Integer getId() {
        return id;
    }

    //endregion
}
