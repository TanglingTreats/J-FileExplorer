module software.fullstack.jfileexplorer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens software.fullstack.jfileexplorer to javafx.fxml;
    exports software.fullstack.jfileexplorer;
}