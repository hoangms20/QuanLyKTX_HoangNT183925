package view.danhsachphong.themphong;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DichVuPhongDAOimpl;
import dao_impl.DienNuocDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

public class ThemPhongController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tPhongMaKhuNha;

    @FXML
    private TextField tTenPhong;

    @FXML
    private TextField tPhongTang;

    @FXML
    private ComboBox<String> tLoaiPhong;

    @FXML
    private TextField tMaPhong;

    @FXML
    private TextField tSLToiDa;

    @FXML
    void xacNhanThemPhong(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        PhongDAOimpl phongimpl = new PhongDAOimpl();
        Phong phong = new Phong();
        phong.setMaP(tMaPhong.getText());
        phong.setTenP(tTenPhong.getText());
        phong.setMaKhu(tPhongMaKhuNha.getText());
        phong.setTang(Integer.parseInt(tPhongTang.getText()));
        phong.setSLToiDa(Integer.parseInt(tSLToiDa.getText()));
        phong.setSLDangO(0);
        phong.setLoaiP(tLoaiPhong.getValue());
        phong.setTinhTrangPhong("Còn trống");
        DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();
        DienNuoc dienNuoc = dienNuocDAOimpl.findDateGanNhat();
        LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        List<LoaiHoaDon> loaiHoaDonList = loaiHoaDonDAOimpl.findAll();

        if (phongimpl.insert(phong) == true){
            if(dienNuoc == null){
                dienNuoc.setThangNamGhiSoDN(Date.valueOf("2020-01-01"));
                dienNuoc.setMaP(tMaPhong.getText());
                dienNuoc.setMaLoaiHD("LHD01");
                dienNuoc.setChiSoCuDN(0);
                dienNuoc.setChiSoMoiDN(0);
                dienNuoc.setThanhTien(0);
                dienNuocDAOimpl.insert(dienNuoc);
                dienNuoc.setMaLoaiHD("LHD02");
                dienNuocDAOimpl.insert(dienNuoc);
            }else {
                dienNuoc.setMaP(tMaPhong.getText());
                dienNuoc.setMaLoaiHD("LHD01");
                dienNuoc.setChiSoCuDN(0);
                dienNuoc.setChiSoMoiDN(0);
                dienNuoc.setThanhTien(0);
                dienNuocDAOimpl.insert(dienNuoc);
                dienNuoc.setMaLoaiHD("LHD02");
                dienNuocDAOimpl.insert(dienNuoc);
            }
            DichVuPhong dichVuPhong = new DichVuPhong();
            dichVuPhong.setMaP(phong.getMaP());
            dichVuPhong.setSoLuongSuDung(0);
            dichVuPhong.setThanhTienDV(0);
            DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
            for (LoaiHoaDon l: loaiHoaDonList) {
                if(!l.getMaLoaiHD().equals("LHD01") && !l.getMaLoaiHD().equals("LHD02")){
                    dichVuPhong.setMaLoaiHD(l.getMaLoaiHD());
                    dichVuPhongDAOimpl.insert(dichVuPhong);
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm phòng thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm phòng thất bại");
            alert.showAndWait();
        }

    }

    ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nữ", "Cả nam và nữ");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tLoaiPhong.setItems(list);
    }
}
