package view.khoa;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.KhoaDAOimpl;
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
import model.Khoa;
import model.MyConnection;

public class KhoaController implements Initializable {
    MyConnection myConnection = new MyConnection();
    KhoaDAOimpl khoaDAOimpl = new KhoaDAOimpl();
    ObservableList<Khoa> khoaObservableList;
    List<Khoa> list;
    public static Khoa selected;


    {
        try {
            myConnection.connectDB();
            list = khoaDAOimpl.findAll();
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
    private TableColumn<Khoa, String> tenKhoa;

    @FXML
    private TableColumn<Khoa, String> sttKhoa;

    @FXML
    private TableView<Khoa> tableKhoa;

    @FXML
    private TableColumn<Khoa, String> maKhoa;

    @FXML
    private TableColumn<Khoa, Integer> namVaoHoc;

    @FXML
    private TextField tkKhoa;

    @FXML
    void timKiemKhoa(ActionEvent event) throws SQLException {
        list = khoaDAOimpl.findLikeByPK(tkKhoa.getText());
        initialize(location, resources);

    }

    @FXML
    void themKhoa(ActionEvent event) {
        loadWindow("/view/khoa/themkhoa/themkhoa.fxml", "Thêm khóa");

    }

    @FXML
    void xoaKhoa(ActionEvent event) throws SQLException {
        selected = tableKhoa.getSelectionModel().getSelectedItem();
        khoaObservableList.remove(selected);
        khoaDAOimpl.delete(selected);

    }

    @FXML
    void capNhatKhoa(ActionEvent event) throws SQLException {
        list = khoaDAOimpl.findAll();
        initialize(location, resources);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        khoaObservableList = FXCollections.observableArrayList(list);

        namVaoHoc.setCellValueFactory(new PropertyValueFactory<Khoa, Integer>("namVaoHoc"));
        maKhoa.setCellValueFactory(new PropertyValueFactory<Khoa, String>("maKhoa"));
        tenKhoa.setCellValueFactory(new PropertyValueFactory<Khoa, String>("tenKhoa"));
        tableKhoa.setItems(khoaObservableList);


        sttKhoa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Khoa, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<Khoa, String> p) {
                return new ReadOnlyObjectWrapper(tableKhoa.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttKhoa.setSortable(false);

    }
}
