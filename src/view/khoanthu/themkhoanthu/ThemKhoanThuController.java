package view.khoanthu.themkhoanthu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.KhoanThuDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.KhoanThu;
import model.MyConnection;

public class ThemKhoanThuController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tMaKhoanThu;

    @FXML
    private TextField tTenKhoanThu;

    @FXML
    void xacNhanThemKhoanThu(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        KhoanThuDAOimpl khoanThuDAOimpl = new KhoanThuDAOimpl();
        KhoanThu khoanThu = new KhoanThu();
        khoanThu.setMaKhoanThu(tMaKhoanThu.getText());
        khoanThu.setTenKhoanThu(tTenKhoanThu.getText());

        if (khoanThuDAOimpl.insert(khoanThu) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm viện thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm viện thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
