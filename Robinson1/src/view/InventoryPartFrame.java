package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.Part;

public class InventoryPartFrame{
	
	public JFrame partFrame;
	private JTextField quantity;
	private JLabel name;
	private JComboBox location;
	private JLabel id;
	
	private int arguments = 8;
	private String[] info = new String[arguments];
	public Part mainPart;
	String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	public InventoryPartFrame(Part p){
		mainPart = p;
		quantity = new JTextField(Integer.toString(mainPart.getQuantity()));
		name = new JLabel("Name: " +mainPart.getPartName());
		location = new JComboBox(locationStrings);
		location.setSelectedItem(mainPart.getLocation());
		id = new JLabel("ID: "+mainPart.getPersonalId());
		setUpGUI(p);
	}
	
	private void setUpGUI(Part p){
		partFrame = new JFrame("Cabinetron: Part");
		partFrame.setSize(300, 300);
		partFrame.setLayout(new GridLayout(0, 2));       
	    partFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
	            partFrame.dispose();
	         }        
	      });
	    
	    //Show Inventory Item on mainframe
	    partFrame.add(id);
	    partFrame.add(new JLabel("Name:"));
	    partFrame.add(name);
	    partFrame.add(new JLabel("Location: "));
	    partFrame.add(location);
	    partFrame.add(new JLabel("Quantity: "));
	    partFrame.add(quantity);
	    
	    
	    partFrame.add(new JLabel(""));
	    JButton updateButton = new JButton("Submit Edits");
	   
	    //Work on this
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	 
	        	 info[0] = name.getText();   	 
	        	 info[1] = quantity.getText();
	        	 info[2] = (String)location.getSelectedItem();
	        	 
	        	 mainPart = MainController.updateInventory(mainPart, info);
	       
	        	 if(mainPart.getErrorCount() > 0){
	        		 partFrame.dispose();
	        		 AddFrame addFrame = new AddFrame(mainPart);
	        		 addFrame.addFrame.setVisible(true);	 	
	        	 }
	        	 else{
	        		partFrame.dispose();
	        		 }
	        	 
	         }
	      });
	    partFrame.add(updateButton);
	}
	
	public void refresh(){
		
		name.setText(mainPart.getPartName());
		quantity.setText(""+mainPart.getQuantity());
		location.setSelectedItem(mainPart.getLocation());
	}
}
