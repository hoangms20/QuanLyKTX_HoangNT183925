package view.uutien.suauutien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.PhongDAOimpl;
import dao_impl.UuTienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.MyConnection;
import model.UuTien;
import view.uutien.UuTienController;

public class SuaUuTienController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label maUuTienSuaUT;

    @FXML
    private TextField tenUuTienMoi;

    @FXML
    private TextField mucDoUuTienMoi;

    @FXML
    private TextField ghiChuUTMoi;

    UuTienController uuTienController = new UuTienController();

    @FXML
    void xacNhanSuaUuTien(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        UuTienDAOimpl uuTienDAOimpl = new UuTienDAOimpl();
        UuTien uuTien = uuTienController.selected;
        uuTien.setTenUuTien(tenUuTienMoi.getText());
        uuTien.setMucDoUuTien(Integer.parseInt(mucDoUuTienMoi.getText()));
        uuTien.setGhiChuUuTien(ghiChuUTMoi.getText());


        if (uuTienDAOimpl.update(uuTien) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sửa phòng thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sửa phòng thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UuTien uuTien = uuTienController.selected;
        maUuTienSuaUT.setText(uuTien.getMaUuTien());
        tenUuTienMoi.setText(uuTien.getTenUuTien());
        mucDoUuTienMoi.setText(String.valueOf(uuTien.getMucDoUuTien()));
        ghiChuUTMoi.setText(uuTien.getGhiChuUuTien());

    }
}
