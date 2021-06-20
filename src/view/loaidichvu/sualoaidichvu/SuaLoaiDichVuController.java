package view.loaidichvu.sualoaidichvu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.LoaiHoaDonDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.LoaiHoaDon;
import model.MyConnection;
import model.Phong;
import view.loaidichvu.LoaiDichVuController;

public class SuaLoaiDichVuController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField suaSoTienVuotDMDV;

    @FXML
    private TextField suaTenDV;

    @FXML
    private TextField suaSoTienThuDV;

    @FXML
    private TextField suaMucHoTroDV;

    @FXML
    private CheckBox suaBatBuocDV;

    @FXML
    private Label suaMaDV;

    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl;
    @FXML
    void xacNhanSuaDV(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        LoaiHoaDon loaiHoaDon = LoaiDichVuController.selected;
        loaiHoaDon.setTenLoaiHD(suaTenDV.getText());
        loaiHoaDon.setGiaTri(Integer.parseInt(suaSoTienThuDV.getText()));
        loaiHoaDon.setTienVuotDM(Integer.parseInt(suaSoTienVuotDMDV.getText()));
        loaiHoaDon.setMucHoTro(Integer.parseInt(suaMucHoTroDV.getText()));
        if (suaBatBuocDV.isSelected()){
            loaiHoaDon.setBatBuoc(1);
        }else {
            loaiHoaDon.setBatBuoc(0);
        }

        if (loaiHoaDonDAOimpl.update(loaiHoaDon) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sửa dịch vụ thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sửa dịch vụ thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoaiHoaDon loaiHoaDon = LoaiDichVuController.selected;
        suaMaDV.setText(loaiHoaDon.getMaLoaiHD());
        suaTenDV.setText(loaiHoaDon.getTenLoaiHD());
        suaSoTienThuDV.setText(String.valueOf(loaiHoaDon.getGiaTri()));
        suaSoTienVuotDMDV.setText(String.valueOf(loaiHoaDon.getTienVuotDM()));
        suaMucHoTroDV.setText(String.valueOf(loaiHoaDon.getMucHoTro()));
        if(loaiHoaDon.getBatBuoc() == 1){
            suaBatBuocDV.setSelected(true);
        }
    }
}
