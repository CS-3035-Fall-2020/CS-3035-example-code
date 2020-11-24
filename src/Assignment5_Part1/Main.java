package Assignment5_Part1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static final GraphModel graphModel = new GraphModel();
	public static final GraphView graphView = new GraphView();
	public static final GraphViewController graphViewController = new GraphViewController();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			graphView.setSize(500,500);
			root.setCenter(graphView);
			Scene scene = new Scene(root,500,500);

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
