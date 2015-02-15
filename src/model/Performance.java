package model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by shagabel on 04/02/2015.
 */
public class Performance{

    private Integer perfId;
    private String name;
    @ManyToOne
    @JoinColumn(name="idSession",
            insertable=false, updatable=false,
            nullable=false)
    private SessionUser sessionUser;

    public Integer getPerfId() {
        return perfId;
    }

    public void setPerfId(Integer perfId) {
        this.perfId = perfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Performance()
    {
        sessionUser = new SessionUser();
    }
}
