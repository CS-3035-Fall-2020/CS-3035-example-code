package Assignment5_Part2;

import Assignment5_Part2.InteractionModel.IAction;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
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

					//create node
					if (Main.graphView.getSelectedVertex()==null &&
							Main.iModel.getSelectedAction()== IAction.CREATE)
					{
						Vertex v = new Vertex(Main.iModel.getSelectedColor().color(),x,y);
						Main.graphModel.vertexListProperty().add(v);
					}
					//delete node
					else if (Main.graphView.getSelectedVertex()!=null &&
							Main.iModel.getSelectedAction()== IAction.DELETE)
					{
						Main.graphModel.deleteAllEdgesForVertex(selectedVertex);
						Main.graphModel.vertexListProperty().remove(selectedVertex);
						selectedVertex = null;
					}
				}
			}

		});

		Main.graphView.setOnMouseReleased(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {

				if (Main.iModel.getSelectedAction()==IAction.CREATE)
				{
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
			}
		});

		Main.graphView.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent ev) {
				if (Main.iModel.getSelectedAction()==IAction.CREATE)
				{
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
			}
		});

		Main.graphView.setOnMouseMoved(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				if (Main.iModel.getSelectedAction()==IAction.DELETE)
				{
					Vertex v = Main.graphView.getVertexAt((int)e.getX(), (int)e.getY());

					if (v != null)
					{
						Main.graphView.setCursor(Cursor.CROSSHAIR);
					}
					else
					{
						Main.graphView.setCursor(Cursor.DEFAULT);
					}
				}
				else
				{
					Main.graphView.setCursor(Cursor.DEFAULT);
				}
			}
		});
	}
}
