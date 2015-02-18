package model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="User")
public class User extends AUser implements Serializable {
    //region Property
    @Id @GeneratedValue
    @Column(name="id")
    protected Integer id;
    //endregion

    //region Constructor
    public User() {
    }

    public User(String username, String email, String password) {
        super(username,email,password);
    }
    //endregion

    //region Getter/Setter
    public Integer getId() {
        return id;
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
    //endregion

    //region Method
    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        User user = (User)o;
        if(username != null ? !username.equals(user.username) : user.username !=null)return false;
        if(email != null ? !email.equals(user.email) : user.email !=null)return false;
        return true;
    }
    //endregion
}

