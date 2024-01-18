package software.fullstack.jfileexplorer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TreeView directoryOverview;

    @FXML
    private BorderPane root;

    /**
     * Empty content notification
     */
    private Node emptyContent;

    private TableView directoryView = new TableView();

    public MainController() {
        Label emptyLabel = new Label();
        emptyLabel.setText("Nothing to show here...");
        HBox emptyHBox = new HBox(emptyLabel);
        emptyHBox.setAlignment(Pos.CENTER);
        emptyHBox.setStyle("-fx-background-color: white");

        emptyContent = emptyHBox;

        // Initialize table view
        TableColumn fileNameCol = new TableColumn("Name");
        TableColumn fileSizeCol = new TableColumn("Size");

        fileNameCol.setCellValueFactory(new PropertyValueFactory<FileNode, String>("fileName"));
        fileSizeCol.setCellValueFactory(new PropertyValueFactory<FileNode, Integer>("fileSize"));

        directoryView.getColumns().addAll(fileNameCol, fileSizeCol);
    }

    @FXML
    public void initialize() {
        ObservableList<FileNode> nodes = FXCollections.observableArrayList(
                new FileNode("sample file 1", 5),
                new FileNode("sample file 2", 10),
                new FileNode("sample file 3", 8)
        );

        directoryView.setItems(nodes);

        // TODO: Set center content based on existence of content in folder
        root.setCenter(directoryView);
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