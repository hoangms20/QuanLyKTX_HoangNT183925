package view.loaidichvu;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.LoaiHoaDonDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import model.KhuNha;
import model.LoaiHoaDon;
import model.MyConnection;
import model.Vien;

public class LoaiDichVuController implements Initializable {

    MyConnection myConnection = new MyConnection();
    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
    ObservableList<LoaiHoaDon> loaiHoaDonObservableList;
    List<LoaiHoaDon> list;
    public static LoaiHoaDon selected;


    {
        try {
            myConnection.connectDB();
            list = loaiHoaDonDAOimpl.findAll();
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
    private TableView<LoaiHoaDon> tableDV;

    @FXML
    private TableColumn<LoaiHoaDon, Integer> mucHoTroDV;

    @FXML
    private TableColumn<LoaiHoaDon, String> maDV;

    @FXML
    private TableColumn<LoaiHoaDon, Integer> tienVuotDMDV;

    @FXML
    private TableColumn<LoaiHoaDon, CheckBox> batBuocDV;

    @FXML
    private TableColumn<LoaiHoaDon, Integer> soTienThuDV;

    @FXML
    private TableColumn<LoaiHoaDon, String> tenDV;

    @FXML
    private TableColumn<LoaiHoaDon, String> sttDV;

    @FXML
    private TextField tkDV;

    @FXML
    void suaDV(ActionEvent event) {
        selected = tableDV.getSelectionModel().getSelectedItem();
        loadWindow("/view/loaidichvu/sualoaidichvu/sualoaidichvu.fxml", "Sửa phòng");
    }

    @FXML
    void timKiemDV(ActionEvent event) throws SQLException {
        list = loaiHoaDonDAOimpl.findLikeByPK(tkDV.getText());
        initialize(location, resources);

    }


    @FXML
    void capNhatDV(ActionEvent event) throws SQLException {
        list = loaiHoaDonDAOimpl.findAll();
        setTableDV();

    }

    @FXML
    void themDV(ActionEvent event) {
        loadWindow("/view/loaidichvu/themloaidichvu/themloaidichvu.fxml", "Thêm loại dịch vụ");

    }


    @FXML
    void xoaDV(ActionEvent event) throws SQLException {
        selected = tableDV.getSelectionModel().getSelectedItem();
        loaiHoaDonObservableList.remove(selected);
        loaiHoaDonDAOimpl.delete(selected);

    }

    public void setTableDV(){
        loaiHoaDonObservableList = FXCollections.observableArrayList(list);

        maDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, String>("maLoaiHD"));
        tenDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, String>("tenLoaiHD"));
        soTienThuDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, Integer>("giaTri"));
        tienVuotDMDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, Integer>("tienVuotDM"));
        mucHoTroDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, Integer>("mucHoTro"));
        batBuocDV.setCellValueFactory(new PropertyValueFactory<LoaiHoaDon, CheckBox>("cbBatBuoc"));

        tableDV.setItems(loaiHoaDonObservableList);

        sttDV.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoaiHoaDon, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<LoaiHoaDon, String> p) {
                return new ReadOnlyObjectWrapper(tableDV.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttDV.setSortable(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableDV();
    }
}
