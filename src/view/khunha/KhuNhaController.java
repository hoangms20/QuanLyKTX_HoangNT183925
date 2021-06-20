package view.khunha;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao_impl.KhuNhaDAOimpl;
import dao_impl.SinhVienDAOimpl;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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
import model.KhuNha;
import model.MyConnection;

public class KhuNhaController implements Initializable {
    MyConnection myConnection = new MyConnection();
    KhuNhaDAOimpl khuNhaDAOimpl = new KhuNhaDAOimpl();
    ObservableList<KhuNha> khuNhaObservableList;
    List<KhuNha> list;
    public static KhuNha selected;

    {
        try {
            myConnection.connectDB();
            list = khuNhaDAOimpl.findAll();
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
    private TableColumn<KhuNha, String> maKhuNha;

    @FXML
    private TableColumn<KhuNha, String> slPhongKhuNha;

    @FXML
    private TableColumn<KhuNha, String> sttKhuNha;

    @FXML
    private TextField tkMaKhuNha;

    @FXML
    private TableView<KhuNha> tableKhuNha;

    @FXML
    private TableColumn<KhuNha, String> slConTrong;

    @FXML
    private TableColumn<KhuNha, String> tenKhuNha;

    @FXML
    void timKiemKhuNha(ActionEvent event) throws SQLException {
        list = khuNhaDAOimpl.findLikeByPK(tkMaKhuNha.getText());
        setTableKhuNha();

    }

    @FXML
    void themKhuNha(ActionEvent event) {
        loadWindow("/view/khunha/themkhunha/themkhunha.fxml", "Thêm khu nhà");

    }

    @FXML
    void xoaKhuNha(ActionEvent event) throws SQLException {
        selected = tableKhuNha.getSelectionModel().getSelectedItem();
        khuNhaObservableList.remove(selected);
        khuNhaDAOimpl.delete(selected);

    }

    @FXML
    void capNhatKhuNha(ActionEvent event) throws SQLException {
        list = khuNhaDAOimpl.findAll();
        setTableKhuNha();

    }

    public void setTableKhuNha(){
        khuNhaObservableList = FXCollections.observableArrayList(list);

        maKhuNha.setCellValueFactory(new PropertyValueFactory<KhuNha, String>("maKhu"));
        tenKhuNha.setCellValueFactory(new PropertyValueFactory<KhuNha, String>("tenKhu"));

        tableKhuNha.setItems(khuNhaObservableList);

        slConTrong.setCellValueFactory(p -> {
                    KhuNha khuNha = p.getValue();
                    Integer a=0;
                    try {
                        a = khuNhaDAOimpl.countSLConTrongKhuNha(khuNha);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(a));
                }
        );

        slPhongKhuNha.setCellValueFactory(p -> {
                    KhuNha khuNha = p.getValue();
                    Integer a=0;
                    try {
                        a = khuNhaDAOimpl.countSLPhong(khuNha);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return new ReadOnlyStringWrapper(String.valueOf(a));
                }
        );


        sttKhuNha.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhuNha, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<KhuNha, String> p) {
                return new ReadOnlyObjectWrapper(tableKhuNha.getItems().indexOf(p.getValue()) + "");
            }
        });
        sttKhuNha.setSortable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableKhuNha();

    }
}
