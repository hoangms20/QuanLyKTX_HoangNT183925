package view.xepphong;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import view.chitietsinhvien.ChiTietSinhVienController;

public class XepPhongController implements Initializable {
    MyConnection myConnection = new MyConnection();
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    ObservableList<Phong> phongObservableList;
    List<Phong> listPhong;
    ThuePhongDAOimpl thuePhongDAOimpl = new ThuePhongDAOimpl();
    ObservableList<SinhVien> sinhVienObservableList;
    List<SinhVien> sinhVienList;


    {
        try {
            myConnection.connectDB();
            listPhong = phongDAOimpl.findAll();
            sinhVienList = sinhVienDAOimpl.findAllDaDuyet();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Phong selectedPhong;
    public static SinhVien selectedSinhVien;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker ngayKTThue;

    @FXML
    private TableView<SinhVien> tableViewDSChoXepPhong;

    @FXML
    private TableColumn<SinhVien, String> mucDoUuTienDSCD;

    @FXML
    private TableColumn<Phong, String> maKhuNha;

    @FXML
    private TableColumn<Phong, Integer> slDangO;

    @FXML
    private TableColumn<Phong, String> loaiPhong;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhDSCD;

    @FXML
    private TableColumn<Phong, String> tinhTrangPhong;

    @FXML
    private TableColumn<SinhVien, Date> ngayDKDSCD;

    @FXML
    private TableColumn<SinhVien, String> mssvDSCD;

    @FXML
    private TableColumn<SinhVien, String> tenSVDSCD;

    @FXML
    private DatePicker ngayBDThue;

    @FXML
    private TableView<Phong> tablePhong;

    @FXML
    private TableColumn<Phong, String> maPhong;

    @FXML
    private TableColumn<Phong, Integer> slToiDa;

    @FXML
    private TableColumn<SinhVien, CheckBox> checkSVDuyetVao;

    @FXML
    void giaHanThemSV(ActionEvent event) throws SQLException {
        List<SinhVien> sinhVienList1 = sinhVienDAOimpl.findAllPhong();
        for (SinhVien s: sinhVienList1) {
            ThuePhong thuePhong = new ThuePhong();
            ThuePhong thuePhong1 = thuePhongDAOimpl.findByMSSV(s.getMSSV());
            thuePhong.setMSSV(s.getMSSV());
            thuePhong.setMaP(thuePhong1.getMaP());
            thuePhong.setNgayBatDau(Date.valueOf(ngayBDThue.getValue()));
            thuePhong.setNgayKetThuc(Date.valueOf(ngayKTThue.getValue()));
            thuePhong.setMaThue(s.getMSSV() + thuePhong1.getMaP() + "." + thuePhong.getNgayBatDau().toString() + "." + thuePhong.getNgayKetThuc().toString());
            thuePhongDAOimpl.insert(thuePhong);

        }

    }

    @FXML
    void capNhat(ActionEvent event) throws SQLException {
        listPhong = phongDAOimpl.findAll();
        sinhVienList = sinhVienDAOimpl.findAllDaDuyet();
        setTablePhong();
        setTableViewDSChoXepPhong();
    }

    @FXML
    void xepSVVao(ActionEvent event) throws SQLException {
        selectedPhong = tablePhong.getSelectionModel().getSelectedItem();
        for (SinhVien s: sinhVienList) {
            if(s.getSelect().isSelected()){
                s.setMaTrangThai(2);
                sinhVienDAOimpl.update(s);
                ThuePhong thuePhong = new ThuePhong();
                thuePhong.setMSSV(s.getMSSV());
                thuePhong.setMaP(selectedPhong.getMaP());
                thuePhong.setNgayBatDau(Date.valueOf(ngayBDThue.getValue()));
                thuePhong.setNgayKetThuc(Date.valueOf(ngayKTThue.getValue()));
                thuePhong.setMaThue(s.getMSSV() + selectedPhong.getMaP() + "." + thuePhong.getNgayBatDau().toString() + "." + thuePhong.getNgayKetThuc().toString());
                thuePhongDAOimpl.insert(thuePhong);
                sinhVienObservableList.remove(s);
                selectedPhong.setSLDangO(selectedPhong.getSLDangO() + 1);
                if(selectedPhong.getSLDangO() == selectedPhong.getSLToiDa()){
                    selectedPhong.setTinhTrangPhong("Đã đầy");
                }
                phongDAOimpl.update(selectedPhong);
            }
        }

    }


    SinhVienDotDK sinhVienDotDK;
    SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
    @FXML
    void xoaSVChoDuyet(ActionEvent event) throws SQLException {
        selectedSinhVien = tableViewDSChoXepPhong.getSelectionModel().getSelectedItem();
        sinhVienObservableList.remove(selectedSinhVien);
        sinhVienDotDK = sinhVienDotDKDAOimpl.findByMSSV(selectedSinhVien.getMSSV());
        sinhVienDotDKDAOimpl.delete(sinhVienDotDK);
        sinhVienDAOimpl.delete(selectedSinhVien);

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

    UuTienDAOimpl uuTienDAOimpl;
    UuTien uuTien;
    public void setTableViewDSChoXepPhong(){
        sinhVienObservableList = FXCollections.observableArrayList(sinhVienList);

        ngayDKDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngayDangKy"));
        checkSVDuyetVao.setCellValueFactory(new PropertyValueFactory<SinhVien, CheckBox>("select"));
        mssvDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        tenSVDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        gioiTinhDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));

        tableViewDSChoXepPhong.setItems(sinhVienObservableList);

        uuTienDAOimpl = new UuTienDAOimpl();
        mucDoUuTienDSCD.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        uuTien = uuTienDAOimpl.findByPK(sinhVien.getMaUuTien());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(uuTien.getMucDoUuTien()));
                }
        );

        tableViewDSChoXepPhong.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableViewDSChoXepPhong.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }

    public void setTablePhong(){
        phongObservableList = FXCollections.observableArrayList(listPhong);

        slToiDa.setCellValueFactory(new PropertyValueFactory<Phong, Integer>("SLToiDa"));
        slDangO.setCellValueFactory(new PropertyValueFactory<Phong, Integer>("SLDangO"));
        maPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("maP"));
        maKhuNha.setCellValueFactory(new PropertyValueFactory<Phong, String>("maKhu"));
        loaiPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("loaiP"));
        tinhTrangPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("tinhTrangPhong"));
        tablePhong.setItems(phongObservableList);

        tablePhong.setRowFactory( tv -> {
            TableRow<Phong> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Phong rowData = row.getItem();
                    selectedPhong = tablePhong.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvientheophong/chitietsinhvientheophong.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTablePhong();
        setTableViewDSChoXepPhong();
    }
}
