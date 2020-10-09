package CustomLayoutPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MyCustomPane root = new MyCustomPane();
        root.setPrefSize(400,400);
        Scene scene = new Scene(root);

        Button b = new Button("Button 1");
        root.getChildren().add(b);

        Button b2 = new Button("Button 2");
        root.getChildren().add(b2);

        primaryStage.setTitle("Custom Layout Pane Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
