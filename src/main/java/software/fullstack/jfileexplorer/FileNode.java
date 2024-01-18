package software.fullstack.jfileexplorer;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileNode {
    private SimpleStringProperty fileName;
    private SimpleIntegerProperty fileSize;

    public FileNode(String name, Integer size) {
        fileName = new SimpleStringProperty(name);
        fileSize = new SimpleIntegerProperty(size);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public int getFileSize() {
        return fileSize.get();
    }

    public SimpleIntegerProperty fileSizeProperty() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize.set(fileSize);
    }
}
