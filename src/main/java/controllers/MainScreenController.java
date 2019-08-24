package controllers;

import Dao.buildingDao.BuildingDao;
import building.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Optional;

public class MainScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private Menu menuEditBuilding;
  //  private MenuItem editBuildingMenu = new MenuItem("Edytuj budynki");
    @FXML
    private Menu menuShowBuilding;
    @FXML
    private MenuItem menuItemAddProduct;
    @FXML
    ImageView imageView;
    private static Building kindOfbuilding = null;
    private BooleanProperty isNewBuilding = new SimpleBooleanProperty(false);
    private static MainScreenController mainScreenController;

    public static MainScreenController getMainScreenController(){ return mainScreenController;}

    public void initialize() {
        mainScreenController = this;
        BuildingDao buildingDao = new BuildingDao();
        isNewBuilding.addListener(observable -> {
            if(isNewBuilding.get()==true){
                createMenuBuilding(buildingDao);
                isNewBuilding.set(false);
            }
        });
        menuItemAddProduct.setOnAction(click-> createCenterPane("/FXML/product/AddProductScreen.fxml"));
        createMenuBuilding(buildingDao);
    }

    private void createMenuBuilding(BuildingDao buildingDao) {
        menuShowBuilding.getItems().clear();
        menuEditBuilding.getItems().clear();
        MenuItem menuItemCreateBuilding = new MenuItem("Stwórz budynek");
        menuItemCreateBuilding.setOnAction(click ->{
            TextInputDialog textInputDialog = new TextInputDialog("Podaj nazwę budynku");
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(resultFromDialog->{
                Building buildingNew = new Building(result.get());
                buildingDao.save(buildingNew);
                isNewBuilding.set(true);
            });
        });
        menuEditBuilding.getItems().add(menuItemCreateBuilding);
        for (Building building1 : buildingDao.getList()) {
            MenuItem menuItem = new MenuItem(building1.getName());
            MenuItem menuItemEdit = new MenuItem(building1.getName());
            menuItem.setOnAction(click -> {
                        kindOfbuilding = building1;
                        createCenterPane("/FXML/building/BuildingScreen.fxml");
                    }
            );
            menuItemEdit.setOnAction(click-> {
                kindOfbuilding=building1;
                        createCenterPane("/FXML/building/EditBuildingScreen.fxml");
                    }
            );
            menuEditBuilding.getItems().add(menuItemEdit);
            menuShowBuilding.getItems().add(menuItem);
        }
    }

    public  void createCenterPane (String path){
            Pane centerPane;
            try {
                centerPane = FXMLLoader.load(getClass().getResource(path));
                anchorGeneral.getChildren().clear();
                anchorGeneral.getChildren().add(centerPane);
                AnchorPane.setRightAnchor(centerPane, 5.0);
                AnchorPane.setLeftAnchor(centerPane, 5.0);
                AnchorPane.setBottomAnchor(centerPane, 5.0);
                AnchorPane.setTopAnchor(centerPane, 5.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Building getKindOfBuilding () {
            return kindOfbuilding;
        }

    @FXML
    void clickShowPoliceman(ActionEvent event) {
createCenterPane("/FXML/worker/ShowPolicemanScreen.fxml");
    }

    @FXML
    void clickAddPoliceman(ActionEvent event){
        createCenterPane("/FXML/worker/AddPolicemanScreen.fxml");
    }

    @FXML
    void clickShowPlatform(){

    }
    @FXML
    void clickShowKsip(){

    }
    @FXML
    void clickShowSwitch(){
        createCenterPane("/FXML/product/SwitchScreen.fxml");
    }

    @FXML
    void clickShowAllEquipment(){
createCenterPane("/FXML/product/ProductScreen.fxml");
    }

    @FXML
    void clickConfiguration(){
        createCenterPane("/FXML/ConfigurationDepartmentScreen.fxml");
    }
    @FXML
    void clickTransfer(){
        createCenterPane("/FXML/product/transferScreen.fxml");
    }
}