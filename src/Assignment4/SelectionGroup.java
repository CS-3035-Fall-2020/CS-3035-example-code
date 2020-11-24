package Assignment4;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class SelectionGroup {
	private SimpleListProperty<Shape> items;
	private BoundingBox bounds = new BoundingBox(0, 0, 0, 0);
	
	public SelectionGroup()
	{
		ArrayList<Shape> list = new ArrayList<Shape>();
		ObservableList<Shape> observableList = (ObservableList<Shape>) FXCollections.observableArrayList(list);
		items = new SimpleListProperty<Shape>(observableList);
		
		itemsProperty().addListener(new ListChangeListener<Shape>() {
			@Override
			public void onChanged(Change<? extends Shape> c) {
				calculateBoundingBox();
			}
		});
	}
	
	public BoundingBox getBoundingBox()
	{
		return bounds;
	}
	
	public SimpleListProperty<Shape> itemsProperty()
	{
		return items;
	}
	
	private void calculateBoundingBox()
	{
		double 	minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, 
				maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE; 
		for (Shape r : itemsProperty())
		{
			if (r.getBoundsInParent().getMinX() < minX)
				minX = r.getBoundsInParent().getMinX();
			if (r.getBoundsInParent().getMinY() < minY)
				minY = r.getBoundsInParent().getMinY();
			if (r.getBoundsInParent().getMaxX() > maxX)
				maxX = r.getBoundsInParent().getMaxX();
			if (r.getBoundsInParent().getMaxY() > maxY)
				maxY = r.getBoundsInParent().getMaxX();
		}
		bounds = new BoundingBox(minX, minY, maxX - minX, maxY - minY);
	}
}
