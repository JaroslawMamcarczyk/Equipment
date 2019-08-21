package controllers.product;

import Dao.productDao.SwitchDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import product.ComputerSwitch;
import product.Product;
import Dao.productDao.ProductDao;

public class AddSwitchScreenController {
    @FXML
    private VBox vBoxGeneral;
    @FXML
    private TextField textFieldSocket;
    @FXML
    private TextField textFieldSwitch;
private ProductDao productDao = new ProductDao();
private SwitchDao switchDao= new SwitchDao();
    public void initialize(){

    }
    @FXML
    public void clickSaveSwitch(){
        for(int i=0;i<Integer.parseInt(textFieldSocket.getText());i++) {
            ComputerSwitch computerSwitch = new ComputerSwitch(textFieldSwitch.getText(), String.valueOf(i),AddProductScreenController.getProductToSave());
            switchDao.save(computerSwitch);
        }
    }
}
