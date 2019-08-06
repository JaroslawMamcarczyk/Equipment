package controllers;

import building.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.apache.tinkerpop.gremlin.process.traversal.P;

import java.io.IOException;

public class MainScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private Menu menuEditBuilding;
    private MenuItem editBuildingMenu = new MenuItem("Edytuj budynki");
    @FXML
    private Menu menuShowBuilding;
    private static Building kindOfbuilding = null;

    public void initialize() {
        createCenterPane("/FXML/ProductScreen.fxml");
        Building building = new Building("Komisariat");
        BuildingDao buildingDao = new BuildingDao();
        //       Room room = new Room("2", Room.Floor.PARTER,building,1,1);
        RoomDao roomDao = new RoomDao();
        //   buildingDao.save(building);
        for (Building building1 : buildingDao.getList()) {
            MenuItem menuItem = new MenuItem(building1.getName());
            MenuItem menuItemEdit = new MenuItem(building1.getName());
            menuItem.setOnAction(click -> {
                        kindOfbuilding = building1;
                        createCenterPane("/FXML/BuildingScreen.fxml");
                    }
            );
            menuItemEdit.setOnAction(click-> {
                createCenterPane("/FXML/EditBuildingScreen.fxml");
            });
            menuEditBuilding.getItems().add(menuItemEdit);
            menuShowBuilding.getItems().add(menuItem);
//        Room room = new Room("2", Room.Floor.PARTER,buildingDao.getList().get(0),1,1);
//       roomDao.save(room);
            // roomDao.delete(3);
//       for(Room rooms:roomDao.getList()){
//           System.out.println(rooms.getId()+" "+rooms.getPositionX()+" "+rooms.getPositionY());
//       }
        }

    }
        private void createCenterPane (String path){
            Pane centerPane;
            try {
                centerPane = FXMLLoader.load(getClass().getResource(path));
                anchorGeneral.getChildren().clear();
                anchorGeneral.getChildren().add(centerPane);
                anchorGeneral.setRightAnchor(centerPane, 0.1);
                anchorGeneral.setLeftAnchor(centerPane, 0.1);
                anchorGeneral.setBottomAnchor(centerPane, 1.0);
                anchorGeneral.setTopAnchor(centerPane, 0.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Building getKindOfBuilding () {
            return kindOfbuilding;
        }


}