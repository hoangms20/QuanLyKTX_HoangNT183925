package view.thongkehoadon;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DienNuocDAOimpl;
import dao_impl.HoaDonDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.DienNuoc;
import model.HoaDon;
import model.MyConnection;

public class ThongKeHoaDonController implements Initializable {
    MyConnection myConnection = new MyConnection();
    List<DienNuoc> dienNuocDate;
    DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();

    {
        try {
            myConnection.connectDB();
            dienNuocDate = dienNuocDAOimpl.findAllDate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tongHoaDon;

    @FXML
    private Label slChuaThanhToan;

    @FXML
    private Label slDaThanhToan;

    @FXML
    private ComboBox<String> ngayTao;

    @FXML
    void capNhat(ActionEvent event) throws SQLException {
        HoaDonDAOimpl hoaDonDAOimpl = new HoaDonDAOimpl();
        List<HoaDon> hoaDonList = hoaDonDAOimpl.findAllByNgayLap(Date.valueOf(ngayTao.getValue()));
        tongHoaDon.setText("Tổng số hóa đơn: " + hoaDonList.size());
        Integer a = 0, b = 0;
        for (HoaDon h: hoaDonList) {
            if(h.getTinhTrangHD().equals("Đã thanh toán")){
                a++;
            }else {
                b++;
            }
        }
        slDaThanhToan.setText(a + " Đã thanh toán");
        slChuaThanhToan.setText(b + " Chưa thanh toán");
    }

    List<String> stringList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DienNuoc d: dienNuocDate) {
            stringList.add(d.getThangNamGhiSoDN().toString());
        }
        ObservableList<String> listNgayTao = FXCollections.observableArrayList(stringList);
        ngayTao.setItems(listNgayTao);

    }
}
