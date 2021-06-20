package view.login;

import dao_impl.NguoiDungDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MyConnection;
import model.NguoiDung;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    MyConnection myConnection = new MyConnection();

    public static String tenDN;

    @FXML
    private AnchorPane rootPanelogin;

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
        Stage stage = (Stage) rootPanelogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taiKhoan;

    @FXML
    private PasswordField matKhau;

    @FXML
    void login(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        NguoiDungDAOimpl userDao = new NguoiDungDAOimpl();
        String userName = taiKhoan.getText();
        NguoiDung user = userDao.findByPK(userName);
        if (user != null && matKhau.getText().equals(user.getMatKhau())){
            tenDN = userName;
            cancel(event);
            if (user.getNhomNguoiDung().equals("Sinh viên")){
                loadWindow("/view/homesv/homesv.fxml", "Home");
            }else {
                loadWindow("/view/dashboard/dashboard.fxml", "Home");
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bạn Nhập Sai Tài Khoản Hoặc Mật Khẩu");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
