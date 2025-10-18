package AnimalCareCentre;

// This class describes the common attributes and methods between all accounts
public class Account {

    private String name;
    private String email;
    private String password;
    private String location;
    private SecurityQuestion securityQuestion;

    //constructor for the class Account
    public Account(String name, String email, String password, String location, SecurityQuestion securityQuestion) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.securityQuestion = securityQuestion;
    }
    //getters area
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    //setters area
    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    //toString from the class Account
    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", securityQuestion=" + securityQuestion +
                '}';
    }
}
