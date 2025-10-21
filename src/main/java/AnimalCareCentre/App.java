package AnimalCareCentre;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author 51084 - Diogo Ferreira
 * @author
 * @author 51297 - Sara Canelas
 * @author
 */
public class App extends Application {

  private static Stage stage;
  private static Navigator nav;
  private static final ACCManager manager = new ACCManager();

  public void start(Stage stage) {
    App.stage = stage;
    nav = new Navigator(stage, manager);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
