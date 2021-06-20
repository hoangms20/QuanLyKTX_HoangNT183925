package view.khoa.themkhoa;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.KhoaDAOimpl;
import dao_impl.LopDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Khoa;
import model.Lop;
import model.MyConnection;
import sun.awt.SunHints;

public class ThemKhoaController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tMaKhoa;

    @FXML
    private TextField tTenKhoa;

    @FXML
    private TextField tNamVaoHoc;

    @FXML
    void xacNhanThemKhoa(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        KhoaDAOimpl khoaDAOimpl = new KhoaDAOimpl();
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(tMaKhoa.getText());
        khoa.setTenKhoa(tTenKhoa.getText());
        khoa.setNamVaoHoc(Integer.parseInt(tNamVaoHoc.getText()));

        if (khoaDAOimpl.insert(khoa) == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm khóa thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm khóa thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
