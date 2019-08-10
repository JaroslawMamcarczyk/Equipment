package controllers;


import building.Room;
import building.RoomDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRoomScreenController {

    @FXML
    private VBox vBoxProduct;

    @FXML
    private ChoiceBox<?> choiceBoxProduct;

    @FXML
    private TextField textFieldDescription;
@FXML
private Button button;
    @FXML
    private TextField textFieldNumberOfRoom;
    private static BooleanProperty isObjectSave = new SimpleBooleanProperty(false);
    private RoomDao roomDao = new RoomDao();

    public static BooleanProperty getIsObjectSave(){
        return isObjectSave;
    }


@FXML
   public void clickSaveRoom(ActionEvent e){
        if(textFieldDescription.getText()!=null) {
            Room room = new Room(textFieldNumberOfRoom.getText(), Room.Floor.PARTER, MainScreenController.getKindOfBuilding(), EditBuildingScreenController.getPosX(), EditBuildingScreenController.getPosY(),textFieldDescription.getText());
            roomDao.save(room);
            isObjectSave.set(true);
        }
        else{
            Room room = new Room(textFieldNumberOfRoom.getText(), Room.Floor.PARTER, MainScreenController.getKindOfBuilding(), EditBuildingScreenController.getPosX(), EditBuildingScreenController.getPosY());
        roomDao.save(room);
        isObjectSave.set(true);
        }

Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

@FXML
    public void clickCancelRoom(ActionEvent event){
    Stage stage = (Stage) button.getScene().getWindow();
    stage.close();
}
}
