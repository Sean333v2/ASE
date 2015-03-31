package view;
/*
 * This function shows the detail view of an inventory part
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.InventoryItem;
import model.InventoryItemDB;
import model.Part;

public class InventoryPartFrame{
	
	public JFrame inventoryFrame;
	private JTextField quantity;
	private JLabel name;
	private JComboBox location;
	private JLabel id;
	private int partId;
	private int arguments = 8;
	private String[] info = new String[arguments];
	public InventoryItem mainItem;
	String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2"};
	
	
	public InventoryPartFrame(InventoryItem p){
		partId = p.getPartId();
		mainItem = p;
		quantity = new JTextField(mainItem.getQuantity());
		if(mainItem.getIsPart())
			name = new JLabel("Name: " +mainItem.getPart().getPartName());
		else
			name = new JLabel("Name: " +mainItem.getPartProduct().getProductDescription());
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
	    inventoryFrame.add(new JLabel(""));
	    inventoryFrame.add(new JLabel("Name:"));
	    inventoryFrame.add(name);
	    inventoryFrame.add(new JLabel("Location: "));
	    inventoryFrame.add(location);
	    inventoryFrame.add(new JLabel("Quantity: "));
	    inventoryFrame.add(quantity);
	    
	    JButton updateButton = new JButton("Submit Edits");
	    JButton partButton = new JButton("Part Details");
	    
	    
	    /*partButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainItem.getPart().listUI.getDetailsButton().doClick();
				
			}
	    });*/
	
	    //Work on this
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	 
	        	 info[0] = ""+partId ;  	 
	        	 info[1] = quantity.getText();
	        	 info[2] = (String)location.getSelectedItem();
	        	 
	        	 mainItem = MainController.updateInventoryItem(info, mainItem);
	        	 if(MainController.timeFlag){
	        		 InventoryItem temp = InventoryItemDB.getInventoryItemByPartIdAndLocation(mainItem.getPartId(), mainItem.getLocation(), mainItem.getIsPart());
	        		 mainItem.setLocation(temp.getLocation());
	        		 mainItem.setPartId(temp.getPartId());
	        		 mainItem.setQuantity(temp.getQuantity());
	        		 mainItem.setTime(temp.getTime());
	        	 }

					/*if( mainItem.getErrorCount() > 0 ){
						for(int i = 0; i < mainItem.getErrorCount(); i++){
							if(mainItem.getErrorListAtIndex(i) != null && !mainItem.getErrorListAtIndex(i).equals("")){
								JOptionPane.showMessageDialog(inventoryFrame,
										mainItem.getErrorListAtIndex(i),
										"Warning",
										JOptionPane.WARNING_MESSAGE);
	       			 			break;
							}
						}
	       			//inventoryFrame.dispose();
	       			//mainItem.setPartName(originalName);
	       			//mainItem.partUI.getDetailsButton().doClick();
					}*/
	       			
	        	 if(mainItem.getErrorCount() > 0){
	        		 /*inventoryFrame.dispose();
	        		 InventoryAddFrame inventoryaddFrame = new InventoryAddFrame(mainItem);
	        		 inventoryaddFrame.addFrame.setVisible(true);*/
	        		 if(MainController.timeFlag){
							MainController.timeFlag = false;
							JOptionPane.showMessageDialog(inventoryFrame,
		       					    "Data has already been modified! Refreshing data!",
		       					    "WARNING",
		       					    JOptionPane.WARNING_MESSAGE);
							
					}
	        		 else{
	        			 for(int i = 0; i<mainItem.getErrorCount(); i++){
	        				 if(mainItem.getErrorListAtIndex(i) != null && !mainItem.getErrorListAtIndex(i).equals("")){
	        					 JOptionPane.showMessageDialog(inventoryFrame,
			       					    mainItem.getErrorListAtIndex(i),
			       					    "WARNING",
			       					    JOptionPane.WARNING_MESSAGE);
	        					 break;
	        				 }
	        			 }
	        		 }
	        		 
	        	 }
	        	 else{
	        		 if(MainController.timeFlag){
							MainController.timeFlag = false;
							JOptionPane.showMessageDialog(inventoryFrame,
		       					    "Data has already been modified! Refreshing data!",
		       					    "WARNING",
		       					    JOptionPane.WARNING_MESSAGE);
			        		 inventoryFrame.dispose();
			        		 mainItem.partUI.getDetailsButton().doClick();
						}
	        		 else
	        			 inventoryFrame.dispose();

	        	}
	        	 
	         }
	      });
	    inventoryFrame.add(updateButton);
	    //inventoryFrame.add(partButton);
	}
	
	public void refresh(){
		if(mainItem.getIsPart())
			name.setText(mainItem.getPart().getPartName());
		else
			name.setText(mainItem.getPartProduct().getProductDescription());
		quantity.setText(""+mainItem.getQuantity());
		location.setSelectedItem(mainItem.getLocation());
	}
}
