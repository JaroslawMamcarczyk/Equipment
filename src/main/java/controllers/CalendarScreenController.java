package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import worksToDo.Works;
import worksToDo.WorksDao;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;


public class CalendarScreenController {
    @FXML
    private GridPane gridPaneGeneral;
    @FXML
    private ImageView imageLeftArrow;
    @FXML
    private ImageView imageRightArrow;
    private static BooleanProperty isNewWorks = new SimpleBooleanProperty(false);
    private WorksDao worksDao = new WorksDao();
    private static LocalDate dateToNewScene = null;
    private Stage newStage = null;
    private LocalDate localDate = LocalDate.now();

    public static LocalDate getDateToNewScene() {
        return dateToNewScene;
    }

    public static boolean isIsNewWorks() {
        return isNewWorks.get();
    }

    public static BooleanProperty isNewWorksProperty() {
        return isNewWorks;
    }

    public void setIsNewWorks(boolean isNewWorks) {
        this.isNewWorks.set(isNewWorks);
    }

    @FXML
    void initialize() {
        Image image = new Image("/Pics/arrowRight.png");
        Image imageArrow =new Image("/Pics/arrowLeft.png");
        imageRightArrow.setImage(image);
        imageLeftArrow.setImage(imageArrow);
        imageLeftArrow.setOnMouseClicked(click->{
            Month monthPrevious;
            Year previousYear;
            if(localDate.getMonthValue()!=1) {
                monthPrevious = Month.of(localDate.getMonth().getValue() - 1);
                previousYear = Year.of(localDate.getYear());
            }else{
                monthPrevious = Month.of(12);
                previousYear = Year.of(localDate.getYear()-1);
            }
            clearGridPane();
            localDate = LocalDate.of(previousYear.getValue(),monthPrevious,1);
            createCalendar(localDate);
        });
        imageRightArrow.setOnMouseClicked(click->{
            Month monthNext;
            Year nextYear;
            if(localDate.getMonthValue()<12) {
                 monthNext = Month.of(localDate.getMonthValue() + 1);
                 nextYear = Year.of(localDate.getYear());
            }else{
                 monthNext = Month.of(1);
                 nextYear = Year.of(localDate.getYear()+1);
            }
            clearGridPane();
            localDate = LocalDate.of(nextYear.getValue(),monthNext,1);
            createCalendar(localDate);
        });
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
        rowConstraintsOne.setPercentHeight(2.5);
        rowConstraintsOne.setValignment(VPos.CENTER);
        RowConstraints rowConstraintsTwo = new RowConstraints();
        rowConstraintsTwo.setPercentHeight(19.5);
        RowConstraints rowConstraintsThree = new RowConstraints();
        rowConstraintsThree.setPercentHeight(19.5);
        RowConstraints rowConstraintsFour = new RowConstraints();
        rowConstraintsFour.setPercentHeight(19.5);
        RowConstraints rowConstraintsFive = new RowConstraints();
        rowConstraintsFive.setPercentHeight(19.5);
        RowConstraints rowConstraintsSix = new RowConstraints();
        rowConstraintsSix.setPercentHeight(19.5);
        gridPaneGeneral.getRowConstraints().addAll(rowConstraintsOne, rowConstraintsTwo, rowConstraintsThree, rowConstraintsFour, rowConstraintsFive, rowConstraintsSix);
        gridPaneGeneral.getColumnConstraints().addAll(columnMonday, columnThursday, columnWednesday, columnTuesday, columnFriday, columnSaturday, columnSunday);
        createCalendar(LocalDate.now());
        isNewWorks.addListener(observable -> {
            if (isNewWorks.get()) {
                createCalendar(localDate);
                isNewWorks.setValue(false);
            }
        });
    }

    private void clearGridPane() {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child:gridPaneGeneral.getChildren()){
            Integer rowIndex = gridPaneGeneral.getRowIndex(child);
            if(rowIndex!=null ){
                deleteNodes.add(child);
            }
        }
        gridPaneGeneral.getChildren().removeAll(deleteNodes);
    }

    private void createCalendar(LocalDate currentDate) {
        //List<Works> worksList = new ArrayList<>(worksDao.getList());
        LocalDate firstDayOfMonth = currentDate.with(firstDayOfMonth());
        LocalDate lastDateOfMonth = currentDate.with(lastDayOfMonth());
        int coly = 1;
        int colx = getPosition(firstDayOfMonth.getDayOfWeek());
        for (int i = 1; i <= lastDateOfMonth.getDayOfMonth(); i++) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            if (i == currentDate.getDayOfMonth()&&currentDate.getMonthValue()==LocalDate.now().getMonthValue()&&currentDate.getYear()==LocalDate.now().getYear()) {
                vBox.setStyle("-fx-border-width: 5;-fx-border-color: red");
            }
            LocalDate calendarDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), i);
            Text label = new Text(calendarDate.toString());
            label.setStyle("-fx-font-weight: bold");
            vBox.getChildren().add(label);
            for (Works works : worksDao.getList()) {
                if (works.getDate().equals(calendarDate)) {
                    Text worksLabel = new Text(works.getWork());
                    if (works.isDone()) {
                        worksLabel.setStyle("-fx-strikethrough: true");
                    }
                    Separator separator = new Separator();
                    vBox.getChildren().addAll(worksLabel,separator);
                }
            }
            vBox.setOnMouseClicked(click -> {
                if(newStage==null) {
                    newStage = clickGrid(click, calendarDate);
                }else{
                    newStage.close();
                    newStage = clickGrid(click, calendarDate);
                }
            });
            gridPaneGeneral.add(vBox, colx, coly);
            colx++;
            if (colx == 7) {
                colx = 0;
                coly++;
            }

        }
    }

    private int getPosition(DayOfWeek day) {
        int position = 0;
        switch (day) {
            case MONDAY: {
                position = 0;
                break;
            }
            case TUESDAY: {
                position = 1;
                break;
            }
            case WEDNESDAY: {
                position = 2;
                break;
            }
            case THURSDAY: {
                position = 3;
                break;
            }
            case FRIDAY: {
                position = 4;
                break;
            }
            case SATURDAY: {
                position = 5;
                break;
            }
            case SUNDAY: {
                position = 6;
                break;
            }
        }
        return position;
    }

    public Stage clickGrid(javafx.scene.input.MouseEvent event, LocalDate date) {
        dateToNewScene = date;
        Stage stage = new Stage();
            AnchorPane anchorPane = null;
            try {
                anchorPane = FXMLLoader.load(getClass().getResource("/FXML/DetailsCalendarScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(anchorPane, 400, 400));
            stage.setTitle(date.toString());
            stage.show();
            return stage;
        }
    }
