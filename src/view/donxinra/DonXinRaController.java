package view.donxinra;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.DonXinRa;
import model.MyConnection;
import model.SinhVien;

public class DonXinRaController implements Initializable {
    MyConnection myConnection = new MyConnection();
    DonXinRaDAOimpl donXinRaDAOimpl = new DonXinRaDAOimpl();
    ObservableList<DonXinRa> donXinRaObservableList;
    List<DonXinRa> donXinRaList;

    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    List<SinhVien> sinhVienList;

    {
        try {
            myConnection.connectDB();
            donXinRaList = donXinRaDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ;
    public static DonXinRa selectedDonXinRa;

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
    private TableView<DonXinRa> tableSVXR;

    @FXML
    private TableColumn<DonXinRa, String> ngaySinhSVXR;

    @FXML
    private TableColumn<DonXinRa, String> lyDoXR;

    @FXML
    private TableColumn<DonXinRa, String> sttSVXR;

    @FXML
    private TableColumn<DonXinRa, String> mssvXR;

    @FXML
    private TextField tkmssv;

    @FXML
    private TableColumn<DonXinRa, String> hoTenSVXR;

    @FXML
    private TableColumn<DonXinRa, String> maLopSVXR;

    @FXML
    private TableColumn<DonXinRa, String> trangThaiSVXR;

    @FXML
    private TableColumn<DonXinRa, String> maXR;

    @FXML
    private TableColumn<DonXinRa, String> gioiTinhSVXR;

    @FXML
    private TableColumn<DonXinRa, Date> ngayLapDonXR;


    @FXML
    void timKiemSVXR(ActionEvent event) throws SQLException {
        donXinRaList = donXinRaDAOimpl.findLikeByPK(tkmssv.getText());
        setTableSVXR();
    }

    @FXML
    void capNhatDSSVXR(ActionEvent event) throws SQLException {
        donXinRaList = donXinRaDAOimpl.findAll();
        setTableSVXR();

    }


    @FXML
    void themSVXR(ActionEvent event) {
        loadWindow("/view/donxinra/themdonxinra/themdonxinra.fxml", "Thêm đơn xin ra");

    }

    @FXML
    void xoaSVXR(ActionEvent event) throws SQLException {
        selectedDonXinRa = tableSVXR.getSelectionModel().getSelectedItem();
        donXinRaObservableList.remove(selectedDonXinRa);
        donXinRaDAOimpl.delete(selectedDonXinRa);

    }

    @FXML
    void taiLenTuTepXR(ActionEvent event) {

    }

    SinhVien sinhVien;
    public void setTableSVXR(){
        donXinRaObservableList = FXCollections.observableArrayList(donXinRaList);

        ngayLapDonXR.setCellValueFactory(new PropertyValueFactory<DonXinRa, Date>("ngayLapDon"));
        maXR.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("maDonXinRa"));
        mssvXR.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("MSSV"));
        lyDoXR.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("lyDo"));
        trangThaiSVXR.setCellValueFactory(new PropertyValueFactory<DonXinRa, String>("trangThai"));
        tableSVXR.setItems(donXinRaObservableList);

        hoTenSVXR.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(sinhVien.getTenSV());
                }
        );

        gioiTinhSVXR.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(sinhVien.getGioiTinh());
                }
        );

        maLopSVXR.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(sinhVien.getMaLop());
                }
        );

        ngaySinhSVXR.setCellValueFactory(p -> {
                    DonXinRa donXinRa = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(donXinRa.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
            return new ReadOnlyStringWrapper(sinhVien.getNgaySinh().toString());
                }
        );

        sttSVXR.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DonXinRa, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<DonXinRa, String> p) {
                return new ReadOnlyObjectWrapper(tableSVXR.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttSVXR.setSortable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableSVXR();
    }
}
