package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Performance")
public class Performance implements Serializable {
    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name="id")
    private Integer idP;

    @Column(name="name",nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name="session",nullable = false)
    private SessionUser session;
    //endregion

    //region Constructor
    public Performance()
    {

    }
    //endregion

    //region Getter/Setter
    public Integer getIdP() {
        return idP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SessionUser getSession() {
        return session;
    }

    public void setSession(SessionUser session) {
        this.session = session;
        session.setPerformance(this);
    }

    //endregion
}
