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
    private TreeView<String> directoryOverview;

    @FXML
    private BorderPane root;

    /**
     * Empty content notification
     */
    private Node emptyContent;

    private TableView<FileNode> directoryView = new TableView<>();

    private FSIndex dirIndex = new FSIndex();

    private ObservableFileNodes observableFileNodes;
//    private ObservableList<FileNode> observableFileNodes = FXCollections.observableArrayList();

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
        TableColumn fileSizeCol = new TableColumn("Size (KB)");

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

        // Populate observable list
        observableFileNodes = new ObservableFileNodes(home, root, emptyContent, directoryView);

        directoryView.setItems(observableFileNodes.getFileNodes());

        // Initialize Directory overview
        directoryOverview.setRoot(getFSTreeItemsFromIndex(home));
        directoryOverview.setCellFactory(p -> new InteractiveTreeCellImpl(observableFileNodes));

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

    private FSTreeItem getFSTreeItemsFromIndex(FSNode fsNode) {
        List<FSNode> children = fsNode.getChildren();

        FSTreeItem rootTreeItem = new FSTreeItem(fsNode.getPath().toFile(), fsNode);
        for (FSNode child: children) {
            if(toShowHiddenFiles(child.getFile().getFileName().startsWith("."))) {
                FSTreeItem treeItem;
                if(child.isLeaf()) {
                    treeItem = new FSTreeItem(child.getPath().toFile(), child);
                } else {
                    treeItem = getFSTreeItemsFromIndex(child);
                }
                rootTreeItem.getChildren().add(treeItem);
            }
        }

        return rootTreeItem;
    }

    private boolean toShowHiddenFiles(boolean startsWithDot) {
        return !(startsWithDot && !showHiddenFiles);
    }
}