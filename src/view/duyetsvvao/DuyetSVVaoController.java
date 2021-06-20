package view.duyetsvvao;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DotDangKyDAOimpl;
import dao_impl.SinhVienDAOimpl;
import dao_impl.SinhVienDotDKDAOimpl;
import dao_impl.UuTienDAOimpl;
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
import view.chitietsinhvien.ChiTietSinhVienController;

public class DuyetSVVaoController implements Initializable {
    MyConnection myConnection = new MyConnection();
    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    ObservableList<SinhVien> sinhVienObservableList1, sinhVienObservableList2;
    List<SinhVien> sinhVienList1, sinhVienList2;

    DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();
    List<DotDangKy> dotDangKyList;

    {
        try {
            myConnection.connectDB();
            dotDangKyList = dotDangKyDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static SinhVien selectedSinhVien;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableColumn<SinhVien, Date> ngayDKDSDD;

    @FXML
    private TableColumn<SinhVien, String> mucDoUuTienDSDD;

    @FXML
    private TableColumn<SinhVien, String> mucDoUuTienDSCD;

    @FXML
    private TableView<SinhVien> tableViewDSChoDuyet;

    @FXML
    private TableColumn<SinhVien, CheckBox> checkHuySVVuaDuyetVao;

    @FXML
    private TableView<SinhVien> tableViewDSDaDuyet;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhDSDD;


    @FXML
    private TableColumn<SinhVien, String> gioiTinhDSCD;

    @FXML
    private TableColumn<SinhVien, String> tenSVDSDD;

    @FXML
    private TableColumn<SinhVien, Date> ngayDKDSCD;

    @FXML
    private TableColumn<SinhVien, String> mssvDSCD;

    @FXML
    private TableColumn<SinhVien, String> mssvDSDD;

    @FXML
    private TableColumn<SinhVien, String> tenSVDSCD;

    @FXML
    private ComboBox<String> cbDotDKDuyetSVVao;

    @FXML
    private Label slSVNamDaDuyet;

    @FXML
    private TableColumn<SinhVien, CheckBox> checkSVDuyetVao;

    @FXML
    private Label slSVNamDK;

    @FXML
    private Label chiTieuDotDK;

    @FXML
    private CheckBox selectALLDSCD;

    @FXML
    private CheckBox selectALLDSDD;

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
    void duyetSVVao(ActionEvent event) throws SQLException {
        for (SinhVien s: sinhVienList1) {
            if(s.getSelect().isSelected()){
                s.setMaTrangThai(1);
                sinhVienObservableList2.add(s);
                sinhVienObservableList1.remove(s);
                sinhVienDAOimpl.update(s);
            }
        }

    }

    @FXML
    void huySVVuaDuyetVao(ActionEvent event) throws SQLException {
        for (SinhVien s: sinhVienList2) {
            if(s.getSelect().isSelected()){
                s.setMaTrangThai(0);
                sinhVienObservableList1.add(s);
                sinhVienObservableList2.remove(s);
                sinhVienDAOimpl.update(s);
            }
        }

    }

    @FXML
    void tichTatCaDSChoDuyet(ActionEvent event) {
        if(selectALLDSCD.isSelected()){
            for (SinhVien s: sinhVienList1) {
                s.getSelect().setSelected(true);
            }
        }else{
            for (SinhVien s: sinhVienList1) {
                s.getSelect().setSelected(false);
            }
        }
    }

    @FXML
    void tichTatCaDSDaDuyet(ActionEvent event) {
        if(selectALLDSDD.isSelected()){
            for (SinhVien s: sinhVienList2) {
                s.getSelect().setSelected(true);
            }
        }else{
            for (SinhVien s: sinhVienList2) {
                s.getSelect().setSelected(false);
            }
        }

    }

    SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
    SinhVienDotDK sinhVienDotDK;
    @FXML
    void xoaSVChoDuyet(ActionEvent event) throws SQLException {
        selectedSinhVien = tableViewDSChoDuyet.getSelectionModel().getSelectedItem();
        sinhVienObservableList1.remove(selectedSinhVien);
        sinhVienDotDK = new SinhVienDotDK(selectedSinhVien.getMSSV(), cbDotDKDuyetSVVao.getValue());
        sinhVienDotDKDAOimpl.delete(sinhVienDotDK);
        sinhVienDAOimpl.delete(selectedSinhVien);

    }


    @FXML
    void capNhatSVDaDuyet(ActionEvent event) throws SQLException {

        chiTieuDotDK.setText(String.valueOf(dotDangKyDAOimpl.findByPK(cbDotDKDuyetSVVao.getValue()).getChiTieu()));
        sinhVienList1 = sinhVienDAOimpl.findChoDuyet(cbDotDKDuyetSVVao.getValue());
        sinhVienList2 = sinhVienDAOimpl.findDaDuyet(cbDotDKDuyetSVVao.getValue());
        slSVNamDK.setText(String.valueOf(sinhVienDotDKDAOimpl.countSLDK(cbDotDKDuyetSVVao.getValue())));
        slSVNamDaDuyet.setText(String.valueOf(sinhVienList2.size()));
        setTableViewDSChoDuyet();
        setTableViewDSDaDuyet();

    }

    UuTienDAOimpl uuTienDAOimpl;
    UuTien uuTien = new UuTien();
    public void setTableViewDSChoDuyet(){
        sinhVienObservableList1 = FXCollections.observableArrayList(sinhVienList1);

        ngayDKDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngayDangKy"));
        checkSVDuyetVao.setCellValueFactory(new PropertyValueFactory<SinhVien, CheckBox>("select"));
        mssvDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        tenSVDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        gioiTinhDSCD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));

        tableViewDSChoDuyet.setItems(sinhVienObservableList1);

        uuTienDAOimpl = new UuTienDAOimpl();
        mucDoUuTienDSCD.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        uuTien = uuTienDAOimpl.findByPK(sinhVien.getMaUuTien());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(uuTien.getMucDoUuTien()));
                }
        );

        tableViewDSChoDuyet.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableViewDSChoDuyet.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }

    public void setTableViewDSDaDuyet(){
        sinhVienObservableList2 = FXCollections.observableArrayList(sinhVienList2);

        ngayDKDSDD.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngayDangKy"));
        checkHuySVVuaDuyetVao.setCellValueFactory(new PropertyValueFactory<SinhVien, CheckBox>("select"));
        mssvDSDD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        tenSVDSDD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        gioiTinhDSDD.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));

        tableViewDSDaDuyet.setItems(sinhVienObservableList2);

        uuTienDAOimpl = new UuTienDAOimpl();
        mucDoUuTienDSDD.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        uuTien = uuTienDAOimpl.findByPK(sinhVien.getMaUuTien());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(uuTien.getMucDoUuTien()));
                }
        );

        tableViewDSDaDuyet.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableViewDSDaDuyet.getSelectionModel().getSelectedItem();
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }

    List<String> stringList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DotDangKy d: dotDangKyList) {
            stringList.add(d.getMaDotDK());
        }
        ObservableList<String> listMaDotDK = FXCollections.observableArrayList(stringList);
        cbDotDKDuyetSVVao.setItems(listMaDotDK);

    }
}

