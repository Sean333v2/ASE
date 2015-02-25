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
	private static JFrame mainFrame;
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
		mainFrame = new JFrame("Cabinetron: Parts in " + location );
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		JButton addButton = new JButton("Add Inventory Part");
		
		mainFrame.setSize(500,600);
		mainFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		
		//Set this window as window for maincontroller to work with
		MainController.setInventoryLocation(this);
		
		
		//Listeners
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            mainFrame.dispose();
	         }        
	      });
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 AddFrame addFrame = new AddFrame();
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
		if(listItemsatLocation != null){
			for(int i=0; i < listItemsatLocation.size(); i++){
				listItemsatLocation.get(i).partUI.setDeleteButton("Delete");
				listItemsatLocation.get(i).partUI.setDetailsButton("Details");
				listItemsatLocation.get(i).partUI.setPartQuantityLabel(listItemsatLocation.get(i).getQuantity());
				listItemsatLocation.get(i).partUI.setPartQuantityLabel(listItemsatLocation.get(i).getPart().getPartName());
				container.add(listItemsatLocation.get(i).partUI.getPartQuantityLabel());
				//container.add(listItemsatLocation.get(i).partUI.getPartUnitLabel());
				container.add(listItemsatLocation.get(i).partUI.getPartNameLabel());
			    container.add(listItemsatLocation.get(i).partUI.getDetailsButton());
			    container.add(listItemsatLocation.get(i).partUI.getDeleteButton());
			}
		}
		mainFrame.setVisible(true);
		
	}
	//This function adds part to the main frame
	public void addPart(InventoryItem addInventoryPart){
		//Do inventory frame
	    final PartFrame partFrame = new PartFrame(addPart);
	    
		//Add to mainframe part details & buttons
		container.add(addInventoryPart.partUI.getPartQuantityLabel());
		container.add(addInventoryPart.partUI.getPartNameLabel());
	    container.add(addInventoryPart.partUI.getDetailsButton());
	    container.add(addInventoryPart.partUI.getDeleteButton());
		
	    //Listeners for the two buttons
	    addInventoryPart.partUI.getDetailsButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	partFrame.refresh();
	            partFrame.partFrame.setVisible(true);
	         }
	      });
	    
	    
	    addInventoryPart.partUI.getDeleteButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Delete Part
	        	 MainController.deleteInventoryPart(addInventoryPart);
	        	 if( partFrame.partFrame.isShowing() )
	        		 partFrame.partFrame.dispose();
 
	        	 
	         }
	      });
	    
	    //UI
	    mainFrame.setVisible(true);
	   
	}
	
	public void refresh(PartsList list){
		container.removeAll();
		mainFrame.dispose();
		prepareGUI();
		
		//Get parts from database here and call function to add apart into GUI
		for(int i=0; i< list.getAmount(); i++){
			addPart(list.list.get(i));
		}

	
}
}
