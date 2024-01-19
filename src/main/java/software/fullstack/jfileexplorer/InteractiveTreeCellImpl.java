package software.fullstack.jfileexplorer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import software.fullstack.jfileexplorer.traversal.FSIndex;
import software.fullstack.jfileexplorer.traversal.FSNode;

public class InteractiveTreeCellImpl extends TreeCell<String> {

    private ObservableFileNodes observableFileNodes;

    private boolean isDoubleClick = false;

    public InteractiveTreeCellImpl(ObservableFileNodes fileNodes) {
        this.observableFileNodes = fileNodes;

        this.setOnMouseClicked(mouseEvent -> {
            if(!isEmpty()) {
                FSTreeItem fsTreeItem = (FSTreeItem) getTreeItem();

                if (fsTreeItem.isDirectory()) {
                    observableFileNodes.loadSelectedFSNode(fsTreeItem.getNode());
                }
            }
        });
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // TODO: Implement editing
            if (isEditing()) {
//                if (textField != null) {
//                    textField.setText(getString());
//                }
                setText(null);
//                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
            }
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
