package AnimalCareCentre;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

import AnimalCareCentre.enums.*;
import AnimalCareCentre.views.*;
import AnimalCareCentre.models.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.awt.Toolkit;

/**
 * @author 51084 - Diogo Ferreira
 * @author
 * @author 51297 - Sara Canelas
 * @author
 */
public class App extends Application {

  private static Account loggedAcc;
  private static Stage stage;
  private static final ACCManager manager = new ACCManager();
  private static Scanner sc = new Scanner(System.in);
  // private static Navigator nav; commenting navigator for now

  public void start(Stage stage) {
    App.stage = stage;
    // nav = new Navigator(stage, manager);

    stage.setTitle("AnimalCareCentre");
    stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100);
    stage.show();
    showMainMenu();
  }

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This method shows the main menu, where you can login or create accounts
   */
  public void showMainMenu() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Button login = new Button("Login");
    Button create = new Button("Create Account");
    Button exit = new Button("Exit");

    login.setOnAction(e -> {
      login();
    });

    create.setOnAction(e -> {
      createAccount();
    });

    exit.setOnAction(e -> {
      manager.exit();
      System.exit(0);
    });

    scene.addItems(login, create, exit);

  }

  /**
   * This method shows the login screen
   */
  public void login() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Label emailLabel = new Label("Email:");
    TextField email = new TextField();
    Label passLabel = new Label("Password:");
    PasswordField password = new PasswordField();
    Button enter = new Button("Enter");
    Button back = new Button("Back");
    Button changePassword = new Button("Forgot Password");
    scene.addItems(emailLabel, email, passLabel, password, enter, back, changePassword);

    enter.setOnAction(e -> {
      Account acc = manager.login(email.getText(), password.getText());
      if (acc != null) {
        loggedAcc = acc;
        if (loggedAcc instanceof Shelter) {
          shelterHomepage();
        } else if (loggedAcc instanceof User) {
          userHomepage();
        }
      } else {
        System.out.println("Wrong credentials!");
      }
    });

    back.setOnAction(e -> {
      showMainMenu();
    });

    changePassword.setOnAction(e -> {
      changePassword();
    });
  }

  /**
   * This method shows the change password screen
   */
  public void changePassword() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Label emailLabel = new Label("Email:");
    TextField email = new TextField();
    Button enter = new Button("Enter");
    Label passwordLabel = new Label("New password:");
    PasswordField password = new PasswordField();
    Button comfirm = new Button("Comfirm");
    Button back = new Button("Back");
    scene.addItems(emailLabel, email, enter, back);
    enter.setOnAction(e -> {
      if (manager.doesEmailExist(email.getText())) {
        scene.clearContent();
        scene.addItems(passwordLabel, password, comfirm, back);
      } else {
        showAlert(AlertType.ERROR, "Invalid Email", "The email is not yet registed");
      }
    });
    comfirm.setOnAction(e -> {
      if (!manager.validatePassword(password.getText())) {
        showAlert(AlertType.ERROR, "Invalid Password",
            "Password must have between 8 and 16 characters, as well as 1 special character and 1 number!");
        return;
      }
      manager.changePassword(email.getText(), password.getText());
      login();
    });
    back.setOnAction(e -> {
      login();
    });
  }

  /**
   * This method shows the create account screen
   */
  public void createAccount() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Label type = new Label("Account type:");
    ComboBox<String> accType = new ComboBox<>();
    accType.getItems().addAll("User", "Admin", "Shelter");
    Label nameLabel = new Label("Name:");
    TextField name = new TextField();
    name.setMaxWidth(150);
    Label emailLabel = new Label("Email:");
    TextField email = new TextField();
    email.setMaxWidth(250);
    Label passLabel = new Label("Password:");
    PasswordField password = new PasswordField();
    password.setMaxWidth(150);
    Label locationLabel = new Label("Location:");
    TextField location = new TextField();
    location.setMaxWidth(150);
    Label secLabel = new Label("Security Question:");
    ComboBox<SecurityQuestion> sec = new ComboBox<>();
    sec.getItems().addAll(SecurityQuestion.values());
    Label answerLabel = new Label("Answer:");
    TextField answer = new TextField();
    answer.setMaxWidth(200);

    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER_LEFT);
    vbox.getChildren().addAll(type, accType, nameLabel, name, emailLabel, email, passLabel, password, locationLabel,
        location, secLabel, sec,
        answerLabel, answer);
    vbox.setMaxWidth(250);
    vbox.setSpacing(10);
    scene.addItems(vbox);

    Label birthLabel = new Label("Birthdate:");
    DatePicker birthDate = new DatePicker();
    Label contactLabel = new Label("Contact:");
    TextField contact = new TextField();
    contact.setMaxWidth(150);
    Label foundYear = new Label("Foundation year:");
    TextField year = new TextField();
    year.setMaxWidth(80);

    year.setTextFormatter(new TextFormatter<>(change -> {
      String num = change.getControlNewText();
      if (num.matches("\\d{0,4}")) {
        return change;
      }
      return null;
    }));

    contact.setTextFormatter(new TextFormatter<>(change -> {
      String num = change.getControlNewText();
      if (num.matches("\\d{0,9}")) {
        return change;
      }
      return null;
    }));

    Button create = new Button("Create");
    Button back = new Button("Back");

    create.setOnAction(e -> {

      if (accType.getValue() == null) {
        showAlert(AlertType.ERROR, "Missing Account Type!", "Please select an account type!");
        return;
      }

      if (manager.doesEmailExist(email.getText())) {
        showAlert(AlertType.ERROR, "Invalid Email!", "This email is already registered!");
        return;
      }

      if (!manager.validateEmail(email.getText())) {
        showAlert(AlertType.ERROR, "Invalid Email!", "Please enter a valid email address!");
        return;
      }

      if (!manager.validatePassword(password.getText())) {
        showAlert(AlertType.ERROR, "Invalid Password!",
            "Password must have between 8 and 16 characters, as well as 1 special character and 1 number!");
        return;
      }

      if (accType.getValue().equals("User")) {

        if (sec.getValue() == null
            || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
                sec.getValue().toString(), answer.getText(), birthDate.getValue().toString(), contact.getText())) {
          showAlert(AlertType.ERROR, "Empty Fields!", "All fields are required!");
          return;
        }

        if (birthDate.getValue() != null && birthDate.getValue().isAfter(LocalDate.now())) {
          showAlert(AlertType.ERROR, "Invalid Date!", "The birth date cannot be in the future");
          return;
        }

        manager.createUserAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), birthDate.getValue(), Integer.parseInt(contact.getText()));
        showMainMenu();

      } else if (accType.getValue().equals("Admin")) {

        if (sec.getValue() == null
            || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
                sec.getValue().toString(), answer.getText())) {
          showAlert(AlertType.ERROR, "Empty Fields!", "All fields are required!");
          return;
        }

        manager.createAdminAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText());
        showMainMenu();

      } else if (accType.getValue().equals("Shelter")) {

        if (sec.getValue() == null
            || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
                sec.getValue().toString(), answer.getText(), year.getText(), contact.getText())) {
          showAlert(AlertType.ERROR, "Empty Fields!", "All fields are required!");
          return;
        }

        if (Integer.parseInt(year.getText()) > LocalDate.now().getYear()) {
          showAlert(AlertType.ERROR, "Invalid Foundation year!", "The foundation year cannot be in the future");
          return;
        }

        manager.createShelterAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), Integer.parseInt(year.getText()),
            Integer.parseInt(contact.getText()));
        showMainMenu();
      }

    });

    back.setOnAction(e -> {
      showMainMenu();
    });

    accType.valueProperty().addListener((obs, old, selected) -> {
      vbox.getChildren().clear();
      vbox.getChildren().addAll(type, accType, nameLabel, name, emailLabel, email, passLabel, password, locationLabel,
          location, secLabel, sec,
          answerLabel, answer);

      if (selected.equals("User")) {
        vbox.getChildren().addAll(birthLabel, birthDate, contactLabel, contact);
      } else if (selected.equals("Shelter")) {
        vbox.getChildren().addAll(contactLabel, contact, foundYear, year);
      }
    });

    scene.addItems(create, back);
  }

  /**
   * This method shows an alert
   */
  public void showAlert(AlertType type, String title, String text) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(text);
    alert.showAndWait();
  }

  /**
   * This method is used temporarily to change to the terminal screen
   */
  private void showTerminalScreen() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Button logout = new Button("Logout");
    logout.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 16px;");
    scene.addItems(logout);

    logout.setOnAction(e -> {
      System.out.println("\n Logout efetuado!");
      loggedAcc = null;
      showMainMenu();
    });
  }

  public void showAnimal(ShelterAnimal animal) {
    System.out.println(animal.toString());
    System.out.println("Menu: ");
    System.out.println("1 - Sponsor Animal");
    int opc = sc.nextInt();
    switch (opc) {
      case 1:
        System.out.println("Insert the amount of money u wish to give as a sponsorship");
        float amount = sc.nextFloat();
        sc.nextLine();
        User user = (User) loggedAcc;
      manager.createSponsorship(user, animal, amount);
    }
  }

  public void searchAnimalMenu() {

    try {
      int opt;
      System.out.println("\n=== SEARCH ANIMAL ===");
      System.out.println("1 - Search by Keyword");
      System.out.println("2 - Search by Type");
      System.out.println("3 - Search by Color");
      System.out.println("0 - Return");

      opt = sc.nextInt();
      sc.nextLine();
      switch (opt) {
        case 1 -> {
          System.out.println("What would you like to search?");
          String search = sc.nextLine();
          List<ShelterAnimal> animals = manager.searchAnimalByKeyword(search);
          if (animals == null || animals.isEmpty()) {
            System.out.println("\n\n\nNo matches! Returning...");
            searchAnimalMenu();
          } else {
            interactList(animals);
            int ani = sc.nextInt();
            sc.nextLine();
            ShelterAnimal choice = animals.get(ani - 1);
            showAnimal(choice);
            searchAnimalMenu();
          }
        }

        case 2 -> {
          AnimalType[] types = AnimalType.values();
          int typeOption;
          while (true) {
            try {
              System.out.println("Select Type: ");
              for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + "- " + types[i]);
              }
              typeOption = sc.nextInt();
              sc.nextLine();
              if (typeOption <= 0 || typeOption > types.length) {
                System.out.println("Invalid Option! Please Try again!");
              } else {
                break;
              }
            } catch (Exception e) {
              System.out.println("Please enter a valid option!");
            }
          }

          List<ShelterAnimal> animals = manager.searchAnimalByParameter("type", types[typeOption - 1]);
          if (animals == null || animals.isEmpty()) {
            System.out.println("\n\n\nNo matches! Returning...");
            searchAnimalMenu();
          } else {
            interactList(animals);
            int ani = sc.nextInt();
            sc.nextLine();
            ShelterAnimal choice = animals.get(ani - 1);
            showAnimal(choice);
            searchAnimalMenu();
          }
        }

        case 3 -> {
          AnimalColor[] colors = AnimalColor.values();
          AnimalColor color;
          while (true) {
            System.out.println("Select Color:");
            for (int i = 0; i < colors.length; i++) {
              System.out.println((i + 1) + ". " + colors[i]);
            }
            int colorOption = sc.nextInt();
            sc.nextLine();
            if (colorOption >= 1 && colorOption <= colors.length) {
              color = colors[colorOption - 1];
              break;
            }
            System.out.println("Invalid option, please try again.");
          }
          List<ShelterAnimal> animals = manager.searchAnimalByParameter(AnimalType.values().toString(), color.toString());
          if (animals == null || animals.isEmpty()) {
            System.out.println("\n\n\nNo matches! Returning...");
            searchAnimalMenu();
          } else {
            interactList(animals);
            int ani = sc.nextInt();
            sc.nextLine();
            ShelterAnimal choice = animals.get(ani - 1);
            showAnimal(choice);
            searchAnimalMenu();
          }

        }

        case 0 -> {
          System.out.println("Returning...");
          userHomepage();
        }
      }
    } catch (InputMismatchException e) {
      System.out.println();
    }
  }

  /**
   * This method shows user's homepage
   */
  private void userHomepage() {
    showTerminalScreen();


    try {

      new Thread(() -> {
        int option;

        System.out.println("=== USER MENU ===");
        System.out.println("1. Search Animal");
        System.out.println("2. Show Lost Aniamls");
        System.out.println("3. Show my Lost Animals");
        System.out.println("0. Logout");
        System.out.print("Option: ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
          case 1 -> {
            searchAnimalMenu();
          }
          case 2 ->{
        	  manager.showLostAnimals();
        	  userHomepage();
          }
          case 3 ->{
        	  manager.showMyLostAnimals(loggedAcc);
        	  userHomepage();
          }
          case 0 -> {
            sc.close();
            System.out.println("Exiting terminal menu...");
            javafx.application.Platform.runLater(() -> showMainMenu());
          }
          default -> System.out.println("Invalid option!");
        }

      }).start();

    } catch (InputMismatchException e) {
      System.out.println("Please pick a valid option!");
      userHomepage();
    }
  }

    /**
     * Method to choose an option from the enum classes
     */
    private Object chooseOption(Object[] values, String title) {
        while (true) {
            System.out.println("Select " + title + ":");

            //Show the options with numbers
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i]);
            }

            System.out.print("Option: ");
            String input = sc.nextLine();

            try {
                int option = Integer.parseInt(input);
                if (option >= 1 && option <= values.length) {
                    return values[option - 1];
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid option, please try again.");
        }
    }

    /**
     * This method shows shelter's homepage
     */
    private void shelterHomepage() {
        showTerminalScreen();

        new Thread(() -> {
            try {
                System.out.println("=== SHELTER MENU ===");
                System.out.println("1. Register Animal");
                System.out.println("2. See Lost Animals");
                System.out.println("3. Show my Lost Animals");
                System.out.println("0. Logout");
                System.out.print("Option: ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1 -> {
                        System.out.println("\n=== REGISTER ANIMAL ===");
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        // Type
                        AnimalType chosenType = (AnimalType) chooseOption(AnimalType.values(), "Type");

                        // Breed
                        List<String> breeds = chosenType.getBreeds();
                        String race = null;

                        while (true) {
                            System.out.println("Select Breed:");
                            for (int i = 0; i < breeds.size(); i++) {
                                System.out.println((i + 1) + ". " + breeds.get(i));
                            }

                            System.out.print("Option: ");
                            String input = sc.nextLine();

                            try {
                                int breedOption = Integer.parseInt(input);
                                if (breedOption >= 1 && breedOption <= breeds.size()) {
                                    race = breeds.get(breedOption - 1);
                                    break;
                                }
                            } catch (NumberFormatException ignored) {}

                            System.out.println("Invalid option, please try again.");
                        }

                        // Size
                        AnimalSize size = (AnimalSize) chooseOption(AnimalSize.values(), "Size");

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        // Color
                        AnimalColor color = (AnimalColor) chooseOption(AnimalColor.values(), "Color");

                        System.out.print("Description: ");
                        String description = sc.nextLine();

                        // Adoption Type
                        AdoptionType adoptionType = (AdoptionType) chooseOption(AdoptionType.values(), "Adoption Type");

                        manager.registerAnimal(name, chosenType, race, size, age, color, description, adoptionType);
                        System.out.println("\nAnimal registered successfully!\n");

                        javafx.application.Platform.runLater(this::showMainMenu);
                    }
                    
                    case 2 ->{
                    	manager.showLostAnimals();
                    	shelterHomepage();
                    }
                    
                    case 3 ->{
                  	  manager.showMyLostAnimals(loggedAcc);
                  	  userHomepage();
                    }
                    
                    case 0 -> {
                        System.out.println("Exiting terminal menu...");
                        javafx.application.Platform.runLater(this::showMainMenu);
                    }

                    default -> {
                        System.out.println("Invalid option!");
                        shelterHomepage();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please pick a valid option!");
                sc.nextLine();
                shelterHomepage();
            }
        }).start();
    }

}
