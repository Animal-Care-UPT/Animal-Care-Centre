package AnimalCareCentre.models;

/**
 * This class describes the model of a sponsorhip to the animals and how it
 * works.
 *
 */
public class Sponsorship {

  private User user;
  private Animal animal;

  /**
   * Constructor for the class Sponsorship
   *
   * @param user
   * @param animal
   */
  public Sponsorship(User user, Animal animal) {
    this.user = user;
    this.animal = animal;
  }

  // Getters area
  public User getUser() {
    return user;
  }

  public Animal getAnimal() {
    return animal;
  }

  @Override
  public String toString() {
    return "Sponsorship{" +
        "user=" + user +
        ", animal=" + animal +
        '}';
  }
}
