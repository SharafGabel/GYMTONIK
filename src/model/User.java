package model;
import javax.persistence.*;
/**
 * Created by shagabel on 04/02/2015.
 */

@Entity
@Table(name="User")
public class User extends AUser {

    public User() {
    }

    @Override
    public void createSession() {

    }

    @Override
    public void setSession(SessionUser session) {

    }

    @Override
    public boolean deleteSession(SessionUser session) {
        return false;
    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public Integer getUser_id() {
        return user_id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        User user = (User)o;
        if(username != null ? !username.equals(user.username) : user.username !=null)return false;
        if(email != null ? !email.equals(user.email) : user.email !=null)return false;
        return true;
    }

}

