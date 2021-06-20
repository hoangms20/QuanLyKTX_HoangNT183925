package view.khoanthudotdk;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.*;
import model.KhoanThuDotDK;

public class KhoanThuDotDKController implements Initializable {
    MyConnection myConnection = new MyConnection();
    KhoanThuDotDKDAOimpl khoanThuDotDKDAOimpl = new KhoanThuDotDKDAOimpl();
    ObservableList<KhoanThuDotDK> khoanThuDotDKObservableList;
    List<KhoanThuDotDK> list;
    public static KhoanThuDotDK selected;
    public static String selectedMaDotDK;

    List<DotDangKy> dotDangKyList;
    DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();

    {
        try {
            myConnection.connectDB();
            dotDangKyList = dotDangKyDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<KhoanThuDotDK, String> sttKTDDK;

    @FXML
    private ComboBox<String> dotDK;

    @FXML
    private Label tongTienKTDDK;

    @FXML
    private TableColumn<KhoanThuDotDK, String> maKTDDK;

    @FXML
    private TableColumn<KhoanThuDotDK, Integer> soTienKTDDK;

    @FXML
    private TableView<KhoanThuDotDK> tableKTDDK;

    @FXML
    private TableColumn<KhoanThuDotDK, String> tenKTDDK;


    @FXML
    void themKTDDK(ActionEvent event) {
        selectedMaDotDK = dotDK.getValue();
        loadWindow("/view/khoanthudotdk/themkhoanthudotdk/themkhoanthudotdk.fxml", "Thêm khoản thu vào đợt đăng ký");

    }

    @FXML
    void taoPhieuKhoanThu(ActionEvent event) throws SQLException {
        SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
        List<SinhVien> sinhVienList = sinhVienDAOimpl.findAllDaDuyet();
        KhoanThuSVDAOimpl khoanThuSVDAOimpl = new KhoanThuSVDAOimpl();
        for (SinhVien s: sinhVienList) {
            KhoanThuSV khoanThuSV = new KhoanThuSV();
            khoanThuSV.setMSSV(s.getMSSV());
            khoanThuSV.setMaKhoanThuSV(dotDK.getValue() + "SV" + s.getMSSV());
            khoanThuSV.setTinhTrangThanhToan(0);
            khoanThuSV.setNgayThu(null);
            khoanThuSV.setTrangThai("Chưa thanh toán");
            khoanThuSV.setTongTienThu(Integer.parseInt(tongTienKTDDK.getText()));
            khoanThuSVDAOimpl.insert(khoanThuSV);
        }


    }

    @FXML
    void xoaKTDDK(ActionEvent event) throws SQLException {
        selected = tableKTDDK.getSelectionModel().getSelectedItem();
        khoanThuDotDKObservableList.remove(selected);
        khoanThuDotDKDAOimpl.delete(selected);

    }


    @FXML
    void capNhatKTDDK(ActionEvent event) throws SQLException {
        list = khoanThuDotDKDAOimpl.findByDDK(dotDK.getValue());
        Integer a=0;
        for (KhoanThuDotDK d: list) {
            a = a + d.getSoTienThu();
        }
        tongTienKTDDK.setText(String.valueOf(a));
        setTableKTDDK();

    }

    KhoanThu khoanThu;
    KhoanThuDAOimpl khoanThuDAOimpl;
    public void setTableKTDDK() throws SQLException {

        khoanThuDotDKObservableList = FXCollections.observableArrayList(list);
        khoanThuDAOimpl = new KhoanThuDAOimpl();

        maKTDDK.setCellValueFactory(new PropertyValueFactory<KhoanThuDotDK, String>("maKhoanThu"));
        soTienKTDDK.setCellValueFactory(new PropertyValueFactory<KhoanThuDotDK, Integer>("soTienThu"));

        tableKTDDK.setItems(khoanThuDotDKObservableList);

        tenKTDDK.setCellValueFactory(p -> {
                    KhoanThuDotDK khoanThuDotDK = p.getValue();
                    try {
                        khoanThu = khoanThuDAOimpl.findByPK(khoanThuDotDK.getMaKhoanThu());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(khoanThu.getTenKhoanThu());
                }
        );

        sttKTDDK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhoanThuDotDK, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<KhoanThuDotDK, String> p) {
                return new ReadOnlyObjectWrapper(tableKTDDK.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttKTDDK.setSortable(false);
    }

    List<String> stringList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DotDangKy d: dotDangKyList) {
            stringList.add(d.getMaDotDK());
        }
        ObservableList<String> listMaDotDK = FXCollections.observableArrayList(stringList);
        dotDK.setItems(listMaDotDK);
    }
}

