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
import software.fullstack.jfileexplorer.traversal.FSIndex;
import software.fullstack.jfileexplorer.traversal.FSNode;

import java.util.ArrayList;
import java.util.List;

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

    private TableView<FileNode> directoryView = new TableView<>();

    private FSIndex dirIndex = new FSIndex();

    private ObservableList<FileNode> observableFileNodes = FXCollections.observableArrayList();

    // Toggle to show or hide hidden files
    private boolean showHiddenFiles = false;

    public MainController() {
        Label emptyLabel = new Label();
        emptyLabel.setText("Nothing to show here...");

        HBox emptyHBox = new HBox(emptyLabel);
        emptyHBox.setAlignment(Pos.CENTER);
        emptyHBox.setStyle("-fx-background-color: white");

        emptyContent = emptyHBox;

        // Initialize table columns
        TableColumn fileNameCol = new TableColumn("Name");
        TableColumn fileSizeCol = new TableColumn("Size");

        fileNameCol.setMinWidth(100);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<FileNode, String>("fileName"));

        fileSizeCol.setMinWidth(100);
        fileSizeCol.setCellValueFactory(new PropertyValueFactory<FileNode, Long>("fileSize"));

        directoryView.getColumns().addAll(fileNameCol, fileSizeCol);
    }

    @FXML
    public void initialize() {
        // Check index
        FSNode home = dirIndex.home;

        directoryView.setItems(observableFileNodes);
        // Populate observable list
        getFileNodesFromIndex(home);

        // Set empty content pane if home is empty
        if(!home.getChildren().isEmpty()) {
            root.setCenter(directoryView);
        } else {
            root.setCenter(emptyContent);
        }

        // Initialize Directory overview
        directoryOverview.setRoot(getFSTreeItemsFromIndex(home));

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

    private void getFileNodesFromIndex(FSNode fsNode) {
        // Clear current data if any
        observableFileNodes.clear();

        List<FSNode> children = fsNode.getChildren();
        for (FSNode child: children) {
            // If file does not start with '.' and is not showing hidden files
            // Prioritise non-hidden files
            if(toShowHiddenFiles(child.getFile().getFileName().startsWith("."))) {
                observableFileNodes.add(child.getFile());
            }
        }
    }

    private TreeItem getFSTreeItemsFromIndex(FSNode fsNode) {
        List<FSNode> children = fsNode.getChildren();
        FSTreeItem rootTreeItem = new FSTreeItem(fsNode.getPath().toFile());
        for (FSNode child: children) {
            if(toShowHiddenFiles(child.getFile().getFileName().startsWith("."))) {
                FSTreeItem treeItem = new FSTreeItem(child.getPath().toFile());
                rootTreeItem.getChildren().add(treeItem);
            }
        }

        return rootTreeItem;
    }

    private boolean toShowHiddenFiles(boolean startsWithDot) {
        return !(startsWithDot && !showHiddenFiles);
    }
}