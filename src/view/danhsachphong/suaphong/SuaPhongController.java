package view.danhsachphong.suaphong;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.PhongDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.MyConnection;
import model.Phong;
import view.danhsachphong.PhongController;

public class SuaPhongController implements Initializable {
    MyConnection myConnection = new MyConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField suaTenPhong;

    @FXML
    private TextField suaPhongMaKhuNha;

    @FXML
    private TextField suaPhongTang;

    @FXML
    private Label suaMaPhong;

    @FXML
    private TextField suaSLToiDa;

    @FXML
    private ComboBox<String> suaLoaiPhong;

    PhongController phongController = new PhongController();

    @FXML
    void xacsuaPhong(ActionEvent event) throws SQLException {
        myConnection.connectDB();
        PhongDAOimpl phongimpl = new PhongDAOimpl();
        Phong phong = phongController.selected;
        phong.setTenP(suaTenPhong.getText());
        phong.setMaKhu(suaPhongMaKhuNha.getText());
        phong.setTang(Integer.parseInt(suaPhongTang.getText()));
        phong.setSLToiDa(Integer.parseInt(suaSLToiDa.getText()));
        phong.setLoaiP(suaLoaiPhong.getValue());
        if (phong.getSLDangO() >= phong.getSLToiDa()){
            phong.setTinhTrangPhong("Đã đầy");
        }

        if (phongimpl.update(phong) == true){
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
    ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nữ", "Cả nam và nữ");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Phong phong = phongController.selected;
        suaMaPhong.setText(phong.getMaP());
        suaLoaiPhong.setItems(list);
        suaPhongMaKhuNha.setText(phong.getMaKhu());
        suaTenPhong.setText(phong.getTenP());
        suaPhongTang.setText(String.valueOf(phong.getTang()));
        suaSLToiDa.setText(String.valueOf(phong.getSLToiDa()));
    }
}
