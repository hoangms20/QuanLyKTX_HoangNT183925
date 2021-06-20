package view.thongtindiennuoc;

import dao_impl.DienNuocDAOimpl;
import dao_impl.KhuNhaDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.beans.property.ReadOnlyStringWrapper;
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

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThongTinDienNuocController implements Initializable {
    MyConnection myConnection = new MyConnection();
    DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();
    ObservableList<DienNuoc> dienNuocObservableList;
    List<DienNuoc> dienNuocList, dienNuocList1, dienNuocList2, dienNuocDate;
    List<KhuNha> khuNhaList;
    KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();
    DienNuoc dienNuoc = new DienNuoc();

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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<DienNuoc, Integer> chiSoCuDN;

    @FXML
    private TableColumn<DienNuoc, String> tenDVDN;

    @FXML
    private TableColumn<DienNuoc, String> maDichVuDN;

    @FXML
    private TableView<DienNuoc> tableDN;

    @FXML
    private TreeView<String> treeViewDN;

    @FXML
    private TableColumn<DienNuoc, String> maPhongDN;

    @FXML
    private TableColumn<DienNuoc, String> tienVuotDMDN;

    @FXML
    private TableColumn<DienNuoc, String> tienDMDN;

    @FXML
    private TableColumn<DienNuoc, Integer> thanhTienDN;

    @FXML
    private TableColumn<DienNuoc, String> mucHoTroDN;

    @FXML
    private TableColumn<DienNuoc, Integer> SLTieuThuDN;

    @FXML
    private TableColumn<DienNuoc, Integer> chiSoMoiDN;

    @FXML
    private ComboBox<String> ngayTaoDN;

    @FXML
    void themDN(ActionEvent event) {
        loadWindow("/view/thongtindiennuoc/themdiennuoc/themdiennuoc.fxml", "Thêm điện nước");

    }

    List<String> stringList = new ArrayList<>();
    @FXML
    void lamMoiDN(ActionEvent event) throws SQLException {
        dienNuocDate = dienNuocDAOimpl.findAllDate();
        ngayTaoDN.setValue(null);
        tableDN.setItems(null);
        stringList.clear();
        for (DienNuoc d: dienNuocDate) {
            stringList.add(d.getThangNamGhiSoDN().toString());
        }
        ObservableList<String> listNgayTao = FXCollections.observableArrayList(stringList);
        ngayTaoDN.setItems(listNgayTao);

    }

    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl;
    LoaiHoaDon loaiHoaDon;
    public void setTableDN(List<DienNuoc> dienNuocList){
        dienNuocObservableList = FXCollections.observableArrayList(dienNuocList);

        maPhongDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, String>("maP"));
        maDichVuDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, String>("maLoaiHD"));
        chiSoCuDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, Integer>("chiSoCuDN"));
        chiSoMoiDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, Integer>("chiSoMoiDN"));
        thanhTienDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, Integer>("thanhTien"));
        SLTieuThuDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, Integer>("SLTieuThu"));

        tableDN.setItems(dienNuocObservableList);

        loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        tenDVDN.setCellValueFactory(p -> {
                    DienNuoc dienNuoc = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dienNuoc.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getTenLoaiHD());
                }
        );
        tienDMDN.setCellValueFactory(p -> {
                    DienNuoc dienNuoc = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dienNuoc.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getGiaTri().toString());
                }
        );

        tienVuotDMDN.setCellValueFactory(p -> {
                    DienNuoc dienNuoc = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dienNuoc.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getTienVuotDM().toString());
                }
        );

        mucHoTroDN.setCellValueFactory(p -> {
                    DienNuoc dienNuoc = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dienNuoc.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getMucHoTro().toString());
                }
        );

    }

    List<TreeItem<String>> treeItemListkhuNha;
    TreeItem<String> treeItem,treeItem2;
    String s;
    List<Phong> phongList;
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (DienNuoc d: dienNuocDate) {
            stringList.add(d.getThangNamGhiSoDN().toString());
        }
        ObservableList<String> listNgayTao = FXCollections.observableArrayList(stringList);
        ngayTaoDN.setItems(listNgayTao);

        treeItemListkhuNha = new ArrayList<>();
        for (KhuNha k: khuNhaList) {
            try {
                phongList = phongDAOimpl.findByKhuNha(k.getMaKhu());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            treeItem2 = new TreeItem<>(k.getTenKhu());
            for (Phong p: phongList) {
                treeItem = new TreeItem<>(p.getTenP());
                treeItem2.getChildren().add(treeItem);
            }
            treeItemListkhuNha.add(treeItem2);
        }
        TreeItem<String> root = new TreeItem<>("Tất cả");
        for (TreeItem<String> t: treeItemListkhuNha) {
            root.getChildren().add(t);
        }
        treeViewDN.setRoot(root);
        treeViewDN.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            s = new String(newValue.getValue());
            dienNuoc.setThangNamGhiSoDN(Date.valueOf(ngayTaoDN.getValue()));
            try {
                dienNuocList1 = dienNuocDAOimpl.findKhuNhaDateGanNhat(dienNuoc, s);
                dienNuocList2 = dienNuocDAOimpl.findPhongDateGanNhat(dienNuoc, s);
                dienNuocList = dienNuocDAOimpl.findAllDateGanNhat(dienNuoc);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(s.equals("Tất cả")){
                setTableDN(dienNuocList);
            }else {
                if(!dienNuocList1.isEmpty()){
                    setTableDN(dienNuocList1);
                }else {
                    if(!dienNuocList2.isEmpty()){
                        setTableDN(dienNuocList2);
                    }else {
                        tableDN.setItems(null);
                    }
                }
            }
        });

    }
}
