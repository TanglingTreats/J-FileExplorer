package software.fullstack.jfileexplorer.traversal;

import software.fullstack.jfileexplorer.FileNode;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FSNode {
    private FileNode file;

    private Path path;

    private List<FSNode> children;

    public FSNode(FileNode file, Path path) {
        this.file = file;
        if(file.getType().equals("dir")) {
            children = new ArrayList<>();
        }

        this.path = path;
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
