package model;

import java.lang.String;

public interface IUser {

    public Integer getUser_id();
    public void setUser_id(Integer user_id);
    public void createSession();
    public void setSession(SessionUser session);
    public boolean deleteSession(SessionUser session);
    public void setWeight(int weight);
    public String getUsername();
    public String getEmail();
    public String getPassword();
    public int getWeight();
    public int getHeight();

}