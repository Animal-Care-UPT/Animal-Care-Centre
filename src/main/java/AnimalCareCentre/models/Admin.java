package AnimalCareCentre.models;

import AnimalCareCentre.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * This class describes the model of an Admin of the system, including its
 * attributes and what it can do.
 *
 */
@Entity
@Table(name = "admin")
public class Admin extends Person {

  @Id
  @Column(name = "admin_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

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

  // toString from the class
  @Override
  public String toString() {
    return "Admin{" + super.toString() + "}";
  }
}
