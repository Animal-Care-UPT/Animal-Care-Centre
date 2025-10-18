package AnimalCareCentre;
/**
 * This class describes the sponsorhips to the animals and how it works.
 *
 */
public class Sponsorship {

    private User user;
    private Animal animal;

    //Constructor from the class
    public Sponsorship(User user, Animal animal) {
        this.user = user;
        this.animal = animal;
    }

    //Getters area
    public User getUser() {
        return user;
    }

    public Animal getAnimal() {
        return animal;
    }

    //toString from the Class
    @Override
    public String toString() {
        return "Sponsorship{" +
                "user=" + user +
                ", animal=" + animal +
                '}';
    }
}
