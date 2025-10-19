package AnimalCareCentre;

import javafx.stage.Stage;
import AnimalCareCentre.views.*;

/**
 * This class handles all of the navigation through the different pages of
 * the app.
 *
 */
public class Navigator {

  Stage stage;

  /**
   * Constructor for Navigator class.
   *
   */
  public Navigator(Stage stage) {
    this.stage = stage;
    stage.setTitle("AnimalCareCentre");
    stage.setMaximized(true);
    stage.show();
    showMainScene();

  }

  /**
   * This method creates a new customized scene that is used throughout most of
   * the pages of the app. It's TEMPORARILY called in the constructor just so that
   * we see a page when we run the code while still developing .
   */
  public void showMainScene() {
    new ACCScene(stage, new ACCVBox());
  }
}
