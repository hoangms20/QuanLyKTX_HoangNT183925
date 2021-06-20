package view.lop;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.LopDAOimpl;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Lop;
import model.MyConnection;

public class LopController implements Initializable {
    MyConnection myConnection = new MyConnection();
    LopDAOimpl lopDAOimpl = new LopDAOimpl();
    ObservableList<Lop> lopObservableList;
    List<Lop> list;
    public static Lop selected;


    {
        try {
            myConnection.connectDB();
            list = lopDAOimpl.findAll();
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
    private TableView<Lop> tableLop;

    @FXML
    private TextField tkLop;

    @FXML
    private TableColumn<Lop, String> maLop;

    @FXML
    private TableColumn<Lop, String> tenLop;

    @FXML
    private TableColumn<Lop, String> maVien;

    @FXML
    private BorderPane rootPaneLop;

    @FXML
    private TableColumn<Lop, String> sttLop;

    @FXML
    void timKiemLop(ActionEvent event) throws SQLException {
        list = lopDAOimpl.findLikeByPK(tkLop.getText());
        initialize(location, resources);

    }

    @FXML
    void themLop(ActionEvent event) {
        loadWindow("/view/lop/themlop/themlop.fxml", "Thêm lớp");

    }

    @FXML
    void xoaLop(ActionEvent event) throws SQLException {
        selected = tableLop.getSelectionModel().getSelectedItem();
        lopObservableList.remove(selected);
        lopDAOimpl.delete(selected);

    }

    @FXML
    void capNhatLop(ActionEvent event) throws SQLException {
        list = lopDAOimpl.findAll();
        initialize(location, resources);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lopObservableList = FXCollections.observableArrayList(list);

        maLop.setCellValueFactory(new PropertyValueFactory<Lop, String>("maLop"));
        tenLop.setCellValueFactory(new PropertyValueFactory<Lop, String>("tenLop"));
        maVien.setCellValueFactory(new PropertyValueFactory<Lop, String>("maVien"));
        tableLop.setItems(lopObservableList);


        sttLop.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Lop, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<Lop, String> p) {
                return new ReadOnlyObjectWrapper(tableLop.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttLop.setSortable(false);

    }
}
