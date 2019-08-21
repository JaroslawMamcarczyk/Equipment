package controllers;
import Dao.productDao.ProductDao;
import building.Room;
import building.RoomDao;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import product.Product;
import java.util.List;


public class BuildingScreenController {
    @FXML
    private StackPane stackPaneGeneral;
    RoomDao roomDao = new RoomDao();

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
                    anchorPane.setPrefSize(500,700);
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