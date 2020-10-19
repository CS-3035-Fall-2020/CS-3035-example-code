package SquareDrawerWithDragSelections;

import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Model {
	private SimpleListProperty<Rectangle> squareListProperty;

	private int squareSideLength;

	public Model(int squareSideLength) {
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		ObservableList<Rectangle> observableList = (ObservableList<Rectangle>) FXCollections.observableArrayList(list);
		squareListProperty = new SimpleListProperty<Rectangle>(observableList);

		this.squareSideLength = squareSideLength;
	}

	public SimpleListProperty<Rectangle> squareListProperty(){
		return squareListProperty;
	}

	public int getSquareSideLegnth() {return squareSideLength;}

	public void addSquare(double x, double y)
	{
		int squareX = (int) x - Main.model.getSquareSideLegnth()/2;
		int squareY = (int) y - Main.model.getSquareSideLegnth()/2;
		
		Rectangle newSquare = new Rectangle(squareX, squareY, squareSideLength, squareSideLength);
		squareListProperty.add(newSquare);
	}

	public void deleteSquareAt(int x, int y)
	{
		Shape delSquare = getSquareAt(x, y);
		squareListProperty.remove(delSquare);
	}

	private Shape getSquareAt(int x, int y)
	{
		Rectangle square = null;

		for (Rectangle s : squareListProperty)
		{
			if (s.contains(x, y))
			{
				square = s;
			}
		}

		return square;
	}
}
