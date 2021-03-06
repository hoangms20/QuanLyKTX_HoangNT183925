package view.homesv.khoanthusv;

import dao_impl.DotDangKyDAOimpl;
import dao_impl.KhoanThuSVDAOimpl;
import dao_impl.SinhVienDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.DotDangKy;
import model.KhoanThuSV;
import model.MyConnection;
import model.SinhVien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KhoanThuSinhVienController implements Initializable {
    MyConnection myConnection = new MyConnection();

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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<KhoanThuSV, String> stt;

    @FXML
    private TableColumn<KhoanThuSV, String> tenSV;

    @FXML
    private TableColumn<KhoanThuSV, String> trangThai;

    @FXML
    private ComboBox<String> dotDK;

    @FXML
    private TableColumn<KhoanThuSV, Integer> tongTien;

    @FXML
    private TextField tkMSSV;

    @FXML
    private TableColumn<KhoanThuSV, String> MSSV;

    @FXML
    private TableColumn<KhoanThuSV, CheckBox> checkThanhToan;

    @FXML
    private ComboBox<String> trangThaiThanhToan;


    @FXML
    private TableView<KhoanThuSV> tableKTSV;


    List<KhoanThuSV> khoanThuSVList;
    KhoanThuSVDAOimpl khoanThuSVDAOimpl = new KhoanThuSVDAOimpl();
    ObservableList<KhoanThuSV> khoanThuSVObservableList;

    @FXML
    void capNhatDS(ActionEvent event) throws SQLException {
        if(trangThaiThanhToan.getValue().equals("T???t c???")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDK(dotDK.getValue());
        }

        if(trangThaiThanhToan.getValue().equals("Ch??a thanh to??n")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDKAndThanhToan(dotDK.getValue(), 0);
        }

        if(trangThaiThanhToan.getValue().equals("???? thanh to??n")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDKAndThanhToan(dotDK.getValue(), 1);
        }

        setTableKTSV();

    }

    @FXML
    void timKiem(ActionEvent event) throws SQLException {
        if(trangThaiThanhToan.getValue().equals("T???t c???")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDKAndLikeMSSV(dotDK.getValue(), tkMSSV.getText());
        }

        if(trangThaiThanhToan.getValue().equals("Ch??a thanh to??n")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDKAndLikeMSSVAndTT(dotDK.getValue(), tkMSSV.getText(),0);
        }

        if(trangThaiThanhToan.getValue().equals("???? thanh to??n")){
            khoanThuSVList = khoanThuSVDAOimpl.findDotDKAndLikeMSSVAndTT(dotDK.getValue(), tkMSSV.getText(),1);
        }

        setTableKTSV();

    }


    SinhVienDAOimpl sinhVienDAOimpl;
    SinhVien sinhVien;
    public void setTableKTSV(){
        khoanThuSVObservableList = FXCollections.observableArrayList(khoanThuSVList);

        MSSV.setCellValueFactory(new PropertyValueFactory<KhoanThuSV, String>("MSSV"));
        trangThai.setCellValueFactory(new PropertyValueFactory<KhoanThuSV, String>("trangThai"));
        tongTien.setCellValueFactory(new PropertyValueFactory<KhoanThuSV,Integer>("tongTienThu"));
        checkThanhToan.setCellValueFactory(new PropertyValueFactory<KhoanThuSV, CheckBox>("selected"));

        tableKTSV.setItems(khoanThuSVObservableList);

        sinhVienDAOimpl = new SinhVienDAOimpl();
        tenSV.setCellValueFactory(p -> {
                    KhoanThuSV khoanThuSV = p.getValue();
                    try {
                        sinhVien = sinhVienDAOimpl.findByPK(khoanThuSV.getMSSV());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(sinhVien.getTenSV());
                }
        );

        stt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhoanThuSV, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<KhoanThuSV, String> p) {
                return new ReadOnlyObjectWrapper(tableKTSV.getItems().indexOf(p.getValue()) + "");
            }
        });
        stt.setSortable(false);

    }

    List<String> stringList = new ArrayList<>();
    ObservableList<String> stringObservableList = FXCollections.observableArrayList("T???t c???","Ch??a thanh to??n","???? thanh to??n") ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DotDangKy d: dotDangKyList) {
            stringList.add(d.getMaDotDK());
        }
        ObservableList<String> listMaDotDK = FXCollections.observableArrayList(stringList);
        dotDK.setItems(listMaDotDK);
        trangThaiThanhToan.setItems(stringObservableList);
    }
}
