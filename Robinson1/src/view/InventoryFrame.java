package view;
/*This class has the inventory
 * mainframe. This shows the list of the available locations, a submit to move to another window to show
 * inventory items from that particular location and an add part option 
 * */


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.Part;
import model.PartsList;


public class InventoryFrame{
	public static JPanel InventoryFrame;
	public JPanel container;
	private JComboBox locationBox;
	private String location;
	private String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	
	//public static PartFrame part;
	
	public InventoryFrame(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		
		InventoryFrame = new JPanel();
		InventoryFrame.setSize(500,600);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		InventoryFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		/*InventoryFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            InventoryFrame.dispose();
	         }        
	      });*/
		
		locationBox = new JComboBox(locationStrings);
		locationBox.setSelectedItem("Unknown");
		
		
		
		JButton submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	
	        	 location = (String)locationBox.getSelectedItem();
	        	 if(location.equals(locationStrings[3])){
	        		return;
	        	 }
	        	 else{
	        		 //Open inventory frame at that location
	        		//Close frame
		             //InventoryFrame.dispose();
	        		 InventoryAtLocationFrame InvFrame = new InventoryAtLocationFrame(location);
		             InventoryAtLocationFrame.mainFrame.setVisible(true);
		             
	        		//Get from database the parts from this location
	        		 
	        	 }	 
	        	 
	        	 
	         }
	      });
		//Add all 'widgets' to frame and set visibility
		container.add(new JLabel("Choose location: "));
	    container.add(locationBox);
	    container.add(submitButton);
		container.add(new JLabel(""));
		InventoryFrame.setVisible(true);
		
	}
/*	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		InventoryFrame If = new InventoryFrame();
		
		
	}*/
}
	
	
	