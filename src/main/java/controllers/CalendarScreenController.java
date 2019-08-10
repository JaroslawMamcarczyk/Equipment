package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import worksToDo.Works;
import worksToDo.WorksDao;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;


public class CalendarScreenController {
    @FXML
    private GridPane gridPaneGeneral;
    private static BooleanProperty isNewWorks=new SimpleBooleanProperty(false);
    private WorksDao worksDao = new WorksDao();
    private static LocalDate dateToNewScene = null;

    public static LocalDate getDateToNewScene() {
        return dateToNewScene;
    }

    public static boolean isIsNewWorks() {
        return isNewWorks.get();
    }

    public static  BooleanProperty isNewWorksProperty() {
        return isNewWorks;
    }

    public void setIsNewWorks(boolean isNewWorks) {
        this.isNewWorks.set(isNewWorks);
    }

    @FXML
     void initialize(){
    //    gridPaneGeneral.setOnMouseClicked(click->clickGrid(click));
        List<Works> worksList = new ArrayList<>(worksDao.getList());
        LocalDate local = LocalDate.now();
        LocalDate first =local.with(firstDayOfMonth());
        LocalDate lastDate = local.with(lastDayOfMonth());
        System.out.println(first.getDayOfWeek());
        System.out.println(lastDate.getDayOfMonth());
        ColumnConstraints columnMonday = new ColumnConstraints();
        columnMonday.setHalignment(HPos.CENTER);
        columnMonday.setPercentWidth(16);
        ColumnConstraints columnTuesday = new ColumnConstraints();
        columnTuesday.setPercentWidth(16);
        columnTuesday.setHalignment(HPos.CENTER);
        ColumnConstraints columnWednesday = new ColumnConstraints();
        columnWednesday.setPercentWidth(16);
        columnWednesday.setHalignment(HPos.CENTER);
        ColumnConstraints columnThursday = new ColumnConstraints();
        columnThursday.setPercentWidth(16);
        columnThursday.setHalignment(HPos.CENTER);
        ColumnConstraints columnFriday = new ColumnConstraints();
        columnFriday.setPercentWidth(16);
        columnFriday.setHalignment(HPos.CENTER);
        ColumnConstraints columnSaturday = new ColumnConstraints();
        columnSaturday.setPercentWidth(10);
        columnSaturday.setHalignment(HPos.CENTER);
        ColumnConstraints columnSunday = new ColumnConstraints();
        columnSunday.setPercentWidth(10);
        columnSunday.setHalignment(HPos.CENTER);
        RowConstraints rowConstraintsOne = new RowConstraints();
        rowConstraintsOne.setPercentHeight(5);
        rowConstraintsOne.setValignment(VPos.CENTER);
        RowConstraints rowConstraintsTwo = new RowConstraints();
        rowConstraintsTwo.setPercentHeight(19);
        RowConstraints rowConstraintsThree = new RowConstraints();
        rowConstraintsThree.setPercentHeight(19);
        RowConstraints rowConstraintsFour = new RowConstraints();
        rowConstraintsFour.setPercentHeight(19);
        RowConstraints rowConstraintsFive = new RowConstraints();
        rowConstraintsFive.setPercentHeight(19);
        RowConstraints rowConstraintsSix = new RowConstraints();
        rowConstraintsSix.setPercentHeight(19);
        gridPaneGeneral.getRowConstraints().addAll(rowConstraintsOne,rowConstraintsTwo,rowConstraintsThree,rowConstraintsFour,rowConstraintsFive,rowConstraintsSix);
        gridPaneGeneral.getColumnConstraints().addAll(columnMonday,columnThursday,columnWednesday,columnTuesday,columnFriday,columnSaturday,columnSunday);
        int coly = 1;
        int colx=getPosition(first.getDayOfWeek());
        for(int i = 1;i<=lastDate.getDayOfMonth();i++){
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            if(i==local.getDayOfMonth()){
                vBox.setStyle("-fx-border-width: 5;-fx-border-color: red");
            }
            LocalDate calendarDate = LocalDate.of(local.getYear(),local.getMonth(),i);
            Text label = new Text(calendarDate.toString());
            label.setStyle("-fx-font-weight: bold");
            vBox.getChildren().add(label);
            for(Works works:worksList){
                if(works.getDate().equals(calendarDate)){
                    Text worksLabel = new Text(works.getWork());
                    worksLabel.setStyle("-fx-font-size: 20");
                    HBox hBox = new HBox();
                    hBox.setSpacing(5);
                    hBox.getChildren().add(worksLabel);
                    if(works.isDone()){
                        worksLabel.setStyle("-fx-strikethrough: true");
                    }else {
                        Button button = new Button("wykonano");
                        button.setOnMouseClicked(click -> {
                            worksDao.setWorkDoing(works.getId());
                        });
                        hBox.getChildren().add(button);
                    }
                    vBox.getChildren().add(hBox);
                }
            }
            vBox.setOnMouseClicked(click->{
                clickGrid(click,calendarDate);
            });
            gridPaneGeneral.add(vBox,colx,coly);
            colx++;
            if(colx==7){
                colx=0;
                coly++;
            }

        }
        isNewWorks.addListener(observable -> {
            if(isNewWorks.get()) {
                System.out.println(worksDao.getList().get(0));
                isNewWorks.setValue(false);
            }
        });
    }

    private int getPosition(DayOfWeek day){
        int position=0;
        switch (day){
            case MONDAY: {
                position=0;
                break;
            }
            case TUESDAY:{
                position=1;
                break;
            }
            case WEDNESDAY: {
                position=2;
                break;
            }
            case THURSDAY: {
                position=3;
                break;
            }
            case FRIDAY: {
                position=4;
                break;
            }
            case SATURDAY: {
                position=5;
                break;
            }
            case SUNDAY: {
                position=6;
                break;
            }
        }
        return position;
    }

    public void clickGrid(javafx.scene.input.MouseEvent event, LocalDate date) {
        Stage stage = new Stage();
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/FXML/DetailsCalendarScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dateToNewScene=date;
        stage.setScene(new Scene(anchorPane,400,400));
        stage.setTitle(date.toString());
        stage.show();
    }
    }
