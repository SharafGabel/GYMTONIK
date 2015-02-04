package model;

import java.lang.String;

public interface IUser {
    public void createSession();
    public void setSession(Session session);
    public boolean deleteSession(Session session);
    public void setWeight(int weight);
}