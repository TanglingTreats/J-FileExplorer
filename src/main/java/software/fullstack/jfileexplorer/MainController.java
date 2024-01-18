package software.fullstack.jfileexplorer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import static software.fullstack.jfileexplorer.JFileExplorer.appWidth;

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

    /**
     * Empty content notification
     */
    private Node emptyContent;

    private Node fileDirectoryView;

    public MainController() {
        Label emptyLabel = new Label();
        emptyLabel.setText("Nothing to show here...");
        HBox emptyHBox = new HBox(emptyLabel);
        emptyHBox.setAlignment(Pos.CENTER);
        emptyHBox.setStyle("-fx-background-color: white");

        emptyContent=emptyHBox;

        // Initialize table view
        TableView directoryView = new TableView();
        TableColumn fileNameCol = new TableColumn("Name");
        TableColumn fileSizeCol = new TableColumn("Size");

        directoryView.getColumns().addAll(fileNameCol, fileSizeCol);
        fileDirectoryView = directoryView;
    }

    @FXML
    public void initialize() {
        // TODO: Set center content based on existence of content in folder
        root.setCenter(emptyContent);
    }
    @FXML
    protected void onHelloButtonClick() {
        notificationText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void createNewFile() {
        System.out.println("Creating new file");
        /*
        Create editable row in file explorer.
        Focus on name column
        On enter,
            confirm file creation
        Else,
            remove editable row from table view
         */
    }
}