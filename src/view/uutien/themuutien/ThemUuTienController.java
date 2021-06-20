package view.uutien.themuutien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.LopDAOimpl;
import dao_impl.UuTienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Lop;
import model.MyConnection;
import model.UuTien;

public class ThemUuTienController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tTenUuTien;

    @FXML
    private TextField tGhiChuUuTien;

    @FXML
    private TextField tMucDoUuTien;

    @FXML
    private TextField tMaUuTien;

    @FXML
    void xacNhanThemUuTien(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        UuTienDAOimpl uuTienimpl = new UuTienDAOimpl();
        UuTien uuTien = new UuTien();
        uuTien.setMaUuTien(tMaUuTien.getText());
        uuTien.setTenUuTien(tTenUuTien.getText());
        uuTien.setGhiChuUuTien(tGhiChuUuTien.getText());
        uuTien.setMucDoUuTien(Integer.parseInt(tMucDoUuTien.getText()));

        if (uuTienimpl.insert(uuTien) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm ưu tiên thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm ưu tiên thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
