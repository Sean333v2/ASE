package view;

import javax.swing.JButton;
import javax.swing.JLabel;

public class listPart {
	public JLabel pq;
	public JLabel pn;
	public JButton delete;
	public JButton details;

	public JLabel getPq() {
		return pq;
	}

	public void setPq(String pq) {
		this.pq = new JLabel(pq);
	}

	public JLabel getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = new JLabel(pn);
	}

	public JButton getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = new JButton(delete);
	}

	public JButton getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = new JButton(details);
	}

}
