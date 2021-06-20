package view.loaidichvu.themloaidichvu;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DichVuPhongDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.DichVuPhong;
import model.LoaiHoaDon;
import model.MyConnection;
import model.Phong;

public class ThemLoaiDichVuController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tSoTienThuDV;

    @FXML
    private TextField tSoTienVuotDMDV;

    @FXML
    private TextField tMucHoTroDV;

    @FXML
    private TextField tTenDV;

    @FXML
    private TextField tMaDV;

    @FXML
    private CheckBox tBatBuocDV;

    @FXML
    void xacNhanThemDV(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        LoaiHoaDon loaiHoaDon = new LoaiHoaDon();
        loaiHoaDon.setMaLoaiHD(tMaDV.getText());
        loaiHoaDon.setTenLoaiHD(tTenDV.getText());
        loaiHoaDon.setGiaTri(Integer.parseInt(tSoTienThuDV.getText()));
        loaiHoaDon.setTienVuotDM(Integer.parseInt(tSoTienVuotDMDV.getText()));
        loaiHoaDon.setMucHoTro(Integer.parseInt(tMucHoTroDV.getText()));
        if(tBatBuocDV.isSelected()){
            loaiHoaDon.setBatBuoc(1);
        }else {
            loaiHoaDon.setBatBuoc(0);
        }

        if (loaiHoaDonDAOimpl.insert(loaiHoaDon) == true){
            PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
            List<Phong> phongList = phongDAOimpl.findAll();
            DichVuPhong dichVuPhong = new DichVuPhong();
            DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
            dichVuPhong.setMaLoaiHD(loaiHoaDon.getMaLoaiHD());
            dichVuPhong.setSoLuongSuDung(0);
            dichVuPhong.setThanhTienDV(0);
            for (Phong p: phongList) {
                dichVuPhong.setMaP(p.getMaP());
                dichVuPhongDAOimpl.insert(dichVuPhong);

            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm loại dịch vụ thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm loại dịch vụ thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
