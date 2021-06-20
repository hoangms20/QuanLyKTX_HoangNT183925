package view.homesv;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.SinhVienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.chitietsinhvien.ChiTietSinhVienController;
import view.login.LoginController;

public class HomeSVController {

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

    void cancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane rootPane;

    @FXML
    void doiMK(ActionEvent event) {
        loadWindow("/view/doimk/doimk.fxml", "");

    }

    @FXML
    void logout(ActionEvent event) {
        cancel();
        loadWindow("/view/login/login.fxml", "");

    }

    @FXML
    void ttSV(ActionEvent event) throws SQLException {
        SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
        ChiTietSinhVienController.selectedSinhVien = sinhVienDAOimpl.findByPK(LoginController.tenDN);
        loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");

    }

    @FXML
    void SVOKTX(ActionEvent event) {
        loadWindow("/view/svoktx/svoktx.fxml", "Sinh viên ở ký túc xá");

    }

    @FXML
    void suaTTSV(ActionEvent event) {

    }

    @FXML
    void hoaDon(ActionEvent event) {
        loadWindow("/view/homesv/hoadonsv/hoadon.fxml", "Danh sách hóa đơn");

    }

    @FXML
    void khoanThu(ActionEvent event) {
        loadWindow("/view/homesv/khoanthusv/khoanthusinhvien.fxml", "Danh sách khoản thu theo đợt");

    }

    @FXML
    void donXinRa(ActionEvent event) {
        loadWindow("/view/donxinra/donxinra.fxml", "Đơn xin ra");

    }

}
