package database;

/**
 * Created by shagabel on 04/02/2015.
 */
public class User extends AUser {
    @Override
    public void createSession() {

    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public boolean deleteSession(Session session) {
        return false;
    }

    @Override
    public void setWeight(int weight) {

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

