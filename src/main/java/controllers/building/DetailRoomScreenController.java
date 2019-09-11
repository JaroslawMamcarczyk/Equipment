package controllers.building;

import Dao.productDao.ProductDao;
import Dao.productDao.ProductTransferDao;
import building.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import policeman.Department;
import product.Product;
import product.ProductTransfer;
import java.io.IOException;
import java.time.Year;
import java.util.Set;
import java.util.TreeSet;

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
        private HBox hBoxToDelete = null;
        private Set<Product> productSet = new TreeSet<>();
        private  Room roomFrom = null;
        private boolean isTransaction = false;


     @FXML
    void initialize() {
         Room room = EditBuildingScreenController.getRoomDetails();
         for(int x=0;x<5;x++){
             for(int y=1;y<7;y++){
                 VBox vBox = new VBox();
                 gridPaneGeneral.add(vBox,x,y,1,1);
                 vBox.setOnDragDropped(dragEvent -> {
                     Dragboard db = dragEvent.getDragboard();
                     if(db.hasString()) {
                         if (db.getString().equals("fromKit")) {
                             Product productFromKit = KitController.getProductToMove();
                             vBox.getChildren().add(createRoomView(KitController.getProductToMove()));
                             gridPaneGeneral.setGridLinesVisible(false);
                             productFromKit.setPositionX(gridPaneGeneral.getColumnIndex(vBox));
                             productFromKit.setPositionY(gridPaneGeneral.getRowIndex(vBox));
                             productFromKit.setDepartment(room.getDepartment());
                             roomFrom = productFromKit.getRoomNumber();
                             productDao.updateBuilding(productFromKit,room);
                             productSet.add(productFromKit);
                             isTransaction=true;
                             vBoxSide.getChildren().remove(hBoxToDelete);
                         } else {
                             vBox.getChildren().add(createRoomView(draggedProduct));
                             gridPaneGeneral.setGridLinesVisible(false);
                             draggedProduct.setPositionX(gridPaneGeneral.getColumnIndex(vBox));
                             draggedProduct.setPositionY(gridPaneGeneral.getRowIndex(vBox));
                             productDao.updatePosition(draggedProduct);
                             vBoxSide.getChildren().remove(hBoxToDelete);
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
         vBoxSide.setOnDragDropped(dragEvent -> {
             Dragboard db = dragEvent.getDragboard();
             if (db.hasString()) {
            vBoxSide.getChildren().add(createRoomView(draggedProduct));
            draggedProduct.setPositionY(null);
            draggedProduct.setPositionX(null);
            productDao.updatePosition(draggedProduct);
             }
         });
         vBoxSide.setOnDragOver(dragEvent -> {
             if(dragEvent.getGestureSource()!=gridPaneGeneral&&dragEvent.getDragboard().hasString()){
                 dragEvent.acceptTransferModes(TransferMode.MOVE);
             }
             dragEvent.consume();
         });
         gridPaneGeneral.setOnDragOver(dragEvent -> {
             if(dragEvent.getGestureSource()!=gridPaneGeneral&&dragEvent.getDragboard().hasString()){
                 dragEvent.acceptTransferModes(TransferMode.MOVE);
             }
             dragEvent.consume();
         });
         try {
             TabPane tabPane = FXMLLoader.load(getClass().getResource("/FXML/kit.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(tabPane,300,500));
             stage.setX(1200);
             stage.show();
             stage.setOnCloseRequest(event->{
                 if(isTransaction)
                 saveTransfer(productSet,roomFrom,room);
             });
         } catch (IOException e) {
             e.printStackTrace();
         }
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
        hBox.setOnDragDetected(mouseEvent -> {
            gridPaneGeneral.setGridLinesVisible(true);
            draggedProduct = product;
            hBoxToDelete = hBox;
            Dragboard db = hBox.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(Integer.toString(product.getProductId()));
            db.setContent(content);
            mouseEvent.consume();
        });
        return hBox;
    }

    public void saveTransfer(Set<Product> product, Room roomFrom, Room roomIn){
       // ProductTransfer productTransfer = new ProductTransfer(Year.now()," ",product,roomFrom,roomIn,roomFrom.getDepartment(),roomIn.getDepartment());
        //ProductTransferDao productTransferDao = new ProductTransferDao();
        //productTransferDao.save(productTransfer);
    }

}
