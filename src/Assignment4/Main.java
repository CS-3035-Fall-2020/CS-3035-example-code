package Assignment4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This example is created to demonstrate Model-View-Controller with an InteractionModel,
 * and the use of a state machine.
 * Like previous versions of this example, it introduces a fair bit of code, but this helps 
 * to keep things organized as interfaces start to get large.
 *
 *
 * @author scottb
 *
 */
public class Main extends Application {
	public static final double squareSideLength = 40;
	public static final double triangleSideLength = 46.188;
	public static final Model model = new Model(squareSideLength, triangleSideLength);
	public static final InteractionModel iModel = new InteractionModel();
	public static final View view = new View();
	public static final Controller controller = new Controller();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.setCenter(view);
			
			ToolBar bar = view.getToolBar();
			root.setTop(bar);
			
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}