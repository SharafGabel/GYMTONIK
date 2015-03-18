package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.jms.Session;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ExerciceSession")
public class ExerciceSession {

    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

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

    @ManyToOne(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private SessionUser sessionUser;

    @ManyToOne(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private ATraining training;
    //endregion

    //region Constructor
    public ExerciceSession() {
        this.ratioRepet = 0;
        this.ratioDuree = 0;
    }

    public ExerciceSession(SessionUser sessionUser, ATraining training) {
        this.sessionUser = sessionUser;
        this.training = training;
        sessionUser.getExerciceSessions().add(this);
        training.getExerciceSessions().add(this);
        this.ratioRepet = 0;
        this.ratioDuree = 0;
    }
    //endregion

    //region Getters/Setters

    public int getDureeEffectue() {
        return dureeEffectue;
    }

    public int getNbRepetEffectue() {
        return nbRepetEffectue;
    }

    public ATraining getTraining() {
        return training;
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(int timeSleep) {
        this.timeSleep = timeSleep;
    }

    public void setTraining(ATraining training) {
        this.training = training;
    }

    public void setNbRepetEffectue(int nbRepetEffectue) {
        this.nbRepetEffectue = nbRepetEffectue;
        this.dateProgEffectue = new Date();
    }

    public void setDureeEffectue(int dureeEffectue) {
        this.dureeEffectue = dureeEffectue;
        this.dateProgEffectue = new Date();
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

    public float calculRatioDuree(int a,int b) {
        this.ratioDuree = (a*100)/b;
        return ratioDuree;
    }

    public float calculRatioRepet(int a,int b) {
        this.ratioRepet = (a*100)/b;
        return ratioRepet;
    }

    public Integer getId() {
        return id;
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    //endregion
}
