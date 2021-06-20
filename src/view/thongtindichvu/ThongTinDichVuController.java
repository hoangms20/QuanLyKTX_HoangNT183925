package view.thongtindichvu;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.*;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

public class ThongTinDichVuController implements Initializable {
    MyConnection myConnection = new MyConnection();
    DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
    public static ObservableList<DichVuPhong> dichVuPhongObservableList;
    List<DichVuPhong> dichVuPhongList;
    List<KhuNha> khuNhaList;
    String s;
    KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();

    {
        try {
            myConnection.connectDB();
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
    private TableView<DichVuPhong> tableDV;

    @FXML
    private TableColumn<DichVuPhong, String> giaTien;

    @FXML
    private TableColumn<DichVuPhong, String> maPhongDV;

    @FXML
    private TableColumn<DichVuPhong, String> maDichVu;

    @FXML
    private TableColumn<DichVuPhong, Integer> soLuongSuDung;

    @FXML
    private TreeView<String> treeViewDV;

    @FXML
    private TableColumn<DichVuPhong, Integer> thanhTienDV;

    @FXML
    private TableColumn<DichVuPhong, String> tenDV;

    @FXML
    void suaDV(ActionEvent event) {
        loadWindow("/view/thongtindichvu/suadichvu/suadichvu.fxml", "Sửa dịch vụ");
    }

    @FXML
    void lamMoiDV(ActionEvent event) {
        tableDV.setItems(null);

    }

    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl;
    LoaiHoaDon loaiHoaDon;
    public void setTableDV(){
        dichVuPhongObservableList = FXCollections.observableArrayList(dichVuPhongList);

        maPhongDV.setCellValueFactory(new PropertyValueFactory<DichVuPhong, String>("maP"));
        maDichVu.setCellValueFactory(new PropertyValueFactory<DichVuPhong, String>("maLoaiHD"));
        soLuongSuDung.setCellValueFactory(new PropertyValueFactory<DichVuPhong, Integer>("soLuongSuDung"));
        thanhTienDV.setCellValueFactory(new PropertyValueFactory<DichVuPhong, Integer>("thanhTienDV"));
        tableDV.setItems(dichVuPhongObservableList);

        loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
        tenDV.setCellValueFactory(p -> {
                    DichVuPhong dichVuPhong = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dichVuPhong.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getTenLoaiHD());
                }
        );
        giaTien.setCellValueFactory(p -> {
                    DichVuPhong dichVuPhong = p.getValue();
                    try {
                        loaiHoaDon = loaiHoaDonDAOimpl.findByPK(dichVuPhong.getMaLoaiHD());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(loaiHoaDon.getGiaTri().toString());
                }
        );

    }

    List<TreeItem<String>> treeItemListkhuNha;
    TreeItem<String> treeItem,treeItem2;
    List<Phong> phongList;
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        treeViewDV.setRoot(root);
        treeViewDV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            s = new String(newValue.getValue());
            try {
                dichVuPhongList = dichVuPhongDAOimpl.findByPhong(s);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            setTableDV();
        });

    }
}
