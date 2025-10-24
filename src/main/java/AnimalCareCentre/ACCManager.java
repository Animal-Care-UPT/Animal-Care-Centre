package AnimalCareCentre;

import java.time.LocalDate;
import java.util.Arrays;

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

  public SessionFactory sessionFactory;

  public void changePassword(String Password, String email) {
	  
	  
	  
	  Session session = sessionFactory.openSession();
	  session.beginTransaction();
	  session.merge();
	  session.getTransaction().commit();
	  session.close();
  }
  
  
  public Account login(String email, String password) {
    Session session = sessionFactory.openSession();
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
      SecurityQuestion securityQuestion, String answer, LocalDate birthDate) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Admin admin = new Admin(name, email, password, location, securityQuestion, answer, birthDate);
    session.persist(admin);
    session.getTransaction().commit();
  }

  public void createShelterAccount(String name, String email, String password, String location,
      SecurityQuestion securityQuestion, String answer, int foundationYear, int contact) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Shelter shelter = new Shelter(name, email, password, location, securityQuestion, answer, foundationYear, contact);
    session.persist(shelter);
    session.getTransaction().commit();
  }

  public void setup() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

    //Method to register animals as a Shelter
  public void registerAnimal(String name, AnimalType type, AnimalRace race, AnimalSize size, int age, AnimalColor color,
                             String description, Image image, AdoptionType adoptionType){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      Animal animal = new Animal(name, type, race, color, false, size, adoptionType, description, image);
      session.persist(animal);
      session.getTransaction().commit();
  }

  public void exit() {
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
    return validator.isValid(email);
  }

  public boolean validateFields(String... strings) {
    for (String string : strings) {
      if (string.isBlank() || string == null) {
        return false;
      }
    }
    return true;
  }
}
