package controllers.product;

import Dao.productDao.ProductDao;
import Dao.productDao.ProductTransferDao;
import building.Room;
import Dao.buildingDao.RoomDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import policeman.Department;
import product.Product;
import product.ProductTransfer;

import java.time.Year;

public class AddProductToTransferScreenController {
        @FXML
        private Label labelFromDepartment;

        @FXML
        private ChoiceBox<Room> choiceBoxRoomIn;

        @FXML
        private ChoiceBox<Product> choiceBoxProduct;

        @FXML
        private TextArea textAreaElements;

        @FXML
        private ChoiceBox<Department> choiceBoxDepartmentIn;

        @FXML
        private Label labelFromRoom;
        private ProductDao productDao = new ProductDao();
        private ObservableList<Product> productObservableList = FXCollections.observableArrayList(productDao.getList());
        private RoomDao roomDao = new RoomDao();
        private ObservableList<Room> roomObservableList = FXCollections.observableArrayList(roomDao.getList());
        @FXML
        public void initialize(){
           choiceBoxRoomIn.setItems(roomObservableList);
          choiceBoxProduct.setItems(productObservableList);
            choiceBoxProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
                labelFromRoom.setText(newValue.getRoomNumber().getNumber());
                labelFromDepartment.setText(newValue.getRoomNumber().getDepartment().getDepartmentName());
            });
        }
        @FXML
public void clickSaveTransaction(){
            Product product = choiceBoxProduct.getValue();
            ProductTransfer productTransfer = new ProductTransfer(Year.now(),textAreaElements.getText(),product,product.getRoomNumber(),choiceBoxRoomIn.getValue(),
                    product.getDepartment(),choiceBoxDepartmentIn.getValue());
            product.setPositionX(0);
            product.setPositionY(0);
            product.setDepartment(choiceBoxDepartmentIn.getValue());
            productDao.update(product);
            ProductTransferDao productTransferDao = new ProductTransferDao();
            productTransferDao.save(productTransfer);
}
    }


