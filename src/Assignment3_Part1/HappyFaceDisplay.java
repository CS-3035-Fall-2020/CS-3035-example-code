package Assignment3_Part1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class HappyFaceDisplay extends Pane {
    private Canvas canvas;
    private final double PADDING = .05;
    private SimpleObjectProperty<Color> colorProperty;

    public HappyFaceDisplay()
    {
        super();
        //this.setPrefSize(600, 600);

        canvas = new Canvas();
        this.getChildren().add(canvas);
        colorProperty = new SimpleObjectProperty<Color>();
        this.setColor(Color.BLUE);
        colorProperty.addListener(
                (observable, oldValue, newValue) -> {
                    drawHappyFace();
                }
        );
    }

    @Override
    public void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());
        drawHappyFace(); // a method that you write
    }

    public Color getColor() {
        return colorProperty.get();
    }

    public void setColor(Color faceColor) {
        colorProperty.set(faceColor);
    }

    public ObjectProperty<Color> color() {
        return colorProperty;
    }

    private void drawHappyFace() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.save();

        g.clearRect(0,0, this.getWidth(), this.getHeight());
        g.setFill(getColor());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setLineWidth(this.getWidth() * .01);
        Double paddingW = this.getWidth() * PADDING;
        Double paddingH = this.getHeight() * PADDING;

        g.setStroke(Color.BLACK);

        //head
        g.strokeOval(
                    paddingW,
                    paddingH,
                this.getWidth() - paddingW * 2,
                this.getHeight() - paddingH * 2);

        //eye
        g.setLineWidth(paddingW);
        g.strokeLine(getWidth() * .25 + paddingW, getHeight() * .25 + paddingH, getWidth() * .25 +  paddingW, getHeight() * .35);
        g.strokeLine(getWidth() * .75 - paddingW, getHeight() * .25 + paddingH, getWidth() * .75 - paddingW, getHeight() * .35);

        //mouth
        g.setLineWidth(this.getWidth() * .01);
        g.strokeArc(getWidth() * .25 + paddingW, getHeight() * .4 + paddingH, getWidth() * .5 - paddingW * 2, getHeight() * .3, 200, 140, ArcType.OPEN);

        g.restore();
    }
}
