package Assignment5_Part2;

import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.ArrayList;

public class GraphModel {
	private SimpleListProperty<Vertex> vertexListProperty;
	private SimpleListProperty<Edge> edgeListProperty;

	public GraphModel() {
		ObservableList<Edge> observableEdgeList = (ObservableList<Edge>) FXCollections.observableArrayList(
				new Callback<Edge, Observable[]>() {
			        @Override
			        public Observable[] call(Edge param) {
			            return new Observable[]{
			                    param.endXProperty(),
			                    param.endYProperty(),
			                    param.hasEndVertexProperty()
			            };
			        }
				}
			);
		edgeListProperty = new SimpleListProperty<Edge>(observableEdgeList);

		//ArrayList<Vertex> vList = new ArrayList<Vertex>();

		ObservableList<Vertex> observableVertexList = (ObservableList<Vertex>) FXCollections.observableArrayList(
			new Callback<Vertex, Observable[]>() {
		        @Override
		        public Observable[] call(Vertex param) {
		            return new Observable[]{
		                    param.xProperty(),
		                    param.yProperty()
		            };
		        }
			}
		);

		vertexListProperty = new SimpleListProperty<Vertex>(observableVertexList);
	}

	public void deleteAllEdgesForVertex(Vertex v)
	{
		ArrayList<Edge> delEdges = new ArrayList<Edge>();
		for (Edge e : edgeListProperty)
		{
			if (e.getVertex1()==v || e.getVertex2()==v)
			{
				delEdges.add(e);
			}
		}

		edgeListProperty.removeAll(delEdges);
	}

	public SimpleListProperty<Vertex> vertexListProperty()
	{
		return vertexListProperty;
	}

	public SimpleListProperty<Edge> edgeListProperty()
	{
		return edgeListProperty;
	}
}
