package ListViewExample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A toy example to show how a ListView works. An object with an external model.
 *
 * @author scottbateman
 *
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Scene scene = new Scene(root,200,200);

            try {
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            } catch(NullPointerException e)
            {
                System.err.println("application.css file not found");
                Platform.exit();
            }

            ObservableList<String> names = FXCollections.observableArrayList(
                    "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
            ListView<String> listView = new ListView<>(names);
            listView.setCellFactory(TextFieldListCell.forListView());
            listView.setEditable(true);

            String[] nameAr = {"Jimmy","Jill","Harold","Jen"};
            ArrayList<String> newNames = new ArrayList<>(Arrays.asList(nameAr));

            Button add = new Button("add name");
            add.setOnAction((event)->{
                if (!newNames.isEmpty())
                    names.add(newNames.remove(0));
                if (newNames.isEmpty())
                    add.setDisable(true);
            });

            root.getChildren().add(add);

            ListView<String> horizontalListView = new ListView<>(names);
            horizontalListView.setOrientation(Orientation.HORIZONTAL);

            root.getChildren().add(listView);
            root.getChildren().add(horizontalListView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

