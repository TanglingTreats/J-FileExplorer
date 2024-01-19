package software.fullstack.jfileexplorer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import software.fullstack.jfileexplorer.traversal.FSNode;

import java.util.List;

public class ObservableFileNodes {

    private final boolean showHiddenFiles = false;
    private final ObservableList<FileNode> observableFileNodes = FXCollections.observableArrayList();

    private final BorderPane root;

    private final Node emptyContent;

    private final Node directoryView;

    public ObservableFileNodes(FSNode fsNode, BorderPane root, Node emptyContent, Node directoryView) {
        this.root = root;
        this.emptyContent = emptyContent;
        this.directoryView = directoryView;

        // Initialize observable file nodes
        getFileNodesFromIndex(fsNode);
    }

    public ObservableList<FileNode> getFileNodes() {
        return this.observableFileNodes;
    }

    public void loadSelectedFSNode(FSNode node) {
        getFileNodesFromIndex(node);
    }

    private void getFileNodesFromIndex(FSNode fsNode) {
        boolean wasEmpty = observableFileNodes.isEmpty();
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

        if(observableFileNodes.isEmpty()) {
            root.setCenter(emptyContent);
        } else {
            if(wasEmpty) {
                root.setCenter(directoryView);
            }
        }
    }

    private boolean toShowHiddenFiles(boolean startsWithDot) {
        return !(startsWithDot && !showHiddenFiles);
    }
}
