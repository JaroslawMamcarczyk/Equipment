package controllers.configuration;

import Dao.policemanDao.DepartmentDao;
import Dao.policemanDao.RangeDao;
import Dao.policemanDao.RankDao;
import Dao.policemanDao.WorkerDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import policeman.Department;
import policeman.Range;
import policeman.Rank;
import policeman.Worker;

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
    @FXML
    private TableView<Range> tableRange;
    @FXML
    private TableColumn<Range, String> columnRange;
    @FXML
    private TableColumn<Range,Range> columnPagons;
    @FXML
    private TableView<Worker> tableWorker;
    @FXML
    private TableColumn<Worker, String> columnName;
    @FXML
    private TableColumn<Worker,String> columnSurname;
    @FXML
    private TableColumn<Worker, String> columnEvidential;

    private BooleanProperty isNewDepartment = new SimpleBooleanProperty(false);
    private DepartmentDao departmentDao = new DepartmentDao();
    private ObservableList<Department> departmentList = FXCollections.observableArrayList(departmentDao.getList());
    private  BooleanProperty isNewRank = new SimpleBooleanProperty(false);
    private RankDao rankDao = new RankDao();
    private Department checkedDepartment;
    private Rank checkedRank;
    private RangeDao rangeDao = new RangeDao();
    private ObservableList<Range> rangeObservableList = FXCollections.observableArrayList(rangeDao.getList());
    private WorkerDao workerDao = new WorkerDao();


    public void initialize(){
createTable();
createTableRange();

isNewDepartment.addListener(observable -> {
    if (isNewDepartment.get()){
        tableViewDepartment.refresh();
        isNewDepartment.set(false);
    }
});
isNewRank.addListener(observable -> {
    if(isNewRank.get()){
        createTableRank(rankDao.getListWhereDepartment(checkedDepartment));
        isNewRank.set(false);
    }
});
tableRank.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> checkedRank = newValue );
tableViewDepartment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> checkedDepartment = newValue);
tableViewDepartment.setOnMouseClicked(click ->{
    createTableRank(rankDao.getListWhereDepartment(checkedDepartment));
    ObservableList<Worker> workerObservableList = FXCollections.observableArrayList(workerDao.getListInDepartment(checkedDepartment));
    createTablePoliceman(workerObservableList);
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

    private  void createTablePoliceman(ObservableList<Worker> workerObservableList){
        tableWorker.setItems(workerObservableList);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnEvidential.setCellValueFactory(new PropertyValueFactory<>("evidential"));
    }

    private void createTableRank(ObservableList<Rank> list){
            tableRank.setItems(list);
            columnIdRank.setCellValueFactory(new PropertyValueFactory<>("ranksId"));
            columnIdRank.prefWidthProperty().bind(tableRank.widthProperty().multiply(0.2));
            columnRank.setCellValueFactory(new PropertyValueFactory<>("nameRanks"));
            columnRank.prefWidthProperty().bind(tableRank.widthProperty().multiply(0.75));
        }

        private void createTableRange(){
        tableRange.setItems(rangeObservableList);
        columnRange.setCellValueFactory(new PropertyValueFactory<>("rangeName"));
        columnRange.prefWidthProperty().bind(tableRange.widthProperty().multiply(0.48));
        columnPagons.setCellValueFactory(cell->new SimpleObjectProperty<Range>(cell.getValue()));
        columnPagons.prefWidthProperty().bind(tableRange.widthProperty().multiply(0.45));
        columnPagons.setStyle("-fx-alignment: CENTER");
        columnPagons.setCellFactory(param->{
            return new TableCell<Range, Range>(){
                @Override
                protected void updateItem(Range range, boolean b) {
                    super.updateItem(range, b);
                    if(!b) {
                        ImageView imageView = new ImageView(new Image(range.getPath()));
                        setGraphic(imageView);
                   }
                }
            };
        });
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
            if (buttonType==ButtonType.OK){
                return new Pair<>(textFieldDepartment.getText(),textFieldShort.getText());
            }else return null;
        });
        Optional <Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(ok->{
            Department department = new Department(ok.getKey(),ok.getValue());
            departmentDao.save(department);
            departmentList.add(department);
            isNewDepartment.set(true);
        });
    }
@FXML
    public void clickAddRank(){
        TextInputDialog textInputDialog = new TextInputDialog("Podaj nazwę");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(resultFromDialog->{
            Rank rank = new Rank(result.get(), checkedDepartment);
            rankDao.save(rank);
            isNewRank.set(true);
        });
    }

    @FXML
    public void clickEditDepartment(){
        if(checkedDepartment!=null){
            Dialog<Pair<String,String>> dialog = new Dialog();
            dialog.setTitle("Edycja Wydziału");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
            TextField textFieldDepartment = new TextField(checkedDepartment.getDepartmentName());
            TextField textFieldShort = new TextField(checkedDepartment.getDepartmentShort());
            Label labelDepartment = new Label("Zmień nazwę Wydziału");
            Label labelShort = new Label("Zmień skrót");
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.getChildren().addAll(labelDepartment,textFieldDepartment,labelShort,textFieldShort);
            dialog.getDialogPane().setContent(vBox);
            dialog.setResultConverter(buttonType -> {
                if (buttonType==ButtonType.OK){
                    return new Pair<>(textFieldDepartment.getText(),textFieldShort.getText());
                }else return null;
            });
            Optional <Pair<String,String>> result = dialog.showAndWait();
            result.ifPresent(ok->{
                Department department = new Department(ok.getKey(),ok.getValue());
                departmentDao.update(department);
                departmentList.get(departmentList.indexOf(checkedDepartment)).setDepartmentName(department.getDepartmentName());
                departmentList.get(departmentList.indexOf(checkedDepartment)).setDepartmentShort(department.getDepartmentShort());
                isNewDepartment.set(true);
            });
        }else{
            createAlert("Błąd nie wybrałeś Wydziału z tabeli").showAndWait();
        }
    }
    @FXML
    public void clickCancelDepartment(){
if(checkedDepartment!=null){
    departmentDao.delete(checkedDepartment.getId());
    departmentList.remove(checkedDepartment);
    isNewDepartment.set(true);
}else{
    createAlert("Nie wybrano Wydziały do usunięcia");
}
    }
    @FXML
    public void clickEditRank(){
        if(checkedRank!=null) {
            TextInputDialog textInputDialog = new TextInputDialog(checkedRank.getNameRanks());
            textInputDialog.setContentText("Zmień nazwę stanowiska");
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(resultFromDialog ->{
                checkedRank.setNameRanks(result.get());
                rankDao.update(checkedRank);
                isNewRank.set(true);
            });
        }else{
            createAlert("Nie wybrano stanowiska do edycji").showAndWait();
        }
    }
    @FXML
    public void clickCancelRank(){
if(checkedRank!=null){
    rankDao.delete(checkedRank.getRanksId());
    isNewRank.set(true);
}else{
    createAlert("Nie wybrano stanowiska do skasowania").showAndWait();
}
    }

    private Alert createAlert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setContentText(text);
        alert.setHeaderText("Błąd edycji");
        return alert;
    }
}
