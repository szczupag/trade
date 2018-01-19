package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    public static Ekonomia ekonomia;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/MenuScreen.fxml"));
        SplitPane splitPane = loader.load();
        Scene scene = new Scene(splitPane,1600,900);
        scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setTitle("Giełda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void zapisz(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\Agata\\untitled\\src\\txt\\zapis.ser",true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ekonomia);
        }catch(IOException e){e.printStackTrace();}

    }
}
