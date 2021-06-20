package view.svdangky.tailentutepexcel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.SinhVienDAOimpl;
import dao_impl.SinhVienDotDKDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.SinhVien;
import model.SinhVienDotDK;
import view.svdangky.SVDangKyController;

public class SVDKExcelController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SinhVien> tableSVDK;

    @FXML
    private TableColumn<SinhVien, Date> ngaySinhSVDK;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhSVDK;

    @FXML
    private TableColumn<SinhVien, String> emailSVDK;

    @FXML
    private TableColumn<SinhVien, String> CMNDSVDK;

    @FXML
    private TableColumn<SinhVien, String> hoTenSVDK;

    @FXML
    private TableColumn<SinhVien, String> maLopSVDK;

    @FXML
    private TableColumn<SinhVien, String> queQuanSVDK;

    @FXML
    private TableColumn<SinhVien, String> maUTSVDK;

    @FXML
    private TableColumn<SinhVien, String> sttSVDK;

    @FXML
    private TableColumn<SinhVien, String> maKhoaSVDK;

    @FXML
    private TableColumn<SinhVien, Date> ngayDK;

    @FXML
    private TableColumn<SinhVien, String> SDTSVDK;

    @FXML
    private TableColumn<SinhVien, String> mssvDK;


    SinhVien selectedSinhVien;
    @FXML
    void xoaSV(ActionEvent event) {
        selectedSinhVien = tableSVDK.getSelectionModel().getSelectedItem();
        sinhVienObservableList.remove(selectedSinhVien);

    }

    @FXML
    void luuSV(ActionEvent event) throws SQLException {
        SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
        SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
        SinhVienDotDK sinhVienDotDK = new SinhVienDotDK();
        sinhVienDotDK.setMaDotDK(SVDangKyController.selectedDotDangKy.getMaDotDK());
        for (SinhVien s: sinhVienObservableList) {
            sinhVienDAOimpl.insert(s);
            sinhVienDotDK.setMSSV(s.getMSSV());
            sinhVienDotDKDAOimpl.insert(sinhVienDotDK);
        }

    }

    ObservableList<SinhVien> sinhVienObservableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sinhVienObservableList = FXCollections.observableArrayList(SVDangKyController.sinhVienList1);

        ngaySinhSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngaySinh"));
        ngayDK.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngayDangKy"));
        mssvDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        hoTenSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        maLopSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maLop"));
        gioiTinhSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));
        queQuanSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("queQuan"));
        maUTSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maUuTien"));
        SDTSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("SDT"));
        emailSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("email"));
        maKhoaSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maKhoa"));
        CMNDSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("CMND"));


        tableSVDK.setItems(sinhVienObservableList);


        sttSVDK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SinhVien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SinhVien, String> p) {
                return new ReadOnlyObjectWrapper(tableSVDK.getItems().indexOf(p.getValue()) + "");
            }
        });
    }
}
