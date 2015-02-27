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
    
    @Column(name="length",nullable = false)
    protected int length;

    @Column(name="name",nullable = false)
    protected String name;

    @Column(name="explanation",nullable = false)
    protected String explanation;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "trainings")
    protected List<SessionUser> sessionUser;


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
        this.sessionUser = new ArrayList<SessionUser>();
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

    public List<SessionUser> getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(List<SessionUser> sessionUser) {
        this.sessionUser = sessionUser;
    }

    public boolean addSession(SessionUser sessionUser1)
    {
        if(!sessionUser.contains(sessionUser1)) {
            this.sessionUser.add(sessionUser1);
            return true;
        }
        return false;
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

    //endregion

    //region equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ATraining)) return false;

        ATraining aTraining = (ATraining) o;

        if (id != aTraining.id) return false;
        if (length != aTraining.length) return false;
        if (!bodyParts.equals(aTraining.bodyParts)) return false;
        if (!explanation.equals(aTraining.explanation)) return false;
        if (!name.equals(aTraining.name)) return false;
        if (!user.equals(aTraining.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + length;
        result = 31 * result + name.hashCode();
        result = 31 * result + explanation.hashCode();
        result = 31 * result + bodyParts.hashCode();
        result = 31 * result + user.hashCode();
        return result;
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