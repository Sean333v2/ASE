package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.LoginController;
import controller.MainController;
import model.Part;

public class PartFrame extends JFrame{
	
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
		setUpGUI();
	}
	
	private void setUpGUI(){
		partFrame = new JFrame("Cabinetron: Part");
		partFrame.setSize(300, 300);
		partFrame.setLayout(new GridLayout(0, 2));       
	    partFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				dispose();
	         }        
	      });
	    
	    partFrame.add(new JLabel("Name:"));
	    partFrame.add(name);
	    partFrame.add(new JLabel("Part Number:"));
	    partFrame.add(number);
	    partFrame.add(new JLabel("Vendor:"));
	    partFrame.add(vendor);
	    partFrame.add(new JLabel("Unit: "));
	    partFrame.add(unit);
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
	        	 info[3] = (String)unit.getSelectedItem();
	        	 info[4] = extnumber.getText();
	        	 String originalName = mainPart.getPartName();
	        	 mainPart = MainController.updatePart(info,mainPart);
	        	
	        	 //The following will check if duplicate Part Name exists when adding a Part, 
	        	 //the user should be warned and allowed to cancel.
        
	       
	        	/* if( !MainController.errorCheckPName(mainPart.getPartName(), mainPart.getPersonalId()) ){
	       			 JOptionPane.showMessageDialog(partFrame,
	       					    "Part Name already exists",
	       					    "PName warning",
	       					    JOptionPane.WARNING_MESSAGE);
	       			 	partFrame.dispose();
	       			 	mainPart.listUI.getDetailsButton().doClick();
					}*/
	       			
	       			
					if (mainPart.getErrorCount() > 0) {
						int i=0;
						while(mainPart.getErrorList()[i].equals("")){i++;}
						JOptionPane.showMessageDialog(partFrame,
	       					    mainPart.getErrorListIndex(i),
	       					    "PName warning",
	       					    JOptionPane.WARNING_MESSAGE);
						dispose();
	       			 	mainPart.listUI.getDetailsButton().doClick();
						/*
						partFrame.dispose();
						AddFrame addFrame = new AddFrame (newPart);
						addFrame.addFrame.setVisible(true);*/
					} else {
						dispose();
					}
			}
	      });
	    
	    if(LoginController.getPermissions(LoginController.currentLogin)[5] == false)
	    	updateButton.setEnabled(false); 
	    
	   
	}
	@Override
	public void dispose(){
		partFrame.dispose();
		
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
