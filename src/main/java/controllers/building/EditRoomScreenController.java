package controllers.building;

import Dao.buildingDao.RoomDao;
import building.Room;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class EditRoomScreenController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ToggleGroup group;
    private Room room = EditBuildingScreenController.getRoomDetails();
    private RoomDao roomDao = new RoomDao();

    @FXML
    public void initialize(){
        VBox vBox = new VBox();
        if(room.getNumber()!=null&& !room.getNumber().equals("")) {
            Label labelNumber = new Label(room.getNumber());
            vBox.getChildren().add(labelNumber);
        }
        if(room.getDescription()!=null&& room.getDescription().equals("")){
            Label labelDescription = new Label(room.getDescription());
            vBox.getChildren().add(labelDescription);
        }
        if(room.getDepartment()!=null) {
            Label labelDepartment = new Label(room.getDepartment().getDepartmentName());
        vBox.getChildren().add(labelDepartment);
        }
        anchorPane.getChildren().add(vBox);
    }

    @FXML
    void clickDelete(ActionEvent event) {
    roomDao.delete(room.getId());
    EditBuildingScreenController.setIsObjectSave(true);
    }

    @FXML
    void clickChangeNumber(ActionEvent event) {

    }

    @FXML
    void clickChangeDescription(ActionEvent event) {

    }

    @FXML
    void clickChangeDepartment(ActionEvent event) {

    }

    @FXML
    void clickMoveRoom(ActionEvent event) {

    }
}
