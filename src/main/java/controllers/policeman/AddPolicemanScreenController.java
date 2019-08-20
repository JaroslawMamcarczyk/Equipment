package controllers.policeman;


import Dao.policemanDao.DepartmentDao;
import Dao.policemanDao.RangeDao;
import Dao.policemanDao.RankDao;
import Dao.policemanDao.WorkerDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import policeman.Department;
import policeman.Range;
import policeman.Rank;


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
//    private BooleanProperty isNewRanks = new SimpleBooleanProperty(false);



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
//        isNewRanks.addListener((observable, oldValue, newValue) -> {
//            if(newValue){
//                Database date = new Database();
//                RanksDao.readRanks(date);
//                int i=choiceDepartament.getSelectionModel().getSelectedIndex();
//                choiceDepartament.getSelectionModel().clearSelection();
//                choiceDepartament.getSelectionModel().select(i);
//                isNewRanks.set(false);
           }
//        });


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
            Department departamentToSave = null;
            if (choiceRange.getValue() != null) {
                rangeToSave = choiceRange.getValue();
            }
            if (choiceRanks.getValue() != null) {
                ranksToSave = choiceRanks.getValue();
            }
            if (choiceDepartment.getValue() != null) {
                departamentToSave = choiceDepartment.getValue();
            }
//            policeman = new WorkerDao(laddName.getText(),lsurrname.getText(),lewidential.getText(),lpesel.getText(),rangeToSave,departamentToSave,ranksToSave,0,0,0,0,0,0);
//            if(checkBoxIntradok.isSelected())policeman.setDaoIntradok(1);
//            if(checkBoxIntranet.isSelected())policeman.setDaoIntranet(1);
//            if(checkBoxExchange.isSelected())policeman.setDaoExchange(1);
//            if(checkBoxCryptomail.isSelected())policeman.setDaoCryptomail(1);
//            if(checkBoxLotus.isSelected())policeman.setDaoLotus(1);
//            if(checkBoxSWD.isSelected())policeman.setDaoSWD(1);
//            policeman.savePoliceman();
//                WorkerDao.isChangeOnDatabaseProperty().setValue(true);
//                CreateWindowAlert.createWindowConfirmation("Dodano Nowego Pracownika");
//           MainScreenController.getMainScreenController().createCenter("/FXML/workers/ShowPolicemanScreen.fxml");
//            }else CreateWindowAlert.createWindowError("Błąd dodawania nowego policjanta - popraw pola świecące na czerowno");
        }
    }

    public void clickAddRanks(){
    //    ConfigurationScreenController.createNewRanks(choiceDepartament.getSelectionModel().getSelectedItem());
 //       isNewRanks.set(true);
    }
}
