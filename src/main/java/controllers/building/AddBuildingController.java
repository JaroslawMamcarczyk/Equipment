package controllers.building;
import Dao.buildingDao.BuildingDao;
import Dao.buildingDao.RoomDao;
import building.Building;
import building.Room;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class AddBuildingController {
    @FXML
    private TextField textFieldName;

    @FXML
    private CheckBox checkBoxSecondFloor;

    @FXML
    private CheckBox checkBoxBasement;

    @FXML
    private CheckBox checkBoxGroundFloor;

    @FXML
    private CheckBox checkBoxThirdFloor;

    @FXML
    private CheckBox checkBoxFirstFloor;
    @FXML
    private Button buttonSave;


    @FXML
    void clickSave(ActionEvent event) {
        Boolean isOk = false;
        String floor = "";
        List<Room> roomList = new ArrayList<>();
        if(checkBoxThirdFloor.isSelected()){
            Room room = new Room(" ", Room.Floor.TRZECIE,null,0,0, Room.KindOfRoom.KORYTARZ);
            roomList.add(room);
            floor = floor+"4";
            isOk = true;
        }
        if (checkBoxSecondFloor.isSelected()){
            floor = floor+"3";
            Room room = new Room(" ", Room.Floor.DRUGIE,null,0,0, Room.KindOfRoom.KORYTARZ);
            roomList.add(room);
            isOk = true;
        }
        if(checkBoxFirstFloor.isSelected()){
            floor = floor+"2";
            Room room = new Room(" ", Room.Floor.PIERWSZE,null,0,0, Room.KindOfRoom.KORYTARZ);
            roomList.add(room);
            isOk = true;
        }
        if (checkBoxGroundFloor.isSelected()){
            floor = floor+"1";
            Room room = new Room(" ", Room.Floor.PARTER,null,0,0, Room.KindOfRoom.KORYTARZ);
            roomList.add(room);
            isOk = true;
        }
        if(checkBoxBasement.isSelected()){
            Room room = new Room(" ", Room.Floor.PIWNICA,null,0,0, Room.KindOfRoom.KORYTARZ);
            roomList.add(room);
            floor = floor+"5";
            isOk = true;
        }
        BuildingDao buildingDao = new BuildingDao();
        if(!textFieldName.getText().equals(" ")&&isOk) {
            Building building = new Building(textFieldName.getText(), floor);
            buildingDao.save(building);
            RoomDao roomDao = new RoomDao();
            for(Room room:roomList) {
                room.setBuilding(building);
                roomDao.save(room);
                Stage stage = (Stage)buttonSave.getScene().getWindow();
                stage.close();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd tworzenia nowego budynku");
            alert.setContentText("Nie podałeś nazwy, lub nie wybrałeś przynajmniej jednego piętra");
            alert.showAndWait();
        }
    }

    @FXML
    void clickCancel(ActionEvent event) {
        Stage stage = (Stage)buttonSave.getScene().getWindow();
        stage.close();
    }
}
