package AnimalCareCentre;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import AnimalCareCentre.enums.*;
import AnimalCareCentre.views.*;
import AnimalCareCentre.models.*;

/**
 * This class handles all of the navigation through the different pages of
 * the app.
 *
 */
public class Navigator {

  private Account loggedAcc;
  private Stage stage;
  private ACCManager manager;

  /**
   * Constructor for Navigator class.
   *
   */
  public Navigator(Stage stage, ACCManager manager) {

    this.stage = stage;
    this.manager = manager;
    stage.setTitle("AnimalCareCentre");
    stage.setMaximized(true);
    stage.show();
    showMainMenu();

  }

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

  public void login() {
    ACCScene scene = new ACCScene(stage, new ACCVBox());
    Label emailLabel = new Label("Email:");
    TextField email = new TextField();
    Label passLabel = new Label("Password:");
    PasswordField password = new PasswordField();
    Button enter = new Button("Enter");
    Button back = new Button("Back");
    scene.addItems(emailLabel, email, passLabel, password, enter, back);

    enter.setOnAction(e -> {
      Account acc = manager.login(email.getText(), password.getText());
      if (acc != null) {
        loggedAcc = acc;
        System.out.println("Logged in!");
      } else {
        System.out.println("Wrong credentials!");
      }
    });

    back.setOnAction(e -> {
      showMainMenu();
    });
  }

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

        if (sec.getValue() == null || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
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

        if (sec.getValue() == null || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue().toString(), answer.getText(), birthDate.getValue().toString())) {
          showAlert(AlertType.ERROR, "Empty Fields!", "All fields are required!");
          return;
        }

        if (birthDate.getValue() != null && birthDate.getValue().isAfter(LocalDate.now())) {
          showAlert(AlertType.ERROR, "Invalid Date!", "The birth date cannot be in the future");
          return;
        }

        manager.createAdminAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), birthDate.getValue());
        showMainMenu();

      } else if (accType.getValue().equals("Shelter")) {

        if (sec.getValue() == null || !manager.validateFields(name.getText(), email.getText(), password.getText(), location.getText(),
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
      } else if (selected.equals("Admin")) {
        vbox.getChildren().addAll(birthLabel, birthDate);
      } else if (selected.equals("Shelter")) {
        vbox.getChildren().addAll(contactLabel, contact, foundYear, year);
      }
    });

    scene.addItems(create, back);
  }

  public void showAlert(AlertType type, String title, String text) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(text);
    alert.showAndWait();
  }
}
