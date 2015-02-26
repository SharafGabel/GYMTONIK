package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
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
    @Column(name="id",unique = true,nullable = false)
    private Integer id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="dateProgram",nullable = false)
    @Type(type="date")
    private Date dateProgram;

    @Column(name="perform",nullable = false)
    private boolean perform;

    @Column(name = "timeSleep")
    private int timeSleep;

    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private AUser user;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "id")
    private List<ATraining> trainings;

    @OneToOne(cascade={CascadeType.ALL},mappedBy = "session")
    private Performance performance;
    //endregion

    //region Constructor
    public SessionUser(int timeSleep){
        trainings = new ArrayList<ATraining>();
        performance = new Performance();
        this.timeSleep = timeSleep;
        this.dateProgram = new Date();
    }

    public SessionUser() {
        this.timeSleep=8;
        this.perform = false;
        this.dateProgram = new Date();
        trainings = new ArrayList<ATraining>();

    }
    //endregion

    //region Getter/Setter
    public Integer getId() {
        return id;
    }

    public AUser getUser() {
        return user;
    }

    public void setUser(AUser user) {
        this.user = user;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public void addTraining(ATraining training) {
        this.trainings.add(training);
    }

    public void deleteTraining(ATraining training) {
        this.trainings.remove(training);
    }

    public void clearTraining() {
        this.trainings.removeAll(this.trainings);
    }

    public Date getDateProgram() {
        return dateProgram;
    }

    public void setDateProgram(Date dateProgram) {
        this.dateProgram = dateProgram;
    }

    public boolean isPerform() {
        return perform;
    }

    public void setPerform(boolean perform) {
        this.perform = perform;
    }

    public List<ATraining> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<ATraining> trainings) {
        this.trainings = trainings;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(int timeSleep) {
        this.timeSleep = timeSleep;
    }
    //endregion

    //region Method

    public int calculatePerformance(AUser user) {
        return -1;
    }

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateProgram=" + dateProgram +
                ", perform=" + perform +
                ", timeSleep=" + timeSleep +
                ", user=" + user +
                // ", trainings=" + trainings +
                ", performance=" + performance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionUser that = (SessionUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        /*
        if (perform != that.perform) return false;
        if (timeSleep != that.timeSleep) return false;
        if (dateProgram != null ? !dateProgram.equals(that.dateProgram) : that.dateProgram != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (performance != null ? !performance.equals(that.performance) : that.performance != null) return false;
        if (trainings != null ? !trainings.equals(that.trainings) : that.trainings != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        */
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateProgram != null ? dateProgram.hashCode() : 0);
        result = 31 * result + (perform ? 1 : 0);
        result = 31 * result + timeSleep;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (trainings != null ? trainings.hashCode() : 0);
        result = 31 * result + (performance != null ? performance.hashCode() : 0);
        return result;
    }
}