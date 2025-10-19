package AnimalCareCentre.models;

import java.util.ArrayList;

/**
 * This class describes the model of a Shelter.
 *
 */
public class Shelter {

  private int foundationYear;
  private int contact;
  private boolean isVerified;
  private ArrayList<Animal> animals;

  /**
   * Constructor for the class Shelter
   *
   * @param foundationYear
   * @param contact
   * @param isVerified
   */
  public Shelter(int foundationYear, int contact, boolean isVerified) {
    this.foundationYear = foundationYear;
    this.contact = contact;
    this.isVerified = isVerified;
    this.animals = new ArrayList<>();
  }

  // Getters
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
        ", animals=" + animals + // uses the toString from the class Animal
        '}';
  }
}
