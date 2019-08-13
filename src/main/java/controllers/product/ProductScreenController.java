package controllers.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import product.Product;
import product.ProductDao;

import java.math.BigDecimal;

public class ProductScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, Integer> columnId;
    @FXML
    private TableColumn<Product, Product.ProductKind> columnKind;
    @FXML
    private TableColumn<Product, String> columnProductName;
    @FXML
    private TableColumn<Product, String> columnSerial;
    @FXML
    private TableColumn<Product, String> columnInventory;
    @FXML
    private TableColumn<Product, String> columnEwidential;
    @FXML
    private TableColumn<Product, BigDecimal> columnPrice;
    @FXML
    private TableColumn<Product, Integer> columnYear;
    @FXML
    private TableColumn<Product, String> columnType;
    @FXML
    private TableColumn<Product, String> columnComments;
    @FXML
    private TableColumn<Product, String> columnRoom;


    public  AnchorPane getAnchorGeneral(){
        return anchorGeneral;
    }

    @FXML
    void initialize(){
        ProductDao productDao = new ProductDao();
       ObservableList<Product> observableListProduct = FXCollections.observableArrayList(productDao.getList());
       setComputerTable(observableListProduct);
    }

    public void setComputerTable(ObservableList<Product> glist) {
        tableProduct.setItems(glist);
        columnId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        columnId.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.04));

           columnKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
           columnKind.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.07));

        columnProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnProductName.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.07));

        columnSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnSerial.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        columnInventory.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnEwidential.setCellValueFactory(new PropertyValueFactory<>("evidentialNumber"));
        columnEwidential.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        columnYear.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnType.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
        columnType.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));

        columnComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        columnComments.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.05));

        columnRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        columnRoom.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.05));

    }
}
