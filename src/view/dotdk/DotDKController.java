package view.dotdk;

import java.net.URL;
import java.security.PublicKey;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.DotDangKyDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import model.DotDangKy;
import model.MyConnection;

public class DotDKController implements Initializable {
    MyConnection myConnection = new MyConnection();
    DotDangKyDAOimpl dotDangKyDAOimpl = new DotDangKyDAOimpl();
    public static  ObservableList<DotDangKy> dangKyObservableList;
    List<DotDangKy> list;
    public static DotDangKy selected;

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
    private TableColumn<DotDangKy, Integer> slDangKy;

    @FXML
    private TextField tkDotDK;

    @FXML
    private TableColumn<DotDangKy, String> sttDotDK;

    @FXML
    private TableColumn<DotDangKy, String> maDotDK;

    @FXML
    private TableColumn<DotDangKy, String> tenDotDK;

    @FXML
    private TableView<DotDangKy> tableDotDK;

    @FXML
    private TableColumn<DotDangKy, Date> ngaybdDotDK;

    @FXML
    private TableColumn<DotDangKy, Date> ngayktDotDK;

    @FXML
    private TableColumn<DotDangKy, Integer> chiTieuDotDK;

    @FXML
    void timKiemDotDK(ActionEvent event) throws SQLException {
        list = dotDangKyDAOimpl.findLikeByPK(tkDotDK.getText());
        setTableDotDK();

    }

    @FXML
    void themDotDK(ActionEvent event) {
        loadWindow("/view/dotdk/themdotdk/themdotdk.fxml", "Thêm đợt đăng ký");

    }

    @FXML
    void xoaDotDK(ActionEvent event) throws SQLException {
        selected = tableDotDK.getSelectionModel().getSelectedItem();
        dangKyObservableList.remove(selected);
        dotDangKyDAOimpl.delete(selected);

    }

    @FXML
    void capNhatDotDK(ActionEvent event) throws SQLException {
        list = dotDangKyDAOimpl.findAll();
        setTableDotDK();

    }

    public void setTableDotDK(){
        dangKyObservableList = FXCollections.observableArrayList(list);

        chiTieuDotDK.setCellValueFactory(new PropertyValueFactory<DotDangKy, Integer>("chiTieu"));
        slDangKy.setCellValueFactory(new PropertyValueFactory<DotDangKy, Integer>("SLDangKy"));
        maDotDK.setCellValueFactory(new PropertyValueFactory<DotDangKy, String>("maDotDK"));
        tenDotDK.setCellValueFactory(new PropertyValueFactory<DotDangKy, String>("tenDotDK"));
        ngaybdDotDK.setCellValueFactory(new PropertyValueFactory<DotDangKy, Date>("ngayBatDauDK"));
        ngayktDotDK.setCellValueFactory(new PropertyValueFactory<DotDangKy, Date>("ngayKetThucDK"));
        tableDotDK.setItems(dangKyObservableList);


        sttDotDK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DotDangKy, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<DotDangKy, String> p) {
                return new ReadOnlyObjectWrapper(tableDotDK.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttDotDK.setSortable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            myConnection.connectDB();
            list = dotDangKyDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setTableDotDK();
    }
}

