package model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Created by shagabel on 04/02/2015.
 */
public abstract class AUser implements IUser{

    @Id @GeneratedValue
    @Column(name="user_id",nullable = false)
    protected Integer user_id;
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
    protected int height;
    @Column(name="weight")
    protected int weight;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    @IndexColumn(name="idx")
    protected List<SessionUser> sessionUsers;

    public AUser()
    {
        super();
        sessionUsers = new ArrayList<SessionUser>();
    }

    public AUser(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public List<SessionUser> getSessionUsers() {
        return sessionUsers;
    }

    public void setSessionUsers(List<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

    @Override
    public Integer getUser_id() {
        return user_id;
    }

    @Override
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
