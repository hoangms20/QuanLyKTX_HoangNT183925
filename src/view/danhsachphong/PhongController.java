package view.danhsachphong;

import dao_impl.PhongDAOimpl;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.MyConnection;
import model.Phong;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PhongController implements Initializable {
    MyConnection myConnection = new MyConnection();
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
    ObservableList<Phong> phongObservableList;
    List<Phong> list;


    {
        try {
            myConnection.connectDB();
            list = phongDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Phong selected;

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
    private TableColumn<Phong, Integer> slToiDaP;

    @FXML
    private TableColumn<Phong, String> loaiPhong;

    @FXML
    private TableColumn<Phong, String> tenPhong;

    @FXML
    private TextField tkPhong;

    @FXML
    private TableView<Phong> tablePhong;

    @FXML
    private TableColumn<Phong, String> sttPhong;

    @FXML
    private TableColumn<Phong, String> maPhong;

    @FXML
    private TableColumn<Phong, Integer> tangSo;

    @FXML
    private TableColumn<Phong, String> tinhTrangPhong;

    @FXML
    private TableColumn<Phong, Integer> slDangOP;

    @FXML
    private TableColumn<Phong, String> maKhu;

    @FXML
    void timKiemPhong(ActionEvent event) throws SQLException {
        list = phongDAOimpl.findLikeByPK(tkPhong.getText());
        initialize(location, resources);
    }

    @FXML
    void themPhong(ActionEvent event) {
        loadWindow("/view/danhsachphong/themphong/themphong.fxml", "Thêm phòng");

    }


    @FXML
    void suaPhong(ActionEvent event) {
        selected = tablePhong.getSelectionModel().getSelectedItem();
        loadWindow("/view/danhsachphong/suaphong/suaphong.fxml", "Sửa phòng");

    }


    @FXML
    void xoaPhong(ActionEvent event) throws SQLException {
        selected = tablePhong.getSelectionModel().getSelectedItem();
        phongObservableList.remove(selected);
        phongDAOimpl.delete(selected);
    }

   @FXML
   void capNhatDSPhong(ActionEvent event) throws SQLException {
        list = phongDAOimpl.findAll();
        initialize(location, resources);
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phongObservableList = FXCollections.observableArrayList(list);

        slToiDaP.setCellValueFactory(new PropertyValueFactory<Phong, Integer>("SLToiDa"));
        slDangOP.setCellValueFactory(new PropertyValueFactory<Phong, Integer>("SLDangO"));
        maPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("maP"));
        maKhu.setCellValueFactory(new PropertyValueFactory<Phong, String>("maKhu"));
        tangSo.setCellValueFactory(new PropertyValueFactory<Phong, Integer>("tang"));
        loaiPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("loaiP"));
        tenPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("tenP"));
        tinhTrangPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("tinhTrangPhong"));
        tablePhong.setItems(phongObservableList);


        sttPhong.setCellValueFactory(new Callback<CellDataFeatures<Phong, String>, ObservableValue<String>>() {
             @Override public ObservableValue<String> call(CellDataFeatures<Phong, String> p) {
                return new ReadOnlyObjectWrapper(tablePhong.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttPhong.setSortable(false);

    }
}
