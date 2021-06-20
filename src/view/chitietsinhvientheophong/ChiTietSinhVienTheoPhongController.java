package view.chitietsinhvientheophong;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.KhuNhaDAOimpl;
import dao_impl.PhongDAOimpl;
import dao_impl.SinhVienDAOimpl;
import dao_impl.ThuePhongDAOimpl;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.KhuNha;
import model.Phong;
import model.SinhVien;
import model.ThuePhong;
import view.chitietsinhvien.ChiTietSinhVienController;
import view.xepphong.XepPhongController;

public class ChiTietSinhVienTheoPhongController implements Initializable {

    ThuePhongDAOimpl thuePhongDAOimpl = new ThuePhongDAOimpl();
    List<SinhVien> sinhVienList;
    ObservableList<SinhVien> sinhVienObservableList;

    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    Phong phong = XepPhongController.selectedPhong;

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
    private TableColumn<SinhVien, String> mssvSVOKTX;

    @FXML
    private Label slDangO;

    @FXML
    private TableColumn<SinhVien, String> ngayktSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> tenSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> sttSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhSVOKTX;

    @FXML
    private Label tenKhu;

    @FXML
    private TableView<SinhVien> tableSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> queQuanSVOKTX;

    @FXML
    private Label tenPhong;

    @FXML
    private TableColumn<SinhVien, String> maLopSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> maKhoaSVOKTX;

    @FXML
    private Label slToiDa;

    @FXML
    private TableColumn<SinhVien, String> ngaybdSVOKTX;

    @FXML
    private TableColumn<SinhVien, Date> ngaySinhSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> cmndSVOKTX;


    @FXML
    void chuyenRa(ActionEvent event) throws SQLException {
        PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
        SinhVien sinhVien = tableSVOKTX.getSelectionModel().getSelectedItem();
        sinhVienObservableList.remove(sinhVien);
        sinhVien.setMaTrangThai(1);
        sinhVienDAOimpl.update(sinhVien);
        phong.setSLDangO(sinhVienObservableList.size());
        phongDAOimpl.update(phong);
        initialize(location, resources);

    }

    ThuePhong thuePhong;
    public void setTableViewSVOKTX(){
        sinhVienObservableList = FXCollections.observableArrayList(sinhVienList);

        ngaySinhSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngaySinh"));
        mssvSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        tenSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        cmndSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("CMND"));
        gioiTinhSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));
        queQuanSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("queQuan"));
        maLopSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maLop"));
        maKhoaSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maKhoa"));

        tableSVOKTX.setItems(sinhVienObservableList);

        ngaybdSVOKTX.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        thuePhong = thuePhongDAOimpl.findByMSSV(sinhVien.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(thuePhong.getNgayBatDau().toString());
                }
        );

        ngayktSVOKTX.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        thuePhong = thuePhongDAOimpl.findByMSSV(sinhVien.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(thuePhong.getNgayKetThuc().toString());
                }
        );

        sttSVOKTX.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SinhVien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SinhVien, String> p) {
                return new ReadOnlyObjectWrapper(tableSVOKTX.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttSVOKTX.setSortable(false);

        tableSVOKTX.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableSVOKTX.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });
    }


    KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();
    KhuNha khuNha;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ThuePhong thuePhong1 = thuePhongDAOimpl.findNgayKetThucGanNhat();
            sinhVienList = sinhVienDAOimpl.findByPhong(phong.getTenP(), thuePhong1);
            khuNha = khuNhaDAOimpl.findByPK(phong.getMaKhu());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tenPhong.setText(phong.getTenP());
        tenKhu.setText(khuNha.getTenKhu());
        slToiDa.setText("Số lượng tối đa: " + phong.getSLToiDa());
        slDangO.setText("Số lượng đang ở: " + phong.getSLDangO());
        setTableViewSVOKTX();

    }
}

