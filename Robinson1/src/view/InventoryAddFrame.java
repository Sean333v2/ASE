package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.InventoryItem;
import model.Part;
import controller.MainController;

public class InventoryAddFrame {
	
	public JFrame addFrame;
	private JTextField quantity;
	private JTextField partId;
	private JComboBox location;
	private int arguments = 7;
	private String[] info = new String[arguments];
	String[] unitStrings = {"Linear Feet", "Pieces", "Unknown"};
	String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};

	public InventoryAddFrame(){
		quantity = new JTextField("");
		partId = new JTextField("");
		location = new JComboBox(locationStrings);
		location.setSelectedItem("Unknown");
		
		setUpGUI();
	}
	public InventoryAddFrame(Part errorPart){
		quantity = new JTextField("");
		partId = new JTextField("");
		location = new JComboBox(locationStrings);
		location.setSelectedItem("Unknown");
		
		quantity = new JTextField(""+errorPart.getQuantity());
		partId = new JTextField(""+errorPart.getExternalNum());
		location.setSelectedItem(errorPart.getLocation());

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
	    
	    addFrame.add(new JLabel("Part Id:"));
	    addFrame.add(partId);
	    addFrame.add(new JLabel("Quantity:"));
	    addFrame.add(quantity);
	    addFrame.add(new JLabel("Location: "));
	    addFrame.add(location);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton); 
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller
	        	 info[0] = ""+partId.getText();
	        	 info[1] = ""+quantity.getText();
	        	 info[2] = (String)location.getSelectedItem();
	        	 
	        	 
	        	 partId = new JTextField(partId.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	 location.setSelectedItem((String)location.getSelectedItem());
	        	 
	        	 	
	        	//Get Error report to possibly show in frame
	        	InventoryItem newItem;
				newItem = MainController.addIventoryItem(info, new InventoryItem());
				
				//show warning if item part/location combo already exists
				String partLocationError = "ERROR: '"+ newItem.getPartId() +"' already exists";
				if( newItem.getErrorCount() == 1 && newPart.getErrorListIndex(0).equals(partNameError) ){
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
	    addFrame.add(new JLabel("Quantity:  " +errorPart.getErrorListIndex(3)));
	    addFrame.add(quantity);
	    addFrame.add(new JLabel("Unit: "+errorPart.getErrorListIndex(4)));
	    addFrame.add(unit);
	    addFrame.add(new JLabel("External Part Number: "+errorPart.getErrorListIndex(5)));
	    addFrame.add(extnumber);
	    addFrame.add(new JLabel("Location: "+errorPart.getErrorListIndex(6)));
	    addFrame.add(location);
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
	        	 info[6] = (String)location.getSelectedItem();
	        	 
	        	 name = new JTextField(name.getText());
	        	 number = new JTextField(number.getText());
	        	 vendor = new JTextField(vendor.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	 unit.setSelectedItem((String)unit.getSelectedItem());
	        	 extnumber = new JTextField(extnumber.getText());
	        	 location.setSelectedItem((String)location.getSelectedItem());

	        	
	        	 Part newPart;
				// If there is a flag that this is an update
				if (errorPart.getIsNew() == false)
					newPart = MainController.updatePart(info, errorPart);
				else
					newPart = MainController.addPart(info,new Part());
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

