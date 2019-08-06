package controllers;

import building.BuildingDao;
import building.Room;
import building.RoomDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;


public class BuildingScreenController {
    @FXML
    private StackPane stackPaneGeneral;
    private BuildingDao buildingDao = new BuildingDao();
    RoomDao roomDao = new RoomDao();

    public void initialize() {
        //roomDao.delete(5);
        GridPane gridPaneGeneral = new GridPane();
        gridPaneGeneral.setAlignment(Pos.CENTER);
        if (roomDao.getList().size() == 0) {

        } else {
            for (Room room : roomDao.getList()) {
              //  if (room.getBuilding().getId() == MainScreenController.getKindOfBuilding()) {
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.setPrefSize(200, 200);
                    Label labelName = new Label(room.getNumber());
                    labelName.setAlignment(Pos.TOP_CENTER);
                    anchorPane.getChildren().add(labelName);
                    anchorPane.setStyle("-fx-background-color: red; -fx-border-color: white");
                    gridPaneGeneral.add(anchorPane, room.getPositionX(), room.getPositionY());
                System.out.println(MainScreenController.getKindOfBuilding().getId()+" "+room.getBuilding().getId());
           //     }
            }
        }
    }
}