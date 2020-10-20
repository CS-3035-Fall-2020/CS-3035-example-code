package Assignment3_Part2;

import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage primaryStage) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Interface.fxml"));

        //get different components
        MyFlowPane myFlowPane = (MyFlowPane) root.getCenter();
        VBox rightVBox = (VBox) root.getRight();
        ListView<String> lv = (ListView) rightVBox.getChildren().get(0);
        Button addButton = (Button) rightVBox.getChildren().get(1);
        Button delButton = (Button) rightVBox.getChildren().get(2);

        //populate the list of colors
        for (String value : ColorUtility.getColorNameList()) {
            lv.getItems().add(value);
        }

        Scene scene = new Scene(root);

        addButton.setOnAction((e)->{
            String colorName = lv.getSelectionModel().selectedItemProperty().get();
            Color c = ColorUtility.getColorsMap().get(colorName);

            HappyFaceDisplay hd = new HappyFaceDisplay(c);

            double prefSize = Math.random() * 100 + 100;
            double minSize = Math.random() * 70 + 10;
            double maxSize = Math.random() * 250 + 250;

            hd.setPrefSize(prefSize,prefSize);
            hd.setMinSize(minSize,minSize);
            hd.setMaxSize(maxSize,maxSize);

            myFlowPane.getChildren().add(hd);
        });

        delButton.setOnAction((e)->{
            myFlowPane.getChildren().clear();
        });

        Label label = (Label) root.getBottom();
        label.textProperty().bind(Bindings.concat(myFlowPane.widthProperty(), ",", myFlowPane.heightProperty()));

        primaryStage.setTitle("Assignment3, Part 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}