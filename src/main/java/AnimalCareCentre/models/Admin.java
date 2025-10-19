package AnimalCareCentre.models;

import AnimalCareCentre.enums.*;
import java.time.LocalDate;

/**
 * This class describes the model of an Admin of the system, including its
 * attributes and what it can do.
 *
 */
public class Admin extends Person {

  // this class has no attributes of its own

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
      LocalDate birthDate) {
    super(name, email, password, location, securityQuestion, birthDate);
  }

  // toString from the class
  @Override
  public String toString() {
    return "Admin{" + super.toString() + "}";
  }
}
