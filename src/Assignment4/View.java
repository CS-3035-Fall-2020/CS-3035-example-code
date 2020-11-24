package Assignment4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class View extends Pane {
	public static final Color FILL_COLOR = Color.GREEN;
	public static final Color SELECTED_FILL_COLOR = Color.BLUE;
	private static Group root;
	private ToolBar toolBar;
	private ToggleButton circleButton, squareButton, triangleButton;
	private Button cutButton, pasteButton, copyButton;
	
	public View() {
		createToolBar();
		
		Main.model.shapeListProperty().addListener(new ListChangeListener<Shape>() {
			@Override
			public void onChanged(Change<? extends Shape> c) {
				while (c.next())
                {
					for (Shape r : c.getRemoved())
						root.getChildren().remove(r);
					
					for (Shape r : c.getAddedSubList())
					{	
						r.setStroke(Color.BLACK);
						r.setFill(FILL_COLOR);
						root.getChildren().add(r);
					}
                }
			}
		});
		Main.iModel.getSelectedShapes().itemsProperty().addListener(new ListChangeListener<Shape>() {
			@Override
			public void onChanged(Change<? extends Shape> c) {
				deselectAll();
				for (Shape r : Main.iModel.getSelectedShapes().itemsProperty())
				{
					selectShape(r);
				}
			}
		});
		
		Main.iModel.selectRegionProperty().addListener(new ChangeListener<Shape>() {
			@Override
			public void changed(ObservableValue<? extends Shape> observable, Shape oldValue,
					Shape newValue) {
				root.getChildren().remove(oldValue);
				
				if (newValue !=null)
				{
					root.getChildren().add(newValue);
					newValue.setFill(new Color(0,0,.5,.3));
					newValue.setStroke(new Color(0,0,.5,1));
				}
			}
		});
		
		root = new Group();
		getChildren().add(root);
	}	
	
	public void deselect(Shape r)
	{
		r.setFill(FILL_COLOR);
		r.setStrokeWidth(1);
	}
	
	public void deselectAll() {
		for (Shape s : Main.model.shapeListProperty())
		{
			deselect(s);
		}
	}

	public void selectShape(Shape node) {
		node.setFill(View.SELECTED_FILL_COLOR);
		node.setStrokeWidth(4);
	}
	
	public ToolBar getToolBar()
	{
		return toolBar;
	}
	
	private void createToolBar() {
		toolBar = new ToolBar();
		ToggleGroup shapeButtonGroup = new ToggleGroup();
		
		//square button
		Image squareImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/square.png"));
		squareButton = new ToggleButton("", new ImageView(squareImg));
		squareButton.setUserData("square");
		squareButton.setToggleGroup(shapeButtonGroup);
		squareButton.setTooltip(new Tooltip("Square"));
		squareButton.setSelected(true);
		
		//circle button
		Image circleImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/circle.png"));
		circleButton = new ToggleButton("", new ImageView(circleImg));
		circleButton.setUserData("circle");
		circleButton.setToggleGroup(shapeButtonGroup);
		circleButton.setTooltip(new Tooltip("Circle"));

		
		//line button
		Image triangleImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/triangle.png"));
		triangleButton = new ToggleButton("", new ImageView(triangleImg));
		triangleButton.setToggleGroup(shapeButtonGroup);
		triangleButton.setTooltip(new Tooltip("Triangle"));
		triangleButton.setUserData("triangle");

		toolBar.getItems().add(squareButton);
		toolBar.getItems().add(circleButton);
		toolBar.getItems().add(triangleButton);
		
		//copy button
		Image copyImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/copy.png"));
		copyButton = new Button("", new ImageView(copyImg));
		copyButton.setTooltip(new Tooltip("Copy"));

		//cut button
		Image cutImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/cut.png"));
		cutButton = new Button("", new ImageView(cutImg));
		cutButton.setTooltip(new Tooltip("Cut"));

		//paste button
		Image pasteImg = new Image(getClass().getClassLoader().getResourceAsStream("Assignment4/images/paste.png"));
		pasteButton = new Button("", new ImageView(pasteImg));
		pasteButton.setTooltip(new Tooltip("Paste"));

		toolBar.getItems().add(new Separator());
		//toolBar.getItems().add(copyButton);//copy not shown
		toolBar.getItems().add(cutButton);
		toolBar.getItems().add(pasteButton);
	}

	public Button getCopyButton() {return copyButton;}
	public Button getPasteButton() {return pasteButton;}
	public Button getCutButton() {return cutButton;}
	public ToggleButton getTriangleButton() {return triangleButton;}
	public ToggleButton getCircleButton() {return circleButton;}
	public ToggleButton getSquareButton() {return squareButton;}
}
