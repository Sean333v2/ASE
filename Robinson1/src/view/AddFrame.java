package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Part;
import controller.MainController;

public class AddFrame {
	
	public JFrame addFrame;
	private JTextField name;
	private JTextField vendor;
	private JTextField number;
	private JTextField extnumber;
	private JComboBox unit;
	private int arguments = 7;
	private String[] info = new String[arguments];
	String[] unitStrings = {"Linear Feet", "Pieces", "Unknown"};
	
	public AddFrame(){
	
		name = new JTextField("");
		vendor = new JTextField("");
		number = new JTextField("");
		extnumber = new JTextField("");
		unit = new JComboBox(unitStrings);
		unit.setSelectedItem("Unknown");
		
		
		setUpGUI();
	}
	public AddFrame(Part errorPart){
		name = new JTextField("");
		vendor = new JTextField("");
		number = new JTextField("");
		extnumber = new JTextField("");
		unit = new JComboBox(unitStrings);
		unit.setSelectedItem("Unknown");
		
		
		if ("".equals(errorPart.getErrorListIndex(0)))
			name = new JTextField(errorPart.getPartName());
		if ("".equals(errorPart.getErrorListIndex(1)))
			number = new JTextField(errorPart.getPartNum());
		if ("".equals(errorPart.getErrorListIndex(2)))
			vendor = new JTextField(errorPart.getVendorName());
	
		if ("".equals(errorPart.getErrorListIndex(3)))
			unit.setSelectedItem(errorPart.getUnit());
		if ("".equals(errorPart.getErrorListIndex(4)))
			extnumber = new JTextField(""+errorPart.getExternalNum());

		

		setUpErrorGUI(errorPart);
		
	}

	private void setUpGUI(){
		addFrame = new JFrame("Cabinetron: Add Part");
		addFrame.setSize(300, 200);
		addFrame.setLayout(new GridLayout(0, 2));       
	    addFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            addFrame.dispose();
	         }        
	      });
	    
	    addFrame.add(new JLabel("Name:"));
	    addFrame.add(name);
	    addFrame.add(new JLabel("Part Number:"));
	    addFrame.add(number);
	    addFrame.add(new JLabel("Vendor:"));
	    addFrame.add(vendor);
	    //addFrame.add(new JLabel("Quantity: "));
	    //addFrame.add(quantity);
	    addFrame.add(new JLabel("Unit: "));
	    addFrame.add(unit);
	    addFrame.add(new JLabel("External Part Number: "));
	    addFrame.add(extnumber);
	   // addFrame.add(new JLabel("Location: "));
	   // addFrame.add(location);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton); 
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller
	        	 info[0] = name.getText();
	        	 info[1] = number.getText();
	        	 info[2] = vendor.getText();
	        	 //info[3] = quantity.getText();
	        	 info[3] = (String)unit.getSelectedItem();
	        	 info[4] = extnumber.getText();
	        	// info[6] = (String)location.getSelectedItem();

	        	 
	        	 
	        	 name = new JTextField(name.getText());
	        	 number = new JTextField(number.getText());
	        	 vendor = new JTextField(vendor.getText());
	        	 unit.setSelectedItem((String)unit.getSelectedItem());
	        	 extnumber = new JTextField(extnumber.getText());
	        	 
	        	 
	        	 	
	        	//Get Error report to possibly show in frame
	        	Part newPart;
				newPart = MainController.addPart(info, new Part());
				
				//show warning if part already exists
				
				if( MainController.nameExists(newPart.getPartName()) ){
       			 JOptionPane.showMessageDialog(addFrame,
       					    "Part Name already exists",
       					    "PName warning",
       					    JOptionPane.WARNING_MESSAGE);
				}
				
				
				if (newPart.getErrorCount() > 0 ) {
					addFrame.dispose();
					AddFrame addFrame = new AddFrame (newPart);
					addFrame.addFrame.setVisible(true);
				} else {
					addFrame.dispose();
				}
	      
	         }
	       
	        
	      });
	   
	}

	private void setUpErrorGUI(Part errorPart) {
		addFrame = new JFrame("***Try Again*** ");
		addFrame.setSize(670, 200);
		addFrame.setLayout(new GridLayout(0, 2));       
	    addFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            addFrame.dispose();
	         }        
	      });
		addFrame.add(new JLabel("Name: " + errorPart.getErrorListIndex(0)));
	    addFrame.add(name);
		addFrame.add(new JLabel("Part Number: " + errorPart.getErrorListIndex(1)));
	    addFrame.add(number);
		addFrame.add(new JLabel("Vendor: " + errorPart.getErrorListIndex(2)));
	    addFrame.add(vendor);
	    addFrame.add(new JLabel("Unit: "+errorPart.getErrorListIndex(3)));
	    addFrame.add(unit);
	    addFrame.add(new JLabel("External Part Number: "+errorPart.getErrorListIndex(4)));
	    addFrame.add(extnumber);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton);
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller

	        	 info[0] = name.getText();
	        	 info[1] = number.getText();
	        	 info[2] = vendor.getText();
	        	 info[3] = (String)unit.getSelectedItem();
	        	 info[4] = extnumber.getText();

	        	 name = new JTextField(name.getText());
	        	 number = new JTextField(number.getText());
	        	 vendor = new JTextField(vendor.getText());
	        	 unit.setSelectedItem((String)unit.getSelectedItem());
	        	 extnumber = new JTextField(extnumber.getText());
	        	 

	        	
	        	 Part newPart;
				// If there is a flag that this is an update
				if (errorPart.getIsNew() == false){
					newPart = MainController.updatePart(info, errorPart);
					if( !MainController.errorCheckPName(errorPart.getPartName(), errorPart.getPersonalId()) ){
		       			 JOptionPane.showMessageDialog(addFrame,
		       					    "Part Name already exists",
		       					    "PName warning",
		       					    JOptionPane.WARNING_MESSAGE);
		       			 	addFrame.dispose();
		       			 	errorPart.listUI.getDetailsButton().doClick();
					}
				}
				else{
					newPart = MainController.addPart(info,new Part());
					if( MainController.nameExists(newPart.getPartName()) ){
		       			 JOptionPane.showMessageDialog(addFrame,
		       					    "Part Name already exists",
		       					    "PName warning",
		       					    JOptionPane.WARNING_MESSAGE);
						}
					
				}
				if (newPart.getErrorCount() > 0) {
					addFrame.dispose();
					AddFrame addFrame = new AddFrame(newPart);
					addFrame.addFrame.setVisible(true);
				} else {
					addFrame.dispose();
				}
	        	
	         }
	        
	      });
	   
	}
}
