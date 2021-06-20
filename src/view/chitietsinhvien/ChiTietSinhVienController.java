package view.chitietsinhvien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.*;

public class ChiTietSinhVienController implements Initializable {

    public static SinhVien selectedSinhVien;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lopVaKhoa;

    @FXML
    private Label SDTVaEmail;

    @FXML
    private Label ngayRoiKhoi;

    @FXML
    private Label ghiChuSV;

    @FXML
    private Label mucDoUTVaTrangThai;

    @FXML
    private Label ngayDangKy;

    @FXML
    private Label vien;

    @FXML
    private Label gioiTinhVaNgaySinh;

    @FXML
    private Label hoTenVaMSSV;

    @FXML
    private Label CMND;

    @FXML
    private Label queQuan;

    KhoaDAOimpl khoaDAOimpl = new KhoaDAOimpl();
    LopDAOimpl lopDAOimpl = new LopDAOimpl();
    VienDAOimpl vienDAOimpl = new VienDAOimpl();
    UuTienDAOimpl uuTienDAOimpl = new UuTienDAOimpl();
    TrangThaiSVDAOimpl trangThaiSVDAOimpl = new TrangThaiSVDAOimpl();

    public void setInformation() throws SQLException {
        hoTenVaMSSV.setText(selectedSinhVien.getTenSV() + "        MSSV: " + selectedSinhVien.getMSSV());
        Khoa khoa = khoaDAOimpl.findByPK(selectedSinhVien.getMaKhoa());
        Lop lop = lopDAOimpl.findByPK(selectedSinhVien.getMaLop());
        lopVaKhoa.setText("Lớp: " + lop.getTenLop() + "       " + khoa.getTenKhoa());
        Vien v = vienDAOimpl.findByPK(lop.getMaVien());
        vien.setText("Viện: " + v.getTenVien());
        UuTien uuTien = uuTienDAOimpl.findByPK(selectedSinhVien.getMaUuTien());
        TrangThaiSV trangThaiSV = trangThaiSVDAOimpl.findByPK(selectedSinhVien.getMaTrangThai());
        mucDoUTVaTrangThai.setText("Mức độ ưu tiên: " + uuTien.getMucDoUuTien() + "       Trạng thái: " + trangThaiSV.getTenTrangThai());
        gioiTinhVaNgaySinh.setText("Giới tính: " + selectedSinhVien.getGioiTinh() + "             Ngày sinh: " + selectedSinhVien.getNgaySinh());
        CMND.setText("CMND: " + selectedSinhVien.getCMND());
        SDTVaEmail.setText("SDT: " + selectedSinhVien.getSDT() + "     Email: " + selectedSinhVien.getEmail());
        queQuan.setText("Quê quán: " + selectedSinhVien.getQueQuan());
        ngayDangKy.setText("Ngày đăng ký: " + selectedSinhVien.getNgayDangKy());
        if (selectedSinhVien.getNgayRoiKhoi() == null){
            ngayRoiKhoi.setText("Ngày rời khỏi: ");
        }else {
            ngayRoiKhoi.setText("Ngày rời khỏi: " + selectedSinhVien.getNgayRoiKhoi());
        }

        if (selectedSinhVien.getGhiChuSV() == null){
            ghiChuSV.setText("Ghi chú: ");
        }else {
            ghiChuSV.setText("Ghi chú: " + selectedSinhVien.getGhiChuSV());
        }


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setInformation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
