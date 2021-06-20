package view.donxinra.themdonxinra;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.DonXinRaDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.DonXinRa;

public class ThemDonXinRaController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker tNgayLapDonXR;

    @FXML
    private TextField tMaDonXR;

    @FXML
    private TextField tMSSVXR;

    @FXML
    private TextField tLyDoXR;

    DonXinRaDAOimpl donXinRaDAOimpl = new DonXinRaDAOimpl();
    DonXinRa donXinRa;
    @FXML
    void xacNhanThemSVXR(ActionEvent event) throws SQLException {
        donXinRa = new DonXinRa(tMaDonXR.getText(), tMSSVXR.getText(), Date.valueOf(tNgayLapDonXR.getValue()), tLyDoXR.getText(), 0);
        if (donXinRaDAOimpl.insert(donXinRa) == true ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm đơn xin ra thành công");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm đơn xin ra thất bại");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
