package model;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="SessionUser")
public class SessionUser{
    @Id @GeneratedValue
    @Column(name="idSession",nullable = false)
    private Integer idSession;
    @Column(name="dateProgram",nullable = false)
    @Type(type="date")
    private Date dateProgram;
    @Column(name="perform",nullable = false)
    private boolean perform;
    @Column(name = "timeSleep")
    private int timeSleep;
    @ManyToOne
    @JoinColumn(name="user_id",
            insertable=false, updatable=false,
            nullable=false)
    private AUser user;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="idSession")
    @IndexColumn(name="idx")
    private List<ITraining> trainings;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="idSession")
    @IndexColumn(name="idx")
    private List<Performance> performances;

    public SessionUser(int timeSleep)
    {
        trainings = new ArrayList<ITraining>();
        performances = new ArrayList<Performance>();
        this.timeSleep = timeSleep;
        user = new User();
    }

    public SessionUser() {
    }

    public AUser getUser() {
        return user;
    }

    public void setUser(AUser user) {
        this.user = user;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public int calculatePerformance(IUser user)
    {
        return -1;
    }

    public void addTraining(ITraining training)
    {

    }

    public void deleteTrainig(ITraining training)
    {

    }

    public void clearTraining()
    {

    }

    public void updateTraining(ITraining training)
    {

    }

    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        SessionUser session =(SessionUser)o;

        return true;
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

    public List<ITraining> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<ITraining> trainings) {
        this.trainings = trainings;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(int timeSleep) {
        this.timeSleep = timeSleep;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }
}