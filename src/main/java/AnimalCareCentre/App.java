package AnimalCareCentre;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
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
    stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
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
          List<Animal> animals = manager.searchAnimalByKeyword(search);
          if (animals == null || animals.isEmpty()) {
            System.out.println("\n\n\nNo matches! Returning...");
            searchAnimalMenu();
          } else {
          System.out.println(manager.searchAnimalByKeyword(search));
            searchAnimalMenu();
          // later it's not going to be just a print, should have options
          }
        }

        case 2 -> {

        }

        case 0 -> {
          userHomepage();
        }
      }

    } catch (InputMismatchException e) {
      System.out.println("Please pick a valid option!");
      searchAnimalMenu();
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
        System.out.println("0. Logout");
        System.out.print("Option: ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
          case 1 -> {
            searchAnimalMenu();
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
   * This method shows shelter's homepage
   */
  private void shelterHomepage() {
    // To show the window with only the logout button
    showTerminalScreen();

    // Shelter Menu
    new Thread(() -> {
      int option;

      do {
        System.out.println("=== SHELTER MENU ===");
        System.out.println("1. Register Animal");
        System.out.println("0. Logout");
        System.out.print("Option: ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
          // To allow shelters to register animals
          case 1 -> {
            System.out.println("\n=== REGISTER ANIMAL ===");
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Type (DOG, CAT or RABBIT): ");
            String typeInput = sc.nextLine();
            AnimalType type = AnimalType.fromString(typeInput);
            if (type == null) {
              System.out.println("Invalid type");
              return;
            }

            System.out.print("Available races for : " + type + ": ");
            for (String race : type.getBreeds()) {
              System.out.println(race);
            }

            System.out.print("Race: ");
            String race = sc.nextLine();

            if (!type.getBreeds().contains(race)) {
              System.out.println("Invalid race");
            }

            System.out.print("Size (SMALL, MEDIUM, LARGE): ");
            AnimalSize size = AnimalSize.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Color: ");
            AnimalColor color = AnimalColor.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Description: ");
            String description = sc.nextLine();

            System.out.print("Adoption type: ");
            AdoptionType adoptionType = AdoptionType.valueOf(sc.nextLine().toUpperCase());

            manager.registerAnimal(name, type, race, size, age, color, description, adoptionType);
            System.out.println("✅ Animal registered successfully!");

          }

          case 0 -> {
            sc.close();
            System.out.println("Exiting terminal menu...");
            javafx.application.Platform.runLater(() -> showMainMenu());
          }
          default -> System.out.println("Invalid option!");
        }
      } while (option != 0);
    }).start();

  }
}
