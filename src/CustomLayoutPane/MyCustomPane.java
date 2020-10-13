package CustomLayoutPane;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.Date;

/**
 * Simple demonstration layout pane that puts all children in the bottom right hand corner.
 */
public class MyCustomPane extends Pane {

    private static int count = 0;

    public MyCustomPane()
    {
        super();

    }

    @Override
    public void layoutChildren() {
        for (int i = 0; i < getChildren().size(); i++) {
            Node n = getChildren().get(i);

            //compute the size based on the internals of the node
            n.autosize();

            //alternatively the size can be determined using max, min, and/or pref size
            //base on your layout algorithm
//            n.prefWidth(100);
//            n.prefHeight(100);

            //relocate vs translate:
            // layout algorithms should use relocate by convention
            // application programmers would use translate
            n.relocate(this.getWidth() - n.getBoundsInParent().getWidth(),this.getHeight() - n.getBoundsInParent().getHeight());
            n.translateXProperty().set(-100);

        }
        System.out.println(count++);

    }
}
