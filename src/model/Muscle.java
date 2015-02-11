package model;

public class Muscle extends AMuscle{
    private int idMuscle;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdMuscle() {
        return idMuscle;
    }

    public void setIdMuscle(int idMuscle) {
        this.idMuscle = idMuscle;
    }

    public Muscle()
    {
        super();
    }

}