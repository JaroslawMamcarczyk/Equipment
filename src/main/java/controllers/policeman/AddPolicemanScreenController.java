package controllers.policeman;


import Dao.policemanDao.DepartmentDao;
import Dao.policemanDao.RangeDao;
import Dao.policemanDao.RankDao;
import Dao.policemanDao.WorkerDao;
import controllers.MainScreenController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import policeman.Department;
import policeman.Range;
import policeman.Rank;
import policeman.Worker;

import java.util.Optional;


public class AddPolicemanScreenController {
    @FXML
    private TextField laddName;
    @FXML
    private TextField lewidential;
    @FXML
    private TextField lsurrname;
    @FXML
    private TextField lpesel;
    @FXML
    private ChoiceBox<Range> choiceRange;
    @FXML
    private ChoiceBox<Rank> choiceRanks;
    @FXML
    private ChoiceBox<Department> choiceDepartment;
    @FXML
    private CheckBox checkBoxIntradok;
    @FXML
    private  CheckBox checkBoxIntranet;
    @FXML
    private CheckBox checkBoxExchange;
    @FXML
    private CheckBox checkBoxLotus;
    @FXML
    private CheckBox checkBoxSWD;
    @FXML
    private CheckBox checkBoxCryptomail;
    private WorkerDao workerDao = new WorkerDao();
    private RangeDao rangeDao = new RangeDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private RankDao rankDao = new RankDao();
    private BooleanProperty isNewRanks = new SimpleBooleanProperty(false);



    @FXML
    void initialize(){
        ObservableList<Range> rangeObservableList = FXCollections.observableArrayList(rangeDao.getList());
        choiceRange.setItems(rangeObservableList);
        ObservableList<Department> departamentObservableList= FXCollections.observableList(departmentDao.getList());
        choiceDepartment.setItems(departamentObservableList);
        choiceDepartment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Rank> ranksObservableList = FXCollections.observableArrayList(rankDao.getListWhereDepartment(newValue));
            choiceRanks.setItems(ranksObservableList);
        });
        isNewRanks.addListener((observable) -> {
            if(isNewRanks.get()) {
                ObservableList<Rank> rankObservableList = FXCollections.observableArrayList(rankDao.getListWhereDepartment(choiceDepartment.getValue()));
                choiceRanks.setItems(rankObservableList);
                choiceRanks.setValue(rankObservableList.get(rankObservableList.size()-1));
                isNewRanks.set(false);
            }
       });
    }

    @FXML
    void clickSave(ActionEvent event) {
        boolean isOK = true;
        if (lewidential.getText().length() != 6) {
            isOK = false;
            lewidential.setStyle("-fx-background-color: red");
        }
        if (lpesel.getText().length() != 11) {
            isOK = false;
            lpesel.setStyle("-fx-background-color: red");
        }
        if (laddName.getText().isEmpty()) {
            isOK = false;
            laddName.setStyle("-fx-background-color: red");
        }
        if (lsurrname.getText().isEmpty()) {
            isOK = false;
            lsurrname.setStyle("-fx-background-color: red");
        }
        if (isOK) {
            Range rangeToSave = null;
            Rank ranksToSave = null;
            Department departmentToSave = null;
            if (choiceRange.getValue() != null) {
                rangeToSave = choiceRange.getValue();
            }
            if (choiceRanks.getValue() != null) {
                ranksToSave = choiceRanks.getValue();
            }
            if (choiceDepartment.getValue() != null) {
                departmentToSave = choiceDepartment.getValue();
            }
            Worker worker = new Worker(laddName.getText(),lsurrname.getText(),lewidential.getText(),lpesel.getText(),rangeToSave,departmentToSave,ranksToSave,false,false,false,false,false,false,true);
            if(checkBoxIntradok.isSelected())worker.setPolicemanIntradok(true);
            if(checkBoxIntranet.isSelected())worker.setPolicemanIntranet(true);
            if(checkBoxExchange.isSelected())worker.setPolicemanExchange(true);
            if(checkBoxCryptomail.isSelected())worker.setPolicemanCryptomail(true);
            if(checkBoxLotus.isSelected())worker.setPolicemanLotus(true);
            if(checkBoxSWD.isSelected())worker.setPolicemanIntradok(true);
            workerDao.save(worker);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Dodawanie nowego pracownika");
            alert.setContentText("Sukces - dodano nowego pracownika");
            alert.showAndWait();
            MainScreenController.getMainScreenController().createCenterPane("/FXML/worker/ShowPolicemanScreen.fxml");
            }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Błąd dodawania nowego pracownika");
            alert.setContentText("Popraw dane podświetlone na czerwono");
            alert.showAndWait();
        };
        }


    public void clickAddRanks(){
        if(choiceDepartment.getValue()!=null) {
            TextInputDialog textInputDialog = new TextInputDialog("Podaj nazwę");
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(resultFromDialog -> {
                Rank rank = new Rank(result.get(), choiceDepartment.getValue());
                rankDao.save(rank);
                isNewRanks.set(true);
            });
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Musisz wybrać departament");
            alert.setTitle("Błąd tworzenia nowego stanowiska");
            alert.showAndWait();
        }
    }
}
