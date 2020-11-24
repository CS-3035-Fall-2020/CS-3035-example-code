package Assignment4;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Model {
	private SimpleListProperty<Shape> shapeListProperty;

	private double squareSideLength;

	private double triangleSideLength;

	public Model(double squareSideLength, double triangleSideLength) {
		ArrayList<Shape> list = new ArrayList<Shape>();
		ObservableList<Shape> observableList = (ObservableList<Shape>) FXCollections.observableArrayList(list);
		shapeListProperty = new SimpleListProperty<Shape>(observableList);

		this.squareSideLength = squareSideLength;
		this.triangleSideLength = triangleSideLength;
	}

	public SimpleListProperty<Shape> shapeListProperty(){
		return shapeListProperty;
	}

	public void addSquare(double x, double y)
	{
		double squareX = x - squareSideLength/2;
		double squareY = y - squareSideLength/2;
		
		Shape newSquare = new Rectangle(squareX, squareY, squareSideLength, squareSideLength);
		shapeListProperty.add(newSquare);
	}

	public void addTriangle(double x, double y) {
		double yPos = y - squareSideLength/2;
		Polygon newTriangle = new Polygon(x,yPos,
		                                x + triangleSideLength/-2, yPos + squareSideLength, 
		                                x + triangleSideLength/2, yPos + squareSideLength);
		shapeListProperty.add(newTriangle);
	}

	public void addCircle(double x, double y) {
		Shape newCircle = new Circle(x, y, squareSideLength/2);	
		shapeListProperty.add(newCircle);
	}
	
	public void deleteShapeAt(int x, int y)
	{
		Shape delShape = getShapeAt(x, y);
		shapeListProperty.remove(delShape);
	}
	

	private Shape getShapeAt(int x, int y)
	{
		Shape shape = null;

		for (Shape s : shapeListProperty)
		{
			if (s.contains(x, y))
			{
				shape = s;
			}
		}

		return shape;
	}
}
