package PushCounter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * PushCounter.java
 * Demonstrates JavaFX buttons and event handlers.
 * @author Lewis/Loftus
*/
public class PushCounter extends Application
{
    private int count;
    private Text countText;

    /**
     * Presents a GUI containing a button and text that displays
     * how many times the button is pushed.
     */
    public void start(Stage primaryStage)
    {
        count = 0;
        countText = new Text("Pushes: "+count);

        Button push = new Button("Push Me!");
        push.setOnAction(this::processButtonPress);

        FlowPane pane = new FlowPane(push, countText);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(200);
        pane.setStyle("-fx-background-color: cyan");

        Scene scene = new Scene(pane, 300, 100);

        primaryStage.setTitle("Push Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Updates the counter and text when the button is pushed.
     */
    public void processButtonPress(ActionEvent event)
    {
        count++;
        countText.setText("Pushes: " + count);
    }
}
