package AnimalCareCentre;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    year.setMaxWidth(30);

    Button create = new Button("Create");
    Button back = new Button("Back");

    create.setOnAction(e -> {
      if (accType.getValue().equals("User")) {
        manager.createUserAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), birthDate.getValue(), Integer.parseInt(contact.getText()));
        showMainMenu();

      } else if (accType.getValue().equals("Admin")) {
        manager.createAdminAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), birthDate.getValue());
        showMainMenu();

      } else if (accType.getValue().equals("Shelter")) {
        manager.createShelterAccount(name.getText(), email.getText(), password.getText(), location.getText(),
            sec.getValue(), answer.getText(), birthDate.getValue(), Integer.parseInt(year.getText()),
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

      if (selected == "User") {
        vbox.getChildren().addAll(birthLabel, birthDate, contactLabel, contact);
      } else if (selected == "Admin") {
        vbox.getChildren().addAll(birthLabel, birthDate);
      } else if (selected == "Shelter") {
        vbox.getChildren().addAll(contactLabel, contactLabel, foundYear, year);
      }
    });

    scene.addItems(create, back);

  }
}
