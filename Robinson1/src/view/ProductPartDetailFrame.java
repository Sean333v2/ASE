package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ProductTemplate;
import model.ProductTemplatePartDetail;

public class ProductPartDetailFrame {
	public JFrame productTemplateDetailFrame;
	private JTextField partName;
	private JTextField partquantity;
	private JLabel partid;
	public ProductTemplatePartDetail mainPart;
	public String[] info = new String[2];
	
	public ProductPartDetailFrame(ProductTemplatePartDetail p){
		mainPart = p;
		partName = new JTextField(mainPart.getPartName());
		partquantity = new JTextField(mainPart.getQuantity());
		partid = new JLabel("ID: "+mainPart.getPartId());
		setUpGUI();
	}
	
	private void setUpGUI(){
		productTemplateDetailFrame = new JFrame("Edit Part");
		productTemplateDetailFrame.setSize(300, 300);
		productTemplateDetailFrame.setLayout(new GridLayout(0, 2));       
	    productTemplateDetailFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				productTemplateDetailFrame.dispose();
	         }        
	      });
	    productTemplateDetailFrame.add(new JLabel(""));
	    JButton updateButton = new JButton("Update");
	    
	    productTemplateDetailFrame.add(partid);
	    productTemplateDetailFrame.add(new JLabel("Part:"));
	    productTemplateDetailFrame.add(partName);
	    productTemplateDetailFrame.add(new JLabel("Quantity:"));
	    productTemplateDetailFrame.add(partquantity);
	    productTemplateDetailFrame.add(updateButton);
	    
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	
	        	 info[0] = partName.getText();
	        	 info[1] = partquantity.getText();
	        	
	        	//Try adding into main controller
	        	 
	        	 //The following will check if duplicate Part Name exists when adding a Part, 
	        	 //the user should be warned and allowed to cancel.
	        	 
	        	 if(mainPart.getErrorCount() > 0){
	        		int i=0;
	        		while(mainPart.getErrorList()[i].equals("")){}
	        		JOptionPane.showMessageDialog(productTemplateDetailFrame,
       					    mainPart.getErrorList()[i],
       					    "PName warning",
       					    JOptionPane.ERROR_MESSAGE);
       			 	productTemplateDetailFrame.dispose();
       			 	mainPart.partUI.getDetailsButton().doClick();		 
	        	 }
	        	 else{
	        		 //********Update product in main controller & show product template view again
	        		 productTemplateDetailFrame.dispose();
	        	 }
	        	
			}
	      });
	    
	   
	}
	
	public void refresh(){
		partName.setText(mainPart.getPartName());
		partquantity.setText(mainPart.getQuantity());	
	}
}



