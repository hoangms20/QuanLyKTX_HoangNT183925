package view.lop.themlop;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.LopDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Lop;
import model.MyConnection;

public class ThemLopController implements Initializable {

    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tTenLop;

    @FXML
    private TextField tMaLop;

    @FXML
    private TextField tLopThuocVien;

    @FXML
    void xacNhanThemLop(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        LopDAOimpl lopDAOimpl = new LopDAOimpl();
        Lop lop = new Lop();
        lop.setMaLop(tMaLop.getText());
        lop.setTenLop(tTenLop.getText());
        lop.setMaVien(tLopThuocVien.getText());

        if (lopDAOimpl.insert(lop) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm lớp thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm lớp thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
