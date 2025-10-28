package AnimalCareCentre.models;

import AnimalCareCentre.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * This class describes the model of an Account.
 *
 */
@Entity
@Table(name = "Accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  private String name;
  private String email;
  private String password;
  private String location;
  private SecurityQuestion securityQuestion;
  private String answer;

  /**
   * Constructor for class Account.
   *
   * @param name
   * @param email
   * @param password
   * @param location
   * @param securityQuestion
   */
  public Account(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      String answer) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.location = location;
    this.securityQuestion = securityQuestion;
    this.answer = answer;
  }

  protected Account() {
  }

  // getters area
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getLocation() {
    return location;
  }

  public SecurityQuestion getSecurityQuestion() {
    return securityQuestion;
  }

  public String getAnswer() {
    return answer;
  }

  // setters area
  public void setPassword(String password) {
    this.password = password;
  }

  public void setSecurityQuestion(SecurityQuestion securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  // toString from the class Account
  @Override
  public String toString() {
    return "Name: " + name + "\nLocation: " + location;
  }
}
