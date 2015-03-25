package model;

import org.hibernate.annotations.*;
import util.GsonExclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ATraining implements Serializable {

    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "idEx", unique = true, nullable = false)
    private int id;
    
    @Column(name="dureeExo",nullable = false)
    private int dureeExo;

    @Column(name="nbRepetition",nullable = false)
    private int nbRepetition;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="explanation",nullable = false)
    private String explanation;

    @Column(name="niveau",nullable=false)
    private int niveau;

    @GsonExclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ExerciceMuscle", joinColumns = @JoinColumn(name = "idEx"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<AMuscle> bodyParts;

    @OneToMany(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ExerciceSession> exerciceSessions;

    @GsonExclude
    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private AUser user;
    //endregion

    //region Constructor
    public ATraining()
    {
        this.bodyParts = new ArrayList<AMuscle>();
    }

    public ATraining(AUser user,int dureeExo,int nbRepetition, String name, String explanation,int niveau) {
        if(nbRepetition==0)
            nbRepetition=5;
        if(dureeExo==0)
            dureeExo=1;
        this.dureeExo = dureeExo;
        this.explanation = explanation;
        this.name = name;
        this.bodyParts = new ArrayList<AMuscle>();
        this.user = user;
        this.niveau = niveau;
        this.nbRepetition = nbRepetition;
    }
    //endregion

    //region Getter/Setter

    public AUser getUser() {
        return user;
    }

    public void setUser(AUser user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getDureeExo() {
        return dureeExo;
    }

    public void setDureeExo(int dureeExo) {
        this.dureeExo = dureeExo;
    }

    public int getNbRepetition() {
        return nbRepetition;
    }

    public void setNbRepetition(int nbRepetition) {
        this.nbRepetition = nbRepetition;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<AMuscle> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<AMuscle> bodyParts) {
        for(AMuscle aMuscle:bodyParts)
            aMuscle.addExercice(this);
        this.bodyParts = bodyParts;
    }

    public void addBodyPart(AMuscle bodyPart) {
        bodyPart.addExercice(this);
        this.bodyParts.add(bodyPart);
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public List<ExerciceSession> getExerciceSessions() {
        return exerciceSessions;
    }

    public void setExerciceSessions(List<ExerciceSession> exerciceSessions) {
        this.exerciceSessions = exerciceSessions;
    }

    public void addExerciceSession(ExerciceSession es){
        this.exerciceSessions.add(es);
    }



//endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ATraining)) return false;

        ATraining aTraining = (ATraining) o;

        if (dureeExo != aTraining.dureeExo) return false;
        if (id != aTraining.id) return false;
        if (nbRepetition != aTraining.nbRepetition) return false;
        if (niveau != aTraining.niveau) return false;
        if (bodyParts != null ? !bodyParts.equals(aTraining.bodyParts) : aTraining.bodyParts != null) return false;
        if (explanation != null ? !explanation.equals(aTraining.explanation) : aTraining.explanation != null)
            return false;
        if (name != null ? !name.equals(aTraining.name) : aTraining.name != null) return false;
        if (user != null ? !user.equals(aTraining.user) : aTraining.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dureeExo;
        result = 31 * result + nbRepetition;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0);
        result = 31 * result + niveau;
        result = 31 * result + (bodyParts != null ? bodyParts.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ATraining{" +
                "id=" + id +
                ", dureeExo=" + dureeExo +
                ", nbRepetition=" + nbRepetition +
                ", name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                ", niveau=" + niveau +
                ", user=" + user +
                '}';
    }
}