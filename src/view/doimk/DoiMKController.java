package view.doimk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.NguoiDungDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.NguoiDung;
import view.login.LoginController;

public class DoiMKController implements Initializable {

    void loadWindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPaneDMK.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane rootPaneDMK;

    @FXML
    private PasswordField xnmkMoi;

    @FXML
    private PasswordField mkCu;

    @FXML
    private PasswordField mkMoi;

    @FXML
    void xacNhanDMK(ActionEvent event) throws SQLException {
        NguoiDungDAOimpl nguoiDungDAOimpl = new NguoiDungDAOimpl();
        NguoiDung nguoiDung = nguoiDungDAOimpl.findByPK(LoginController.tenDN);
        if(mkCu.getText().equals(nguoiDung.getMatKhau())){
            if(mkMoi.getText().equals(xnmkMoi.getText())){
                nguoiDung.setMatKhau(mkMoi.getText());
                nguoiDungDAOimpl.update(nguoiDung);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Đổi Mật Khẩu Thành Công");
                alert.showAndWait();
                cancel(event);
                loadWindow("/view/login/login.fxml", "");
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Bạn Xác Nhận Sai Mật Khẩu");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bạn Nhập Sai Mật Khẩu");
            alert.showAndWait();
        }

    }

    @FXML
    void huyDoiMK(ActionEvent event) {
        cancel(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
