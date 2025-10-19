package AnimalCareCentre.models;

import java.time.LocalDate;

/**
 * This class describes the model of a Donation with its attributes and how it
 * works.
 *
 */
public class Donation {

  private Shelter receiver;
  private Animal animal;
  private User donator;
  private float amount;
  private LocalDate donationDate;

  /**
   * Constructor for the class Donation.
   *
   * @param receiver
   * @param animal
   * @param donator
   * @param amount
   * @param donationDate
   */
  public Donation(Shelter receiver, Animal animal, User donator, float amount, LocalDate donationDate) {
    this.receiver = receiver;
    this.animal = animal;
    this.donator = donator;
    this.amount = amount;
    this.donationDate = donationDate;
  }

  // Getters area
  public Shelter getReceiver() {
    return receiver;
  }

  public Animal getAnimal() {
    return animal;
  }

  public User getDonator() {
    return donator;
  }

  public float getAmount() {
    return amount;
  }

  public LocalDate getDonationDate() {
    return donationDate;
  }

  // ToString from the class
  @Override
  public String toString() {
    return "Donation{" +
        "receiver=" + receiver +
        ", animal=" + animal +
        ", donator=" + donator +
        ", amount=" + amount +
        ", donationDate=" + donationDate +
        '}';
  }
}
