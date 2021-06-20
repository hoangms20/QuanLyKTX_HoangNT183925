package view.duyetxinra;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.*;
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

public class DuyetDonXinRaController implements Initializable {
    MyConnection myConnection = new MyConnection();
    DonXinRaDAOimpl donXinRaDAOimpl = new DonXinRaDAOimpl();
    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    SinhVien sinhVien;
    ObservableList<DonXinRa> donXinRaObservableList1, donXinRaObservableList2;
    List<DonXinRa> donXinRaList1, donXinRaList2;

    {
        try {
            myConnection.connectDB();
            donXinRaList1 = donXinRaDAOimpl.findByXetDuyet(0);
            donXinRaList2 = donXinRaDAOimpl.findByXetDuyet(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static DonXinRa selectedDonXinRa;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label slDXRDaDuyet;

    @FXML
    private TableView<DonXinRa> tableDXRChoDuyet;

    @FXML
    private TableColumn<DonXinRa, String> trangThaiDXRDSDD;

    @FXML
    private TableColumn<DonXinRa, CheckBox> checkDXRDuyetVao;

    @FXML
    private TableColumn<DonXinRa, String> trangThaiDXRDSCD;

    @FXML
    private TableColumn<DonXinRa, String> tenSVDSDD;

    @FXML
    private CheckBox selectALLDSDD;

    @FXML
    private CheckBox selectALLDSCD;

    @FXML
    private TableColumn<DonXinRa, String> mssvDSCD;

    @FXML
    private TableView<DonXinRa> tableDSDaDuyet;

    @FXML
    private TableColumn<DonXinRa, String> maDXRDSCD;

    @FXML
    private TableColumn<DonXinRa, String> mssvDSDD;

    @FXML
    private TableColumn<DonXinRa, String> maDXRDSDD;

    @FXML
    private Label slDXRChuaDuyet;

    @FXML
    private TableColumn<DonXinRa, String> tenSVDSCD;

    @FXML
    private TableColumn<DonXinRa, Date> ngayDXRDSCD;

    @FXML
    private TableColumn<DonXinRa, CheckBox> checkDXRDaDuyet;

    @FXML
    private Label chiTieuDotDK;

    @FXML
    private TableColumn<DonXinRa, Date> ngayDXRDSDD;

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

    ThuePhongDAOimpl thuePhongDAOimpl = new ThuePhongDAOimpl();
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
    @FXML
    void duyetDXR(ActionEvent event) throws SQLException {
        for (DonXinRa d: donXinRaList1) {
            if(d.getSelect().isSelected()){
                d.setXetDuyet(1);
                donXinRaObservableList2.add(d);
                donXinRaObservableList1.remove(d);
                donXinRaDAOimpl.update(d);
                SinhVien sinhVien1 = sinhVienDAOimpl.findByPK(d.getMSSV());
                ThuePhong thuePhong = thuePhongDAOimpl.findByMSSV(d.getMSSV());
                Phong phong = phongDAOimpl.findByPK(thuePhong.getMaP());
                phong.setSLDangO(phong.getSLDangO() - 1);
                phongDAOimpl.update(phong);
                sinhVien1.setNgayRoiKhoi((Date) thuePhong.getNgayKetThuc());
                sinhVien1.setMaTrangThai(3);
                sinhVienDAOimpl.update(sinhVien1);
            }
        }

    }

    @FXML
    void huyDonXR(ActionEvent event) throws SQLException {
        for (DonXinRa d: donXinRaList2) {
            if(d.getSelect().isSelected()){
                d.setXetDuyet(0);
                donXinRaObservableList1.add(d);
                donXinRaObservableList2.remove(d);
                donXinRaDAOimpl.update(d);
                SinhVien sinhVien1 = sinhVienDAOimpl.findByPK(d.getMSSV());
                sinhVien1.setNgayRoiKhoi(null);
                sinhVien1.setMaTrangThai(2);
                sinhVienDAOimpl.update(sinhVien1);
                ThuePhong thuePhong = thuePhongDAOimpl.findByMSSV(d.getMSSV());
                Phong phong = phongDAOimpl.findByPK(thuePhong.getMaP());
                phong.setSLDangO(phong.getSLDangO() + 1);
                phongDAOimpl.update(phong);
            }
        }

    }

    @FXML
    void tichTatCaDSChoDuyet(ActionEvent event) {
        if(selectALLDSCD.isSelected()){
            for (DonXinRa d: donXinRaList1) {
                d.getSelect().setSelected(true);
            }
        }else{
            for (DonXinRa d: donXinRaList1) {
                d.getSelect().setSelected(false);
            }
        }

    }

    @FXML
    void tichTatCaDSDaDuyet(ActionEvent event) {
        if(selectALLDSCD.isSelected()){
            for (DonXinRa d: donXinRaList2) {
                d.getSelect().setSelected(true);
            }
        }else{
            for (DonXinRa d: donXinRaList2) {
                d.getSelect().setSelected(false);
            }
        }

    }

    @FXML
    void xoaDXRChoDuyet(ActionEvent event) throws SQLException {
        selectedDonXinRa = tableDXRChoDuyet.getSelectionModel().getSelectedItem();
        donXinRaObservableList1.remove(selectedDonXinRa);
        donXinRaDAOimpl.delete(selectedDonXinRa);

    }

    @FXML
    void capNhatDXRDaDuyet(ActionEvent event) throws SQLException {

        donXinRaList1 = donXinRaDAOimpl.findByXetDuyet(0);
        donXinRaList2 = donXinRaDAOimpl.findByXetDuyet(1);
        slDXRDaDuyet.setText(String.valueOf(donXinRaList2.size()));
        slDXRChuaDuyet.setText(String.valueOf(donXinRaList1.size()));

        setTableDXRChoDuyet();
        setTableDSDaDuyet();

    }

    public void setTableDXRChoDuyet(){
        donXinRaObservableList1 = FXCollections.observableArrayList(donXinRaList1);

        ngayDXRDSCD.setCellValueFactory(new PropertyValueFactory<DonXinRa, Date>("ngayLapDon"));
        checkDXRDuyetVao.setCellValueFactory(new PropertyValueFactory<DonXinRa, CheckBox>("select"));
        mssvDSCD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("MSSV"));
        trangThaiDXRDSCD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("trangThai"));
        maDXRDSCD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("maDonXinRa"));

        tableDXRChoDuyet.setItems(donXinRaObservableList1);

        sinhVienDAOimpl  = new SinhVienDAOimpl();
        tenSVDSCD.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(sinhVien.getTenSV()));
                }
        );

        tableDXRChoDuyet.setRowFactory( tv -> {
            TableRow<DonXinRa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DonXinRa rowData = row.getItem();
                    DonXinRa donXinRa = tableDXRChoDuyet.getSelectionModel().getSelectedItem();
                    try {
                        ChiTietSinhVienController.selectedSinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }

    public void setTableDSDaDuyet(){
        donXinRaObservableList2 = FXCollections.observableArrayList(donXinRaList2);

        ngayDXRDSDD.setCellValueFactory(new PropertyValueFactory<DonXinRa, Date>("ngayLapDon"));
        checkDXRDaDuyet.setCellValueFactory(new PropertyValueFactory<DonXinRa, CheckBox>("select"));
        mssvDSDD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("MSSV"));
        trangThaiDXRDSDD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("trangThai"));
        maDXRDSDD.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("maDonXinRa"));

        tableDSDaDuyet.setItems(donXinRaObservableList2);

        sinhVienDAOimpl  = new SinhVienDAOimpl();
        tenSVDSDD.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(sinhVien.getTenSV()));
                }
        );

        tableDSDaDuyet.setRowFactory( tv -> {
            TableRow<DonXinRa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DonXinRa rowData = row.getItem();
                    DonXinRa donXinRa = tableDSDaDuyet.getSelectionModel().getSelectedItem();
                    try {
                        ChiTietSinhVienController.selectedSinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    loadWindow("/view/chitietsinhvien/chitietsinhvien.fxml", "Thông tin sinh viên");
                }
            });
            return row ;
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableDSDaDuyet();
        setTableDXRChoDuyet();
    }
}
