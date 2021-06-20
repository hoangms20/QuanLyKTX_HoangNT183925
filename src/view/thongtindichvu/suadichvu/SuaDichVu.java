package view.thongtindichvu.suadichvu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuaDichVu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("suadichvu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
