package software.fullstack.jfileexplorer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import software.fullstack.jfileexplorer.traversal.FSNode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ObservableFileNodes {

    private boolean showHiddenFiles = false;
    private ObservableList<FileNode> observableFileNodes = FXCollections.observableArrayList();

    public ObservableFileNodes(FSNode fsNode) {
        getFileNodesFromIndex(fsNode);
    }

    public ObservableList<FileNode> getFileNodes() {
        return this.observableFileNodes;
    }

    public void loadSelectedFSNode(FSNode node) {
        getFileNodesFromIndex(node);
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
    private boolean toShowHiddenFiles(boolean startsWithDot) {
        return !(startsWithDot && !showHiddenFiles);
    }
}
