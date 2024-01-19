package software.fullstack.jfileexplorer.traversal;

import software.fullstack.jfileexplorer.FileNode;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Index directories
 */
public class FSIndex {

    public FSNode home;

    private final int maxDepth = 2;

    public FSIndex() {
        FileSystem fs = FileSystems.getDefault();
        Path homePath = fs.getPath(System.getProperty("user.home"));

        try {
            System.out.println("Starting path at: " + homePath);
            System.out.println("Size of home (KB): " + Files.size(homePath) / 1024);

            // manipulate stack if depth changes
            Stack<FSNode> fsNodeStack = new Stack<>();
            AtomicReference<FSNode> lastNode = new AtomicReference<>();

            AtomicBoolean firstDir = new AtomicBoolean(true);

            Files.walk(homePath, maxDepth).forEach(path -> {
                File file = path.toFile();
                printFile(file);

                // Is file directory?
                // If yes,
                //      Create new FSNode and push to stack
                //      Is current depth lower?
                //          Push to stack, update depth
                //      Else
                //          Pop stack
                // If no, isLastFileDir = true, peek and add to FSNode children
                if(file.isDirectory()) {
                    // Create directory file node
                    FileNode fileNode = new FileNode(file.getName(), "dir", 0L, file.getAbsolutePath());
                    FSNode fsNode = new FSNode(fileNode);

                    // Set home file node
                    if(firstDir.get()) {
                        home = fsNode;
                        firstDir.set(false);

                        fsNodeStack.push(fsNode);
                    } else {
                        int stackDepth = Path.of(fsNodeStack.peek().getFile().getAbsPath()).getNameCount();
                        int folderDepth = path.getNameCount();

                        System.out.println("Stack depth:" + stackDepth + "\tCurrent depth: " + folderDepth);
                        System.out.println("Current stack: " + fsNodeStack.peek().getFile().getFileName());

                        if(stackDepth == folderDepth - 1) {
                            // Current depth is direct descendant, add current node to children
                            fsNodeStack.peek().getChildren().add(fsNode);

                        } else if (stackDepth < folderDepth - 1) {
                            // Descended
                            // push last dir node into stack
                            System.out.println("Pushing into stack: "  + lastNode.get().getFile().getFileName());
                            fsNodeStack.push(lastNode.get());

                            // Add children
                            fsNodeStack.peek().getChildren().add(fsNode);
                        } else {
                            // Pop from depth stack
                            fsNodeStack.pop();

                            // Add children
                            fsNodeStack.peek().getChildren().add(fsNode);
                        }

                    }

                    lastNode.set(fsNode);
                    System.out.println("Storing last node: " + fsNode.getFile().getFileName());

                } else {
                    System.out.println("Adding file");
                    int stackDepth = Path.of(fsNodeStack.peek().getFile().getAbsPath()).getNameCount();
                    int folderDepth = path.getNameCount();

                    System.out.println("Stack depth:" + stackDepth + "\tCurrent depth: " + folderDepth);
                    System.out.println("Current stack: " + fsNodeStack.peek().getFile().getFileName());

                    if(stackDepth == folderDepth) {
                        fsNodeStack.pop();
                    } else if(stackDepth < folderDepth - 1) {
                        System.out.println("Pushing into stack: "  + lastNode.get().getFile().getFileName());
                        fsNodeStack.push(lastNode.get());
                    }

                    FileNode fileNode = new FileNode(file.getName(), "file", file.length() / 1024, file.getAbsolutePath());
                    FSNode fsNode = new FSNode(fileNode);

                    FSNode parentDir = fsNodeStack.peek();
                    List<FSNode> children = parentDir.getChildren();
                    children.add(fsNode);
                }

                System.out.println();
            });

        } catch (UncheckedIOException uncheckedIOException) {
            System.out.println("Encountered permission error");
        } catch (IOException ioException) {
            System.err.println("Problem accessing path: " + ioException.getMessage());
        }
    }

    private void printFile(File file) {
        if(file.isDirectory()) {
            System.out.println("Directory: " + file.getAbsolutePath());
        } else {
            System.out.println("File: " + file.getAbsolutePath());
        }
    }

    private void makeTree(File file) {
        if(file.isDirectory()) {
            System.out.println("Directory: " + file.getAbsolutePath());
        } else {
            System.out.println("File: " + file.getAbsolutePath());
        }
    }
}
