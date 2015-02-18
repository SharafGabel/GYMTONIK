package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(UserDebugListener.class)
public abstract class AUser {
    //region Parameter
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;
    
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

        setSalt();
        setPassword(password);
        setUsername_canonical();
        setEmail_canonical();
    }
    //endregion

    //region Getter/Setter

    public Integer getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        setUsername_canonical();
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical() {
        username_canonical = username.toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setEmail_canonical();
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical() {
        email_canonical = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        try {
            salt = Integer.toString(SecureRandom.getInstance("SHA1PRNG").nextInt());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

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

    public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = password + salt;
        md.update(text.getBytes("UTF-8"));

        byte[] hashedPassword = md.digest();

        return new String(hashedPassword, "UTF-8");
    }

    public boolean validatePassword(String password) {
        boolean valid = false;

        try {
            valid = this.password.equals(hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return valid;
    }

    @Override
    public String toString() {
        return "AUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", username_canonical='" + username_canonical + '\'' +
                ", email='" + email + '\'' +
                ", email_canonical='" + email_canonical + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", sessions=" + sessions +
                '}';
    }
}
