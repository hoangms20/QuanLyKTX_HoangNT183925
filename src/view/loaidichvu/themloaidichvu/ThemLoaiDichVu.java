package view.loaidichvu.themloaidichvu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ThemLoaiDichVu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("themloaidichvu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
