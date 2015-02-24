package view;

/*
 * This function shows a view of parts at a particular location
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.MainController;
import model.Part;
import model.PartsList;


public class InventoryAtLocationFrame{
	public static JFrame mainFrame;
	private JPanel container;
	private String location;
	//public static PartFrame part;
	
	public InventoryAtLocationFrame(String location){
		this.location = location;
		prepareGUI();
	}
	private void prepareGUI(){
		MainController.setInventoryFrame(this);
		
		//Prepare frame and panel
		mainFrame = new JFrame("Cabinetron: Parts in " + location );
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		JButton addButton = new JButton("Add Inventory Part");
		
		mainFrame.setSize(500,600);
		mainFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		
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
		ArrayList<Part> inventoryItems = MainController.gatherInventoryItems(location);
		for(int i=0; i< inventoryItems.size(); i++){
			addPart(inventoryItems.get(i));
		}
		mainFrame.setVisible(true);
		
	}
	//This function adds part to the main frame
	public void addPart(Part addPart){
	    final PartFrame partFrame = new PartFrame(addPart);
	    
		//Add to mainframe part details & buttons
		container.add(addPart.listUI.getPartQuantityLabel());
		container.add(addPart.listUI.getPartUnitLabel());
		container.add(addPart.listUI.getPartNameLabel());
	    container.add(addPart.listUI.getDetailsButton());
	    container.add(addPart.listUI.getDeleteButton());
		
	    //Listeners for the two buttons
	    addPart.listUI.getDetailsButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	partFrame.refresh();
	            partFrame.partFrame.setVisible(true);
	         }
	      });
	    
	    
	    addPart.listUI.getDeleteButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Delete Part
	        	 MainController.deletePart(addPart);
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
