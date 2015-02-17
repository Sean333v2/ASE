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
	private static JFrame InventoryFrame;
	public JPanel container;
	private JComboBox locationBox;
	private String location;
	private String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	
	
	//public static PartFrame part;
	
	public InventoryFrame(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		InventoryFrame = new JFrame("Inventory");
		InventoryFrame.setSize(500,600);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		InventoryFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		InventoryFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		
		locationBox = new JComboBox(locationStrings);
		locationBox.setSelectedItem("Unknown");
		
		
		JButton addButton = new JButton("Add Part");
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 AddFrame addFrame = new AddFrame();
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		JButton submitButton = new JButton("Submit");
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 location = (String)locationBox.getSelectedItem();
	        	 if(location.equals(locationStrings[3])){
	        		return;
	        	 }
	        	 else{
	        		//Get from database the parts from this location
	        		 
	        	 }	 
	        	 
	        	 
	         }
	      });
		container.add(new JLabel("Choose location: "));
	    container.add(locationBox);
	    container.add(submitButton);
		container.add(addButton);
		container.add(new JLabel(""));
		InventoryFrame.setVisible(true);
		
	}
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		InventoryFrame If = new InventoryFrame();
		
		
	}
}
	
	
	