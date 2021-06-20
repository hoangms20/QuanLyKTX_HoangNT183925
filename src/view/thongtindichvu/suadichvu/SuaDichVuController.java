package view.thongtindichvu.suadichvu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao_impl.DichVuPhongDAOimpl;
import dao_impl.LoaiHoaDonDAOimpl;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DichVuPhong;
import model.LoaiHoaDon;
import view.thongtindichvu.ThongTinDichVuController;

public class SuaDichVuController implements Initializable {

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
    private TableColumn<DichVuPhong, TextField> soLuongSDMoi;

    @FXML
    private TableColumn<DichVuPhong, String> tenDV;

    @FXML
    private TableColumn<DichVuPhong, Integer> soLuongSDCu;

    @FXML
    void luuDV(ActionEvent event) throws SQLException {
        LoaiHoaDon loaiHoaDon;
        DichVuPhongDAOimpl dichVuPhongDAOimpl = new DichVuPhongDAOimpl();
        for (DichVuPhong d: dichVuPhongObservableList1) {
            loaiHoaDon = loaiHoaDonDAOimpl.findByPK(d.getMaLoaiHD());
            d.setSoLuongSuDung(Integer.parseInt(d.getTfSoLuongSuDung().getText()));
            Integer a = 0;
            a = loaiHoaDon.getGiaTri() * d.getSoLuongSuDung();
            d.setThanhTienDV(a);
            dichVuPhongDAOimpl.update(d);
        }

    }

    ObservableList<DichVuPhong> dichVuPhongObservableList1 = ThongTinDichVuController.dichVuPhongObservableList;
    LoaiHoaDonDAOimpl loaiHoaDonDAOimpl;
    LoaiHoaDon loaiHoaDon;
    public void setTableDV(){

        maPhongDV.setCellValueFactory(new PropertyValueFactory<DichVuPhong, String>("maP"));
        maDichVu.setCellValueFactory(new PropertyValueFactory<DichVuPhong, String>("maLoaiHD"));
        soLuongSDCu.setCellValueFactory(new PropertyValueFactory<DichVuPhong, Integer>("soLuongSuDung"));
        soLuongSDMoi.setCellValueFactory(new PropertyValueFactory<DichVuPhong, TextField>("tfSoLuongSuDung"));
        tableDV.setItems(dichVuPhongObservableList1);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableDV();
    }
}
