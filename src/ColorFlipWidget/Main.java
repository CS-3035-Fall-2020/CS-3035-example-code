package ColorFlipWidget;

import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {
    private Text text;

    public void start(Stage primaryStage)
    {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);

        ColorFlipWidget fw1 = new ColorFlipWidget(50, 50);
        ColorFlipWidget fw2 = new ColorFlipWidget(50, 50);

        root.add(fw1, 0, 0);
        root.add(fw2, 1, 0);

        text = new Text();
        text.setTextAlignment(TextAlignment.CENTER);
        updateColorName(fw1.getCounter());
        root.add(text, 0, 1, 2, 1);
        root.setHalignment(text, HPos.CENTER);

        fw1.counter().addListener(this::counterUpdateHandler);
        fw2.counter().addListener(this::counterUpdateHandler);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void counterUpdateHandler(Observable observable, Object oldValue, Object newValue) {
        updateColorName((Integer) newValue);
    }

    private void updateColorName(int colorValue)
    {
        List<String> colorNameList = ColorUtility.getColorNameList();
        text.setText(colorNameList.get(colorValue));
    }
}
