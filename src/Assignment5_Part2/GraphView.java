package Assignment5_Part2;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

public class GraphView extends Pane {
	public static enum VertexColor{
		BLUE(Color.ALICEBLUE),
		RED(Color.SALMON),
		GREEN(Color.LIGHTGREEN),
		SELECTED(Color.ORANGE);

		private final Color color;
		VertexColor(Color c){ color = c;}
		public Color color(){return color;}
	};
	public static final int VERTEX_DIAMETER = 50;
	public static final Font VERTEX_FONT = new Font(20);

	private Canvas canvas;
	private Vertex selectedVertex;
	private Edge selectedEdge;

	public GraphView() {
		canvas = new Canvas();
		this.getChildren().add(canvas);
		Main.graphModel.vertexListProperty().addListener(new ListChangeListener<Vertex>() {
			@Override
			public void onChanged(Change<? extends Vertex> v) {
				drawGraph();
			}
		});

		Main.graphModel.edgeListProperty().addListener(new ListChangeListener<Edge>() {
			@Override
			public void onChanged(Change<? extends Edge> e) {
				drawGraph();
			}
		});
	}

	@Override
	public void layoutChildren()
	{
		canvas.setWidth(this.getWidth());
		canvas.setHeight(this.getHeight());
		drawGraph();
	}

	public void drawGraph()
	{
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		//draw Edges
		for (Edge e: Main.graphModel.edgeListProperty())
		{
			int startX = (int) e.getStartPoint().getX();
			int startY = (int) e.getStartPoint().getY();
			int endX = (int) e.getEndPoint().getX();
			int endY = (int) e.getEndPoint().getY();

			if (e.hasEndVertexProperty().getValue())
				g.setLineWidth(1);
			else
				g.setLineWidth(4);

			g.strokeLine(startX, startY, endX, endY);
			g.setLineWidth(1);
		}

		int count = 0;

		//draw Vertices
		for (Vertex v: Main.graphModel.vertexListProperty())
		{
			int x = (int) v.getLocation().getX();
			int y = (int) v.getLocation().getY();

			if (v == selectedVertex)
				g.setFill(VertexColor.SELECTED.color());
			else
				g.setFill(v.getColor());

			g.strokeOval(x - VERTEX_DIAMETER/2, y - VERTEX_DIAMETER/2, VERTEX_DIAMETER, VERTEX_DIAMETER);
			g.fillOval(x - VERTEX_DIAMETER/2, y - VERTEX_DIAMETER/2, VERTEX_DIAMETER, VERTEX_DIAMETER);

			g.setStroke(Color.BLACK);
			g.setFill(Color.BLACK);
			g.setFont(VERTEX_FONT);
			g.fillText(""+count, x - VERTEX_FONT.getSize()/3, y + VERTEX_FONT.getSize()/3);

			count++;
		}
	}

	public Vertex getVertexAt(int x, int y) {
		Vertex returnVertex = null;

		for (Vertex v : Main.graphModel.vertexListProperty()){
			Ellipse n = new Ellipse(v.getLocation().getX(), v.getLocation().getY(), VERTEX_DIAMETER/2, VERTEX_DIAMETER/2);

			if (n.contains(x,y)){
				returnVertex = v;
			}
		};

		return returnVertex;
	}

	public void clearSelectedVertex()
	{
		selectedVertex = null;
		drawGraph();
	}

	public Vertex getSelectedVertex() {
		return selectedVertex;
	}

	public void setSelectedVertex(Vertex v)
	{
		selectedVertex = v;
		drawGraph();
	}

	public Edge getSelectedEdge() {
		return selectedEdge;
	}

	public void setSelectedEdge(Edge e) {
		selectedEdge = e;
		drawGraph();
	}

	public void clearSelectedEdge() {
		selectedEdge = null;
		drawGraph();
	}

	public void setSize(int w, int h)
	{
		this.setWidth(w);
		this.setHeight(h);
	}
}
