package controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import product.Product;

import java.math.BigDecimal;

public class ProductScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, String> columnSerial;
    @FXML
    private TableColumn<Product, Integer> columnYear;
    @FXML
    private TableColumn<Product, String> columnType;
    @FXML
    private TableColumn<Product, String> columnInventory;
    @FXML
    private TableColumn<Product, BigDecimal> columnPrice;
    @FXML
    private TableColumn<Product, Product> columnKind;
    @FXML
    private TableColumn<Product, String> columnId;
    @FXML
    private TableColumn<Product, String> columnEwidential;
    @FXML
    private TableColumn<Product, String> columnComments;
    @FXML
    private TableColumn<Product, String> columnProduct;

    public  AnchorPane getAnchorGeneral(){
        return anchorGeneral;
    }

    public void setComputerTable(ObservableList<Product> glist) {
        tableProduct.setItems(glist);
        columnSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        columnSerial.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        columnYear.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.08));
       // columnType.setCellValueFactory(new PropertyValueFactory<>(""));
      //  columnGate.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.08));
        columnInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        columnInventory.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
     //   columnKind.setCellValueFactory(new PropertyValueFactory<>("productKinde"));
      //  columnKind.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.07));
        columnEwidential.setCellValueFactory(new PropertyValueFactory<>("computerEwidential"));
        columnEwidential.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.1));
        columnId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        columnId.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.07));
        columnComments.setCellValueFactory(new PropertyValueFactory<>("coments"));
        columnComments.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.05));
        columnProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnProduct.prefWidthProperty().bind(tableProduct.widthProperty().multiply(0.07));
    }
}
