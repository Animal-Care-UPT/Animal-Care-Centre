package AnimalCareCentre;

import java.time.LocalDate;

/**
 * This class describes an Admin from the system, its attributes and what it can do.
 *
 */
public class Admin extends Person {

        //this class has no attributes of its own


    //Constructor from class
    public Admin(String name, String email, String password, String location, SecurityQuestion securityQuestion, LocalDate birthDate) {
        super(name, email, password, location, securityQuestion, birthDate);
    }

    //toString from the class
    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}
