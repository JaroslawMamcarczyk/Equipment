package controllers;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import product.Product;
import product.ProductDao;
import product.ProductGroup;
import product.ProductGroupDao;

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
    private     ProductGroupDao productGroupDao = new ProductGroupDao();
    private BooleanProperty isNewProductgroup = new SimpleBooleanProperty(false);

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
        Product product = new Product(textName.getText(),textFieldSerial.getText(),choiceBoxKinde.getValue(),textFieldinventory.getText(),textFieldEvidential.getText(),new BigDecimal(textFieldPrice.getText()),Integer.parseInt(textFieldYear.getText()),choiceBoxGroup.getValue(),textFieldComment.getText(),choiceBoxRoom.getValue());
        productDao.save(product);
    }

    @FXML
    void clickSaveAndNext(ActionEvent event) {

    }
}
