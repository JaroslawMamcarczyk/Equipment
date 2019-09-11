package controllers.product;

import Dao.policemanDao.DepartmentDao;
import Dao.productDao.ProductTransferDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import policeman.Department;
import product.ProductTransfer;
import java.io.IOException;
import java.time.Year;
import java.util.List;

public class TransferScreenController {

    @FXML
    private TableColumn<ProductTransfer, String> columnInRoom;

    @FXML
    private TableColumn<ProductTransfer, ProductTransfer> columnProduct;

    @FXML
    private TableView<ProductTransfer> tableTransfers;

    @FXML
    private ChoiceBox<Year> choiceYear;
    @FXML
    private TableColumn<ProductTransfer, String> columnDepartmentFrom;
    @FXML
    private TableColumn<ProductTransfer, String> columnFromRoom;
    @FXML
    private TableColumn<ProductTransfer, String> columnDepartmentIn;
    @FXML
    private TableColumn<ProductTransfer, String> columnElements;
    @FXML
    private VBox vBoxGeneral;
    @FXML
    private ChoiceBox<Department> choiceBoxDepartmentIn;
    @FXML
    private ChoiceBox<Department> choiceBoxDepartmentFor;
    private ProductTransferDao productTransferDao = new ProductTransferDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    @FXML
    public void initialize() {
        GridPane gridPane = null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("/FXML/product/AddProductToTransferScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vBoxGeneral.getChildren().add(gridPane);
        ObservableList<Year> yearObservableList = FXCollections.observableArrayList();
        for (int i=2019; i<=Integer.parseInt(Year.now().toString());i++){
            yearObservableList.add(Year.of(i));
        }
        choiceYear.setItems(yearObservableList);
        choiceYear.setValue(Year.now());
        createTable(productTransferDao.getListFromYear(choiceYear.getValue()));
        ObservableList<Department> departmentObservableList = FXCollections.observableArrayList(departmentDao.getList());
        choiceBoxDepartmentFor.setItems(departmentObservableList);
        choiceBoxDepartmentIn.setItems(departmentObservableList);
    }
@FXML
    void clickGenerateAll(ActionEvent event) {

    }
@FXML
    void clickGenerate(ActionEvent event) {

    }
public void createTable(List<ProductTransfer> productTransferList){
    ObservableList<ProductTransfer> productTransferObservableList = FXCollections.observableArrayList(productTransferList);
        tableTransfers.setItems(productTransferObservableList);
        columnProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        columnProduct.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.3));
        columnFromRoom.setCellValueFactory(new PropertyValueFactory<>("roomFrom"));
        columnFromRoom.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.15));
        columnInRoom.setCellValueFactory(new PropertyValueFactory<>("roomTo"));
        columnInRoom.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.15));
        columnDepartmentFrom.setCellValueFactory(new PropertyValueFactory<>("departmentFrom"));
        columnDepartmentFrom.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.1));
        columnDepartmentIn.setCellValueFactory(new PropertyValueFactory<>("departmentTo"));
        columnDepartmentIn.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.1));
        columnElements.setCellValueFactory(new PropertyValueFactory<>("elements"));
        columnElements.prefWidthProperty().bind(tableTransfers.prefWidthProperty().multiply(0.2));
}
}

