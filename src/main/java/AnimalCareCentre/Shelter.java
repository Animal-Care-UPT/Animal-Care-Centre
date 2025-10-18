package AnimalCareCentre;

import java.util.ArrayList;

/**
 * This class describes all the atribbutes and methods from Shelters.
 *
 */
public class Shelter {

    private int foundationYear;
    private int contact;
    private boolean isVerified;
    private ArrayList<Animal>animals;

    //Constructor from the class
    public Shelter(int foundationYear, int contact, boolean isVerified) {
        this.foundationYear = foundationYear;
        this.contact = contact;
        this.isVerified = isVerified;
        this.animals = new ArrayList<>();
    }

    //Different getters from the class
    public int getFoundationYear() {
        return foundationYear;
    }

    public int getContact() {
        return contact;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }


    @Override
    public String toString() {
        return "Shelter{" +
                "foundationYear=" + foundationYear +
                ", contact=" + contact +
                ", isVerified=" + isVerified +
                ", animals=" + animals + //uses the toString from the class Animal
                '}';
    }
}
