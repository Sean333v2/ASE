package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.Part;

public class PartFrame{
	
	public JFrame partFrame;
	//private JTextField quantity;
	private JTextField name;
	private JTextField vendor;
	private JTextField number;
	private JTextField extnumber;
	private JComboBox unit;
	//private JComboBox location;
	private JLabel id;
	private int arguments = 8;
	private String[] info = new String[arguments];
	public Part mainPart;
	String[] unitStrings = {"Linear Feet", "Pieces", "Unknown"};
	//String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	public PartFrame(Part p){
		mainPart = p;
		//quantity = new JTextField(Integer.toString(mainPart.getQuantity()));
		name = new JTextField(mainPart.getPartName());
		vendor = new JTextField(mainPart.getVendorName());
		number = new JTextField(mainPart.getPartNum());
		unit = new JComboBox(unitStrings);
		unit.setSelectedItem(mainPart.getUnit());
		//location = new JComboBox(locationStrings);
		//location.setSelectedItem(mainPart.getLocation());
		id = new JLabel("ID: "+p.getPersonalId());
		extnumber =  new JTextField(mainPart.getExternalNum());
		setUpGUI(p);
	}
	
	private void setUpGUI(Part newPart){
		partFrame = new JFrame("Cabinetron: Part");
		partFrame.setSize(300, 300);
		partFrame.setLayout(new GridLayout(0, 2));       
	    partFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
	            partFrame.dispose();
	         }        
	      });
	    
	    partFrame.add(new JLabel("Name:"));
	    partFrame.add(name);
	    partFrame.add(new JLabel("Part Number:"));
	    partFrame.add(number);
	    partFrame.add(new JLabel("Vendor:"));
	    partFrame.add(vendor);
	   //partFrame.add(new JLabel("Quantity: "));
	  //  partFrame.add(quantity);
	    partFrame.add(new JLabel("Unit: "));
	    partFrame.add(unit);
	   // partFrame.add(new JLabel("Location: "));
	   // partFrame.add(location);
	    partFrame.add(new JLabel("External Part Number: "));
	    partFrame.add(extnumber);
	    partFrame.add(id);
	    
	    
	    partFrame.add(new JLabel(""));
	    JButton updateButton = new JButton("Update");
	    partFrame.add(updateButton);
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	 
	        	 info[0] = name.getText();
	        	 info[1] = number.getText();
	        	 info[2] = vendor.getText();
	        	// info[3] = quantity.getText();
	        	 info[3] = (String)unit.getSelectedItem();
	        	 info[4] = extnumber.getText();
	        	// info[6] = (String)location.getSelectedItem();
	        	 String originalName = newPart.getPartName();
	        	 mainPart = MainController.updatePart(info,mainPart);
	        	
	        	 //The following will check if duplicate Part Name exists when adding a Part, 
	        	 //the user should be warned and allowed to cancel.
        
	        	//show warning if part already exists
					String partNameError = "ERROR: '"+ newPart.getPartName()+"' already exists";
					if( newPart.getErrorCount() == 1 && newPart.getErrorListIndex(0).equals(partNameError) ){
	       			 JOptionPane.showMessageDialog(partFrame,
	       					    "Part Name already exists",
	       					    "PName warning",
	       					    JOptionPane.WARNING_MESSAGE);
	       			partFrame.dispose();
	       			newPart.setPartName(originalName);
	       			newPart.listUI.getDetailsButton().doClick();
	       			
	       			
	       			 
					}
					else if (newPart.getErrorCount() > 0) {
						partFrame.dispose();
						AddFrame addFrame = new AddFrame (newPart);
						addFrame.addFrame.setVisible(true);
					} else {
						partFrame.dispose();
					}
			}
	      });
	    
	   
	}
	
	public void refresh(){
		name.setText(mainPart.getPartName());
		number.setText(mainPart.getPartNum());
		vendor.setText(mainPart.getVendorName());
		//quantity.setText(""+mainPart.getQuantity());
		unit.setSelectedItem(mainPart.getUnit());
		extnumber.setText(mainPart.getExternalNum());
		//location.setSelectedItem(mainPart.getLocation());
	}
}
