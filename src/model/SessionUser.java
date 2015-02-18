package model;

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
    @Id @GeneratedValue
    @Column(name="id",nullable = false)
    private Integer id;

    @Column(name="dateProgram",nullable = false)
    @Type(type="date")
    private Date dateProgram;

    @Column(name="perform",nullable = false)
    private boolean perform;

    @Column(name = "timeSleep")
    private int timeSleep;

    @ManyToOne
    @JoinColumn(name="userId",insertable=false, updatable=false,nullable=false)
    private User user;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "id")
    private List<Exercise> trainings;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "id")
    private List<Performance> performances;
    //endregion

    //region Constructor
    public SessionUser(int timeSleep){
        trainings = new ArrayList<Exercise>();
        performances = new ArrayList<Performance>();
        this.timeSleep = timeSleep;
        user = new User();
    }

    public SessionUser() {
    }
    //endregion

    //region Getter/Setter
    public Integer getId() {
        return id;
    }

    public AUser getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public void addTraining(ITraining training) {

    }

    public void deleteTraining(ITraining training) {

    }

    public void clearTraining() {

    }

    public void updateTraining(ITraining training) {

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

    public List<Exercise> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Exercise> trainings) {
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

    public int calculatePerformance(IUser user) {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if(this ==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        SessionUser session =(SessionUser)o;

        return true;
    }
    //endregion
}