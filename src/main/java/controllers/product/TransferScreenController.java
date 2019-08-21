package controllers.product;

import building.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import policeman.Department;
import product.Product;
import product.ProductTransfer;

public class TransferScreenController {

    @FXML
    private TableColumn<ProductTransfer, String> columnInRoom;

    @FXML
    private TextArea textAreaElements;

    @FXML
    private ChoiceBox<Department> choiceDepartmentFrom;

    @FXML
    private ChoiceBox<Room> choiceRoomIn;

    @FXML
    private ChoiceBox<Department> choiceDepartmentIn;

    @FXML
    private TableColumn<ProductTransfer, String> columnProduct;

    @FXML
    private TableView<ProductTransfer> tableTransfers;

    @FXML
    private ChoiceBox<?> choiceYear;

    @FXML
    private TableColumn<ProductTransfer, String> columnDepartmentFrom;

    @FXML
    private TableColumn<ProductTransfer, String> columnFromRoom;

    @FXML
    private TableColumn<ProductTransfer, String> columnDepartmentIn;

    @FXML
    private TableColumn<ProductTransfer, String> columnElements;

    @FXML
    private ChoiceBox<Product> choiceProduct;

    @FXML
    private ChoiceBox<Room> choiceRoomFrom;

    @FXML
    void clickSave(ActionEvent event) {

    }

    @FXML
    void clickGenerateDocument(ActionEvent event) {

    }

}

