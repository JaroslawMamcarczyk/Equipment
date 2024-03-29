package controllers.policeman;


import Dao.policemanDao.DepartmentDao;
import Dao.policemanDao.WorkerDao;
import controllers.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import policeman.Department;
import policeman.Worker;

import java.util.ArrayList;
import java.util.List;

public class ShowPolicemanScreenController {
    @FXML
    private TableColumn<Worker, String> nameColumn;
    @FXML
    private TableColumn<Worker, String> peselColumn;
    @FXML
    private TableView<Worker> policemanTableView;
    @FXML
    private TableColumn<Worker, String> surrnameColumn;
    @FXML
    private TableColumn<Worker, String> identityColumn;
    @FXML
    private TableColumn<Worker, String> standingColumn;
    @FXML
    private TextField searchPolicemanTextField;
    @FXML
    private VBox vBoxButtons;
    private static Worker editWorker;
    private List<Worker> temporaryList = new ArrayList<>();
    private WorkerDao workerDao = new WorkerDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private ObservableList<Worker> observablePolicemanList = FXCollections.observableArrayList(workerDao.getList());

    public static Worker getEditWorker() {
        return editWorker;
    }


    @FXML
    public void initialize() {
//        WorkerDao.isChangeOnDatabaseProperty().addListener((observable, oldValue, newValue) -> {
//            if(newValue){
//                Database date = new Database();
//                WorkerDao.readWorkers(date);
//                WorkerDao.isChangeOnDatabaseProperty().setValue(false);
//            }
//        });
//        ObservableList<Worker> observableListWorker;
//        observableListWorker = FXCollections.observableList(Worker.getWorekrList());
        setPolicemanTableView(observablePolicemanList);
        /**
         * Getting the element from the table on which the cursor is positioned
        */
        policemanTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->editWorker = newValue);

        policemanTableView.setOnMouseClicked(click ->{
            if (click.getClickCount()==2){
                MainScreenController.getMainScreenController().createCenterPane("/FXML/workers/DetailsPolicemanScreen.fxml");
            }
        });
        /**
         * Reading the values ​​of the search field and displaying the matching values ​​dynamically
         */
        searchPolicemanTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    temporaryList = new ArrayList<>();
                    String search = newValue.toLowerCase();
                    for (Worker x : observablePolicemanList) {
                        if (x.getName().toLowerCase().contains(newValue.toLowerCase())) {
                           if (!temporaryList.contains(x))
                               temporaryList.add(x);
                        }
                        if (x.getSurname().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                        if (x.getEvidential().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                        if (x.getPesel().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                   }
                    ObservableList<Worker> listResult = FXCollections.observableArrayList(temporaryList);
                   setPolicemanTableView(listResult);
                }
        );
        ToggleGroup buttonsGroup = new ToggleGroup();
        for(Department department:departmentDao.getList()){
            ToggleButton toggleButton = new ToggleButton(department.getDepartmentName());
            toggleButton.setWrapText(true);
            toggleButton.setPrefWidth(340);
            toggleButton.setToggleGroup(buttonsGroup);
            toggleButton.setOnAction(event-> {
                        List <Worker> workerSpecialList = new ArrayList<>();
                for (Worker worker : observablePolicemanList) {
                    if(worker.getPolicemanDepartment()!=null&& worker.getPolicemanDepartment().equals(department))
                    workerSpecialList.add(worker);
                }
                setPolicemanTableView(FXCollections.observableArrayList(workerSpecialList));
                        });
            vBoxButtons.getChildren().add(toggleButton);
        }
    }
    /**
     * filling the table with data
     * @param glist - workers list
     */

    public void setPolicemanTableView(ObservableList<Worker> glist) {
        policemanTableView.setItems(glist);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surrnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        identityColumn.setCellValueFactory(new PropertyValueFactory<>("evidential"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        standingColumn.setCellValueFactory(new PropertyValueFactory<>("policemanDepartment"));
    }

    @FXML
     void clickShowAll(){
       setPolicemanTableView(observablePolicemanList);
    }
}
