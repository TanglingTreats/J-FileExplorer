package software.fullstack.jfileexplorer;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

public class FSTreeItem extends TreeItem<String> {
    public static Image folderCollapseImage=new Image(ClassLoader.getSystemResourceAsStream("com/huguesjohnson/javafxfilebrowsedemo/folder.png"));
    public static Image folderExpandImage=new Image(ClassLoader.getSystemResourceAsStream("com/huguesjohnson/javafxfilebrowsedemo/folder-open.png"));
    public static Image fileImage=new Image(ClassLoader.getSystemResourceAsStream("com/huguesjohnson/javafxfilebrowsedemo/text-x-generic.png"));
}
