package model;

public abstract class AMuscle implements IBodyPart{

    public String name;
    public String description;
    
    public AMuscle()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}