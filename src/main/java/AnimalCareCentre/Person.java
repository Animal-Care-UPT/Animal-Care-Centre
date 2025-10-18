package AnimalCareCentre;

import java.time.LocalDate;

//This class describes the common aspects of different classes of people's accounts.
public class Person extends Account{

    private LocalDate birthDate;

    //Constructor from the class
    public Person(String name, String email, String password, String location, SecurityQuestion securityQuestion, LocalDate birthDate) {
        super(name, email, password, location, securityQuestion);
        this.birthDate = birthDate;
    }

    //Getter from birthDate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                super.toString() +  // calls toString() from Account
                "birthDate=" + birthDate +
                '}';
    }
}
