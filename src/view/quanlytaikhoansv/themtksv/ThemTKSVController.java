package view.quanlytaikhoansv.themtksv;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.NguoiDungDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyConnection;
import model.NguoiDung;

public class ThemTKSVController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane rootPaneDMK;

    @FXML
    private TextField tTenDangNhap;

    @FXML
    private TextField tHoTen;

    @FXML
    private PasswordField tMatKhau;

    @FXML
    private PasswordField xacNhanMK;

    void cancel() {
        Stage stage = (Stage) rootPaneDMK.getScene().getWindow();
        stage.close();
    }

    @FXML
    void xacNhan(ActionEvent event) throws SQLException {
        MyConnection myConnection = new MyConnection();
        myConnection.connectDB();
        NguoiDungDAOimpl nguoiDungDAOimpl = new NguoiDungDAOimpl();
        NguoiDung nguoiDung = new NguoiDung();
        if(tMatKhau.getText().equals(xacNhanMK.getText())){
            nguoiDung.setMatKhau(tMatKhau.getText());
            nguoiDung.setNhomNguoiDung("Sinh viên");
            nguoiDung.setTenDangNhap(tTenDangNhap.getText());
            nguoiDung.setHoTen(tHoTen.getText());
            if(nguoiDungDAOimpl.insert(nguoiDung) == true){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Thêm Thành Công");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Thêm Thất Bại");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bạn Xác Nhận Sai Mật Khẩu");
            alert.showAndWait();
        }

    }

    @FXML
    void huy(ActionEvent event) {
        cancel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
