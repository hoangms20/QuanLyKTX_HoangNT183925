package view.chitiethoadon;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DichVuPhongDAOimpl;
import dao_impl.DienNuocDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.*;
import view.hoadon.HoaDonController;

public class ChiTietHoaDonController implements Initializable {
    HoaDon hoaDon = HoaDonController.selectedHoaDon;
    DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
    DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();
    List<DichVuPhong> dichVuPhongList;
    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label DV;

    @FXML
    private Label chiTietHD;

    @FXML
    private Label DN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dichVuPhongList = dichVuPhongDAOimpl.findLikeByPK(hoaDon.getMaP());
            DienNuoc dienNuoc1 = dienNuocDAOimpl.findByPK((Date) hoaDon.getNgayLap(), "LHD01", hoaDon.getMaP());
            DienNuoc dienNuoc2 = dienNuocDAOimpl.findByPK((Date) hoaDon.getNgayLap(), "LHD02", hoaDon.getMaP());
            DN.setText("Điện:\n -Chỉ số cũ: " + dienNuoc1.getChiSoCuDN() + ", Chỉ số mới: " + dienNuoc1.getChiSoMoiDN() + ", Số lượng tiêu thụ: " + dienNuoc1.getSLTieuThu() + ", Thành tiền: " + dienNuoc1.getThanhTien()
            + "\nNước:\n -Chỉ số cũ: " + dienNuoc2.getChiSoCuDN() + ", Chỉ số mới: " + dienNuoc2.getChiSoMoiDN() + ", Số lượng tiêu thụ: " + dienNuoc2.getSLTieuThu() + ", Thành tiền: " + dienNuoc2.getThanhTien() );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        chiTietHD.setText("Hóa đơn phòng " + hoaDon.getMaP() + ", ngày tạo: " + hoaDon.getNgayLap().toString());
        String s = "";
        for (DichVuPhong d: dichVuPhongList) {
            try {
                LoaiHoaDon loaiHoaDon = loaiHoaDonDAOimpl.findByPK(d.getMaLoaiHD());
                s = s + "\n" + loaiHoaDon.getTenLoaiHD() + ": giá: " + loaiHoaDon.getGiaTri() + ", Số lượng sử dụng: " + d.getSoLuongSuDung() + ", Thành tiền: " + d.getThanhTienDV();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        DV.setText(s);
    }
}
