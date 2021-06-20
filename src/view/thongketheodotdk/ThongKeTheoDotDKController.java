package view.thongketheodotdk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DonXinRaDAOimpl;
import dao_impl.DotDangKyDAOimpl;
import dao_impl.SinhVienDotDKDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.DonXinRa;
import model.DotDangKy;
import model.MyConnection;
import model.SinhVienDotDK;

public class ThongKeTheoDotDKController implements Initializable {
    MyConnection myConnection = new MyConnection();

    DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();
    List<DotDangKy> dotDangKyList;

    {
        try {
            myConnection.connectDB();
            dotDangKyList = dotDangKyDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label slDangKy;

    @FXML
    private ComboBox<String> dotDK;

    @FXML
    private Label slRoiKhoi;

    @FXML
    void capNhat(ActionEvent event) throws SQLException {
        DotDangKy dotDangKy = dotDangKyDAOimpl.findByPK(dotDK.getValue());
        SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
        List<SinhVienDotDK> sinhVienDotDKList = sinhVienDotDKDAOimpl.findByDotDK(dotDK.getValue());
        slDangKy.setText(" Số lượng đăng ký: " + sinhVienDotDKList.size());
        DonXinRaDAOimpl donXinRaDAOimpl = new DonXinRaDAOimpl();
        List<DonXinRa> donXinRaList = donXinRaDAOimpl.findByDotVaXetDuyet(dotDangKy);
        slRoiKhoi.setText(" Số lượng rời khỏi: " + donXinRaList.size());
    }

    List<String> stringList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DotDangKy d: dotDangKyList) {
            stringList.add(d.getMaDotDK());
        }
        ObservableList<String> listMaDotDK = FXCollections.observableArrayList(stringList);
        dotDK.setItems(listMaDotDK);
    }
}
