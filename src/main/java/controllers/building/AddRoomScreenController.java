package controllers.building;


import building.Room;
import Dao.buildingDao.RoomDao;
import controllers.MainScreenController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRoomScreenController {
    @FXML
    private ChoiceBox<Room.KindOfRoom> choiceBoxKindOfRoom;
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
public void initialize(){
        choiceBoxKindOfRoom.setItems(FXCollections.observableArrayList(Room.KindOfRoom.values()));
        choiceBoxKindOfRoom.setValue(Room.KindOfRoom.POKÓJ);
}
@FXML
   public void clickSaveRoom(ActionEvent e){
        if(textFieldDescription.getText()!=null) {
            Room room = new Room(textFieldNumberOfRoom.getText(), EditBuildingScreenController.getFloorToAdd(), EditBuildingScreenController.getCheckedBuilding(), EditBuildingScreenController.getPosX(), EditBuildingScreenController.getPosY(),textFieldDescription.getText(), choiceBoxKindOfRoom.getValue());
            roomDao.save(room);
            isObjectSave.set(true);
        }
        else{
            Room room = new Room(textFieldNumberOfRoom.getText(), EditBuildingScreenController.getFloorToAdd(), EditBuildingScreenController.getCheckedBuilding(), EditBuildingScreenController.getPosX(), EditBuildingScreenController.getPosY(), choiceBoxKindOfRoom.getValue());
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
