package Assignment4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class Controller {
	public enum State {READY, DRAG_SELECTION_STARTED, DRAG_ITEMS_STARTED}
	private State state;
	enum ButtonType {COPY, CUT, PASTE};
	
	public Controller() {
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());	
		state = State.READY;
		Main.view.getCopyButton().setOnAction(new ButtonHandler(ButtonType.COPY));
		Main.view.getPasteButton().setOnAction(new ButtonHandler(ButtonType.PASTE));
		Main.view.getCutButton().setOnAction(new ButtonHandler(ButtonType.CUT));
	}
	
	public class ButtonHandler implements EventHandler<ActionEvent>{
		private ButtonType type;
		
		public ButtonHandler(ButtonType t)
		{
			type = t;
		}
		
		@Override
		public void handle(ActionEvent event) {
			if (type == ButtonType.CUT)
			{
				Main.iModel.cutSelectedShapes();
			}
			else if (type == ButtonType.COPY)
			{
				
			}
			else if (type == ButtonType.PASTE)
			{
				Main.iModel.pasteCopiedShapes();
			}
		}
	}
	public class MouseHandler implements EventHandler<MouseEvent>{
		private double prevX = 0, prevY = 0;
		
		@Override
		public void handle(MouseEvent e) {
						
			switch(state)
			{
				case READY:
					if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
						prevX = e.getSceneX();
						prevY = e.getSceneY();
						
						if (Shape.class.isAssignableFrom(e.getTarget().getClass()))
						{
							Shape node = ((Shape) e.getTarget());
						
				            node.toFront();
													
							if (!Main.iModel.getSelectedShapes().itemsProperty().contains(node))
							{
								if (!e.isControlDown())
									Main.iModel.getSelectedShapes().itemsProperty().clear();
								
								Main.iModel.getSelectedShapes().itemsProperty().add(node);
							}
							else if (e.isControlDown())
							{
								Main.iModel.getSelectedShapes().itemsProperty().remove(node);
							}
						}	
						else 
						{
							Main.iModel.getSelectedShapes().itemsProperty().clear();
						}
					}
					else if (e.getEventType()==MouseEvent.DRAG_DETECTED)
					{					
						if (e.getTarget().getClass() == Main.view.getClass())
						{
							state = State.DRAG_SELECTION_STARTED;
							Main.iModel.startSelectRegion(e.getX(), e.getY());
						}
						else if (Shape.class.isAssignableFrom(e.getTarget().getClass()))
						{
							state = State.DRAG_ITEMS_STARTED;
						}
					}
					else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
					{
						if (e.getTarget().getClass()==Main.view.getClass())
						{
							if (Main.view.getSquareButton().isSelected())
								Main.model.addSquare(e.getX(), e.getY());
							else if (Main.view.getTriangleButton().isSelected())
								Main.model.addTriangle(e.getX(), e.getY());
							else if (Main.view.getCircleButton().isSelected())
								Main.model.addCircle(e.getX(), e.getY());
						}
						
						if (Shape.class.isAssignableFrom(e.getTarget().getClass()))
						{
							if (!e.isControlDown())
							{
								Main.iModel.getSelectedShapes().itemsProperty().clear();
							}
						}
					}
					break;	//end State.READY
			
				case DRAG_SELECTION_STARTED:
					if (e.getEventType() == MouseEvent.MOUSE_DRAGGED)
					{
						Main.iModel.updateSelectRegion(e.getX(), e.getY());
					}
					else if (e.getEventType() == MouseEvent.MOUSE_RELEASED)
					{
						state = State.READY;
						selectObjectsWithRegion();
						Main.iModel.selectRegionProperty().setValue(null);
					}
					break;// end State.DRAG_SELECTION_STARTED
			
				case DRAG_ITEMS_STARTED:
					if (e.getEventType()==MouseEvent.MOUSE_DRAGGED)
					{			
						double deltaX = e.getSceneX() - prevX;
	                    double deltaY = e.getSceneY() - prevY;
								            
						moveSelected(deltaX, deltaY);
						
						prevX = e.getSceneX();
						prevY = e.getSceneY();
					}
					
					else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
					{
						state = State.READY;
					}
					break; //end State.DRAG_ITEMS_STARTED
			}//end switch(state)
		}
	}

	private void selectObjectsWithRegion() {
		Shape selectRegion = Main.iModel.selectRegionProperty().getValue();
		Main.iModel.getSelectedShapes().itemsProperty().clear();
		
		if (selectRegion != null)
		{
			for (Shape s : Main.model.shapeListProperty())
			{
				Point2D topLeft = new Point2D(s.getBoundsInParent().getMinX(), s.getBoundsInParent().getMinY());
				Point2D bottomRight = new Point2D(s.getBoundsInParent().getMaxX(), s.getBoundsInParent().getMaxY());
				
				if (selectRegion.contains(topLeft) && selectRegion.contains(bottomRight))
				{
					Main.iModel.getSelectedShapes().itemsProperty().add(s);
				}
			}
		}
	}
	
	private void moveSelected(double addX, double addY) 
	{
		for (Shape r : Main.iModel.getSelectedShapes().itemsProperty())
		{
				r.setTranslateX(r.getLayoutX() + r.getTranslateX() + addX);	
				r.setTranslateY(r.getLayoutY() + r.getTranslateY() + addY);
		}
	}
}
