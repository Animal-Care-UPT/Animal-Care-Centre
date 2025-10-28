package AnimalCareCentre.models;

import AnimalCareCentre.enums.AnimalColor;
import AnimalCareCentre.enums.AnimalSize;
import AnimalCareCentre.enums.AnimalType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LostAnimal extends Animal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String location;

    boolean isLost;

    int contacto;

    @Override
    public String toString() {
        return "LostAnimal" +
                "name" + super.getName() +
                "type" + super.getType() +
                "race" + super.getRace() +
                "size" + super.getSize() +
                "color" + super.getColor() +
                " location='" + location +
                "description " + super.getDescription() + '\n';
    }

    public LostAnimal() {
    }

    public LostAnimal(String name, AnimalType type, String race, AnimalColor color, AnimalSize size, String description, String location) {
        super(name, type, race, size,  color,  description );
        this.location = location;
        this.contacto = contacto;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}

