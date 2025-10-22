package AnimalCareCentre.enums;

/**
 * This enum lists all the possible types Animals.
 *
 */
public enum AnimalType {

  DOG("Dog"),
  CAT("Cat"),
  RABBIT("Rabbit");

  private final String animalType;

  /**
   * Constructor from the AnimalType
   *
   */
  AnimalType(String animalType) {
    this.animalType = animalType;
  }

  /**
   * @return the animal type
   *
   */
  public String toString() {
    return animalType;
  }

  /**
   * This method reads a string and returns the respective AnimalType object
   *
   * @param text
   * @return
   */
  public static AnimalType fromString(String text) {
    for (AnimalType a : AnimalType.values()) {
      if (a.animalType.equals(text)) {
        return a;
      }
    }
    return null;
  }
}
