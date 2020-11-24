package Assignment5_Part1;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

public class Edge {
	private SimpleIntegerProperty endX, endY;
	private SimpleBooleanProperty hasEndVertex;
	private Vertex vertex1, vertex2;

	public Edge(Vertex vertex1, Point2D endPoint) {
		endX = new SimpleIntegerProperty((int) endPoint.getX());
		endY = new SimpleIntegerProperty((int) endPoint.getY());
		hasEndVertex = new SimpleBooleanProperty(false);
		this.vertex1 = vertex1;
	}

	public Edge(Vertex vertex1, Vertex vertex2)
	{
		endX = new SimpleIntegerProperty(0);
		endY = new SimpleIntegerProperty(0);
		hasEndVertex = new SimpleBooleanProperty(true);
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public SimpleBooleanProperty hasEndVertexProperty()
	{
		return hasEndVertex;
	}

	public SimpleIntegerProperty endXProperty()
	{
		return endX;
	}

	public SimpleIntegerProperty endYProperty()
	{
		return endY;
	}

	public Point2D getStartPoint()
	{
		return vertex1.getLocation();
	}

	public void setEndPoint(int x, int y)
	{
		endX.set(x);
		endY.set(y);
	}

	public void setEndPoint(Vertex v2)
	{
		this.vertex2 = v2;
		this.hasEndVertex.set(true);
	}

	public Vertex getVertex2()
	{
		return vertex2;
	}

	public Vertex getVertex1()
	{
		return vertex1;
	}

	public Point2D getEndPoint()
	{
		if (hasEndVertex.get())
		{
			return vertex2.getLocation();
		}
		else
		{
			return new Point2D(endX.doubleValue(), endY.doubleValue());
		}
	}
}
