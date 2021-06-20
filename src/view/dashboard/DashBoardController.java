package view.dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private BorderPane rootPane;

    void loadWindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void quanLyTKSV(ActionEvent event) {
        loadWindow("/view/quanlytaikhoansv/quanlytaikhoansv.fxml", "Quản lý tài khoản sinh viên");

    }

    @FXML
    void doiMK(ActionEvent event) {
        loadWindow("/view/doimk/doimk.fxml", "");

    }

    @FXML
    void logout(ActionEvent event) {
        cancel(event);
        loadWindow("/view/login/login.fxml", "");
    }

    @FXML
    void SVDK(ActionEvent event) {
        loadWindow("/view/svdangky/svdangky.fxml", "Sinh viên đăng ký vào ký túc xá");

    }

    @FXML
    void SVOKTX(ActionEvent event) {
        loadWindow("/view/svoktx/svoktx.fxml", "Sinh viên ở ký túc xá");

    }

    @FXML
    void lop(ActionEvent event) {
        loadWindow("/view/lop/lop.fxml", "Danh sách lớp");

    }

    @FXML
    void dotDK(ActionEvent event) {
        loadWindow("/view/dotdk/dotdk.fxml", "Danh sách đợt đăng ký");

    }

    @FXML
    void duyetsvvao(ActionEvent event) {
        loadWindow("/view/duyetsvvao/duyetsvvao.fxml", "Duyệt sinh viên vào");

    }

    @FXML
    void donxinra(ActionEvent event) {
        loadWindow("/view/donxinra/donxinra.fxml", "Đơn xin ra");

    }

    @FXML
    void duyetsvra(ActionEvent event) {
        loadWindow("/view/duyetxinra/duyetdonxinra.fxml", "Duyệt đơn xin ra");

    }

    @FXML
    void dsPhong(ActionEvent event) {
        loadWindow("/view/danhsachphong/phong.fxml", "Danh sách phòng");

    }

    @FXML
    void xepPhong(ActionEvent event) {
        loadWindow("/view/xepphong/xepphong.fxml", "Xếp phòng");

    }

    @FXML
    void khuNha(ActionEvent event) {
        loadWindow("/view/khunha/khunha.fxml", "Danh sách khu nhà");

    }

    @FXML
    void dsKhoanThu(ActionEvent event) {
        loadWindow("/view/khoanthu/khoanthu.fxml", "Danh sách khoản thu");

    }

    @FXML
    void khoa(ActionEvent event) {
        loadWindow("/view/khoa/khoa.fxml", "Danh sách các khóa");

    }

    @FXML
    void khoanThuTheoDot(ActionEvent event) {
        loadWindow("/view/khoanthudotdk/khoanthudotdk.fxml", "Danh sách khoản thu theo đợt");

    }

    @FXML
    void khoanThuSV(ActionEvent event) {
        loadWindow("/view/khoanthusinhvien/khoanthusinhvien.fxml", "Danh sách khoản thu theo đợt");

    }

    @FXML
    void thongTinDV(ActionEvent event) {
        loadWindow("/view/thongtindichvu/thongtindichvu.fxml", "Thông tin dịch vụ");

    }

    @FXML
    void thongTinDN(ActionEvent event) {
        loadWindow("/view/thongtindiennuoc/thongtindiennuoc.fxml", "Thông tin điện nước");

    }

    @FXML
    void loaiDV(ActionEvent event) {
        loadWindow("/view/loaidichvu/loaidichvu.fxml", "Loại dịch vụ");

    }

    @FXML
    void hoaDon(ActionEvent event) {
        loadWindow("/view/hoadon/hoadon.fxml", "Danh sách hóa đơn");

    }

    @FXML
    void tkTongThe(ActionEvent event) {
        loadWindow("/view/thongketoanbo/thongketoanbo.fxml", "Báo cáo tổng thể");

    }

    @FXML
    void tkDotDK(ActionEvent event) {
        loadWindow("/view/thongketheodotdk/thongketheodotdk.fxml", "Báo cáo đợt đăng ký");

    }

    @FXML
    void tkHoaDon(ActionEvent event) {
        loadWindow("/view/thongkehoadon/thongkehoadon.fxml", "Báo cáo hóa đơn");

    }

    @FXML
    void uuTien(ActionEvent event) {
        loadWindow("/view/uutien/uutien.fxml", "Danh sách ưu tiên");

    }

    @FXML
    void vien(ActionEvent event) {
        loadWindow("/view/vien/vien.fxml", "Danh sách các viện");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
