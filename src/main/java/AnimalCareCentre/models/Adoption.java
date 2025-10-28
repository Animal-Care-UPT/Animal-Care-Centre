package AnimalCareCentre.models;

import java.time.LocalDate;
import AnimalCareCentre.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This class describes the model of an Adoption.
 *
 */
@Entity
@Table(name = "Adoptions")
public class Adoption {

  @Id
  @Column(name = "adoption_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "animal_id")
  private Animal animal;
  private LocalDate date;
  private AdoptionType type;

  /**
   * Constructor of class Adoption.
   *
   * @param user
   * @param shelter
   * @param animal
   * @param type
   */
  public Adoption(User user, Animal animal, AdoptionType type) {
    this.user = user;
    this.animal = animal;
    this.type = type;
    date = LocalDate.now();
  }

  // Getters area
  public User getUser() {
    return user;
  }

  public Animal getAnimal() {
    return animal;
  }

  public LocalDate getDate() {
    return date;
  }

  public AdoptionType getType() {
    return type;
  }

  // ToString from the class
  @Override
  public String toString() {
    return "Adoption{" +
        "user=" + user +
        ", animal=" + animal +
        ", date=" + date +
        ", type=" + type +
        '}';
  }
}
