package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import worksToDo.Works;
import worksToDo.WorksDao;
import java.util.Optional;


public class DetailsCalendarScreenController {

    @FXML
    private HBox hBoxGeneral;
@FXML
    private Button buttonAdd;
    private WorksDao worksDao = new WorksDao();
    private static  Works works = null;

    public static Works getWorks(){return works;}
    @FXML
    void initialize(){
        for(Works work:worksDao.getList()){
            if(work.getDate().equals(CalendarScreenController.getDateToNewScene())){
                VBox vBox = new VBox();
                vBox.setSpacing(5);
                Text labelWork = new Text(work.getWork());
                Text labelDescription = new Text(work.getDescription());
                if(work.isDone()){
                    labelWork.setStyle("-fx-strikethrough: true");
                    labelDescription.setStyle("-fx-strikethrough: true");
                    vBox.getChildren().addAll(labelWork,labelDescription);
                }else {
                    Button buttonDo = new Button("Wykonano");
                    buttonDo.setOnMouseClicked(click -> {
                        worksDao.setWorkDoing(work.getId());
                    });
                    vBox.getChildren().addAll(labelWork,labelDescription,buttonDo);
                }
                hBoxGeneral.getChildren().add(vBox);
            }
        }
    }
    @FXML
    void clickAddWorks(ActionEvent event) {
        Dialog<Pair <String,String>> dialog = new Dialog();
        dialog.setTitle("Dodoaj nowy wpis");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY,ButtonType.CANCEL);
        TextField textFieldWork = new TextField();
        TextArea textFieldDescription = new TextArea();
        Label labelWork = new Label("Dodaj nowy wpis");
        Label labelDescription = new Label("Dodaj opis (opcjonalnie)");
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(labelWork,textFieldWork,labelDescription,textFieldDescription);
        dialog.getDialogPane().setContent(vBox);
        dialog.setResultConverter(buttonType -> {
            if (buttonType==ButtonType.APPLY){
                return new Pair<>(textFieldWork.getText(),textFieldDescription.getText());
            }else return null;
        });
        Optional <Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(ok->{
             works = new Works(ok.getKey(),false,CalendarScreenController.getDateToNewScene(),ok.getValue());
            worksDao.save(works);
            CalendarScreenController.isNewWorksProperty().set(true);
            Stage stage = (Stage)buttonAdd.getScene().getWindow();
            stage.close();
        });
    }

}

