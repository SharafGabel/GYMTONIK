package model;

import javax.persistence.*;

public class Performance{
    //region Property
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name="idSession",insertable=false, updatable=false,nullable=false)
    private SessionUser sessionUser;
    //endregion

    //region Constructor
    public Performance()
    {
        sessionUser = new SessionUser();
    }
    //endregion

    //region Getter/Setter
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
