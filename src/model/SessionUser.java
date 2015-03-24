package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="SessionUser")
public class SessionUser implements Serializable {
    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name="idS",unique = true,nullable = false)
    private Integer idS;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="dateProgram",nullable = false)
    @Type(type="date")
    private Date dateProgram;


    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private AUser user;

    @OneToMany(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ExerciceSession> exerciceSessions;


    //endregion

    //region Constructor

    public SessionUser() {
        this.dateProgram = new Date();
        exerciceSessions = new ArrayList<ExerciceSession>();

    }

    public SessionUser(String name,Date dateProgram) {
        this.dateProgram = dateProgram;
        this.name = name;
        this.exerciceSessions = new ArrayList<ExerciceSession>();
    }
    //endregion

    //region Getter/Setter
    public Integer getIdS() {
        return idS;
    }

    public AUser getUser() {
        return user;
    }

    public void setUser(AUser user) {
        this.user = user;
    }

    public Date getDateProgram() {
        return dateProgram;
    }

    public void setDateProgram(Date dateProgram) {
        this.dateProgram = dateProgram;
    }

    public List<ExerciceSession> getExerciceSessions() {
        return exerciceSessions;
    }

    public void setExerciceSessions(List<ExerciceSession> exerciceSessions) {
        this.exerciceSessions = exerciceSessions;
    }

    //endregion

    //region Method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + idS +
                ", name='" + name + '\'' +
                ", dateProgram=" + dateProgram +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionUser that = (SessionUser) o;

        if (idS != null ? !idS.equals(that.idS) : that.idS != null) return false;
        /*
        if (dateProgram != null ? !dateProgram.equals(that.dateProgram) : that.dateProgram != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        */
        return true;
    }

    @Override
    public int hashCode() {
        int result = idS != null ? idS.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateProgram != null ? dateProgram.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}