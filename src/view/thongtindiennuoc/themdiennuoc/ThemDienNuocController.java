package view.thongtindiennuoc.themdiennuoc;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DienNuocDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import dao_impl.PhongDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;

public class ThemDienNuocController implements Initializable {
    MyConnection myConnection = new MyConnection();
    ObservableList<DienNuoc> dienNuocObservableList;
    List<DienNuoc> dienNuocList;
    List<Phong> phongList;
    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl = new LoaiHoaDonDAOimpl();
    PhongDAOimpl phongDAOimpl = new PhongDAOimpl();
    DienNuocDAOimpl dienNuocDAOimpl = new DienNuocDAOimpl();


    {
        try {
            myConnection.connectDB();
            phongList = phongDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<DienNuoc, String> sttThemDN;

    @FXML
    private CheckBox cbNuoc;

    @FXML
    private TableColumn<DienNuoc, String> maPhongThemDN;

    @FXML
    private CheckBox cbDien;

    @FXML
    private TableColumn<DienNuoc, TextField> chiSoMoiThemDN;

    @FXML
    private TableColumn<DienNuoc, Integer> chiSoCuThemDN;

    @FXML
    private TableView tableDN;

    @FXML
    private DatePicker ngayGhiSoDN;

    @FXML
    void luuDN(ActionEvent event) throws SQLException {
        Date date = Date.valueOf(ngayGhiSoDN.getValue());
        LoaiHoaDon loaiHoaDon;
        if(cbDien.isSelected()){
            loaiHoaDon = loaiHoaDonDAOimpl.findByPK("LHD01");
        }else {
            loaiHoaDon = loaiHoaDonDAOimpl.findByPK("LHD02");
        }
        for (DienNuoc dienNuoc: dienNuocObservableList) {
            dienNuoc.setThangNamGhiSoDN(date);
            dienNuoc.setChiSoCuDN(dienNuoc.getChiSoMoiDN());
            dienNuoc.setChiSoMoiDN(Integer.parseInt(dienNuoc.getTfChiSoMoi().getText()));
            Integer sl = dienNuoc.getChiSoMoiDN() - dienNuoc.getChiSoCuDN();
            dienNuoc.setSLTieuThu(sl);
            Integer a = 0;
            if(sl <= loaiHoaDon.getMucHoTro()){
                a = sl * loaiHoaDon.getGiaTri();
            }else {
                a = loaiHoaDon.getMucHoTro() * loaiHoaDon.getGiaTri() + (sl - loaiHoaDon.getMucHoTro()) * loaiHoaDon.getTienVuotDM();
            }
            dienNuoc.setThanhTien(a);
            dienNuocDAOimpl.insert(dienNuoc);
        }
    }

    @FXML
    void selectNuoc(ActionEvent event) throws SQLException {
        cbDien.setSelected(false);
        dienNuocList = dienNuocDAOimpl.findNuocGanNhat();
        setTableDN();

    }

    @FXML
    void selectDien(ActionEvent event) throws SQLException {
        cbNuoc.setSelected(false);
        dienNuocList = dienNuocDAOimpl.findDienGanNhat();
        setTableDN();
    }

    public void setTableDN() {
        dienNuocObservableList = FXCollections.observableArrayList(dienNuocList);

        maPhongThemDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, String>("maP"));
        chiSoCuThemDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, Integer>("chiSoMoiDN"));
        chiSoMoiThemDN.setCellValueFactory(new PropertyValueFactory<DienNuoc, TextField>("tfChiSoMoi"));

        tableDN.setItems(dienNuocObservableList);

        sttThemDN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DienNuoc, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<DienNuoc, String> p) {
                return new ReadOnlyObjectWrapper(tableDN.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttThemDN.setSortable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

