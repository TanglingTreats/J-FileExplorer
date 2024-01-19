package software.fullstack.jfileexplorer;

import griffon.plugins.tangoicons.Tango;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import griffon.javafx.support.tangoicons.TangoIcon;
import javafx.scene.input.MouseEvent;
import software.fullstack.jfileexplorer.traversal.FSNode;

import java.io.File;

public class FSTreeItem extends TreeItem<String> {
    public static Image folderCollapseImage= new TangoIcon(Tango.PLACES_FOLDER);
    public static Image folderExpandImage=new TangoIcon(Tango.STATUS_FOLDER_OPEN);
    public static Image fileImage=new TangoIcon(Tango.MIMETYPES_TEXT_X_GENERIC);

    private String fullPath;

    private boolean isDirectory;

    private FSNode node;

    public FSTreeItem(File file, FSNode node) {
        super(file.getAbsolutePath());

        this.fullPath = file.getAbsolutePath();

        this.node = node;

        this.isDirectory = file.isDirectory();
        if(file.isDirectory()) {
            this.setGraphic(new ImageView(folderCollapseImage));
        } else {
            this.setGraphic(new ImageView(fileImage));
        }

        //set the value
        if(!fullPath.endsWith(File.separator)){
            //set the value (which is what is displayed in the tree)
            String value=file.toString();
            int indexOf=value.lastIndexOf(File.separator);
            if(indexOf>0){
                this.setValue(value.substring(indexOf+1).replaceAll("[\\n\\r]", ""));
            }else{
                this.setValue(value.replaceAll("[\\n\\r]", ""));
            }
        }

        // TreeItem expand event
        this.addEventHandler(TreeItem.branchExpandedEvent(),new EventHandler(){
            @Override
            public void handle(Event e){
                FSTreeItem source=(FSTreeItem)e.getSource();
                if(source.isDirectory()&&source.isExpanded()){
                    ImageView iv=(ImageView)source.getGraphic();
                    iv.setImage(folderExpandImage);
                }
            }
        });

        // TreeItem collapse event
        this.addEventHandler(TreeItem.branchCollapsedEvent(),new EventHandler(){
            @Override
            public void handle(Event e){
                FSTreeItem source=(FSTreeItem)e.getSource();
                if(source.isDirectory()&&!source.isExpanded()){
                    ImageView iv=(ImageView)source.getGraphic();
                    iv.setImage(folderCollapseImage);
                }
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            FSTreeItem source = (FSTreeItem) event.getSource();
            System.out.println(source.fullPath);
        });
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public FSNode getNode() {
        return node;
    }

    public void setNode(FSNode node) {
        this.node = node;
    }
}
