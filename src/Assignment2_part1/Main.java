package Assignment2_part1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private Label label;
    private CheckBox cFont;
    private CheckBox cColor;
    private CheckBox cUnder;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assignment 2, Part 1");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(200);

        label = new Label("Assignment 2, Part 1");
        cFont = new CheckBox("font");
        cFont.setOnAction(this::updateFont);
        cColor = new CheckBox("color");
        cColor.setOnAction(this::updateFont);
        cUnder = new CheckBox("underline");
        cUnder.setOnAction(this::updateFont);

        HBox checkBoxBox = new HBox(cFont, cColor, cUnder);
        checkBoxBox.setAlignment(Pos.CENTER);
        checkBoxBox.spacingProperty().setValue(20);

        Button quitButton = new Button("quit");
        quitButton.setOnAction((event -> Platform.exit()));

        VBox root = new VBox(label, checkBoxBox, quitButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20, 20, 20, 20));

        this.updateFont();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void updateFont(ActionEvent actionEvent)
    {
        this.updateFont();
    }
    private void updateFont() {
        double fontSize = 24;
        String family = "Arial";
        Color color = Color.BLACK;

        if (cFont.isSelected()) {
            family = "Times New Roman";
            fontSize = 26;
        }
        if (cColor.isSelected()) color = Color.FIREBRICK;

        label.setFont(Font.font(family,fontSize));
        label.setTextFill(color);
        label.setUnderline(cUnder.isSelected());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
