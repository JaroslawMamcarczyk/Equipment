package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainScreenController {
    @FXML
    private AnchorPane anchorGeneral;
   private AnchorPane centerPane;

    public void initialize(){
        try { centerPane = FXMLLoader.load(getClass().getResource("/FXML/ProductScreen.fxml"));
            anchorGeneral.getChildren().add(centerPane);
            anchorGeneral.setRightAnchor(centerPane,0.1);
            anchorGeneral.setLeftAnchor(centerPane,0.1);
            anchorGeneral.setBottomAnchor(centerPane,1.0);
            anchorGeneral.setTopAnchor(centerPane,20.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
