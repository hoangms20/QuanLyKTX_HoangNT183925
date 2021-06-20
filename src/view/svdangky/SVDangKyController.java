package view.svdangky;

import dao_impl.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.chitietsinhvien.ChiTietSinhVienController;

public class SVDangKyController implements Initializable {

    MyConnection myConnection = new MyConnection();
    SinhVienDAOimpl sinhVienDAOimpl = new SinhVienDAOimpl();
    ObservableList<SinhVien> sinhVienObservableList;
    List<SinhVien> sinhVienList;

    DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();
    List<DotDangKy> dotDangKyList;

    UuTienDAOimpl uuTienDAOimpl = new UuTienDAOimpl();

    TrangThaiSVDAOimpl trangThaiSVDAOimpl = new TrangThaiSVDAOimpl();


    {
        try {
            myConnection.connectDB();
            dotDangKyList = dotDangKyDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ;
    public static SinhVien selectedSinhVien;
    public static DotDangKy selectedDotDangKy;

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
    private TableView<SinhVien> tableSVDK;

    @FXML
    private TableColumn<SinhVien, Date> ngaySinhSVDK;

    @FXML
    private TableColumn<SinhVien, String> gioiTinhSVDK;

    @FXML
    private TableColumn<SinhVien, String> sttSVDK;

    @FXML
    private TableColumn<SinhVien, String> hoTenSVDK;

    @FXML
    private TextField tkmssv;

    @FXML
    private Label slDaDangKy;

    @FXML
    private TableColumn<SinhVien, String> maLopSVDK;

    @FXML
    private TableColumn<SinhVien, String> mssvDK;

    @FXML
    private TableColumn<SinhVien, String> queQuanSVDK;

    @FXML
    private TableColumn<SinhVien, String> trangThaiSVDK;

    @FXML
    private TableColumn<SinhVien, String> uuTienSVDK;

    @FXML
    private ComboBox<String> dotDKSV;

    @FXML
    void timKiemSVDK(ActionEvent event) throws SQLException {
        sinhVienList = sinhVienDAOimpl.findLikeByPKDotDK(tkmssv.getText(), dotDKSV.getValue());
        setTableSVDK();

    }

    @FXML
    void themSV(ActionEvent event) throws SQLException {
        selectedDotDangKy = new DotDangKy();
        selectedDotDangKy.setMaDotDK(dotDKSV.getValue());
        loadWindow("/view/svdangky/themsv/themsv.fxml", "Sinh viên đăng ký");

    }

    SinhVienDotDKDAOimpl sinhVienDotDKDAOimpl = new SinhVienDotDKDAOimpl();
    SinhVienDotDK sinhVienDotDK;
    @FXML
    void xoaSV(ActionEvent event) throws SQLException {
        selectedSinhVien = tableSVDK.getSelectionModel().getSelectedItem();
        sinhVienObservableList.remove(selectedSinhVien);
        sinhVienDotDK = new SinhVienDotDK(selectedSinhVien.getMSSV(), dotDKSV.getValue());
        sinhVienDotDKDAOimpl.delete(sinhVienDotDK);
        sinhVienDAOimpl.delete(selectedSinhVien);

    }

    public static List<SinhVien> sinhVienList1 = new ArrayList<>();
    @FXML
    void taiLenTuTep(ActionEvent event) throws IOException, SQLException {
        FileInputStream file = new FileInputStream("svdk.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        selectedDotDangKy = new DotDangKy();
        selectedDotDangKy.setMaDotDK(dotDKSV.getValue());
        for(Row row: sheet){
            SinhVien s = new SinhVien();
            s.setMSSV(String.valueOf(row.getCell(1)));
            s.setMaUuTien(String.valueOf(row.getCell(2)));
            s.setMaLop(String.valueOf(row.getCell(3)));
            s.setMaKhoa(String.valueOf(row.getCell(4)));
            s.setMaTrangThai(0);
            s.setTenSV(String.valueOf(row.getCell(5)));
            s.setNgaySinh(Date.valueOf(String.valueOf(row.getCell(6))));
            s.setGioiTinh(String.valueOf(row.getCell(7)));
            s.setCMND(String.valueOf(row.getCell(8)));
            s.setSDT(String.valueOf(row.getCell(9)));
            s.setEmail(String.valueOf(row.getCell(10)));
            s.setQueQuan(String.valueOf(row.getCell(11)));
            s.setNgayDangKy(Date.valueOf(String.valueOf(row.getCell(12))));
            sinhVienList1.add(s);
        }
        wb.close();
        file.close();
        loadWindow("/view/svdangky/tailentutepexcel/svdkexcel.fxml", "Sinh viên đăng ký");

    }

    @FXML
    void capNhatDSSVDK(ActionEvent event) throws SQLException {
        sinhVienList = sinhVienDAOimpl.findByDotDK(dotDKSV.getValue());
        slDaDangKy.setText(String.valueOf(sinhVienDotDKDAOimpl.countSLDK(dotDKSV.getValue())));
        setTableSVDK();

    }

    UuTien uuTien = new UuTien();
    TrangThaiSV trangThaiSV = new TrangThaiSV();
    public void setTableSVDK() {
        sinhVienObservableList = FXCollections.observableArrayList(sinhVienList);

        ngaySinhSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, Date>("ngaySinh"));
        mssvDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("MSSV"));
        hoTenSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));
        maLopSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maLop"));
        gioiTinhSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));
        queQuanSVDK.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("queQuan"));
        tableSVDK.setItems(sinhVienObservableList);

        uuTienSVDK.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        uuTien = uuTienDAOimpl.findByPK(sinhVien.getMaUuTien());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(uuTien.getTenUuTien());
                }
        );

        trangThaiSVDK.setCellValueFactory(p -> {
                    SinhVien sinhVien = p.getValue();
                    try {
                        trangThaiSV = trangThaiSVDAOimpl.findByPK(sinhVien.getMaTrangThai());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(trangThaiSV.getTenTrangThai());
                }
        );

        sttSVDK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SinhVien, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SinhVien, String> p) {
                return new ReadOnlyObjectWrapper(tableSVDK.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttSVDK.setSortable(false);

        tableSVDK.setRowFactory( tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SinhVien rowData = row.getItem();
                    ChiTietSinhVienController.selectedSinhVien = tableSVDK.getSelectionModel().getSelectedItem();
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
        dotDKSV.setItems(listMaDotDK);

    }
}

