package view;

/*
 * This function shows a view of parts at a particular location
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.Part;
import model.PartsList;


public class InventoryAtLocationFrame{
	private static JFrame mainFrame;
	public JPanel container;
	private String location;
	//public static PartFrame part;
	
	public InventoryAtLocationFrame(String location){
		this.location = location;
		prepareGUI();
	}
	private void prepareGUI(){
		mainFrame = new JFrame("Cabinetron: Parts in " + location );
		mainFrame.setSize(500,600);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		mainFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		
		container.add(new JLabel("Quantity"));
		container.add(new JLabel("Name"));
		
		JButton addButton = new JButton("Add Inventory Part");
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 AddFrame addFrame = new AddFrame();
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		container.add(addButton);
		container.add(new JLabel(""));
		mainFrame.setVisible(true);
		
	}
	
	public void addPart(Part p){
	    final PartFrame part = new PartFrame(p);
		p.lp.setPq((Integer.toString(p.getQuantity())));
		p.lp.setPu(p.getUnit());
		p.lp.setPn(p.getPartName());
		container.add(p.lp.getPq());
		container.add(p.lp.getPu());
		container.add(p.lp.getPn());
		p.lp.setDetails("Details");
	    p.lp.getDetails().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	part.refresh();
	            part.partFrame.setVisible(true);
	         }
	      });
	    
	    p.lp.setDelete("Delete");
	    container.add(p.lp.getDetails());
	    container.add(p.lp.getDelete());
	    mainFrame.setVisible(true);
	    p.lp.getDelete().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //Delete Part
	        	 MainController.deletePart(p);
	        	 if( part.partFrame.isShowing() )
	        		 part.partFrame.dispose();
 
	        	 
	         }
	      });
	   
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
