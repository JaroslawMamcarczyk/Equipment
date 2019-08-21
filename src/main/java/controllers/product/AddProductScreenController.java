package controllers.product;
import building.Building;
import building.BuildingDao;
import building.Room;
import building.RoomDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import product.Product;
import Dao.productDao.ProductDao;
import product.ProductGroup;
import Dao.productDao.ProductGroupDao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class AddProductScreenController {
    @FXML
    private TextField textFieldinventory;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private ChoiceBox<Room> choiceBoxRoom;
    @FXML
    private TextField textName;
    @FXML
    private TextField textFieldSerial;
    @FXML
    private TextField textFieldYear;
    @FXML
    private TextField textFieldEvidential;
    @FXML
    private ChoiceBox<Building> choiceBoxBuilding;
    @FXML
    private ChoiceBox<Product.ProductKind> choiceBoxKinde;
    @FXML
    private TextField textFieldComment;
    @FXML
    private ChoiceBox<ProductGroup> choiceBoxGroup;
    @FXML
    private AnchorPane anchorDetails;
    private     ProductGroupDao productGroupDao = new ProductGroupDao();
    private BooleanProperty isNewProductgroup = new SimpleBooleanProperty(false);
    private static Product productToSave = null;
    public static Product getProductToSave(){return productToSave;}

    @FXML
    void initialize(){
        RoomDao roomDao = new RoomDao();
        BuildingDao buildingDao = new BuildingDao();
        ObservableList<Room> observableListRoom = FXCollections.observableArrayList();
        choiceBoxBuilding.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)->{
        for(Room room:roomDao.getList()){
            if(room.getBuilding().getId()==newValue.getId()){
                observableListRoom.add(room);
            }
            choiceBoxRoom.setItems(observableListRoom);
        }
        });
        ObservableList<Building> observableListBuilding = FXCollections.observableArrayList(buildingDao.getList());
        choiceBoxBuilding.setItems(observableListBuilding);
        createProductGroupChoiceBox();
        isNewProductgroup.addListener(observable -> {
            if(isNewProductgroup.get()){
                isNewProductgroup.set(false);
                createProductGroupChoiceBox();
            }
        });
        choiceBoxKinde.setItems(FXCollections.observableArrayList(Product.ProductKind.INFORMATYKA, Product.ProductKind.ŁĄCZNOŚĆ));
        choiceBoxKinde.setValue(Product.ProductKind.INFORMATYKA);
    }

    private void createProductGroupChoiceBox() {
        ObservableList<ProductGroup> observableListProductGroup = FXCollections.observableArrayList(productGroupDao.getList());
        ProductGroup productGroup = new ProductGroup("Dodaj");
        observableListProductGroup.add(productGroup);
        choiceBoxGroup.setItems(observableListProductGroup);
        choiceBoxGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue.equals(productGroup)){
                TextInputDialog textInputDialog = new TextInputDialog("Podaj nazwę nowej grupy");
                Optional<String> result = textInputDialog.showAndWait();
                result.ifPresent(click->{
                    ProductGroup productGroupNew = new ProductGroup(result.get());
                    productGroupDao.save(productGroupNew);
                    isNewProductgroup.set(true);
                });
            }
        });
    }

    @FXML
    void clickSaveAndGo(ActionEvent event) {
        ProductDao productDao = new ProductDao();
        VBox vBox = null;
        Product product = new Product(textName.getText(),textFieldSerial.getText(),choiceBoxKinde.getValue(),textFieldinventory.getText(),textFieldEvidential.getText(),new BigDecimal(textFieldPrice.getText()),Integer.parseInt(textFieldYear.getText()),choiceBoxGroup.getValue(),textFieldComment.getText(),choiceBoxRoom.getValue());
        productDao.save(product);
        productToSave = product;
        if (choiceBoxGroup.getValue().getGroupName().equals("Switch")){
            try {
                 vBox = FXMLLoader.load(getClass().getResource("/FXML/product/AddSwitchScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        anchorDetails.getChildren().add(vBox);
    }

    @FXML
    void clickSaveAndNext(ActionEvent event) {

    }
}
