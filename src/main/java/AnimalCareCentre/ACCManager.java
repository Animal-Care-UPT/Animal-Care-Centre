package AnimalCareCentre;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import AnimalCareCentre.enums.*;
import AnimalCareCentre.models.*;

public class ACCManager {

  public ACCManager() {
    setup();
  }

  private static SessionFactory sessionFactory;
  private static Session session;

  public void createSponsorship(User user, ShelterAnimal animal, float amount) {
    session.beginTransaction();
    Sponsorship sponsor = new Sponsorship(user, animal, amount);
    user.addSponsor(sponsor);
    animal.addSponsor(sponsor);
    session.persist(sponsor);
    session.getTransaction().commit();
  }

  /**
   * This method searches animals by a keyword
   *
   * @param search
   * @return
   */
  public List<ShelterAnimal> searchAnimalByKeyword(String search) {
    Session session = sessionFactory.openSession();
    Query<ShelterAnimal> query = session.createQuery(
        "FROM ShelterAnimal WHERE race LIKE :search " +
            "OR CAST(type AS string) LIKE :search " +
            "OR CAST(size AS string) LIKE :search " +
            "OR CAST(color AS string) LIKE :search",
        ShelterAnimal.class);
    query.setParameter("search", "%" + search + "%");
    List<ShelterAnimal> results = query.getResultList();
    session.close(); // Don't forget to close the session!
    return results;
  }

  /**
   * This method searches animals by parameter
   *
   * @param parameter
   * @param search
   * @return
   */
  public List<ShelterAnimal> searchAnimalByParameter(String parameter, String search) {
    Session session = sessionFactory.openSession();
    Query<ShelterAnimal> query = session.createQuery(
        "From ShelterAnimal WHERE " + parameter + " =:search", ShelterAnimal.class);
    query.setParameter("search", search);
    return query.getResultList();
  }

  public void changePassword(String email, String password) {
    session.beginTransaction();
    Query<Account> query = session.createQuery("FROM Account WHERE email =:email", Account.class);
    query.setParameter("email", email);
    Account account = query.uniqueResult();
    account.setPassword(password);
    session.merge(account);
    session.getTransaction().commit();
  }

  public Account login(String email, String password) {
    session.beginTransaction();
    Query<Account> query = session.createQuery("FROM Account WHERE email = :email", Account.class);
    query.setParameter("email", email);
    Account acc = query.uniqueResult();
    session.getTransaction().commit();

    if (acc == null) {
      return null;
    }

    if (acc.getPassword().equals(password)) {
      return acc;
    } else {
      return null;
    }
  }

  public void createUserAccount(String name, String email, String password, String location,
      SecurityQuestion securityQuestion, String answer, LocalDate birthDate, int contact) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User user = new User(name, email, password, location, securityQuestion, answer, birthDate, contact);
    session.persist(user);
    session.getTransaction().commit();
  }

  public void createAdminAccount(String name, String email, String password, String location,
      SecurityQuestion securityQuestion, String answer) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Account admin = new Account(name, email, password, location, securityQuestion, answer);
    session.persist(admin);
    session.getTransaction().commit();
  }

  public void createShelterAccount(String name, String email, String password, String location,
      SecurityQuestion securityQuestion, String answer, int foundationYear, int contact) {
    session.beginTransaction();
    Shelter shelter = new Shelter(name, email, password, location, securityQuestion, answer, foundationYear, contact);
    session.persist(shelter);
    session.getTransaction().commit();
  }

  public void setup() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      session = sessionFactory.openSession();
    } catch (Exception e) {
      StandardServiceRegistryBuilder.destroy(registry);
      e.printStackTrace();
    }
  }

  // Method to register animals as a Shelter
  public void registerAnimal(String name, AnimalType type, String race, AnimalSize size, int age, AnimalColor color,
      String description, AdoptionType adoptionType) {
    session.beginTransaction();
    ShelterAnimal animal = new ShelterAnimal(name, type, race, color, false, size, adoptionType, description);
    session.persist(animal);
    session.getTransaction().commit();
  }

  //Method to see the animals in each shelter
   // public List<Animal> getAnimalsByShelter(Shelter shelter){
    //  Query<Animal> query = session.createQuery("FROM Animal WHERE shelter = :shelter", Animal.class);
    //  query.setParameter("shelter", shelter);
     // return query.getResultList();
  //  }

  public void exit() {
    session.close();
    sessionFactory.close();
  }

  public boolean validatePassword(String password) {
    PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 16),
        new CharacterRule(EnglishCharacterData.UpperCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1),
        new CharacterRule(EnglishCharacterData.Special, 1), new WhitespaceRule()));
    RuleResult result = validator.validate(new PasswordData(password));
    return result.isValid();
  }

  public boolean validateEmail(String email) {
    EmailValidator validator = EmailValidator.getInstance();
    if (!validator.isValid(email)) {
      return false;
    } else {
      return true;
    }
  }

  public boolean doesEmailExist(String email) {
    Query<Account> query = session.createQuery("FROM Account WHERE email = :email", Account.class);
    query.setParameter("email", email);
    Account acc = query.uniqueResult();
    if (acc == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean validateFields(String... strings) {
    for (String string : strings) {
      if (string == null || string.isBlank()) {
        return false;
      }
    }
    return true;
  }
}
