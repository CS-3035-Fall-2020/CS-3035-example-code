package Assignment5_Part1;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GraphViewController{

	public GraphViewController() {
		Main.graphView.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY)
				{
					int x = (int) e.getX();
					int y = (int) e.getY();

					Vertex selectedVertex = Main.graphView.getVertexAt(x,y);
					Main.graphView.setSelectedVertex(selectedVertex);

					if (Main.graphView.getSelectedVertex()==null)
					{
						String label = Main.graphModel.vertexListProperty().size() + 1 + "";
						Vertex v = new Vertex(label,x,y);
						Main.graphModel.vertexListProperty().add(v);
					}
				}
			}

		});

		Main.graphView.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				Main.graphView.clearSelectedVertex();

				Edge edge = Main.graphView.getSelectedEdge();

				if (edge != null)
				{
					Vertex v = Main.graphView.getVertexAt((int)e.getX(), (int)e.getY());
					if (v != null)
					{
						edge.setEndPoint(v);
					}
					else
					{
						Main.graphModel.edgeListProperty().remove(edge);
					}
				}
				Main.graphView.clearSelectedEdge();

			}

		});

		Main.graphView.setOnMouseDragged(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent ev) {

				Vertex v = Main.graphView.getSelectedVertex();
				Edge edge = Main.graphView.getSelectedEdge();

				if (v != null){
					if (ev.isShiftDown())
					{
						if (edge == null)
						{
							Edge e = new Edge(v, new Point2D(ev.getX(), ev.getY()));
							Main.graphView.setSelectedEdge(e);
							Main.graphModel.edgeListProperty().add(e);
						}
						else{
							edge.setEndPoint((int) ev.getX(), (int) ev.getY());
						}
					}
					else
					{
						v.setLocation((int) ev.getX(), (int) ev.getY());
					}
				}
			}
		});
	}

}
