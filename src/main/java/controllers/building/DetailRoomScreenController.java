package controllers.building;

import Dao.productDao.ProductDao;
import building.Room;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import product.Product;

public class DetailRoomScreenController {
        @FXML
        private Label labelDepartment;

        @FXML
        private GridPane gridPaneGeneral;

        @FXML
        private VBox vBoxSide;

        @FXML
        private Label labelRoomNumber;
        private ProductDao productDao = new ProductDao();
        private Product draggedProduct = null;
     @FXML
    void initialize() {
         Room room = BuildingScreenController.getRoomDetails();
         for(int x=0;x<5;x++){
             for(int y=1;y<7;y++){
                 StackPane stackPane = new StackPane();
                 gridPaneGeneral.add(stackPane,x,y,1,1);
                 stackPane.setOnDragDropped(new EventHandler<DragEvent>() {
                     @Override
                     public void handle(DragEvent dragEvent) {
                         Dragboard db = dragEvent.getDragboard();
                         if(db.hasString()){
                             stackPane.getChildren().add(createRoomView(draggedProduct));
                             gridPaneGeneral.setGridLinesVisible(false);
                             draggedProduct.setPositionX(gridPaneGeneral.getColumnIndex(stackPane));
                             draggedProduct.setPositionY(gridPaneGeneral.getRowIndex(stackPane));
                             productDao.updatePosition(draggedProduct);
                         }
                     }
                 });
             }
         }
         labelRoomNumber.setText(room.getNumber() + "   " + room.getDescription());
         if (room.getDepartment() != null) {
             labelDepartment.setText(room.getDepartment().getDepartmentName());
         }
         for(Product product:productDao.getListFromRoom(room)) {
             if (product.getPositionX() == null || product.getPositionY() == null) {
                 vBoxSide.getChildren().add(createRoomView(product));
             }else{
                 gridPaneGeneral.add(createRoomView(product),product.getPositionX(),product.getPositionY(),1,1);
             }
         }
         gridPaneGeneral.setOnDragOver(new EventHandler<DragEvent>() {
             @Override
             public void handle(DragEvent dragEvent) {
                 if(dragEvent.getGestureSource()!=gridPaneGeneral&&dragEvent.getDragboard().hasString()){
                     dragEvent.acceptTransferModes(TransferMode.MOVE);
                 }
                 dragEvent.consume();
             }
         });
     }

    private HBox createRoomView(Product product){
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Image image = null;
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
        hBox.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gridPaneGeneral.setGridLinesVisible(true);
                draggedProduct = product;
                Dragboard db = hBox.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(Integer.toString(product.getProductId()));
                db.setContent(content);
                mouseEvent.consume();
            }
        });
        return hBox;
    }
}
