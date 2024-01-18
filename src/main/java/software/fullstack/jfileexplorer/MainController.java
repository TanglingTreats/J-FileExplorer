package software.fullstack.jfileexplorer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainController {
    @FXML
    private Label notificationText;

    /**
     * Contains other columns
     */
    @FXML
    private TableView directoryContentHolder;

    @FXML
    private BorderPane root;

    private Node emptyContent;

    public MainController() {
        Label emptyLabel = new Label();
        emptyLabel.setText("Nothing to show here...");
        HBox emptyHBox = new HBox(emptyLabel);
        emptyHBox.setAlignment(Pos.CENTER);
        emptyHBox.setStyle("-fx-background-color: white");

        emptyContent=emptyHBox;
    }

    @FXML
    public void initialize() {
        root.setCenter(emptyContent);
    }
    @FXML
    protected void onHelloButtonClick() {
        notificationText.setText("Welcome to JavaFX Application!");
    }
}