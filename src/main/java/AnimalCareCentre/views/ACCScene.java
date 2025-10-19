package AnimalCareCentre.views;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * This class defines the main scene that will be used throughout the app.
 *
 */
public class ACCScene extends Scene {
  private ACCVBox mainVbox;
  private ACCVBox content;
  private ACCHBox header;
  private Stage stage;

  /**
   * Constructor for ACCScene. Defines the default settings.
   *
   * @param stage
   * @param vbox
   */
  public ACCScene(Stage stage, ACCVBox vbox) {
    super(vbox);

    mainVbox = vbox;
    mainVbox.setFillWidth(true);
    mainVbox.setStyle("-fx-background-color: #FFFAF1;");

    this.stage = stage;
    content = new ACCVBox();
    header = new ACCHBox();

    createPage();

    stage.setScene(this);

  }

  /**
   * This method creates the Main page and is called on the constructor because it
   * is should be used every time there's a new scene of this type.
   */
  private void createPage() {
    ImageView logo = createLogo();
    ACCHBox body = new ACCHBox();
    ImageView left = createLeftBorder();
    ImageView right = createRightBorder();
    ACCHBox footer = new ACCHBox();

    header.setStyle("-fx-background-color: #69462B;");
    header.setMinHeight(50);

    content.setMaxWidth(Double.MAX_VALUE);
    HBox.setHgrow(content, Priority.ALWAYS);
    body.setFillHeight(true);

    logo.setPreserveRatio(true);
    logo.fitWidthProperty().bind(stage.widthProperty().multiply(0.6));
    logo.fitHeightProperty().bind(stage.heightProperty().multiply(0.25));
    logo.setSmooth(true);
    logo.setCache(true);

    left.setPreserveRatio(true);
    right.setPreserveRatio(true);
    left.fitWidthProperty().bind(stage.widthProperty().multiply(0.15));
    right.fitWidthProperty().bind(stage.widthProperty().multiply(0.15));

    body.addItems(left, content, right);
    ACCVBox.setVgrow(body, javafx.scene.layout.Priority.ALWAYS);

    mainVbox.addItems(logo, header, body, footer);
  }

  /**
   * This method adds items to the navigation bar of the app.
   */
  public void setHeader(Node... items) {
    header.addItems(items);
  }

  /**
   * This method adds items to the main body portion of the page.
   */
  public void addItems(Node... items) {
    mainVbox.addItems(items);
  }

  /**
   * This method returns the banner logo.
   *
   */
  private ImageView createLogo() {
    Image img = new Image(getClass().getResourceAsStream("/banner.png"));
    return new ImageView(img);
  }

  /**
   * This method returns the left decorative border.
   *
   */
  private ImageView createLeftBorder() {
    Image img = new Image(getClass().getResourceAsStream("/left.png"));
    return new ImageView(img);
  }

  /**
   * This method returns the right decoration border.
   *
   */
  private ImageView createRightBorder() {
    Image img = new Image(getClass().getResourceAsStream("/right.png"));
    return new ImageView(img);
  }

}
