package controllers.building;

import Dao.buildingDao.BuildingDao;
import building.Room;
import Dao.buildingDao.RoomDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EditBuildingScreenController {
    @FXML
    private StackPane stackPaneGeneral;
    private BuildingDao buildingDao = new BuildingDao();
    RoomDao roomDao = new RoomDao();
    private List<Room> roomList = roomDao.getList();
    private static int posX;
    private static int posY;
    private int roomToModify;

    public static int getPosX() {
        return posX;
    }

    public static int getPosY() {
        return posY;
    }

    public void initialize() {
        GridPane gridPaneGeneral = new GridPane();
        gridPaneGeneral.setAlignment(Pos.CENTER);
        if (roomList.size() == 0) {
            AnchorPane hall = new AnchorPane();
            Label label = new Label(buildingDao.getList().get(0).getName());
            label.setPrefSize(100.0, 100.0);
            label.setAlignment(Pos.CENTER);
            hall.getChildren().add(label);
            gridPaneGeneral.add(hall, 2, 2);
            gridPaneGeneral.add(createButton(2, 1, 0), 2, 1);
            gridPaneGeneral.add(createButton(1, 2, 0), 1, 2);
            gridPaneGeneral.add(createButton(2, 3, 0), 2, 3);
            gridPaneGeneral.add(createButton(3, 2, 0), 3, 2);
        } else {
            for (Room room : roomList) {
                AnchorPane anchorPane = new AnchorPane();
                Label labelName = new Label(room.getNumber()+" "+room.getDescription());
                labelName.setAlignment(Pos.TOP_CENTER);
                anchorPane.getChildren().add(labelName);
                anchorPane.setStyle("-fx-background-color: red; -fx-border-color: white");
                gridPaneGeneral.add(anchorPane, room.getPositionX(), room.getPositionY());
                if (!compareRoom(room.getPositionX() - 1, room.getPositionY())) {
                    gridPaneGeneral.add(createButton(room.getPositionX() - 1, room.getPositionY(), 1), room.getPositionX() - 1, room.getPositionY());
                }
                if (!compareRoom(room.getPositionX() + 1, room.getPositionY())) {
                    gridPaneGeneral.add(createButton(room.getPositionX() + 1, room.getPositionY(), 0), room.getPositionX() + 1, room.getPositionY());
                }
                if (!compareRoom(room.getPositionX(), room.getPositionY() - 1)) {
                    gridPaneGeneral.add(createButton(room.getPositionX(), room.getPositionY() - 1, 2), room.getPositionX(), room.getPositionY() - 1);
                }
                if (!compareRoom(room.getPositionX(), room.getPositionY() + 1)) {
                    gridPaneGeneral.add(createButton(room.getPositionX(), room.getPositionY() + 1, 0), room.getPositionX(), room.getPositionY() + 1);
                }

            }
        }
        AddRoomScreenController.getIsObjectSave().addListener(observable -> {
            if (AddRoomScreenController.getIsObjectSave().get()) {
                switch (roomToModify) {
                    case 0:
                        break;
                    case 1: {
                        for (Room room : roomList) {
                            roomDao.changePositionX(room.getPositionX() + 1, room.getId());
                        }
                        break;
                    }
                    case 2: {
                        for (Room room : roomList) {
                            roomDao.changePositionY(room.getPositionY() + 1, room.getId());
                        }
                        break;
                    }
                }
            }
        });
        stackPaneGeneral.getChildren().add(gridPaneGeneral);
        Stage stage = new Stage();
        Parent child = null;
        try {
            child = FXMLLoader.load(getClass().getResource("/FXML/kit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Przybornik");
        stage.setScene(new Scene(child, 200, 300));
        // stage.show();

    }

    private boolean compareRoom(int x, int y) {
        Boolean result = false;
        for (Room room : roomList) {
            if (room.getPositionX() == x && room.getPositionY() == y)
                result = true;
        }
        return result;
    }

    private Button createButton(int x, int y, int modify) {
        Button button = new Button("Dodaj");
        button.setStyle("-fx-pref-height: 40px; -fx-pref-width: 80px; -fx-border-color: black");
        button.setAlignment(Pos.CENTER);
        button.setOnMouseClicked(mouseEvent -> {
            AnchorPane anchorPane = null;
            try {
                anchorPane = FXMLLoader.load(getClass().getResource("/FXML/building/AddRoomScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            posX = x;
            posY = y;
            roomToModify=modify;
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane, 300, 300));
            stage.show();
        });
        return button;
    }

}