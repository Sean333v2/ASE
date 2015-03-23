package view;

/*
 * This function shows a view of parts at a particular location
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.MainController;
import model.InventoryItem;
import model.Part;
import model.PartsList;


public class InventoryAtLocationFrame{
	public static JPanel mainFrame;
	public JPanel container;
	private String location;
	private ArrayList<InventoryItem> listItemsatLocation;
	//public static PartFrame part;
	
	public InventoryAtLocationFrame(String location){
		this.location = location;
		prepareGUI();
	}
	private void prepareGUI(){
		
		//Prepare frame and panel
		mainFrame = new JPanel();
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		JButton addButton = new JButton("Add Inventory Part");
		
		mainFrame.setSize(550,600);
		mainFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 4));
		
		//Set this window as window for maincontroller to work with
		MainController.setInventoryLocation(this);
		
		
		//Listeners
		/*mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            mainFrame.dispose();
	         }        
	      });*/
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 InventoryAddFrame addFrame = new InventoryAddFrame(location);
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		
		//Add all widgets to frame and set visibility
		container.add(new JLabel("Quantity"));
		container.add(new JLabel("Name"));
		container.add(addButton);
		container.add(new JLabel(""));
		
		//Display items at a particular location if there already
		listItemsatLocation = MainController.getInventoryAtLocation(location);
		for(int i=0; i < listItemsatLocation.size(); i++){
			addPart(listItemsatLocation.get(i));
			
		}
		mainFrame.setVisible(true);
		
	}
	//This function adds part to the main frame
	public void addPart(InventoryItem addInventoryPart){
		//Do inventory frame
	    final InventoryPartFrame itemFrame = new InventoryPartFrame(addInventoryPart);
	    addInventoryPart.partUI.setDeleteButton("Delete");
	    addInventoryPart.partUI.setDetailsButton("Details");
		addInventoryPart.partUI.setPartQuantityLabel(addInventoryPart.getQuantity());
		addInventoryPart.partUI.setPartNameLabel(addInventoryPart.getPart().getPartName());
		
	    
		//Add to mainframe part details & buttons
		container.add(addInventoryPart.partUI.getPartQuantityLabel());
		container.add(addInventoryPart.partUI.getPartNameLabel());
	    container.add(addInventoryPart.partUI.getDetailsButton());
	    container.add(addInventoryPart.partUI.getDeleteButton());
		
	    //Listeners for the two buttons
	    addInventoryPart.partUI.getDetailsButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	itemFrame.refresh();
	            itemFrame.inventoryFrame.setVisible(true);
	         }
	      });
	    
	    
	    addInventoryPart.partUI.getDeleteButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Delete Part
	        	 MainController.deleteInventoryItem(addInventoryPart);
	        	 //if( itemFrame.inventoryFrame.isShowing() )
	        		// itemFrame.inventoryFrame.dispose();
 
	        	 
	         }
	      });
	    
	    //UI
	    mainFrame.setVisible(true);
	   
	}
	
	public void refresh(){
		container.removeAll();
		//mainFrame.dispose();
		prepareGUI();
		/*listItemsatLocation = MainController.getInventoryAtLocation(location);
		//Get parts from database here and call function to add apart into GUI
		for(int i=0; i< listItemsatLocation.size(); i++){
			addPart(listItemsatLocation.get(i));
		}*/

	
}
}
