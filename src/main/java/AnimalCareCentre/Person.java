package AnimalCareCentre;

import java.time.LocalDate;

/**
 * This class describes the model a Person.
 *
 */
public class Person extends Account {

  private LocalDate birthDate;

  /**
   * Constructor for the class Person
   *
   * @param name
   * @param email
   * @param password
   * @param location
   * @param securityQuestion
   * @param birthDate
   */
  public Person(String name, String email, String password, String location, SecurityQuestion securityQuestion,
      LocalDate birthDate) {
    super(name, email, password, location, securityQuestion);
    this.birthDate = birthDate;
  }

  // Getter from birthDate
  public LocalDate getBirthDate() {
    return birthDate;
  }

  @Override
  public String toString() {
    return "Person{" +
        super.toString() + // calls toString() from Account
        "birthDate=" + birthDate +
        '}';
  }
}
