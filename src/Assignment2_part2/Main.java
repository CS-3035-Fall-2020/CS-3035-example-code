package Assignment2_part2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private Label label;
    private MyCheckBox cFont;
    private MyCheckBox cColor;
    private MyCheckBox cUnder;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Assignment 2, Part 2");

        label = new Label("Assignment 2, Part 2");
        cFont = new MyCheckBox();
        Label lFont = new Label("font");
        HBox fontBox = new HBox(cFont, lFont);
        fontBox.setSpacing(5);
        cFont.selectedProperty().addListener(((observable, oldValue, newValue) ->{ updateFont(); }));

        cColor = new MyCheckBox();
        Label lColor = new Label("color");
        HBox colorBox = new HBox(cColor, lColor);
        colorBox.setSpacing(5);
        cColor.selectedProperty().addListener(((observable, oldValue, newValue) ->{updateFont();}));

        cUnder = new MyCheckBox();
        Label lUnder = new Label("under");
        HBox underBox = new HBox(cUnder, lUnder);
        underBox.setSpacing(5);
        cUnder.selectedProperty().addListener(((observable, oldValue, newValue) ->{updateFont();}));

        HBox checkBoxBox = new HBox(fontBox, colorBox, underBox);
        checkBoxBox.setAlignment(Pos.CENTER);
        checkBoxBox.spacingProperty().setValue(20);

        Button quitButton = new Button("quit");
        quitButton.setOnAction((event -> {
            Platform.exit();
        } ));

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
