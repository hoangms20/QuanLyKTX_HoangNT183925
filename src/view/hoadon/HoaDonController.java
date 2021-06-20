package view.hoadon;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.HoaDon;

public class HoaDonController implements Initializable {
    MyConnection myConnection = new MyConnection();
    HoaDonDAOimpl hoaDonDAOimpl = new HoaDonDAOimpl();
    List<HoaDon> hoaDonList1, hoaDonList2;
    ObservableList<HoaDon> hoaDonObservableList;
    List<DienNuoc> dienNuocDate;
    DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();
    List<KhuNha> khuNhaList;
    KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();

    {
        try {
            myConnection.connectDB();
            dienNuocDate = dienNuocDAOimpl.findAllDate();
            khuNhaList = khuNhaDAOimpl.findAll();
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
    private ComboBox<String> ngayTaoHD;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<HoaDon, String> maHD;

    @FXML
    private TableColumn<HoaDon, CheckBox> cbThanhToan;

    @FXML
    private TableColumn<HoaDon, String> maPhongHD;

    @FXML
    private TableColumn<HoaDon, String> tinhTrangHD;

    @FXML
    private TableView<HoaDon> tableHD;

    @FXML
    private TableColumn<HoaDon, Integer> tongTienHD;

    @FXML
    private TreeView<String> treeViewHD;

    @FXML
    private CheckBox cbSelectAll;

    @FXML
    void selectAll(ActionEvent event) {
        if(cbSelectAll.isSelected()){
            for (HoaDon h: hoaDonObservableList) {
                h.getCbThanhToan().setSelected(true);
            }
        }else{
            for (HoaDon h: hoaDonObservableList) {
                h.getCbThanhToan().setSelected(false);
            }
        }
    }

    @FXML
    void lamMoiHD(ActionEvent event) {
        tableHD.setItems(null);
    }

    @FXML
    void themHDAll(ActionEvent event) throws SQLException {
        PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
        List<Phong> phongList =  phongDAOimpl.findAll();
        LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        List<LoaiHoaDon> loaiHoaDonList = loaiHoaDonDAOimpl.findAll();
        ChiTietHoaDonDAOimpl chiTietHoaDonDAOimpl = new ChiTietHoaDonDAOimpl();
        for (Phong p: phongList) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaP(p.getMaP());
            hoaDon.setTinhTrangHD("Chưa thanh toán");
            hoaDon.setNgayLap(Date.valueOf(ngayTaoHD.getValue()));
            hoaDon.setMaHD(ngayTaoHD.getValue() + p.getMaP());
            Integer a = 0;
            DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
            List<DichVuPhong> dichVuPhongList = dichVuPhongDAOimpl.findByPhong(p.getTenP());
            for (DichVuPhong d: dichVuPhongList) {
                a = a + d.getThanhTienDV();
            }
            DienNuoc dienNuoc = dienNuocDAOimpl.findByPK(Date.valueOf(ngayTaoHD.getValue()), "LHD01", p.getMaP());
            a = a + dienNuoc.getThanhTien();
            dienNuoc = dienNuocDAOimpl.findByPK(Date.valueOf(ngayTaoHD.getValue()), "LHD02", p.getMaP());
            a = a +  dienNuoc.getThanhTien();
            hoaDon.setTongTien(a);
            hoaDonDAOimpl.insert(hoaDon);
            for (LoaiHoaDon l: loaiHoaDonList) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHD(hoaDon.getMaHD());
                chiTietHoaDon.setMaLoaiHD(l.getMaLoaiHD());
                chiTietHoaDonDAOimpl.insert(chiTietHoaDon);
            }
        }

    }

    @FXML
    void xacNhanHD(ActionEvent event) throws SQLException {
        for (HoaDon h: hoaDonObservableList) {
            if(h.getCbThanhToan().isSelected()){
                h.setTinhTrangHD("Đã thanh toán");
                hoaDonDAOimpl.update(h);
            }
        }

    }

    public static HoaDon selectedHoaDon;

    public void setTableHD(List<HoaDon> hoaDonList){
        hoaDonObservableList = FXCollections.observableArrayList(hoaDonList);

        maHD.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("maHD"));
        maPhongHD.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("maP"));
        tongTienHD.setCellValueFactory(new PropertyValueFactory<HoaDon, Integer>("tongTien"));
        tinhTrangHD.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("tinhTrangHD"));
        cbThanhToan.setCellValueFactory(new PropertyValueFactory<HoaDon, CheckBox>("cbThanhToan"));

        tableHD.setItems(hoaDonObservableList);

        tableHD.setRowFactory( tv -> {
            TableRow<HoaDon> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    HoaDon rowData = row.getItem();
                    selectedHoaDon = tableHD.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitiethoadon/chitiethoadon.fxml", "Chi tiết hóa đơn");
                }
            });
            return row ;
        });

    }

    List<TreeItem<String>> treeItemListkhuNha;
    TreeItem<String> treeItem;
    String s;
    List<String> stringList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DienNuoc d: dienNuocDate) {
            stringList.add(d.getThangNamGhiSoDN().toString());
        }
        ObservableList<String> listNgayTao = FXCollections.observableArrayList(stringList);
        ngayTaoHD.setItems(listNgayTao);

        treeItemListkhuNha = new ArrayList<>();
        for (KhuNha k: khuNhaList) {
            treeItem = new TreeItem<>(k.getTenKhu());
            treeItemListkhuNha.add(treeItem);
        }
        TreeItem<String> root = new TreeItem<>("Tất cả");
        for (TreeItem<String> t: treeItemListkhuNha) {
            root.getChildren().add(t);
        }
        treeViewHD.setRoot(root);
        treeViewHD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            s = new String(newValue.getValue());
            if(s.equals("Tất cả")){
                try {
                    hoaDonList1 = hoaDonDAOimpl.findAllByNgayLap(Date.valueOf(ngayTaoHD.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                setTableHD(hoaDonList1);
            }else {
                try {
                    hoaDonList2 = hoaDonDAOimpl.findByKhu(s, Date.valueOf(ngayTaoHD.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                setTableHD(hoaDonList2);
            }

        });

    }
}
