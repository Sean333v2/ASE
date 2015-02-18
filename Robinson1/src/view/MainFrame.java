package view;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.MainController;
import model.Part;
import model.PartsList;


public class MainFrame{
	private static JFrame mainFrame;
	public JPanel container;
	//public static PartFrame part;
	
	public MainFrame(){
		prepareGUI();
	}
	

	
	
	private void prepareGUI(){
		
		//Allocate memory
		mainFrame = new JFrame("Cabinetron");
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		container.setLayout(new GridLayout(0, 5));
		JButton addButton = new JButton("Add Part");
		JButton inventoryButton = new JButton("Inventory");
		
		//Construct mainframe
		mainFrame.setSize(550,600);
		mainFrame.add(scrPane);
		
		//Add buttons and labels to UI
		container.add(new JLabel("Quantity"));
		container.add(new JLabel("Unit"));
		container.add(new JLabel("Name"));
		container.add(addButton);
		container.add(inventoryButton);
		
		
		//Listeners
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 AddFrame addFrame = new AddFrame();
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		
		inventoryButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 InventoryFrame InvFrame = new InventoryFrame();
	             InventoryFrame.InventoryFrame.setVisible(true);
	         }
	      });
		
		
		mainFrame.setVisible(true);
		
		
	}
	
	
	public void addPart(Part addPart){
	    final PartFrame partFrame = new PartFrame(addPart);
	    
		//Adds all to necessary fields to the  main frame
		container.add(addPart.listUI.getPartQuantityLabel());
		container.add(addPart.listUI.getPartUnitLabel());
		container.add(addPart.listUI.getPartNameLabel());
		container.add(addPart.listUI.getDetailsButton());
		container.add(addPart.listUI.getDeleteButton());
		
		//Lsiteners on buttons
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
	    mainFrame.setVisible(true);
	   
	}
	
	public void refresh(PartsList list){
		container.removeAll();
		mainFrame.dispose();
		prepareGUI();
		for(int i=0; i< list.getAmount(); i++){
			addPart(list.list.get(i));
		}
	
}
}
