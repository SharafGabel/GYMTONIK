package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ATraining {
    //region Property
    @Id
    @GeneratedValue(generator="idGen")
    @GenericGenerator(name="idGen",strategy="org.hibernate.id.IncrementGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name="length",nullable = false)
    public int length;

    @Column(name="name",nullable = false)
    protected String name;

    @Column(name="explanation",nullable = false)
    public String explanation;

    @ManyToOne
    @JoinColumn(name="idSession",insertable=false, updatable=false,nullable=false)
    public SessionUser sessionUser;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="bodyparts")
    public List<AMuscle> bodyParts;
    //endregion

    //region Constructor
    public ATraining()
    {
        this.bodyParts = new ArrayList<AMuscle>();
    }

    public ATraining(int length, String name, String explanation) {
        this.length = length;
        this.explanation = explanation;
        this.name = name;
        this.bodyParts = new ArrayList<AMuscle>();
    }
    //endregion

    //region Getter/Setter
    public int getId() {
        return id;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<AMuscle> getBodyPart() {
        return this.bodyParts;
    }

    public void setBodyPart(List<AMuscle> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public void addBodyPart(AMuscle bodyPart) {
        this.bodyParts.add(bodyPart);
    }

    //endregion
}