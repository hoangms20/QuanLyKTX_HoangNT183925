package view.thongtindiennuoc.themdiennuoc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThemDienNuoc extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("themdiennuoc.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
