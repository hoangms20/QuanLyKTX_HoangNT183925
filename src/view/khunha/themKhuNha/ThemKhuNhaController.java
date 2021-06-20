package view.khunha.themKhuNha;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.KhuNhaDAOimpl;
import dao_impl.UuTienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.KhuNha;
import model.MyConnection;
import model.UuTien;

public class ThemKhuNhaController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tMaKhuNha;

    @FXML
    private TextField tTenKhuNha;

    @FXML
    void xacNhanThemKhuNha(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        KhuNhaDAOimpl khuNhaimpl = new KhuNhaDAOimpl();
        KhuNha khuNha = new KhuNha();
        khuNha.setMaKhu(tMaKhuNha.getText());
        khuNha.setTenKhu(tTenKhuNha.getText());

        if (khuNhaimpl.insert(khuNha) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm khu nhà thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm khu nhà thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
