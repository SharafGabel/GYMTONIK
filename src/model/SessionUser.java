package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionUser{
    private Integer idSession;
    private Date dateProgram;
    private boolean perform;
    private List<ITraining> trainings;
    private int timeSleep;

    public SessionUser(int timeSleep)
    {
        trainings = new ArrayList<ITraining>();
        this.timeSleep = timeSleep;
    }

    public SessionUser() {
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