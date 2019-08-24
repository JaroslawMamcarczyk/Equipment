package controllers.product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import product.ProductTransfer;

import java.io.IOException;
import java.time.Year;

public class TransferScreenController {

    @FXML
    private TableColumn<ProductTransfer, String> columnInRoom;

    @FXML
    private TableColumn<ProductTransfer, String> columnProduct;

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
    public void initialize() {
        GridPane gridPane = null;
        try {
            gridPane = FXMLLoader.load(getClass().getResource("/FXML/product/AddproductToTransferScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vBoxGeneral.getChildren().add(gridPane);
    }

    void clickSave(ActionEvent event) {

    }

    void clickGenerateDocument(ActionEvent event) {

    }

}

