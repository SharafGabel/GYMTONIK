package database;

/**
 * Created by shagabel on 04/02/2015.
 */
public abstract class AUser implements IUser{

    public String username;
    public String username_canonical;
    public String email;
    public String email_canonical;
    public String password;
    public String salt;
    public int height;
    public int weight;
    public AUser()
    {
        super();
    }
}
