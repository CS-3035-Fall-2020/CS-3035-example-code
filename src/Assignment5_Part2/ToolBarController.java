package Assignment5_Part2;

import Assignment5_Part2.GraphView.VertexColor;
import Assignment5_Part2.InteractionModel.IAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class ToolBarController {

	@FXML
	private void colorButtonAction(ActionEvent e)
	{
		String value = ((ToggleButton) e.getSource()).getText();

		VertexColor c = VertexColor.GREEN;

		switch(value){
			case "red": c = VertexColor.RED; break;
			case "green": c = VertexColor.GREEN; break;
			case "blue": c = VertexColor.BLUE; break;
		}
		Main.iModel.setSelectedColor(c);
	}

	@FXML
	private void actionButtonAction(ActionEvent e)
	{
		String value = ((ToggleButton) e.getSource()).getText();

		switch(value){
			case "create": Main.iModel.setSelectedAction(IAction.CREATE);break;
			case "delete": Main.iModel.setSelectedAction(IAction.DELETE);break;
		}
	}
}
