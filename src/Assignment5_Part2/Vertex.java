package Assignment5_Part2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Vertex {
	private SimpleIntegerProperty xProperty;
	private SimpleIntegerProperty yProperty;
	private Color color;

	public Vertex(Color c, int x, int y) {
		setColor(c);
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

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
