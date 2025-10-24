package AnimalCareCentre.models;

import java.util.ArrayList;
import AnimalCareCentre.enums.*;
import javafx.scene.image.Image;

/**
 * This class describes the model of an Animal.
 *
 */
public class Animal {

    private String name;
  private AnimalType type;
  private String race;
  private AnimalColor color;
  private boolean isVacinated;
  private AnimalSize size;
  private AdoptionType listetFor;
  private String description;
  private Image image;
  ArrayList<Adoption> adoptions;
  ArrayList<Sponsorship> sponsors;

  /**
   * Constructor for the class Animal.
   *
   * @param name
   * @param type
   * @param race
   * @param color
   * @param isVacinated
   * @param size
   * @param listetFor
   * @param description
   * @param image
   */
  public Animal(String name, AnimalType type, String race, AnimalColor color, boolean isVacinated, AnimalSize size,
      AdoptionType listetFor, String description, Image image) {
    this.name = name;
    this.type = type;
    this.race = race;
    this.color = color;
    this.isVacinated = isVacinated;
    this.size = size;
    this.listetFor = listetFor;
    this.description = description;
    this.image = image;
    this.adoptions = new ArrayList<>();
    this.sponsors = new ArrayList<>();
  }

  // The different getter from the class
  public String getName() {
    return name;
  }

  public AnimalType getType() {
    return type;
  }

  public String getRace() {
    return race;
  }

  public AnimalColor getColor() {
    return color;
  }

  public boolean isVacinated() {
    return isVacinated;
  }

  public AnimalSize getSize() {
    return size;
  }

  public AdoptionType getListetFor() {
    return listetFor;
  }

  public String getDescription() {
    return description;
  }

  public Image getImage() {
    return image;
  }

  public ArrayList<Adoption> getAdoptions() {
    return adoptions;
  }

  public ArrayList<Sponsorship> getSponsors() {
    return sponsors;
  }

  // The different setters from the class
  public void setVacinated(boolean vacinated) {
    isVacinated = vacinated;
  }

  public void setListetFor(AdoptionType listetFor) {
    this.listetFor = listetFor;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  // toString from the class
  @Override
  public String toString() {
    return "Animal{" +
        "name='" + name + '\'' +
        ", type=" + type +
        ", race=" + race +
        ", color=" + color +
        ", isVacinated=" + isVacinated +
        ", size=" + size +
        ", listetFor=" + listetFor +
        ", description='" + description + '\'' +
        '}';
  }
}
