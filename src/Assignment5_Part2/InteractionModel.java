package Assignment5_Part2;

import Assignment5_Part2.GraphView.VertexColor;

public class InteractionModel {
	public static enum IAction{CREATE,DELETE};

	private VertexColor selectedColor;
	private IAction selectedAction;

	public InteractionModel()
	{
		selectedAction = IAction.CREATE;
		selectedColor = VertexColor.GREEN;
	}

	public VertexColor getSelectedColor(){ return selectedColor;}
	public void setSelectedColor(VertexColor selected){ selectedColor = selected; }
	public IAction getSelectedAction(){return selectedAction;}
	public void setSelectedAction(IAction selected){ selectedAction = selected;}
}
