package view.uutien;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import dao_impl.UuTienDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.MyConnection;
import model.UuTien;

public class UuTienController implements Initializable {
    MyConnection myConnection = new MyConnection();
    UuTienDAOimpl uuTienDAOimpl = new UuTienDAOimpl();
    ObservableList<UuTien> uuTienObservableList;
    List<UuTien> list;
    public static UuTien selected;


    {
        try {
            myConnection.connectDB();
            list = uuTienDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void loadWindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<UuTien, String> tenUuTien;

    @FXML
    private TableColumn<UuTien, String> maUuTien;

    @FXML
    private TextField tkUuTien;

    @FXML
    private TableColumn<UuTien, String> ghiChuUuTien;

    @FXML
    private TableColumn<UuTien, Integer> mucDoUuTien;

    @FXML
    private TableView<UuTien> tableUuTien;

    @FXML
    private TableColumn<UuTien, String> sttUuTien;

    @FXML
    void timKiemUuTien(ActionEvent event) throws SQLException {
        list = uuTienDAOimpl.findLikeByPK(tkUuTien.getText());
        initialize(location, resources);

    }

    @FXML
    void themUuTien(ActionEvent event) {
        loadWindow("/view/uutien/themuutien/themuutien.fxml", "Thêm ưu tiên");

    }


    @FXML
    void suaUuTien(ActionEvent event) {
        selected = tableUuTien.getSelectionModel().getSelectedItem();
        loadWindow("/view/uutien/suauutien/suauutien.fxml", "Sửa ưu tiên");

    }

    @FXML
    void xoaUuTien(ActionEvent event) throws SQLException {
        selected = tableUuTien.getSelectionModel().getSelectedItem();
        uuTienObservableList.remove(selected);
        uuTienDAOimpl.delete(selected);

    }

    @FXML
    void capNhatUuTien(ActionEvent event) throws SQLException {
        list = uuTienDAOimpl.findAll();
        initialize(location, resources);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uuTienObservableList = FXCollections.observableArrayList(list);

        mucDoUuTien.setCellValueFactory(new PropertyValueFactory<UuTien, Integer>("mucDoUuTien"));
        maUuTien.setCellValueFactory(new PropertyValueFactory<UuTien, String>("maUuTien"));
        tenUuTien.setCellValueFactory(new PropertyValueFactory<UuTien, String>("tenUuTien"));
        ghiChuUuTien.setCellValueFactory(new PropertyValueFactory<UuTien, String>("ghiChuUuTien"));

        tableUuTien.setItems(uuTienObservableList);


        sttUuTien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UuTien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<UuTien, String> p) {
                return new ReadOnlyObjectWrapper(tableUuTien.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttUuTien.setSortable(false);

    }
}
