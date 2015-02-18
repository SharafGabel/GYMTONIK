package model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@MappedSuperclass
public abstract class AUser implements IUser{
    //region Parameter
    @Column(name="username",nullable = false)
    protected String username;

    @Column(name="username_canonical",nullable = false)
    protected String username_canonical;

    @Column(name="email",nullable = false)
    protected String email;

    @Column(name="email_canonical",nullable = false)
    protected String email_canonical;

    @Column(name="password",nullable = false)
    protected String password;

    @Column(name="salt")
    protected String salt;

    @Column(name="height")
    protected int height = 0;

    @Column(name="weight")
    protected int weight = 0;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="sessions")
    protected List<SessionUser> sessions;
    //endregion

    //region Constructor
    public AUser(){
        super();
        sessions = new ArrayList<SessionUser>();
    }

    public AUser(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
        setEmail_canonical();
        setUsername_canonical();
    }
    //endregion

    //region Getter/Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical() {
        username_canonical = username.toLowerCase();
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

    public void setEmail_canonical() {
        email_canonical = email.toLowerCase();
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

    @Override
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

    public List<SessionUser> getSessionUsers() {
        return sessions;
    }

    public void addSession(SessionUser session) { this.sessions.add(session);}

    public void setSessionUsers(List<SessionUser> sessionUsers) {
        this.sessions = sessionUsers;
    }
    //endregion
}
