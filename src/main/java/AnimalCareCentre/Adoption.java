package AnimalCareCentre;

import java.time.LocalDate;
//This class describes the different atributes from the Adoption and how it works.
public class Adoption {

    private User user;
    private Shelter shelter;
    private Animal animal;
    private LocalDate date;
    private AdoptionType type;


    //consctructor from the class
    public Adoption(User user, Shelter shelter, Animal animal, LocalDate date, AdoptionType type) {
        this.user = user;
        this.shelter = shelter;
        this.animal = animal;
        this.date = date;
        this.type = type;
    }

    //Getters area
    public User getUser() {
        return user;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDate getDate() {
        return date;
    }

    public AdoptionType getType() {
        return type;
    }

    //ToString from the class
    @Override
    public String toString() {
        return "Adoption{" +
                "user=" + user +
                ", shelter=" + shelter +
                ", animal=" + animal +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
