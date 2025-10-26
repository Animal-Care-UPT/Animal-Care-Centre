package AnimalCareCentre.models;

import java.time.LocalDate;
import AnimalCareCentre.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * This class describes the model of a User from the system, its attributes and
 * what it can do.
 *
 */
@Entity
@Table(name ="Users")
public class User extends Account {

  private int contact;
  private LocalDate birthDate;
  // private ArrayList<Adoption> adoptions;
  // private ArrayList<Sponsorship> sponsorships;
  // private ArrayList<Donation> donations; commented for now


  /**
   * Constructor for the class User
   *
   * @param name
   * @param email
   * @param password
   * @param location
   * @param securityQuestion
   * @param birthDate
   * @param contact
   */
  public User(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      String answer,
      LocalDate birthDate, int contact) {
    super(name, email, password, location, securityQuestion, answer);
    this.birthDate = birthDate;
    this.contact = contact;
  }

  protected User() {}

  // Getter from the contact of the user
  public int getContact() {
    return contact;
  }

  @Override
  public String toString() {
    return "User{" +
        super.toString() + // calls toString() from Person
        "contact=" + contact +
        '}';
  }
}
