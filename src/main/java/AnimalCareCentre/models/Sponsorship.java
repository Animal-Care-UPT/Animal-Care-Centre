package AnimalCareCentre.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * This class describes the model of a sponsorhip to the animals and how it
 * works.
 *
 */

@Entity
@Table(name = "Sponsorships")
public class Sponsorship {

  @Id
  @Column(name = "Sponsorship_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "animal_id")
  private ShelterAnimal animal;
  private LocalDate startDate;
  private float amount;
  @OneToMany(mappedBy = "sponsorship", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Donation> donations = new ArrayList<>();

  /**
   * Constructor for the class Sponsorship
   *
   * @param user
   * @param animal
   */
  
  public Sponsorship(User user, ShelterAnimal animal, float amount) {
    this.user = user;
    this.animal = animal;
    this.amount = amount;
    startDate = LocalDate.now();
  }

  public Sponsorship() {
  }

  // Getters area
  public User getUser() {
    return user;
  }

  public ShelterAnimal getAnimal() {
    return animal;
  }

  public void addDonation() {
    Donation donation = new Donation(amount);
    donations.add(donation);
    donation.setSponsorship(this);
  }

  @Override
  public String toString() {
    return "Sponsorship{" +
        "user=" + user +
        ", animal=" + animal +
        '}';
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public void setDonations(List<Donation> donations) {
    this.donations = donations;
  }

}
