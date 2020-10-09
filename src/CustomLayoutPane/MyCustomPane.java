package CustomLayoutPane;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Simple demonstration layout pane that puts all children in the bottom right hand corner.
 */
public class MyCustomPane extends Pane {

    public MyCustomPane()
    {
        super();

    }

    @Override
    public void layoutChildren() {
        System.out.println(this.getWidth()+" "+getChildren().size());
        for (int i = 0; i < getChildren().size(); i++) {
            Node n = getChildren().get(i);

            //compute the size based on the internals of the node
            n.autosize();

            //relocate vs translate:
            // layout algorithms should use relocate by convention
            // application programmers would use translate
            System.out.println(this.getWidth() - n.getBoundsInParent().getWidth());
            n.relocate(this.getWidth() - n.getBoundsInParent().getWidth(),this.getHeight() - n.getBoundsInParent().getHeight());

            //alternatively the size can be determined using max, min, and/or pref size
            //base on your layout algorithm
//            n.prefWidth(100);
//            n.prefHeight(100);

        }

    }
}
