package AnimalCareCentre;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author 51084 - Diogo Ferreira
 * @author
 * @author 51297 - Sara Canelas
 * @author
 */
public class AppFX extends Application {

  private static Stage stage;
  private static Navigator nav;

  public void start(Stage stage) {
    AppFX.stage = stage;
    nav = new Navigator(stage);
  }

  // Class just waiting for GUI implementation

  // public static void main(String[] args) {
  //   launch(args);
  // }
}
