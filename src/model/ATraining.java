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
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name="length",nullable = false)
    protected int length;

    @Column(name="name",nullable = false)
    protected String name;

    @Column(name="explanation",nullable = false)
    protected String explanation;

    /*@ManyToOne
    @JoinColumn(name="idSession",nullable=false)
    protected SessionUser sessionUser;
    */

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "id")
    protected List<AMuscle> bodyParts;

    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private AUser user;
    //endregion

    //region Constructor
    public ATraining()
    {}
    public ATraining(AUser user,int length, String name, String explanation) {
        this.length = length;
        this.explanation = explanation;
        this.name = name;
        this.bodyParts = new ArrayList<AMuscle>();
        this.user = user;
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
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        this.length = length;
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

    /*
    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(SessionUser sessionUser) {
        if (!this.sessionUser.equals(sessionUser)) {
            // TODO: corriger le bordel causé lorsqu'une nouvelle session est assignée
            this.sessionUser.deleteTraining(this);
            this.sessionUser = sessionUser;
            sessionUser.addTraining(this);
        }
    }
    */
    public List<AMuscle> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<AMuscle> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public void addBodyPart(AMuscle bodyPart) {
        this.bodyParts.add(bodyPart);
    }

    //endregion


    @Override
    public String toString() {
        return "ATraining{" +
                "id=" + id +
                ", length=" + length +
                ", name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                //", bodyParts=" + bodyParts +
                '}';
    }
}