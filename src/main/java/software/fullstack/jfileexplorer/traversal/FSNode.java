package software.fullstack.jfileexplorer.traversal;

import software.fullstack.jfileexplorer.FileNode;

import java.util.ArrayList;
import java.util.List;

public class FSNode {
    private FileNode file;

    private List<FSNode> children;

    public FSNode(FileNode file) {
        this.file = file;
        if(file.getType().equals("dir")) {
            children = new ArrayList<>();
        }
    }

    public FileNode getFile() {
        return file;
    }

    public void setFile(FileNode file) {
        this.file = file;
    }

    public List<FSNode> getChildren() {
        return children;
    }

    public void setChildren(List<FSNode> children) {
        this.children = children;
    }
}
