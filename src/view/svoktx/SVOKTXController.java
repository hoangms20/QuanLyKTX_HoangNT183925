package view.svoktx;

import com.sun.javafx.collections.ObservableListWrapper;
import dao_impl.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.*;
import view.chitietsinhvien.ChiTietSinhVienController;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SVOKTXController implements Initializable {
    MyConnection myConnection = new MyConnection();
    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    ThuePhongDAOimpl thuePhongDAOimpl = new ThuePhongDAOimpl();

    ThuePhong thuePhong1;
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
    List<Phong> phongList;

    KhuNhaDAOimpl khuNhaDAOimpl =  new KhuNhaDAOimpl();
    List<KhuNha> khuNhaList;

    {
        try {
            myConnection.connectDB();
            khuNhaList = khuNhaDAOimpl.findAll();
            thuePhong1 = thuePhongDAOimpl.findNgayKetThucGanNhat();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static SinhVien selectedSinhVien;

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
    private TableColumn<SinhVien, String> maPhong;

    @FXML
    private TableColumn<SinhVien, String> mssvSVOKTX;

    @FXML
    private TableView<SinhVien> tableSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> queQuanSVOKTX;

    @FXML
    private TreeView<String> treeViewSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> ngayktSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> maLopSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> tenSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> sttSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> maKhoaSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> ngaybdSVOKTX;

    @FXML
    private TableColumn<SinhVien, Date> ngaySinhSVOKTX;

    @FXML
    private TableColumn<SinhVien, String> cmndSVOKTX;

    ThuePhong thuePhong;
    ObservableList<SinhVien> sinhVienObservableList;
    public void setTableViewSVOKTX(List<SinhVien> sinhVienList){
        sinhVienObservableList = FXCollections.observableArrayList(sinhVienList);

        ngaySinhSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngaySinh"));
        mssvSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        tenSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        cmndSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("CMND"));
        gioiTinhSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));
        queQuanSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("queQuan"));
        maLopSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maLop"));
        maKhoaSVOKTX.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maKhoa"));

        tableSVOKTX.setItems(sinhVienObservableList);

        maPhong.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        thuePhong = thuePhongDAOimpl.findByMSSV(sinhVien.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(thuePhong.getMaP());
                }
        );
        ngaybdSVOKTX.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        thuePhong = thuePhongDAOimpl.findByMSSV(sinhVien.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(thuePhong.getNgayBatDau().toString());
                }
        );

        ngayktSVOKTX.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        thuePhong = thuePhongDAOimpl.findByMSSV(sinhVien.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(thuePhong.getNgayKetThuc().toString());
                }
        );

        sttSVOKTX.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SinhVien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SinhVien, String> p) {
                return new ReadOnlyObjectWrapper(tableSVOKTX.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttSVOKTX.setSortable(false);

        tableSVOKTX.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableSVOKTX.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });
    }

    List<TreeItem<String>> treeItemListkhuNha;
    TreeItem<String> treeItem,treeItem2;
    List<SinhVien> sinhVienList1,sinhVienList2,sinhVienList3;
    String s;
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
        treeViewSVOKTX.setRoot(root);
        treeViewSVOKTX.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            s = new String(newValue.getValue());
            try {
                sinhVienList1 = sinhVienDAOimpl.findByPhong(s, thuePhong1);
                sinhVienList2 = sinhVienDAOimpl.findByKhuNha(s, thuePhong1);
                sinhVienList3 = sinhVienDAOimpl.findAllPhong();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(s.equals("Tất cả")){
                setTableViewSVOKTX(sinhVienList3);
            }else {
                if(!sinhVienList1.isEmpty()){
                    setTableViewSVOKTX(sinhVienList1);
                }else {
                    if(!sinhVienList2.isEmpty()){
                        setTableViewSVOKTX(sinhVienList2);
                    }else {
                        tableSVOKTX.setItems(null);
                    }
                }
            }
        });
    }

}
