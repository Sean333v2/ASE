package view;
/*
 * This function shows the detail view of an inventory part
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.InventoryItem;
import model.Part;

public class InventoryPartFrame{
	
	public JFrame inventoryFrame;
	private JTextField quantity;
	private JLabel name;
	private JComboBox location;
	private JLabel id;
	private int arguments = 8;
	private String[] info = new String[arguments];
	public InventoryItem mainItem;
	String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	public InventoryPartFrame(InventoryItem p){
		mainItem = p;
		quantity = new JTextField(mainItem.getQuantity());
		name = new JLabel("Name: " +mainItem.getPart().getPartName());
		location = new JComboBox(locationStrings);
		location.setSelectedItem(mainItem.getLocation());
		id = new JLabel("ID: "+mainItem.getItemId());
		setUpGUI();
	}
	
	private void setUpGUI(){
		inventoryFrame = new JFrame("Cabinetron: Part");
		inventoryFrame.setSize(300, 300);
		inventoryFrame.setLayout(new GridLayout(0, 2));       
	    inventoryFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
	            inventoryFrame.dispose();
	         }        
	      });
	    
	    //Show Inventory Item on mainframe
	    inventoryFrame.add(id);
	    inventoryFrame.add(new JLabel("Name:"));
	    inventoryFrame.add(name);
	    inventoryFrame.add(new JLabel("Location: "));
	    inventoryFrame.add(location);
	    inventoryFrame.add(new JLabel("Quantity: "));
	    inventoryFrame.add(quantity);
	    
	    
	    inventoryFrame.add(new JLabel(""));
	    JButton updateButton = new JButton("Submit Edits");
	   
	    //Work on this
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	 
	        	 info[0] = name.getText();   	 
	        	 info[1] = quantity.getText();
	        	 info[2] = (String)location.getSelectedItem();
	        	 
	        	 mainItem = MainController.updateInventory(mainItem, info);
	       
	        	 if(mainItem.getErrorCount() > 0){
	        		 inventoryFrame.dispose();
	        		 InventoryAddFrame inventoryaddFrame = new InventoryAddFrame(mainItem);
	        		 inventoryaddFrame.addFrame.setVisible(true);	 	
	        	 }
	        	 else{
	        		inventoryFrame.dispose();
	        		 }
	        	 
	         }
	      });
	    inventoryFrame.add(updateButton);
	}
	
	public void refresh(){
		
		name.setText(mainItem.getPart().getPartName());
		quantity.setText(""+mainItem.getQuantity());
		location.setSelectedItem(mainItem.getLocation());
	}
}
