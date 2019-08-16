package controllers.product;

import Dao.productDao.SwitchDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import product.*;
import java.util.List;

public class SwitchScreenController {
    @FXML
   private ScrollPane scrollPane;
    @FXML
    private HBox hBoxTitle;
    @FXML
    private TableView<ComputerSwitch> tableSwitch;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnSocket;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnEquipment;
    @FXML
    private TableColumn<ComputerSwitch, ComputerSwitch> columnType;
    @FXML
    private TableView<ComputerSwitch> tableSwitch2;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnSocket2;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnEquipment2;
    @FXML
    private TableColumn<ComputerSwitch, ComputerSwitch> columnType2;
    @FXML
    private TableView<ComputerSwitch> tableSwitch3;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnSocket3;
    @FXML
    private TableColumn<ComputerSwitch,ComputerSwitch> columnEquipment3;
    @FXML
    private TableColumn<ComputerSwitch, ComputerSwitch> columnType3;
    private ProductDao productDao = new ProductDao();
    private SwitchDao switchDao = new SwitchDao();
    private ProductGroupDao productGroupDao = new ProductGroupDao();
    private List<ProductGroup> productGroupsList = productGroupDao.getList();
    private List<ComputerSwitch> computerSwitchList = switchDao.getList();
    private ObservableList<Product> productList = FXCollections.observableArrayList(productDao.getList());
    private Boolean isTable1 = false;
    private Boolean isTable2;
    private Boolean isTable3;
    public void initialize(){
        setColumnSize(tableSwitch,columnSocket,columnType, columnEquipment);
        setColumnSize(tableSwitch2,columnSocket2,columnType2, columnEquipment2);
        setColumnSize(tableSwitch3,columnSocket3,columnType3, columnEquipment3);
        String first = " ";
        for(ComputerSwitch computerSwitch:computerSwitchList){
            if(!computerSwitch.getSwitchName().equals(first)) {
              first = computerSwitch.getSwitchName();
                Button button = new Button(first);
                button.setOnMouseClicked(click->{
                    ObservableList<ComputerSwitch> computerSwitchObservableList = FXCollections.observableArrayList();
                    for(ComputerSwitch comp:computerSwitchList) {
                        if (comp.getSwitchName().equals(button.getText())) {
                            computerSwitchObservableList.add(comp);
                        }
                    }
                            if(!isTable1){
                                tableSwitch.setItems(computerSwitchObservableList);
                                isTable1 = true;
                                isTable2 = false;
                            }
                            else if(!isTable2){
                                tableSwitch2.setItems(computerSwitchObservableList);
                                isTable2=true;
                                isTable3 = false;
                            }
                            else if(!isTable3){
                                tableSwitch3.setItems(computerSwitchObservableList);
                                isTable3 = true;
                                isTable1 = false;
                            }
                });
                hBoxTitle.getChildren().add(button);
            }
        }
    }

private MenuBar createList(ComputerSwitch computerSwitch, TableView table){
        MenuBar menuGeneral = new MenuBar();
        Menu menuuGeneral = new Menu("Wybierz");
        for(ProductGroup productGroup:productGroupsList){
            Menu menu = new Menu(productGroup.getGroupName());
            for(Product product:productList){
                if(product.getProductGroup().equals(productGroup)) {
                    MenuItem menuItem = new MenuItem(product.toString());
                    menuItem.setOnAction(click->{
                        if(product.getSocketFromSwitch() != null){
                          computerSwitchList.get(computerSwitchList.indexOf(product.getSocketFromSwitch())).setProductOnSocket(null);
                            switchDao.saveproductOnSocket(null,product.getSocketFromSwitch());
                            if(isTable2) tableSwitch2.refresh();
                            if(isTable3) tableSwitch3.refresh();
                        }
                  computerSwitchList.get(computerSwitchList.indexOf(computerSwitch)).setProductOnSocket(product);
                  switchDao.saveproductOnSocket(product,computerSwitch);
                        table.refresh();
                    });
                    menu.getItems().add(menuItem);
                }
            }
            menuuGeneral.getItems().add(menu);
        }
    menuGeneral.getMenus().add(menuuGeneral);
        menuGeneral.autosize();
        return menuGeneral;
}

private void setColumnSize(TableView<ComputerSwitch> table, TableColumn<ComputerSwitch,ComputerSwitch>columnSocket, TableColumn<ComputerSwitch,ComputerSwitch>columnType, TableColumn<ComputerSwitch, ComputerSwitch> columnEquipment){
        columnSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
        columnSocket.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    columnEquipment.setCellValueFactory(cell-> new SimpleObjectProperty<>(cell.getValue()));
    columnEquipment.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
    columnEquipment.setCellFactory(param ->new TableCell<ComputerSwitch,ComputerSwitch>(){
        @Override
        protected void updateItem(ComputerSwitch computer, boolean b) {
            if(!b) {
                if (computer.getProductOnSocket() != null) {
                    setText(computer.getProductOnSocket().getProductName());
                }else{
                    setGraphic(createList(computer,table));
                }
            }
        }
    });
    columnType.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue()));
    columnType.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
    columnType.setCellFactory(param->new TableCell<ComputerSwitch,ComputerSwitch>() {
        @Override
        protected void updateItem(ComputerSwitch computerSwitch, boolean b) {
                    if (!b) {
                        if(computerSwitch.getProductOnSocket()!=null) {
                            setText(computerSwitch.getProductOnSocket().getProductGroup().getGroupName());
                        }else{
                            setText("brak");
                        }
                    }
                }
            }
    );

}
}
