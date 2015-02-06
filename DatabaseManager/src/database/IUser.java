package database;

public interface IUser {
    public void createSession();
    public void setSession(Session session);
    public boolean deleteSession(Session session);
    public void setWeight(int weight);
    public String getUsername();
    public String getEmail();
    public String getPassword();
    public int getWeight();
    public int getHeight();

}