package software.fullstack.jfileexplorer;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileNode {
    private SimpleStringProperty fileName;
    private SimpleStringProperty type;
    private SimpleLongProperty fileSize;
    private String absPath;

    public FileNode(String name, String ftype, Long size, String absPath) {
        fileName = new SimpleStringProperty(name.replaceAll("[\\n\\r]", ""));
        type = new SimpleStringProperty(ftype);
        fileSize = new SimpleLongProperty(size);
        this.absPath = absPath;
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

    public long getFileSize() {
        return fileSize.get();
    }

    public SimpleLongProperty fileSizeProperty() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize.set(fileSize);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }
}
