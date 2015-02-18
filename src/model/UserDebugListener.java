package model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by shagabel on 18/02/2015.
 */
public class UserDebugListener {
    //region listeners
    @PreUpdate
    @PrePersist
    public void prePersist(AUser user){
        user.setEmail_canonical();
        user.setUsername_canonical();
    }
    //endregion
}
