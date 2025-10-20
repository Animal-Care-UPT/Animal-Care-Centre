package AnimalCareCentre.models;

import java.util.ArrayList;

import AnimalCareCentre.enums.SecurityQuestion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class describes the model of a Shelter.
 *
 */
@Entity
@Table(name = "shelter")
public class Shelter extends Account {

  @Id
  @Column(name = "shelter_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
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
  public Shelter(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      String answer, int foundationYear, int contact) {
    super(name, email, password, location, securityQuestion, answer);
    this.foundationYear = foundationYear;
    this.contact = contact;
    isVerified = true;
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
