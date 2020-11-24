package Assignment5_Part2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static final GraphModel graphModel = new GraphModel();
	public static final GraphView graphView = new GraphView();
	public static final GraphViewController graphViewController = new GraphViewController();
	public static final InteractionModel iModel = new InteractionModel();
	public static final ToolBarController toolBarController = new ToolBarController();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			ToolBar toolBar = (ToolBar) FXMLLoader.load(getClass().getResource("toolbar.fxml"));
			root.setTop(toolBar);
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
