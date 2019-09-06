package controllers;

import Dao.buildingDao.BuildingDao;
import Dao.buildingDao.RoomDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class MainScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private MenuItem menuItemAddProduct;
    @FXML
    ImageView imageView;
    @FXML
    Menu menuCalendar;
    private static MainScreenController mainScreenController;
    private BuildingDao buildingDao = new BuildingDao();
    private RoomDao roomDao = new RoomDao();

    public static MainScreenController getMainScreenController(){ return mainScreenController;}

    public void initialize() {
        mainScreenController = this;
        menuItemAddProduct.setOnAction(click-> createCenterPane("/FXML/product/AddProductScreen.fxml"));
        menuCalendar.setOnAction(click->createCenterPane("/FXML/CalendarScreen.fxml"));
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
    @FXML
    void clickShowBuilding(){createCenterPane("/FXML/building/EditBuildingScreen.fxml");}
    @FXML
    void clickCreateBuilding(){
        AnchorPane pane = new AnchorPane();
        Stage stage = new Stage();
        try {
            pane = FXMLLoader.load(getClass().getResource("/FXML/building/AddBuildingScreen.fxml"));
            stage.setScene(new Scene(pane,300,200));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}