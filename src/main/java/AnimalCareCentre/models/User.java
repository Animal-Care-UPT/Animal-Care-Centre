package AnimalCareCentre.models;

import java.time.LocalDate;
import java.util.ArrayList;
import AnimalCareCentre.enums.*;

/**
 * This class describes the model of a User from the system, its attributes and
 * what it can do.
 *
 */
public class User extends Person {

  private int contact;
  private ArrayList<Adoption> adoptions;
  private ArrayList<Sponsorship> sponsorships;
  private ArrayList<Donation> donations;

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
      LocalDate birthDate, int contact) {
    super(name, email, password, location, securityQuestion, birthDate);
    this.contact = contact;
    this.adoptions = new ArrayList<>();
    this.sponsorships = new ArrayList<>();
    this.donations = new ArrayList<>();
  }

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
