package controllers.building;

import Dao.buildingDao.BuildingDao;
import building.Building;
import building.Room;
import Dao.buildingDao.RoomDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class EditBuildingScreenController {
    @FXML
    private GridPane gridPaneGeneral;
    @FXML
    private ToggleButton buttonBasement;
    @FXML
    private ToggleButton buttonGroundFloor;
    @FXML
    private ToggleButton buttonSecondFloor;
    @FXML
    private ToggleButton buttonThirdFloor;
    @FXML
    private ToggleButton buttonFirstFloor;
    @FXML
    private Button buttonEdit;
    @FXML
    private VBox vBoxBuilding;
    private static Building checkedBuilding = null;
    private static Room roomToEdit = null;
    private static Room roomDetails = null;

   private BuildingDao buildingDao = new BuildingDao();
   private RoomDao roomDao = new RoomDao();
    private static int roomToModify=0;

    public static Building getCheckedBuilding(){return checkedBuilding;}
    public static Room getRoomDetails() { return roomDetails; }
    public static Room getRoomToEdit(){ return roomToEdit;}
    public static int getRoomToModify(){ return roomToModify ;}



    public void initialize() {
        gridPaneGeneral.setAlignment(Pos.CENTER);
        ToggleGroup toggleGroup = new ToggleGroup();
        for (Building building : buildingDao.getList()) {
            ToggleButton button = new ToggleButton(building.getName());
            button.setToggleGroup(toggleGroup);
            button.setPrefWidth(100);
            button.setOnMouseClicked(click -> {
                setMenuButton(building);
                checkedBuilding = building;
            });
            vBoxBuilding.getChildren().add(button);
        }
        AddRoomScreenController.getIsObjectSave().addListener(observable -> {
            if (AddRoomScreenController.getIsObjectSave().get()) {
                clickEditBuilding(roomDao.getListRoomFromBuilding(checkedBuilding,roomToEdit.getFloor()));
            AddRoomScreenController.setIsObjectSave(false);
            }
        });

    }

    private void addNewRoom(Room room, int modify){
        AnchorPane anchorPane = null;
        roomToEdit = room;
        roomToModify = modify;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/FXML/building/AddRoomScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane, 240, 300));
        stage.show();
    }

    void clickEditBuilding(List<Room> list) {
        gridPaneGeneral.getChildren().clear();
        gridPaneGeneral.setHgap(6);
        gridPaneGeneral.setVgap(6);
       for (Room room : list) {
          GridPane gridPane = new GridPane();
           gridPane.setHgap(6);
           gridPane.setVgap(6);
           gridPane.setPrefSize(130, 130);
           Button buttonAdUp = new Button("dodaj");
           buttonAdUp.setStyle("-fx-font-size: 10");
           buttonAdUp.setOnMouseClicked(click->addNewRoom(room,1));
           Button buttonAdDown = new Button("Dodaj");
           buttonAdDown.setStyle("-fx-font-size: 10");
           buttonAdDown.setOnMouseClicked(click->addNewRoom(room,3));
           Button buttonAdLeft = new Button("Dodaj");
           buttonAdLeft.setStyle("-fx-font-size: 10");
           buttonAdLeft.setOnMouseClicked(click->addNewRoom(room,4));
           Button buttonAdRight = new Button("Dodaj");
           buttonAdRight.setStyle("-fx-font-size: 10");
           buttonAdRight.setOnMouseClicked(click->addNewRoom(room,2));
           Button buttonEdit = new Button("Edytuj");
           buttonEdit.setStyle("-fx-font-size: 10");
           Label label = new Label(room.getNumber()+" "+room.getDescription());
           gridPane.add(buttonAdDown,1,3);
           gridPane.add(buttonAdLeft,0,2);
           gridPane.add(buttonEdit,1,2);
           gridPane.add(buttonAdRight,2,2);
           gridPane.add(buttonAdUp,1,1);
           gridPane.add(label,0,0,3,1);
           gridPaneGeneral.add(gridPane,room.getPositionX(),room.getPositionY(),room.getSizeX(),room.getSizeY());
        }
    }


private void setMenuButton(Building building){
        if(building.getCountFloor().contains("1")){
            buttonGroundFloor.setVisible(true);
            buttonGroundFloor.setOnMouseClicked(click->clickToggleButton(roomDao.getListRoomFromBuilding(checkedBuilding, Room.Floor.PARTER)));
        }
        if(building.getCountFloor().contains("2")){
            buttonFirstFloor.setVisible(true);
            buttonFirstFloor.setOnMouseClicked(click->
                    clickToggleButton(roomDao.getListRoomFromBuilding(checkedBuilding, Room.Floor.PIERWSZE)));
            }
        if(building.getCountFloor().contains("3")){
            buttonSecondFloor.setVisible(true);
            buttonSecondFloor.setOnMouseClicked(click->clickToggleButton(roomDao.getListRoomFromBuilding(checkedBuilding, Room.Floor.DRUGIE)));
        }
        if(building.getCountFloor().contains("4")){
            buttonThirdFloor.setVisible(true);
            buttonThirdFloor.setOnMouseClicked(click->clickToggleButton(roomDao.getListRoomFromBuilding(checkedBuilding, Room.Floor.TRZECIE)));
}
        if(building.getCountFloor().contains("5")){
            buttonBasement.setVisible(true);
            buttonBasement.setOnMouseClicked(click->clickToggleButton(roomDao.getListRoomFromBuilding(checkedBuilding, Room.Floor.PIWNICA)));
        }
}

public void clickToggleButton(List<Room> list){
        gridPaneGeneral.getChildren().clear();
        buttonEdit.setOnMouseClicked(click->clickEditBuilding(list));
            for(Room room:list){
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setOnMouseClicked(click -> {
                roomDetails = room;
                try {
                    AnchorPane anchor = FXMLLoader.load(getClass().getResource("/FXML/building/DetailsRoomScreen.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(anchor, 800, 400));
                      stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            anchorPane.setPrefSize(100, 100);
            anchorPane.setStyle("-fx-border-color: black");
            if(room.getKindOfRoom().equals(Room.KindOfRoom.POKÓJ)) {
                anchorPane.setStyle("-fx-background-color: blue");
            }
            VBox vBox = new VBox();
           Label labelName = new Label(room.getNumber() + " - " + room.getDescription());
                vBox.getChildren().add(labelName);
            if(room.getDepartment()!=null) {
                Label labelDepartment = new Label(room.getDepartment().getDepartmentName());
                vBox.getChildren().add(labelDepartment);
            }
            anchorPane.getChildren().add(vBox);
            gridPaneGeneral.add(anchorPane,room.getPositionX(),room.getPositionY());
        }

}
}
