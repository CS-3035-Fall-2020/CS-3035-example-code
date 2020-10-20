package Assignment2_part2;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MyCheckBox extends Pane{
    //property for selected
    public final BooleanProperty selected = new SimpleBooleanProperty();
    public Boolean isSelected(){ return selected.get();}
    public void setSelected(boolean selected){ this.selected.set(selected);}
    public BooleanProperty selectedProperty(){ return selected;}

    private double min, max;
    private Canvas canvas;
    private int borderWidth = 2;
    private Color bgColor = Color.WHITE;
    private Color fgColor = Color.DARKGRAY;
    private Color borderColor = Color.BLACK;

    private int widgetWidth = 20, widgetHeight= 20;


    public MyCheckBox()
    {
        super();
        canvas = new Canvas(widgetWidth,widgetHeight);
        getChildren().add(canvas);

        drawSelected();

        addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseHandler());
    }

     public void drawSelected()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(borderColor);
        gc.fillRect(0, 0, widgetWidth, widgetHeight);

        gc.setFill(bgColor);
        gc.fillRect(borderWidth, borderWidth, widgetWidth - borderWidth * 2, widgetHeight - borderWidth * 2);

        if (selected.get())
        {
            gc.setStroke(fgColor);
            gc.setLineWidth(3);
            gc.strokeLine(borderWidth + 1, borderWidth + 1, widgetWidth - borderWidth - 1, widgetHeight - borderWidth - 1);
            gc.strokeLine(widgetWidth - borderWidth - 1,  borderWidth + 1, borderWidth + 1, widgetHeight - borderWidth - 1);
        }
    }

    public class MouseHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent click) {
            boolean value = !selected.get();
            selected.set(value);

            drawSelected();
        }
    }
}
