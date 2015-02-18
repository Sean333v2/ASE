package view;

import javax.swing.JButton;
import javax.swing.JLabel;
//This class is specifically for the viewing of the parts in the mainframe
//Each part has a listpart object that gets set in the main controller method addpart
public class listPart {
	private JLabel partQuantityLabel;
	private JLabel partUnitLabel;
	private JLabel partNameLabel;
	private JButton deleteButton = new JButton("Delete");
	private JButton detailsButton = new JButton("Details");

	public JLabel getPartQuantityLabel() {
		return partQuantityLabel;
	}

	public void setPartQuantityLabel(String partQuantityLabel) {
		this.partQuantityLabel = new JLabel(partQuantityLabel);
	}
	public JLabel getPartUnitLabel(){
		return partUnitLabel;
	}
	public void setPartUnitLabel(String partUnitLabel){
		this.partUnitLabel = new JLabel(partUnitLabel);
	}
	public JLabel getPartNameLabel() {
		return partNameLabel;
	}

	public void setPartNameLabel(String partNameLabel) {
		this.partNameLabel = new JLabel(partNameLabel);
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getDetailsButton() {
		return detailsButton;
	}


}
