package software.fullstack.jfileexplorer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JFileExplorer extends Application {
    public static final int appHeight = 768;
    public static final int appWidth = 1080;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JFileExplorer.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), appWidth, appHeight);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("J File Explorer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}