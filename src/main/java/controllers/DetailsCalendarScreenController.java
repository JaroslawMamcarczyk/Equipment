package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import worksToDo.Works;
import worksToDo.WorksDao;

import java.util.Calendar;
import java.util.Optional;


public class DetailsCalendarScreenController {

    @FXML
    private HBox hBoxGeneral;
    private WorksDao worksDao = new WorksDao();


    @FXML
    void clickAddWorks(ActionEvent event) {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setTitle("Wpisz zadanie");
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(ok->{
                Works works = new Works(result.get(),false, CalendarScreenController.getDateToNewScene()," ");
                worksDao.save(works);
                CalendarScreenController.isNewWorksProperty().set(true);
                Stage stage =(Stage) hBoxGeneral.getScene().getWindow();
                stage.close();
            });
    }

}

