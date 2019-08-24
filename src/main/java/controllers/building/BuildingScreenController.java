package controllers.building;
import Dao.productDao.ProductDao;
import building.Room;
import Dao.buildingDao.RoomDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import product.Product;

import java.io.IOException;
import java.util.List;


public class BuildingScreenController {
    @FXML
    private StackPane stackPaneGeneral;
    RoomDao roomDao = new RoomDao();
    private Product productDetails;
    private static  Room roomDetails = null;

    public static Room getRoomDetails() {
        return roomDetails;
    }


    public void initialize() {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.getList();
        GridPane gridPaneGeneral = new GridPane();
        gridPaneGeneral.setAlignment(Pos.CENTER);
        if (roomDao.getList().size() == 0) {

        } else {
            for (Room room : roomDao.getList()) {
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.setOnMouseClicked(click->{
                        roomDetails = room;
                        try {
                            AnchorPane anchor = FXMLLoader.load(getClass().getResource("/FXML/building/DetailsRoomScreen.fxml"));
                            Stage stage = new Stage();
                            stage.setScene(new Scene(anchor,800,400));
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    anchorPane.setPrefSize(200, 300);
                    VBox vBox = new VBox();
                    Label labelName = new Label(room.getNumber()+" - "+room.getDescription());
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setSpacing(5);
                    vBox.getChildren().add(labelName);
                    for(Product product:productList){
                        if( product.getRoomNumber().getId()==room.getId()){
                                vBox.getChildren().add(createRoomView(product));
                        }
                    }
                    anchorPane.setStyle("-fx-background-color: red; -fx-border-color: white");
                    gridPaneGeneral.add(anchorPane, room.getPositionX(), room.getPositionY());
                anchorPane.getChildren().add(vBox);
                anchorPane.setRightAnchor(vBox,0.0);
                anchorPane.setLeftAnchor(vBox,1.0);
            }
            stackPaneGeneral.getChildren().add(gridPaneGeneral);
        }
    }

    private HBox createRoomView(Product product){
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Image image;
        ImageView imageView = new ImageView();
        switch (product.getProductGroup().getGroupName()){
            case "Komputer": {
                image = new Image("/Pics/komputer.png");
                imageView.setImage(image);
                break;
            }
            case "Monitor": {
                image = new Image("/Pics/monitor.png");
                imageView.setImage(image);
                break;
            }
            case "Switch": {
                image = new Image("/Pics/switch.png");
                imageView.setImage(image);
                break;
            }
            case "Zestaw komputerowy": {
                image = new Image("/Pics/zestaw.jpg");
                imageView.setImage(image);
                break;
            }
            case "Laptop": {
                image = new Image("/Pics/laptop.png");
                imageView.setImage(image);
                break;
            }
        }
        Label label = new Label(product.getProductName()+" "+product.getEvidentialNumber());
        hBox.getChildren().addAll(imageView,label);
        return hBox;
    }
}