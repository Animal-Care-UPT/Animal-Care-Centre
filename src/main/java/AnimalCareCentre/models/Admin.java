package AnimalCareCentre.models;

import AnimalCareCentre.enums.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

/**
 * This class describes the model of an Admin of the system, including its
 * attributes and what it can do.
 *
 */
@Entity
@DiscriminatorValue("Admin")
public class Admin extends Person {


  // This class has no atributes of its own

  /**
   * Constructor for the class Admin.
   *
   * @param name
   * @param email
   * @param password
   * @param location
   * @param securityQuestion
   * @param birthDate
   */
  public Admin(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      String answer,
      LocalDate birthDate) {
    super(name, email, password, location, securityQuestion, answer, birthDate);
  }

  protected Admin() {}

  // toString from the class
  @Override
  public String toString() {
    return "Admin{" + super.toString() + "}";
  }
}
