package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Part;
import controller.MainController;

public class AddFrame {
	
	public JFrame addFrame;
	private JTextField quantity;
	private JTextField name;
	private JTextField vendor;
	private JTextField number;
	private JTextField extnumber;
	private JComboBox unit;
	private int arguments = 8;
	private String[] info = new String[arguments];
	String[] unitStrings = {"Linear Feet", "Pieces", "Unknown"};

//git test2
	public AddFrame(){
		quantity = new JTextField("");
		name = new JTextField("");
		vendor = new JTextField("");
		number = new JTextField("");
		extnumber = new JTextField("");
		unit = new JComboBox(unitStrings);
		unit.setSelectedItem("Unknown");
		
		setUpGUI();
	}
	public AddFrame(Part errorPart){
		quantity = new JTextField("");
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
			quantity = new JTextField(""+errorPart.getQuantity());
		if ("".equals(errorPart.getErrorListIndex(4)))
			unit.setSelectedItem(errorPart.getUnit());
		if ("".equals(errorPart.getErrorListIndex(5)))
			extnumber = new JTextField(""+errorPart.getExternalNum());
		setUpErrorGUI(errorPart);
		
	}
	/*public AddFrame(String[] error, String n, String num, String v, String q) {
		quantity = new JTextField("");
		name = new JTextField("");
		vendor = new JTextField("");
		number = new JTextField("");

		if ("".equals(error[0]))
			name = new JTextField(n);
		if ("".equals(error[1]))
			number = new JTextField(num);
		if ("".equals(error[2]))
			vendor = new JTextField(v);
		if ("".equals(error[3]))
			quantity = new JTextField(q);

		setUpErrorGUI(error);
	}*/

	/*public AddFrame(String[] error) {
		quantity = new JTextField("");
		name = new JTextField("");
		vendor = new JTextField("");
		number = new JTextField("");

		setUpErrorGUI(error);
	}*/
	/*public AddFrame(String[] error) {
	}*/
	
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
	    addFrame.add(new JLabel("Quantity: "));
	    addFrame.add(quantity);
	    addFrame.add(new JLabel("Unit: "));
	    addFrame.add(unit);
	    addFrame.add(new JLabel("External Part Number: "));
	    addFrame.add(extnumber);
	    
	    
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton); 
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller
	        	 info[0] = name.getText();
	        	 info[1] = number.getText();
	        	 info[2] = vendor.getText();
	        	 info[3] = quantity.getText();
	        	 info[4] = (String)unit.getSelectedItem();
	        	 info[5] = extnumber.getText();
	        	 
	        	 
	        	 name = new JTextField(name.getText());
	        	 number = new JTextField(number.getText());
	        	 vendor = new JTextField(vendor.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	 unit.setSelectedItem((String)unit.getSelectedItem());
	        	 extnumber = new JTextField(extnumber.getText());
	        	 
	        	//Get Error report to possibly show in frame
	        	 Part newPart;
				newPart = MainController.addPart(info);
				if (newPart.getErrorCount() > 0) {
					addFrame.dispose();
					/*AddFrame addFrame = new AddFrame(newPart.getErrorList(),
							name.getText(), number.getText(), vendor.getText(),
							quantity.getText());*/
					AddFrame addFrame = new AddFrame (newPart);
					// addFrame.updateJTextFields(error, name.getText(),
					// number.getText(), vendor.getText(), quantity.getText());
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
	    addFrame.add(new JLabel("Quantity:  " +errorPart.getErrorListIndex(3)));
	    addFrame.add(quantity);
	    addFrame.add(new JLabel("Unit: "+errorPart.getErrorListIndex(4)));
	    addFrame.add(unit);
	    addFrame.add(new JLabel("External Part Number: "+errorPart.getErrorListIndex(5)));
	    addFrame.add(extnumber);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton);
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller

	        	 info[0] = name.getText();
	        	 info[1] = number.getText();
	        	 info[2] = vendor.getText();
	        	 info[3] = quantity.getText();
	        	 info[4] = (String)unit.getSelectedItem();
	        	 info[5] = extnumber.getText();
	        	 
	        	 name = new JTextField(name.getText());
	        	 number = new JTextField(number.getText());
	        	 vendor = new JTextField(vendor.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	 unit.setSelectedItem((String)unit.getSelectedItem());
	        	 extnumber = new JTextField(extnumber.getText());
	        	
	        	 Part newPart;
				// If there is a flg thhat this is an update
				if (errorPart.getErrorListIndex(7).equals("1"))
					newPart = MainController.updatePart(errorPart, info);
				else
					newPart = MainController.addPart(info);
				if (newPart.getErrorCount() > 0) {
					addFrame.dispose();
					/*AddFrame addFrame = new AddFrame(newPart.getErrorList(),
							name.getText(), number.getText(), vendor.getText(),
							quantity.getText());*/
					AddFrame addFrame = new AddFrame(newPart);
					addFrame.addFrame.setVisible(true);
				} else {
					addFrame.dispose();
				}
	        	
	         }
	        
	      });
	   
	}
}