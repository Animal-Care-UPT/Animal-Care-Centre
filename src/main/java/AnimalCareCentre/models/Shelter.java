package AnimalCareCentre.models;

import AnimalCareCentre.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * This class describes the model of a Shelter.
 *
 */
@Entity
@Table(name = "Shelters")
public class Shelter extends Account {

  private int foundationYear;
  private int contact;
  private boolean isVerified;
  // private ArrayList<Animal> animals; commented for now

  /**
   * Constructor for the class Shelter
   *
   * @param foundationYear
   * @param contact
   * @param isVerified
   */
  public Shelter(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      String answer, int foundationYear, int contact) {
    super(name, email, password, location, securityQuestion, answer);
    this.foundationYear = foundationYear;
    this.contact = contact;
    isVerified = true;
  }

  protected Shelter() {
  }

  // Getters
  public int getFoundationYear() {
    return foundationYear;
  }

  public int getContact() {
    return contact;
  }

  public boolean getVerification() {
    return isVerified;
  }

  @Override
  public String toString() {
    return "Shelter{" +
        "foundationYear=" + foundationYear +
        ", contact=" + contact +
        ", isVerified=" + isVerified +
        // ", animals=" + animals + // uses the toString from the class Animal
        '}';
  }

}
