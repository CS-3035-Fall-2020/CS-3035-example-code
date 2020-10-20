package Assignment3_Part2;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class MyFlowPane extends Pane {

    public MyFlowPane() {
        super();

        //add in clipping to the pane
        Rectangle clipRectangle = new Rectangle();
        this.setClip(clipRectangle);
        this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            clipRectangle.setWidth(newValue.getWidth());
            clipRectangle.setHeight(newValue.getHeight());
        });
    }

    @Override
    public void layoutChildren() {

        //set max, min and pref dimensions
        double prefWidth = 0, prefHeight = 0, minWidth = 0, minHeight = 0, maxHeight = 0, maxWidth = 0;

        double x = 0, y = 0;
        if (!getChildren().isEmpty())
        {
            //determine the dimensions
            for (int i = 0; i < getChildren().size(); i++) {
                Region r = (Region) getChildren().get(i);
                prefHeight += r.getPrefHeight();
                prefWidth += r.getPrefWidth();
                minWidth += r.getMinWidth();
                minHeight += r.getMinHeight();
                maxWidth += r.getMaxWidth();
                maxHeight += r.getMaxHeight();
            }

            //use max dimensions if there is enough space horizontal space
            if (maxWidth <= this.getWidth())
            {
                for (int i = 0; i < getChildren().size(); i++) {
                    Region r = (Region) getChildren().get(i);
                    r.resize(r.getMaxWidth(), r.getMaxHeight());
                    r.setTranslateX(x);
                    r.setTranslateY(y);
                    x += r.getMaxWidth();
                    y += r.getMaxHeight();
                }
            }

            //use pref dimensions if there is enough vertical space
            else if (prefWidth <= this.getWidth())
            {
                for (int i = 0; i < getChildren().size(); i++) {
                    Region r = (Region) getChildren().get(i);
                    r.resize(r.getPrefWidth(), r.getPrefHeight());
                    r.setTranslateX(x);
                    r.setTranslateY(y);
                    x += r.getPrefWidth();
                    y += r.getPrefHeight();
                }
            }

            //use min dimensions otherwise
            else
            {
                for (int i = 0; i < getChildren().size(); i++) {
                    Region r = (Region) getChildren().get(i);
                    r.resize(r.getMinWidth(), r.getMinHeight());
                    r.setTranslateX(x);
                    r.setTranslateY(y);
                    x += r.getMinWidth();
                    y += r.getMinHeight();
                }
            }
        }
    }
}
