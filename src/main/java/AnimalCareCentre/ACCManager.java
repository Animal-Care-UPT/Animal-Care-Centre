package AnimalCareCentre;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import AnimalCareCentre.enums.SecurityQuestion;
import AnimalCareCentre.models.*;

public class ACCManager {

  public SessionFactory sessionFactory;

  public void createAccount(String name, String email, String password, String location, SecurityQuestion securityQuestion, String answer) {
    Account account = new Account(name, email, password, location, securityQuestion)
  }

  public void setup() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  public void exit() {
    sessionFactory.close();
  }

}
