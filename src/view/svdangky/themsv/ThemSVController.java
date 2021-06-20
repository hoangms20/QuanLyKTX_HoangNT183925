package view.svdangky.themsv;

import dao_impl.SinhVienDAOimpl;
import dao_impl.SinhVienDotDKDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.DotDangKy;
import model.MyConnection;
import model.SinhVien;
import model.SinhVienDotDK;
import view.svdangky.SVDangKyController;

import java.sql.Date;

public class ThemSVController implements Initializable{
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tML;

    @FXML
    private TextField tMK;

    @FXML
    private DatePicker tNgaySinh;

    @FXML
    private TextField tQueQuan;

    @FXML
    private TextField tCMND;

    @FXML
    private TextField tHVT;

    @FXML
    private ComboBox<String> tcBGioiTinh;

    @FXML
    private TextField tEmail;

    @FXML
    private TextField tSDT;

    @FXML
    private DatePicker tNgayDK;

    @FXML
    private TextField tMSSV;

    @FXML
    private TextField tMUT;

    @FXML
    void xacNhanThemSV(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
        SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
        SinhVien sinhVien = new SinhVien();
        sinhVien.setMSSV(tMSSV.getText());
        sinhVien.setTenSV(tHVT.getText());
        sinhVien.setCMND(tCMND.getText());
        sinhVien.setMaUuTien(tMUT.getText());
        sinhVien.setMaKhoa(tMK.getText());
        sinhVien.setMaLop(tML.getText());
        sinhVien.setMaTrangThai(0);
        sinhVien.setNgayDangKy(Date.valueOf(tNgayDK.getValue()));
        sinhVien.setEmail(tEmail.getText());
        sinhVien.setSDT(tSDT.getText());
        sinhVien.setNgaySinh(Date.valueOf(tNgaySinh.getValue()));
        sinhVien.setQueQuan(tQueQuan.getText());
        sinhVien.setGhiChuSV(null);
        sinhVien.setNgayRoiKhoi(null);
        sinhVien.setGioiTinh(tcBGioiTinh.getValue());

        SinhVienDotDK sinhVienDotDK = new SinhVienDotDK();
        sinhVienDotDK.setMSSV(tMSSV.getText());
        sinhVienDotDK.setMaDotDK(SVDangKyController.selectedDotDangKy.getMaDotDK());

        if (sinhVienDAOimpl.insert(sinhVien) == true ){
            sinhVienDotDKDAOimpl.insert(sinhVienDotDK);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Đăng ký sinh viên thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Đăng ký sinh viên thất bại");
            alert.showAndWait();
        }

    }


    ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nữ");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcBGioiTinh.setItems(list);
    }
}

