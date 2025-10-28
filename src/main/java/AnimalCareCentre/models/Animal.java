package AnimalCareCentre.models;

import java.util.ArrayList;
import java.util.List;

import AnimalCareCentre.enums.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * This class describes the model of an Animal.
 *
 */
@Entity
@Table(name = "Animals")
public class Animal {

  @Id
  @Column(name = "animal_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @Enumerated(EnumType.STRING)
  private AnimalType type;
  private String race;
  @Enumerated(EnumType.STRING)
  private AnimalColor color;
  private boolean isVacinated;
  @Enumerated(EnumType.STRING)
  private AnimalSize size;
  @Enumerated(EnumType.STRING)
  private AdoptionType listedFor;
  private String description;
  @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Adoption> adoptions = new ArrayList<>();
  @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Sponsorship> sponsors = new ArrayList<>();

  /**
   * Constructor for the class Animal.
   *
   * @param name
   * @param type
   * @param race
   * @param color
   * @param isVacinated
   * @param size
   * @param listedFor
   * @param description
   */
  public Animal(String name, AnimalType type, String race, AnimalColor color, boolean isVacinated, AnimalSize size,
      AdoptionType listedFor, String description) {
    this.name = name;
    this.type = type;
    this.race = race;
    this.color = color;
    this.isVacinated = isVacinated;
    this.size = size;
    this.listedFor = listedFor;
    this.description = description;
  }

  public Animal() {
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
    return listedFor;
  }

  public String getDescription() {
    return description;
  }

  // public List<Adoption> getAdoptions() {
  //   return adoptions;
  // }

  public List<Sponsorship> getSponsors() {
    return sponsors;
  }
  
  public void addSponsor(Sponsorship sponsor) {
		sponsors.add(sponsor);
    sponsor.setAnimal(this);
	  }


  // The different setters from the class
  public void setVacinated(boolean vacinated) {
    isVacinated = vacinated;
  }

  public void setListetFor(AdoptionType listedFor) {
    this.listedFor = listedFor;
  }

  public void setDescription(String description) {
    this.description = description;
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
        ", listedFor=" + listedFor +
        ", description='" + description + '\'' +
        '}';
  }
}
