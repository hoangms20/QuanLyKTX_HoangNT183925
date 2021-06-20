package view.thongketoanbo;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.KhuNhaDAOimpl;
import dao_impl.PhongDAOimpl;
import dao_impl.SinhVienDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.KhuNha;
import model.MyConnection;
import model.Phong;
import model.SinhVien;

public class ThongKeToanBoController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label soKhu;

    @FXML
    private Label slConTrong;

    @FXML
    private Label slSV;

    @FXML
    private Label soPhong;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
        KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();
        SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
        List<Phong> phongList = null;
        List<KhuNha> khuNhaList = null;
        List<SinhVien> sinhVienList = null;
        Integer a = 0;
        try {
            myConnection.connectDB();
            phongList = phongDAOimpl.findAll();
            khuNhaList = khuNhaDAOimpl.findAll();
            sinhVienList = sinhVienDAOimpl.findAllPhong();
            a = phongDAOimpl.countSLConTrong();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        soKhu.setText( " Số khu nhà: " + khuNhaList.size());
        soPhong.setText(" Số phòng: " + phongList.size());
        slSV.setText(" Số lượng sinh viên: " + sinhVienList.size());
        slConTrong.setText(" Số vị trí còn trống: " + a);
    }
}
