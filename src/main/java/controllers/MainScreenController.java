package controllers;

import Dao.buildingDao.BuildingDao;
import Dao.buildingDao.RoomDao;
import building.Building;
import building.Room;
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
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Tworzenie Nowego Budynku");
        textInputDialog.setContentText("Podaj nazwę budynku");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(resultFromDialog->{
            Building buildingNew = new Building(result.get());
            buildingDao.save(buildingNew);
            Room room = new Room(" ", Room.Floor.PARTER,buildingNew,1,1, Room.KindOfRoom.KORYTARZ);
            roomDao.save(room);
        });
    }
}