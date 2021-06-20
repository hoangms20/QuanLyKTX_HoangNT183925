package view.khoanthu;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.KhoanThuDAOimpl;
import dao_impl.VienDAOimpl;
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
import model.KhoanThu;
import model.MyConnection;

public class KhoanThuController implements Initializable {
    MyConnection myConnection = new MyConnection();
    KhoanThuDAOimpl khoanThuDAOimpl = new KhoanThuDAOimpl();
    ObservableList<KhoanThu> khoanThuObservableList;
    List<KhoanThu> list;
    public static KhoanThu selected;


    {
        try {
            myConnection.connectDB();
            list = khoanThuDAOimpl.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
    private TableColumn<KhoanThu, String> maKhoanThu;

    @FXML
    private TableColumn<KhoanThu, String> tenKhoanThu;

    @FXML
    private TableView<KhoanThu> tableKhoanThu;

    @FXML
    private TextField tkKhoanThu;

    @FXML
    private TableColumn<KhoanThu, String> sttKhoanThu;


    @FXML
    void timKiemKhoanThu(ActionEvent event) throws SQLException {
        list = khoanThuDAOimpl.findLikeByPK(tkKhoanThu.getText());
        initialize(location, resources);

    }


    @FXML
    void capNhatDSKhoanThu(ActionEvent event) throws SQLException {
        list = khoanThuDAOimpl.findAll();
        initialize(location, resources);

    }

    @FXML
    void themKhoanThu(ActionEvent event) {
        loadWindow("/view/khoanthu/themkhoanthu/themkhoanthu.fxml", "Thêm khoản thu");

    }


    @FXML
    void xoaKhoanThu(ActionEvent event) throws SQLException {
        selected = tableKhoanThu.getSelectionModel().getSelectedItem();
        khoanThuObservableList.remove(selected);
        khoanThuDAOimpl.delete(selected);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        khoanThuObservableList = FXCollections.observableArrayList(list);

        maKhoanThu.setCellValueFactory(new PropertyValueFactory<KhoanThu, String>("maKhoanThu"));
        tenKhoanThu.setCellValueFactory(new PropertyValueFactory<KhoanThu, String>("tenKhoanThu"));

        tableKhoanThu.setItems(khoanThuObservableList);


        sttKhoanThu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhoanThu, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<KhoanThu, String> p) {
                return new ReadOnlyObjectWrapper(tableKhoanThu.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttKhoanThu.setSortable(false);
    }
}
