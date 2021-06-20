package view.dotdk.themdotdk;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;

import dao_impl.DotDangKyDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DotDangKy;
import model.MyConnection;
import view.dotdk.DotDKController;

public class ThemDotDKController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker tNgaybdDotDK;

    @FXML
    private Label slConTrongKTX;

    @FXML
    private DatePicker tNgayktDotDK;

    @FXML
    private TextField tMaDotDK;

    @FXML
    private TextField chiTieuKTX;

    @FXML
    private TextField tTenLDotDK;

    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();

    @FXML
    void xacNhanThemDotDK(ActionEvent event) throws SQLException {

        myConnection.connectDB();
        DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();
        DotDangKy dotDangKy = new DotDangKy();
        dotDangKy.setMaDotDK(tMaDotDK.getText());
        dotDangKy.setTenDotDK(tTenLDotDK.getText());
        dotDangKy.setChiTieu(Integer.parseInt(chiTieuKTX.getText()));;
        dotDangKy.setNgayBatDauDK(Date.valueOf(tNgaybdDotDK.getValue()));
        dotDangKy.setNgayKetThucDK(Date.valueOf(tNgayktDotDK.getValue()));
        dotDangKy.setSLDangKi(0);

        if (Integer.parseInt(slConTrongKTX.getText()) >= Integer.parseInt(chiTieuKTX.getText())){
            if((dotDangKyDAOimpl.insert(dotDangKy) == true)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Thêm đợt đăng ký thành công");
                alert.showAndWait();
                DotDKController.dangKyObservableList.add(dotDangKy);
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Thêm đợt đăng ký thất bại");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Chỉ tiêu phải nhỏ hơn hoặc bằng số lượng còn trống");
            alert.showAndWait();
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            myConnection.connectDB();
            slConTrongKTX.setText(String.valueOf(phongDAOimpl.countSLConTrong()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

