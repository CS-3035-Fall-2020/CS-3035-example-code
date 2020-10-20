package Assignment3_Part1;

import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Interface.fxml"));

        //get components
        HappyFaceDisplay happyFace = (HappyFaceDisplay) root.getCenter();
        ListView lv = (ListView) root.getRight();

        //populate color list
        for (String value : ColorUtility.getColorNameList())
        {
            lv.getItems().add(value);
        }

        //set color of face based on selection
        lv.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Color c = ColorUtility.getColorsMap().get(newValue);
                    happyFace.setColor(c);
                });

        Scene scene = new Scene(root);

        //set bottom dimensions
        Label label = (Label) root.getBottom();
        label.textProperty().bind(Bindings.concat(happyFace.widthProperty(), ",", happyFace.heightProperty()));

        primaryStage.setTitle("Assignment 3, Part 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}