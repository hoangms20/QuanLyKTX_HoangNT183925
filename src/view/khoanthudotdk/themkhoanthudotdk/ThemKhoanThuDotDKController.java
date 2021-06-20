package view.khoanthudotdk.themkhoanthudotdk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.KhoanThuDotDKDAOimpl;
import dao_impl.SinhVienDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.KhoanThuDotDK;
import model.MyConnection;
import view.khoanthudotdk.KhoanThuDotDKController;

public class ThemKhoanThuDotDKController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tMaKhoanThu;

    @FXML
    private TextField tSoTienThu;

    @FXML
    private Label maDDK;

    @FXML
    void xacNhanThemKhoanThuVaoDot(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        KhoanThuDotDKDAOimpl khoanThuDotDKDAOimpl = new KhoanThuDotDKDAOimpl();
        KhoanThuDotDK khoanThuDotDK = new KhoanThuDotDK();
        khoanThuDotDK.setMaDotDk(KhoanThuDotDKController.selectedMaDotDK);
        khoanThuDotDK.setMaKhoanThu(tMaKhoanThu.getText());
        khoanThuDotDK.setSoTienThu(Integer.parseInt(tSoTienThu.getText()));

        if (khoanThuDotDKDAOimpl.insert(khoanThuDotDK) == true ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm thất bại");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        maDDK.setText(KhoanThuDotDKController.selectedMaDotDK);
    }
}
