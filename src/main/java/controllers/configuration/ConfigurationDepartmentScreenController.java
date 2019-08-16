package controllers.configuration;

import Dao.policemanDao.DepartmentDao;
import Dao.policemanDao.RankDao;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import policeman.Department;
import policeman.Rank;

import java.util.List;
import java.util.Optional;

public class ConfigurationDepartmentScreenController {
    @FXML
    private TableView<Department> tableViewDepartment;
    @FXML
    private TableColumn<Department, Integer> columnId;
    @FXML
    private TableColumn<Department, String> columnDepartment;
    @FXML
    private  TableColumn<Department, String> columnShortName;
    @FXML
    private  TableView<Rank> tableRank;
    @FXML
    private TableColumn<Rank,Integer> columnIdRank;
    @FXML
    private TableColumn<Rank, String> columnRank;
    private BooleanProperty isNewDepartment = new SimpleBooleanProperty(false);
    private DepartmentDao departmentDao = new DepartmentDao();
    private ObservableList<Department> departmentList = FXCollections.observableArrayList(departmentDao.getList());
    private  BooleanProperty isNewRank = new SimpleBooleanProperty(false);
    private RankDao rnkDao = new RankDao();
    private ObservableList<Rank> rankObservableList = FXCollections.observableArrayList(rnkDao.getList());


    public void initialize(){
createTable();
isNewDepartment.addListener(observable -> {
    if (isNewDepartment.get()){
        tableViewDepartment.refresh();
        isNewDepartment.set(false);
    }
});
    }

    private void createTable(){
        tableViewDepartment.setItems(departmentList);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.prefWidthProperty().bind(tableViewDepartment.widthProperty().multiply(0.2));
        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        columnDepartment.prefWidthProperty().bind(tableViewDepartment.widthProperty().multiply(0.55));
        columnShortName.setCellValueFactory(new PropertyValueFactory<>("departmentShort"));
        columnShortName.prefWidthProperty().bind(tableViewDepartment.widthProperty().multiply(0.2));
    }

    private void createtableRank(){
            tableRank.setItems(rankObservableList);
            columnIdRank.setCellValueFactory(new PropertyValueFactory<>("ranksId"));
            columnIdRank.prefWidthProperty().bind(tableRank.widthProperty().multiply(0.2));
            columnRank.setCellValueFactory(new PropertyValueFactory<>("nameRanks"));
            columnRank.prefWidthProperty().bind(tableRank.widthProperty().multiply(0.75));
        }

    @FXML
    public void clickAddDepartment(){
        Dialog<Pair<String,String>> dialog = new Dialog();
        dialog.setTitle("Dodoaj nowy Department");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        TextField textFieldDepartment = new TextField();
        TextField textFieldShort = new TextField();
        Label labelDepartment = new Label("Podaj nazwę nowego Wydziału");
        Label labelShort = new Label("Podaj skrót");
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(labelDepartment,textFieldDepartment,labelShort,textFieldShort);
        dialog.getDialogPane().setContent(vBox);
        dialog.setResultConverter(buttonType -> {
            if (buttonType==ButtonType.APPLY){
                return new Pair<>(textFieldDepartment.getText(),textFieldShort.getText());
            }else return null;
        });
        Optional <Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(ok->{
            Department department = new Department(ok.getKey(),ok.getValue());
            departmentDao.save(department);
            isNewDepartment.set(true);
        });
    }

    public void clickAddRank(){

    }
}
