package Assignment5_Part1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

public class Vertex {
	private SimpleIntegerProperty xProperty;
	private SimpleIntegerProperty yProperty;

	public Vertex(String label, int x, int y) {
		xProperty = new SimpleIntegerProperty(x);
		yProperty = new SimpleIntegerProperty(y);
	}

	public SimpleIntegerProperty xProperty()
	{
		return xProperty;
	}

	public SimpleIntegerProperty yProperty()
	{
		return yProperty;
	}

	public Point2D getLocation()
	{
		return new Point2D(xProperty.getValue(),yProperty.getValue());
	}

	public void setLocation(int x, int y)
	{
		xProperty.set(x);
		yProperty.set(y);
	}

}
