package model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ATraining {

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

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "id")
    private List<AMuscle> bodyParts;

    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private AUser user;
    //endregion

    //region Constructor
    public ATraining()
    {}

    public ATraining(AUser user,int dureeExo,int nbRepetition, String name, String explanation,int niveau) {
        this.dureeExo = dureeExo;
        this.explanation = explanation;
        this.name = name;
        this.bodyParts = new ArrayList<AMuscle>();
        this.user = user;
        this.niveau = niveau;
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

    public List<AMuscle> getBodyPart() {
        return this.bodyParts;
    }

    public void setBodyPart(List<AMuscle> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public List<AMuscle> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<AMuscle> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public void addBodyPart(AMuscle bodyPart) {
        this.bodyParts.add(bodyPart);
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    //endregion

    //region equals


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