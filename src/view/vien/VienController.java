package view.vien;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.VienDAOimpl;
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
import model.Vien;
import model.MyConnection;

public class VienController implements Initializable {
    MyConnection myConnection = new MyConnection();
    VienDAOimpl vienDAOimpl = new VienDAOimpl();
    ObservableList<Vien> vienObservableList;
    List<Vien> list;
    public static Vien selected;


    {
        try {
            myConnection.connectDB();
            list = vienDAOimpl.findAll();
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
    private TableView<Vien> tableVien;

    @FXML
    private TableColumn<Vien, String> sttVien;

    @FXML
    private TableColumn<Vien, String> tenVien;

    @FXML
    private TextField tkVien;

    @FXML
    private TableColumn<Vien, String> maVien;

    @FXML
    void timKiemVien(ActionEvent event) throws SQLException {
        list = vienDAOimpl.findLikeByPK(tkVien.getText());
        initialize(location, resources);

    }

    @FXML
    void themVien(ActionEvent event) {
        loadWindow("/view/vien/themvien/themvien.fxml", "Thêm viện");

    }

    @FXML
    void xoaVien(ActionEvent event) throws SQLException {
        selected = tableVien.getSelectionModel().getSelectedItem();
        vienObservableList.remove(selected);
        vienDAOimpl.delete(selected);

    }

    @FXML
    void capNhatVien(ActionEvent event) throws SQLException {
        list = vienDAOimpl.findAll();
        initialize(location, resources);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vienObservableList = FXCollections.observableArrayList(list);

        maVien.setCellValueFactory(new PropertyValueFactory<Vien, String>("maVien"));
        tenVien.setCellValueFactory(new PropertyValueFactory<Vien, String>("tenVien"));

        tableVien.setItems(vienObservableList);


        sttVien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<Vien, String> p) {
                return new ReadOnlyObjectWrapper(tableVien.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttVien.setSortable(false);

    }
}
