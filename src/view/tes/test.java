package view.tes;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        //TableColumn firstNameCol = new TableColumn("First Name");
        //firstNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        TableColumn searchCol = new TableColumn<>();

        TableView table = new TableView();
        //table.getColumns().addAll(firstNameCol, lastNameCol);
        table.setItems(FXCollections.observableArrayList(
                new Person("Jacob", "Smith"),
                new Person("Isabella", "Johnson"),
                new Person("Ethan", "Williams")
        ));
        TextField headerTextField = new TextField();
        Label label = new Label("First Name");
        VBox headerGraphic = new VBox();
        headerGraphic.setAlignment(Pos.CENTER);
        headerGraphic.getChildren().addAll(label, headerTextField);

        TableColumn<Person, String> firstNameCol = new TableColumn<>();
        firstNameCol.setGraphic(headerGraphic);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        StackPane layout = new StackPane();
        layout.setStyle("-fx-padding: 10;");
        layout.getChildren().add(table);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

//        for (Node n: table.lookupAll(".column-header > .label")) {
//            if (n instanceof Label) {
//                Label label = (Label) n;
//                TextField textField = new TextField(label.getText());
//                label.textProperty().bind(textField.textProperty());
//                label.setGraphic(textField);
//                label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//            }
//        }

    }

}