package Assignment4;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class InteractionModel {
	private SelectionGroup selectedShapes;
	private SimpleObjectProperty<Rectangle> selectRegion;
	private double selectStartX = 0, selectStartY = 0;
	private SelectionGroup copiedShapes;
	
	public InteractionModel()
	{
		copiedShapes = new SelectionGroup();
		selectedShapes = new SelectionGroup();
		selectRegion = new SimpleObjectProperty<Rectangle>();
	}
	
	public void cutSelectedShapes()
	{
		copiedShapes.itemsProperty().clear();
		for (Shape s : selectedShapes.itemsProperty())
		{


			copiedShapes.itemsProperty().add(s);


			Main.model.shapeListProperty().remove(s);

		}
		selectedShapes.itemsProperty().clear();
	}
	
	public void pasteCopiedShapes()
	{
		selectedShapes.itemsProperty().clear();

		for (Shape s : copiedShapes.itemsProperty())
		{
			Main.model.shapeListProperty().add(s);
			selectedShapes.itemsProperty().add(s);
		}
		
		copiedShapes.itemsProperty().clear();
	}
	
	public SimpleObjectProperty<Rectangle> selectRegionProperty()
	{
		return selectRegion;
	}
	
	public SelectionGroup getSelectedShapes(){
		return selectedShapes;
	}
	
	public void startSelectRegion(double x, double y)
	{
		selectRegion.set(new Rectangle(x,y,0,0));
		selectStartX = x;
		selectStartY = y;
	}
	
	public void updateSelectRegion(double x, double y)
	{
		Rectangle selectRect = selectRegionProperty().getValue();
		
		if (x >= selectStartX)
		{
			selectRect.setWidth(x - selectStartX);
		}
		else
		{
			double deltaX = selectRect.getX() - x;
			selectRect.setWidth(selectRect.getWidth()+deltaX);
			selectRect.setX(x);
		}
		
		if (y >= selectStartY)
		{
			selectRect.setHeight(y - selectStartY);
		}
		else 
		{
			double deltaY = selectRect.getY() - y;
			selectRect.setHeight(selectRect.getHeight()+deltaY);
			selectRect.setY(y);
		}
	}
}