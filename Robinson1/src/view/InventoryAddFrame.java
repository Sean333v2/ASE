package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.InventoryItem;
import model.InventoryItemDB;
import model.Part;
import controller.MainController;

public class InventoryAddFrame extends JFrame{
	
	public JFrame addFrame;
	private JTextField quantity;
	private JTextField partId;
	private JComboBox location;
	private JCheckBox isPart;
	private String itemlocation;
	private int arguments = 7;
	private String[] info = new String[arguments];
	String[] unitStrings = {"Linear Feet", "Pieces", "Unknown"};
	String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	
	public InventoryAddFrame(String location){
	
		
		itemlocation = location;
		isPart = new JCheckBox();
		quantity = new JTextField("");
		partId = new JTextField("");
		//location = new JComboBox(locationStrings);
		//location.setSelectedItem("Unknown");
		
		setUpGUI();
	}
	/*public InventoryAddFrame(InventoryItem errorItem){
		quantity = new JTextField("");
		partId = new JTextField("");
		isPart = new JCheckBox();
		//location = new JComboBox(locationStrings);
		//location.setSelectedItem("Unknown");
		
		quantity = new JTextField(""+errorItem.getQuantity());
		partId = new JTextField(""+errorItem.getPartId());
		//location.setSelectedItem(errorItem.getLocation());

		//location.setSelectedItem(errorItem.getLocation());
		itemlocation = errorItem.getLocation();
		
		setUpErrorGUI(errorItem);
		
	}*/

	private void setUpGUI(){
		addFrame = new JFrame("Cabinetron: Add Part in "+itemlocation );
		addFrame.setSize(300, 200);
		addFrame.setLayout(new GridLayout(0, 2));       
	    addFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	dispose();
	         }        
	      });
	    addFrame.add(new JLabel("Is This Item a Part?"));
	    addFrame.add(isPart);
	    addFrame.add(new JLabel("Part/Product Id:"));
	    addFrame.add(partId);
	    addFrame.add(new JLabel("Quantity:"));
	    addFrame.add(quantity);
	    //addFrame.add(new JLabel("Location: "));
	    //addFrame.add(location);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton); 
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller
	        	 info[0] = ""+partId.getText();
	        	 if(isPart.isSelected())
	        		 info[1] = "True";
	        	 else
	        		 info[1] = "False";
	        	 info[2] = ""+quantity.getText();
	        	 info[3] = itemlocation;
	        	 
	        	 
	        	 partId = new JTextField(partId.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	 //location.setSelectedItem((String)location.getSelectedItem());
	        	 
	        	 	
	        	//Get Error report to possibly show in frame
	        	InventoryItem newItem;
				newItem = MainController.addInventoryItem(info, new InventoryItem());
				
				//show warning if item part/location combo already exists
				/*if( newItem.getErrorCount() == 1 && newItem.getErrorListAtIndex(0) != null){
       			 JOptionPane.showMessageDialog(addFrame,
       					    "That part is already at that location!",
       					    "PName warning",
       					    JOptionPane.WARNING_MESSAGE);
       			
       			  
	 
       		 	}*/
				/*for(int i=0; i < newItem.getErrorCount(); i++){
					//System.out.println(newItem.getErrorListAtIndex(i));
				}*/
				/*if (newItem.getErrorCount() > 0 ) {
					dispose();
					InventoryAddFrame addFrame = new InventoryAddFrame (newItem);
					addFrame.addFrame.setVisible(true);
				} */
				if (newItem.getErrorCount() > 0 ) {
					int i=0;
					while(newItem.getErrorListAtIndex(i) == null || "".equals(newItem.getErrorListAtIndex(i))){i++;}
					JOptionPane.showMessageDialog(addFrame,
       					    newItem.getErrorListAtIndex(i),
       					    "ITem warning",
       					    JOptionPane.WARNING_MESSAGE);
					
					dispose();
					InventoryAddFrame addFrame = new InventoryAddFrame (itemlocation);
					addFrame.addFrame.setVisible(true);
       			 	
					/*addFrame.dispose();
					AddFrame addFrame = new AddFrame (newPart);
					addFrame.addFrame.setVisible(true);*/
				}
				else {
					dispose();
				}
	      
	         }
	       
	        
	      });
	   
	}
/*
	private void setUpErrorGUI(InventoryItem errorItem) {
		addFrame = new JFrame("***Try Again*** ");
		addFrame.setSize(670, 200);
		addFrame.setLayout(new GridLayout(0, 2));       
	    addFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            addFrame.dispose();
	         }        
	      });
		addFrame.add(new JLabel("PartId: " + errorItem.getErrorListAtIndex(1)));
	    addFrame.add(partId);
		addFrame.add(new JLabel("Quantity: " + errorItem.getErrorListAtIndex(2)));
	    addFrame.add(quantity);
		//addFrame.add(new JLabel("Location: " + errorItem.getErrorListAtIndex(3)));
	    //addFrame.add(location);
	    JButton submitButton = new JButton("Submit");
	    addFrame.add(submitButton);
	    
	    submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Get the text fields and send to controller

	        	 info[0] = partId.getText();
	        	 if(isPart.isSelected())
	        		 info[1] = "True";
	        	 else
	        		 info[1] = "False";
	        	 info[2] = quantity.getText();
	        	 info[3] = itemlocation;
	        	 
	        	 partId = new JTextField(partId.getText());
	        	 quantity = new JTextField(quantity.getText());
	        	// location.setSelectedItem((String)location.getSelectedItem());

	        	
	        	 InventoryItem newItem;
				// If there is a flag that this is an update
				if (errorItem.getItemId() > 0){
					newItem = MainController.updateInventoryItem(info, errorItem);
					if(MainController.timeFlag){
						newItem = InventoryItemDB.getInventoryItemByPartIdAndLocation(errorItem.getPartId(), errorItem.getLocation(), errorItem.getIsPart());
					}
				}
				else
					newItem = MainController.addInventoryItem(info,new InventoryItem());
				if (newItem.getErrorCount() > 0) {
					addFrame.dispose();
					InventoryAddFrame addFrame = new InventoryAddFrame(newItem);
					addFrame.addFrame.setVisible(true);
					if(MainController.timeFlag){
						MainController.timeFlag = false;
						JOptionPane.showMessageDialog(addFrame.addFrame,
	       					    "Data has already been modified! Refreshing data!",
	       					    "WARNING",
	       					    JOptionPane.WARNING_MESSAGE);
						
					}
				} else {
					addFrame.dispose();
				}
	        	
	         }
	        
	      });
	   
	}
	@Override
	public void dispose(){
		addFrame.dispose();
		
		
	}*/
}


