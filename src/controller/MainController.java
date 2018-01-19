package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    /*@FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() {
        loadMainScreen();
    }

    public void loadMainScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/MenuScreen.fxml"));
        SplitPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(SplitPane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }*/
}
