package controllers.product;

import Dao.productDao.SwitchDao;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import product.ComputerSwitch;
import product.Product;
import product.ProductDao;

import java.util.List;

public class SwitchScreenController {
    @FXML
   private ScrollPane scrollPane;
    @FXML
    private HBox hBoxTitle;
    @FXML
    private TableView<ComputerSwitch> tableSwitch;
    @FXML
    private TableColumn<ComputerSwitch,String> columnSocket;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnEquipment;
    @FXML
    private TableColumn<ComputerSwitch, ComputerSwitch> columnType;
    private ProductDao productDao = new ProductDao();
    private SwitchDao switchDao = new SwitchDao();
    private List<ComputerSwitch> computerSwitchList = switchDao.getList();
    private ObservableList<Product> productList = FXCollections.observableArrayList(productDao.getList());
    public void initialize(){
        String first = " ";
        for(ComputerSwitch computerSwitch:computerSwitchList){
            if(!computerSwitch.getSwitchName().equals(first)) {
                first = computerSwitch.getSwitchName();
                Label label = new Label(computerSwitch.getSwitchName());
                label.setOnMouseClicked(click->{
                createTable();
                });
                hBoxTitle.getChildren().add(label);
            }
        }
    }

    private void createTable(){
        ObservableList<ComputerSwitch> rangeObservableList = FXCollections.observableArrayList(computerSwitchList);
        tableSwitch.setItems(rangeObservableList);
        columnSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
        columnEquipment.setCellValueFactory(cell-> new SimpleObjectProperty<>(cell.getValue()));
        columnEquipment.setCellFactory(param ->new TableCell<ComputerSwitch,ComputerSwitch>(){
            @Override
            protected void updateItem(ComputerSwitch computer, boolean b) {
                if(!b) {
                    if (computer.getProductOnSocket() != null) {
                        setText(computer.getProductOnSocket().getProductName());
                    }else{
                        ChoiceBox<Product> choiceBox = new ChoiceBox<>();
                        choiceBox.setItems(productList);
                        setText("brak");
                    }
                }
            }
        });
        columnType.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue()));
        columnType.setCellFactory(param->new TableCell<ComputerSwitch,ComputerSwitch>() {
                    @Override
                    protected void updateItem(ComputerSwitch computerSwitch, boolean b) {
                        if (!b) {
                            if(computerSwitch.getProductOnSocket()!=null) {
                                setText(computerSwitch.getProduct().getProductGroup().getGroupName());
                            }else{
                                setText("brak");
                            }
                        }
                    }
                }
        );
    }

}
