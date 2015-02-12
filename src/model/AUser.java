package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shagabel on 04/02/2015.
 */
public abstract class AUser implements IUser{

    protected String username;
    protected String username_canonical;
    protected String email;
    protected String email_canonical;
    protected String password;
    protected String salt;
    protected int height;
    protected int weight;
    protected List<SessionUser> sessionUsers;
    public AUser()
    {
        super();
        sessionUsers = new ArrayList<SessionUser>();
    }

    public List<SessionUser> getSessionUsers() {
        return sessionUsers;
    }

    public void setSessionUsers(List<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
