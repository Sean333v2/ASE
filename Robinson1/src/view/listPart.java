package view;

import javax.swing.JButton;
import javax.swing.JLabel;
//This class is specifically for the viewing of the parts in the mainframe
//Each part has a listpart object that gets set in the main controller method addpart
public class listPart {
	private JLabel partQuantityLabel;
	private JLabel partUnitLabel;
	private JLabel partNameLabel;
	private JButton deleteButton ;
	private JButton detailsButton;
	
	
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
	public void setDeleteButton(String delete){
		deleteButton = new JButton(delete);
	}
	public JButton getDetailsButton() {
		return detailsButton;
	}
	public void setDetailsButton(String detail){
		detailsButton = new JButton(detail);
	}


}
