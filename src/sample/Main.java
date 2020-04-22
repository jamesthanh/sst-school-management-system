package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.controller.FileController;
import sample.implement.FileSerialization;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/View.fxml"));
        primaryStage.setTitle("System");
        primaryStage.setScene(new Scene(root, 1280,720));





        primaryStage.show();
    }

    @Override
    public void stop (){
        FileSerialization fileSerialization = new FileSerialization();
        fileSerialization.storeData();
    }


    public static void main(String[] args) {

        FileController.readFile();
        launch(args);


    }

    }
