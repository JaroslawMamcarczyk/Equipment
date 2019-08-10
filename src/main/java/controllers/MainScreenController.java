package controllers;

import building.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
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
    private MenuItem editBuildingMenu = new MenuItem("Edytuj budynki");
    @FXML
    private Menu menuShowBuilding;
    @FXML
    private MenuItem menuItemAddProduct;
    private static Building kindOfbuilding = null;
    private BooleanProperty isNewBuilding = new SimpleBooleanProperty(false);

    public void initialize() {
        BuildingDao buildingDao = new BuildingDao();
        isNewBuilding.addListener(observable -> {
            if(isNewBuilding.get()==true){
                createMenuBuilding(buildingDao);
                isNewBuilding.set(false);
            }
        });
        menuItemAddProduct.setOnAction(click-> createCenterPane("/FXML/AddProductScreen.fxml"));

        createCenterPane("/FXML/CalendarScreen.fxml");
        //       Room room = new Room("2", Room.Floor.PARTER,building,1,1);
        RoomDao roomDao = new RoomDao();
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
                        createCenterPane("/FXML/BuildingScreen.fxml");
                    }
            );
            menuItemEdit.setOnAction(click-> {
                kindOfbuilding=building1;
                        createCenterPane("/FXML/EditBuildingScreen.fxml");
                    }
            );
            menuEditBuilding.getItems().add(menuItemEdit);
            menuShowBuilding.getItems().add(menuItem);
        }
    }

    private void createCenterPane (String path){
            Pane centerPane;
            try {
                centerPane = FXMLLoader.load(getClass().getResource(path));
                anchorGeneral.getChildren().clear();
                anchorGeneral.getChildren().add(centerPane);
                anchorGeneral.setRightAnchor(centerPane, 5.0);
                anchorGeneral.setLeftAnchor(centerPane, 5.0);
                anchorGeneral.setBottomAnchor(centerPane, 5.0);
                anchorGeneral.setTopAnchor(centerPane, 5.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Building getKindOfBuilding () {
            return kindOfbuilding;
        }


}