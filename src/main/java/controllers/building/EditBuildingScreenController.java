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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import product.Product;
import java.io.IOException;
import java.util.ArrayList;
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
    private static Room.Floor floorToAdd = null;
    private static Room roomDetails = null;
    private static int posX;
    private static int posY;

   private BuildingDao buildingDao = new BuildingDao();
   private RoomDao roomDao = new RoomDao();

    private List<Room> roomsBasement = new ArrayList<>();
    private List<Room> roomsGroundFloor = new ArrayList<>();
    private List<Room> roomsFirstFloor = new ArrayList<>();
    private List<Room> roomSecondFloor = new ArrayList<>();
    private List<Room> roomsThirdFloor = new ArrayList<>();
//    private int roomToModify;
//    private Boolean isNewWindow = false;

    public static Building getCheckedBuilding(){return checkedBuilding;}
    public static Room getRoomDetails() { return roomDetails; }
    public static Room.Floor getFloorToAdd(){ return floorToAdd;}
    public static int getPosX() { return posX; }public static int getPosY() { return posY; }


    public void initialize() {
        gridPaneGeneral.setAlignment(Pos.CENTER);
        ToggleGroup toggleGroup = new ToggleGroup();
        for(Building building:buildingDao.getList()){
            ToggleButton button = new ToggleButton(building.getName());
            button.setToggleGroup(toggleGroup);
            button.setOnMouseClicked(click->clickButtonBuilding(building));
            vBoxBuilding.getChildren().add(button);
        }
//        for (Room room : roomDao.getList()) {
//            setMenuButton(room);
//            clickToggleButton(roomsGroundFloor);
//            AddRoomScreenController.getIsObjectSave().addListener(observable -> {
//                if (AddRoomScreenController.getIsObjectSave().get()) {
//                    switch (roomToModify) {
//                        case 0:
//                            break;
//                        case 1: {
//                            for (Room rooms : roomList) {
//                                roomDao.changePositionX(rooms.getPositionX() + 1, rooms.getId());
//                            }
//                            break;
//                        }
//                        case 2: {
//                            for (Room rooms : roomList) {
//                                roomDao.changePositionY(rooms.getPositionY() + 1, rooms.getId());
//                            }
//                            break;
//                        }
//                    }
//                }
//            });
//            Stage stage = new Stage();
//            Parent child = null;
//            try {
//                child = FXMLLoader.load(getClass().getResource("/FXML/kit.fxml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            stage.setTitle("Przybornik");
//            stage.setScene(new Scene(child, 200, 300));
//            // stage.show();

   //     }
    }

//    private boolean compareRoom(int x, int y) {
//        Boolean result = false;
//        for (Room room : roomList) {
//            if (room.getPositionX() == x && room.getPositionY() == y)
//                result = true;
//        }
//        return result;
//    }

//    private Button createButton(int x, int y, int modify) {
//        Button button = new Button("Dodaj");
//        button.setStyle("-fx-pref-height: 40px; -fx-pref-width: 100px; -fx-border-color: black");
//        button.setAlignment(Pos.CENTER);
//        button.setOnMouseClicked(mouseEvent -> {
//            AnchorPane anchorPane = null;
//            try {
//                anchorPane = FXMLLoader.load(getClass().getResource("/FXML/building/AddRoomScreen.fxml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            posX = x;
//            posY = y;
//            roomToModify = modify;
//            Stage stage = new Stage();
//            stage.setScene(new Scene(anchorPane, 300, 300));
//            stage.show();
//        });
//        return button;
//    }

//    void clickEditBuilding(List<Room> list) {
//        for (Room room : list) {
//            if (!compareRoom(room.getPositionX() - 1, room.getPositionY())) {
//                gridPaneGeneral.add(createButton(room.getPositionX() - 1, room.getPositionY(), 1), room.getPositionX() - 1, room.getPositionY());
//            }
//            if (!compareRoom(room.getPositionX() + 1, room.getPositionY())) {
//                gridPaneGeneral.add(createButton(room.getPositionX() + 1, room.getPositionY(), 0), room.getPositionX() + 1, room.getPositionY());
//            }
//            if (!compareRoom(room.getPositionX(), room.getPositionY() - 1)) {
//                gridPaneGeneral.add(createButton(room.getPositionX(), room.getPositionY() - 1, 2), room.getPositionX(), room.getPositionY() - 1);
//            }
//            if (!compareRoom(room.getPositionX(), room.getPositionY() + 1)) {
//                gridPaneGeneral.add(createButton(room.getPositionX(), room.getPositionY() + 1, 0), room.getPositionX(), room.getPositionY() + 1);
//            }
//        }
//    }


private void setMenuButton(Room room){
        switch (room.getFloor()){
            case PIWNICA:{
                floorToAdd = Room.Floor.PIWNICA;
                buttonBasement.setVisible(true);
                roomsBasement.add(room);
               // buttonBasement.setOnMouseClicked(click->clickToggleButton(roomsBasement));
                break;
            }
            case PARTER:{
                floorToAdd = Room.Floor.PARTER;
                buttonGroundFloor.setVisible(true);
                roomsGroundFloor.add(room);
             //   buttonGroundFloor.setOnMouseClicked(click->clickToggleButton(roomsGroundFloor));
                break;
            }
            case PIERWSZE:{
                floorToAdd = Room.Floor.PIERWSZE;
                buttonFirstFloor.setVisible(true);
                roomsFirstFloor.add(room);
            //    buttonFirstFloor.setOnMouseClicked(click->clickToggleButton(roomsFirstFloor));
                break;
            }
            case DRUGIE:{
                floorToAdd = Room.Floor.DRUGIE;
                buttonSecondFloor.setVisible(true);
                roomSecondFloor.add(room);
            //    buttonSecondFloor.setOnMouseClicked(click->clickToggleButton(roomSecondFloor));
                break;
            }
            case TRZECIE:{
                floorToAdd= Room.Floor.TRZECIE;
                buttonThirdFloor.setVisible(true);
                roomsThirdFloor.add(room);
             //   buttonThirdFloor.setOnMouseClicked(click->clickToggleButton(roomsThirdFloor));
                break;
            }
        }
}
//
//public void clickToggleButton(List<Room> lists){
//    buttonEdit.setOnMouseClicked(click->clickEditBuilding(lists));
//            for(Room room:lists){
//            AnchorPane anchorPane = new AnchorPane();
//            anchorPane.setOnMouseClicked(click -> {
//                roomDetails = room;
//                try {
//                    AnchorPane anchor = FXMLLoader.load(getClass().getResource("/FXML/building/DetailsRoomScreen.fxml"));
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(anchor, 800, 400));
//                    if (isNewWindow) {
//                        stage.setX(600);
//                        stage.setY(400);
//                        isNewWindow = false;
//                    } else {
//                        stage.setX(0);
//                        stage.setY(0);
//                        isNewWindow = true;
//                    }
//                    stage.show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            anchorPane.setPrefSize(100, 100);
//            if(room.getKindOfRoom().equals(Room.KindOfRoom.POKÓJ)) {
//                anchorPane.setStyle("-fx-background-color: blue; -fx-border-color: black");
//            }
//            Label labelName = new Label(room.getNumber() + " - " + room.getDescription());
//            anchorPane.getChildren().add(labelName);
//            gridPaneGeneral.add(anchorPane,room.getPositionX(),room.getPositionY());
//        }
//
//}
    private void clickButtonBuilding(Building building){
        for(Room room:roomDao.getListRoomFromBuilding(building)){
            setMenuButton(room);
        }
    }
}
