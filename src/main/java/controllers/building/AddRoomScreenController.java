package controllers.building;


import Dao.policemanDao.DepartmentDao;
import building.Room;
import Dao.buildingDao.RoomDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import policeman.Department;

import java.util.List;
import java.util.Optional;

public class AddRoomScreenController {
    @FXML
    private ChoiceBox<Room.KindOfRoom> choiceBoxKindOfRoom;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private Button button;
    @FXML
    private TextField textFieldNumberOfRoom;
    @FXML
    private ChoiceBox<Department> choiceBoxDepartment;
    private static BooleanProperty isObjectSave = new SimpleBooleanProperty(false);
    private RoomDao roomDao = new RoomDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private Room roomFromEditBuilding = EditBuildingScreenController.getRoomToEdit();
    private List<Room> listRoom = roomDao.getListRoomFromBuilding(roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getFloor());

    public static BooleanProperty getIsObjectSave(){
        return isObjectSave;
    }
    public static void setIsObjectSave(Boolean objectSave){ isObjectSave.set(objectSave);}
    private int toModify = EditBuildingScreenController.getRoomToModify();

@FXML
public void initialize(){
        choiceBoxKindOfRoom.setItems(FXCollections.observableArrayList(Room.KindOfRoom.values()));
        choiceBoxDepartment.setItems(FXCollections.observableArrayList(departmentDao.getList()));
        choiceBoxKindOfRoom.setValue(Room.KindOfRoom.POKÓJ);
        for(Room room:listRoom){
            System.out.println(room.getNumber()+"posX:"+room.getPositionX()+" posY:"+room.getPositionY());
        }
}
@FXML
public void clickSaveRoom(){
     Room roomToSave = null;
    if(choiceBoxKindOfRoom.getValue()!=null) {
        String description = "";
        if (textFieldDescription.getText() != null) description = textFieldDescription.getText();
        switch (toModify) {
            case 1: {
                if (roomFromEditBuilding.getPositionY() == 0) {
                    createAndMoveEverything(1);
                    roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), 0, description, choiceBoxKindOfRoom.getValue());
                }else{
                    Room roomConflict = isConflict(roomFromEditBuilding.getPositionX(),roomFromEditBuilding.getPositionY()-1);
                    if(roomConflict==null){
                        roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY()-1, description, choiceBoxKindOfRoom.getValue());
                    }else{
                        int result = createAlertWindow(roomConflict,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                        if(result==1){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                        }else if(result==0){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY()-1, description, choiceBoxKindOfRoom.getValue());
                        }else if(result==2){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY()-1, description, choiceBoxKindOfRoom.getValue());
                        }
                    }
                }
                break;
            }
            case 2: {
                Room roomConflict = isConflict(roomFromEditBuilding.getPositionX()+1,roomFromEditBuilding.getPositionY());
                if(roomConflict==null) {
                    roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                }else{
                    int result = createAlertWindow(roomConflict,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
               if(result==0){
                   roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
               }else if(result==1){
                   roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY()+1, description, choiceBoxKindOfRoom.getValue());
               }else if(result==2){
                   roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
               }
                }
                break;
            }
            case 3: {
                Room roomConflict = isConflict(roomFromEditBuilding.getPositionX(),roomFromEditBuilding.getPositionY()+1);
                if(roomConflict==null) {
                    roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY() + 1, description, choiceBoxKindOfRoom.getValue());
                }else{
                    int result = createAlertWindow(roomConflict,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                    if(result==0){
                        roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY()+1, description, choiceBoxKindOfRoom.getValue());
                    }else if(result==1){
                        roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY()+2, description, choiceBoxKindOfRoom.getValue());
                    }else if(result==2){
                        roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() + 1, roomFromEditBuilding.getPositionY()+1, description, choiceBoxKindOfRoom.getValue());
                    }
                }
                break;
            }
            case 4: {
                if (roomFromEditBuilding.getPositionX() == 0) {
                    createAndMoveEverything(2);
                    roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(),0, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                }else{
                    Room roomConflict = isConflict(roomFromEditBuilding.getPositionX()-1,roomFromEditBuilding.getPositionY());
                    if(roomConflict==null) {
                        roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(),roomFromEditBuilding.getPositionX()-1, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                    }else{
                        int result = createAlertWindow(roomConflict,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                        if(result==0){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() - 1, roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                        }else if(result==1){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX() - 1, roomFromEditBuilding.getPositionY()+1, description, choiceBoxKindOfRoom.getValue());
                        }else if(result==2){
                            roomToSave = new Room(textFieldNumberOfRoom.getText(), roomFromEditBuilding.getFloor(), roomFromEditBuilding.getBuilding(), roomFromEditBuilding.getPositionX(), roomFromEditBuilding.getPositionY(), description, choiceBoxKindOfRoom.getValue());
                        }
                    }
                }
                break;
            }
        }
        if (roomToSave != null) {
            if(choiceBoxDepartment.getValue()!=null){
                roomToSave.setDepartment(choiceBoxDepartment.getValue());
            }
            roomDao.save(roomToSave);
            isObjectSave.set(true);
        }
    }
    Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
}

    private int createAlertWindow(Room room, String text) {
        int isOk = 0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Problem z dodaniem nowego pokoju");
        alert.setHeaderText(text);
        alert.setContentText("Wybierz co chcesz zrobić z istniejącym pokojem");
        ButtonType moveUp = new ButtonType("Przesuń w górę");
        ButtonType moveDown = new ButtonType("Przesuń w dół");
        ButtonType moveLeft = new ButtonType("Przesuń w lewo");
        ButtonType moveRight = new ButtonType("Przesuń w prawo");
        alert.getButtonTypes().addAll(moveUp, moveRight, moveDown, moveLeft);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(moveUp)) {
                if (room.getPositionY() == 0) {
                    createAndMoveEverything(1);
                    roomDao.changePositionY(0, room.getId());
                    isOk = 1;
                } else {
                    Room roomNext = isConflict(room.getPositionX(), room.getPositionY() - 1);
                    if (roomNext == null) {
                        roomDao.changePositionY(room.getPositionY() - 1, room.getId());
                    }else{
                        int resultNext = createAlertWindow(roomNext,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                   if(resultNext==0){
                       roomDao.changePositionY(room.getPositionY()-1,room.getId());
                   }else if(resultNext==1){
                        roomDao.changePositionY(room.getPositionY(),room.getId());
                        isOk=1;
                    }else if(resultNext==2){
                        roomDao.changePositionY(room.getPositionY()-1,room.getId());
                        isOk=2;
                    }
                    }
                }
            }
                else if (result.get() == moveRight) {
                    Room roomNext = isConflict(room.getPositionX()+1,room.getPositionY());
                    if(roomNext == null){
                        roomDao.changePositionX(room.getPositionX()+1,room.getId());
                    }else{
                        int resultNext = createAlertWindow(roomNext,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                            roomDao.changePositionX(room.getPositionX()+1,room.getId());
                         if(resultNext==1){
                            isOk=1;
                        }else if(resultNext==2){
                            isOk=2;
                        }
                    }

                } else if (result.get() == moveDown) {
                    Room roomNext = isConflict(room.getPositionX(),room.getPositionY()+1);
                    if(roomNext == null){
                        roomDao.changePositionY(room.getPositionY()+1,room.getId());
                    }else{
                      int resultNext = createAlertWindow(roomNext,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                      roomDao.changePositionY(room.getPositionY()+1,room.getId());
                      if(resultNext==1){
                          isOk=1;
                      }else if(resultNext==2){
                          isOk=2;
                      }
                    }
                } else if (result.get() == moveLeft) {
                if (room.getPositionX() == 0) {
                    createAndMoveEverything(2);
                    roomDao.changePositionX(0, room.getId());
                    isOk = 2;
                } else {
                    Room roomNext = isConflict(room.getPositionX()-1, room.getPositionY());
                    if (roomNext == null) {
                        roomDao.changePositionX(room.getPositionX() - 1, room.getId());
                    }else{
                        int resultNext = createAlertWindow(roomNext,"Próba dodania nowego pokoju nie powiodła się. Wykryto już stworzony pokój na tej pozycji");
                        if(resultNext==0){
                            roomDao.changePositionX(room.getPositionX()-1,room.getId());
                        }else if(resultNext==1){
                            roomDao.changePositionY(room.getPositionX()-1,room.getId());
                            isOk=1;
                        }else if(resultNext==2){
                            roomDao.changePositionX(room.getPositionX(),room.getId());
                            isOk=2;
                        }
                    }
                }
                }
        }return isOk;
    }


    private void createAndMoveEverything(int modify) {
        switch (modify) {
            case 1: {
                for (Room roomFromList : listRoom) {
                    roomDao.changePositionY(roomFromList.getPositionY() + 1, roomFromList.getId());
                }
                break;
            }
            case 2:{
                for (Room roomFromList : listRoom) {
                    roomDao.changePositionX(roomFromList.getPositionX()+1 , roomFromList.getId());
                }
                break;
            }
        }
    }

    @FXML
    public void clickCancelRoom(ActionEvent event){
    Stage stage = (Stage) button.getScene().getWindow();
    stage.close();
}
@FXML
    public void clickGrowUpRoom(){
}

public Room isConflict(int x, int y){
    Room result = null;
    for(Room room1:listRoom){
        if(y==room1.getPositionY()&&x==room1.getPositionX()){
            result = room1;
        }
    }
    return result;
}
}
