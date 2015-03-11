package view;

import javax.swing.JButton;
import javax.swing.JLabel;

public class productTemplateListUI {
	private JLabel productIdLabel;
	private JLabel productNumLabel;
	private JButton editButton ;
	private JButton detailsButton;
	

	public JButton getEditButton() {
		return editButton;
	}
	public void setEditButton(String edit){
		editButton = new JButton(edit);
	}
	public JButton getDetailsButton() {
		return detailsButton;
	}
	public void setDetailsButton(String detail){
		detailsButton = new JButton(detail);
	}

	public JLabel getProductIdLabel() {
		return productIdLabel;
	}

	public void setProductIdLabel(String productIdLabel) {
		this.productIdLabel = new JLabel(productIdLabel);
	}

	public JLabel getProductNumLabel() {
		return productNumLabel;
	}

	public void setProductNumLabel(String productNumLabel) {
		this.productNumLabel = new JLabel(productNumLabel);
	}
}
