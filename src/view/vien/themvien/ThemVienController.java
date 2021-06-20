package view.vien.themvien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.KhoaDAOimpl;
import dao_impl.VienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Khoa;
import model.MyConnection;
import model.Vien;

public class ThemVienController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tTenVien;

    @FXML
    private TextField tMaVien;

    @FXML
    void xacNhanThemVien(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        VienDAOimpl vienDaoimpl = new VienDAOimpl();
        Vien vien = new Vien();
        vien.setMaVien(tMaVien.getText());
        vien.setTenVien(tTenVien.getText());

        if (vienDaoimpl.insert(vien) == true){
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
