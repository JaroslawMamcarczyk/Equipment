package controllers.building;

import Dao.buildingDao.BuildingDao;
import Dao.productDao.ProductDao;
import building.Building;
import building.Room;
import controllers.building.DetailRoomScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import product.Product;

import java.util.ArrayList;
import java.util.List;

public class KitController {
    @FXML
    private TableView<Product> tableViewProduct;
@FXML
private TabPane tabPaneGeneral;
    @FXML
    private HBox hBoxMenu;

    @FXML
    private TableColumn<Product, String> columnEvidential;

    @FXML
    private TableColumn<Product, String> columnName;

    private ProductDao productDao = new ProductDao();
    private List<Product> productList = new ArrayList<>();
    private static Product productToMove = null;
    private BuildingDao buildingDao = new BuildingDao();
    public static Product getProductToMove(){return productToMove;}
    public void initialize(){
    productList=productDao.getList();
    createTabElement();
        settable();
        tableViewProduct.setOnDragDetected(mouseEvent -> {
            Product product = tableViewProduct.getSelectionModel().getSelectedItem();
            createDragOnTable(product);
            mouseEvent.consume();
        });
}

    private void createDragOnTable(Product product) {
        if(product!=null){
            productToMove = product;
            Dragboard db= tableViewProduct.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("fromKit");
            db.setContent(content);
        }
    }

    private void settable(){
    List<Product> productList = productDao.getListWhenRoomIsNull();
    ObservableList<Product> productObservableList = FXCollections.observableArrayList(productList);
        tableViewProduct.setItems(productObservableList);
        columnName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnEvidential.setCellValueFactory(new PropertyValueFactory<>("evidentialNumber"));
}

private void createTabElement(){
    for(Building building:buildingDao.getList()) {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        for(Product product:productList){
            if(product.getRoomNumber()!=null && product.getRoomNumber().getBuilding().equals(building)){
                productObservableList.add(product);
            }
        }
        System.out.println(productObservableList.size());
        Tab tab = new Tab(building.getName());
        TableView<Product> tableView = new TableView<>();
        TableColumn<Product, String> columnName = new TableColumn<>("Produkt");
        TableColumn<Product, String> columnEvidential = new TableColumn<>("Nr. ewid.");
        columnName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnEvidential.setCellValueFactory(new PropertyValueFactory<>("evidentialNumber"));
        tableView.getColumns().addAll(columnName,columnEvidential);
        tableView.setItems(productObservableList);
        tableView.setOnDragDetected(mouseEvent->{
            Product product = tableView.getSelectionModel().getSelectedItem();
            createDragOnTable(product);
            mouseEvent.consume();
        });
        tab.setContent(tableView);
        tabPaneGeneral.getTabs().add(tab);
    }
    }
}
