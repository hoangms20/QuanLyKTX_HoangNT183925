package view.quanlytaikhoansv;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.NguoiDungDAOimpl;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.KhoanThuSV;
import model.MyConnection;
import model.NguoiDung;

public class QuanLyTaiKhoanSVController implements Initializable {
    MyConnection myConnection = new MyConnection();
    NguoiDungDAOimpl nguoiDungDAOimpl = new NguoiDungDAOimpl();
    ObservableList<NguoiDung> nguoiDungObservableList;
    List<NguoiDung> nguoiDungList;

    {
        try {
            myConnection.connectDB();
            nguoiDungList = nguoiDungDAOimpl.findNhomNguoiDung("Sinh viên");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<NguoiDung> tableTKSV;

    @FXML
    private TableColumn<NguoiDung, String> stt;

    @FXML
    private TableColumn<NguoiDung, String> matKhau;

    @FXML
    private TableColumn<NguoiDung, String> tenDangNhap;

    @FXML
    private TableColumn<NguoiDung, String> hoTen;

    @FXML
    private TextField tkSV;

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
    void themTKSV(ActionEvent event) {
        loadWindow("/view/quanlytaikhoansv/themtksv/themtksv.fxml", "Thêm tài khoản sinh viên");

    }


    @FXML
    void xoaTKSV(ActionEvent event) throws SQLException {
        NguoiDung nguoiDung = tableTKSV.getSelectionModel().getSelectedItem();
        nguoiDungDAOimpl.delete(nguoiDung);

    }

    @FXML
    void timKiemSV(ActionEvent event) throws SQLException {
        nguoiDungList = nguoiDungDAOimpl.findLikeByPK(tkSV.getText());
        setTableTKSV();
    }

    @FXML
    void capNhatDSTK(ActionEvent event) throws SQLException {
        nguoiDungList = nguoiDungDAOimpl.findNhomNguoiDung("Sinh viên");
        setTableTKSV();

    }

    public void setTableTKSV(){
        nguoiDungObservableList = FXCollections.observableArrayList(nguoiDungList);

        hoTen.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("hoTen"));
        tenDangNhap.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("tenDangNhap"));
        matKhau.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("matKhau"));

        tableTKSV.setItems(nguoiDungObservableList);

        stt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NguoiDung, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<NguoiDung, String> p) {
                return new ReadOnlyObjectWrapper(tableTKSV.getItems().indexOf(p.getValue()) + "");
            }
        });
        stt.setSortable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableTKSV();

    }
}
