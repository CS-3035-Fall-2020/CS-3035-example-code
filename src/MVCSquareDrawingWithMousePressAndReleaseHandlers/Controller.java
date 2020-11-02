package MVCSquareDrawingWithMousePressAndReleaseHandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {

	public Controller() {
		Main.model.addSquare(100, 100);
		
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());	
	}
	
	public class MouseHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent e) {
		
			if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
				System.out.println("PRESSED");

				if (e.getTarget().getClass() == Rectangle.class)
				{
					Rectangle node = ((Rectangle) e.getTarget());
					if (node.contains(e.getX(), e.getY()))
						node.setFill(View.SELECTED_FILL_COLOR);
				}
				else
				{
					int x = (int) e.getX() - Main.model.getSquareSideLegnth()/2;
					int y = (int) e.getY() - Main.model.getSquareSideLegnth()/2;
					Main.model.addSquare(x, y);
				}
				
			}
			else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
			{
				System.out.println("RELEASED");
				if (e.getTarget().getClass() == Rectangle.class)
				{
					Rectangle node = ((Rectangle) e.getTarget());
					if (node.contains(e.getX(), e.getY()))
						node.setFill(View.FILL_COLOR);
				}
			}
		}
	}

}
