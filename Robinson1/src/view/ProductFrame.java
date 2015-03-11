package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Part;
import model.PartDB;
import controller.MainController;

public class ProductFrame {
		public static JFrame productFrame;
		public JPanel container;
		//public static PartFrame part;
		
		public ProductFrame(){
			prepareGUI();
		}
		

		
		
		private void prepareGUI(){
			
			//Allocate memory
			productFrame = new JFrame("Cabinetron Products");
			productFrame.setLocation(0, 0);
			container = new JPanel();
			JScrollPane scrPane = new JScrollPane(container);
			container.setLayout(new GridLayout(0, 4));
			JButton addButton = new JButton("Add Product");
			
			
			//Construct mainframe
			productFrame.setSize(550,600);
			productFrame.add(scrPane);
			
			//Add buttons and labels to UI
			
			
			container.add(new JLabel("Product Name"));
			container.add(addButton);
			
			
			
			//Listeners
			productFrame.addWindowListener(new WindowAdapter() {
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
			
			//mainFrame.setVisible(true);
			
			
		}
		
		
		public void addPart(Part addPart){
		    final PartFrame partFrame = new PartFrame(addPart);
		    
		    addPart.listUI.setDeleteButton("Delete");
		    addPart.listUI.setDetailsButton("Details");
		    addPart.listUI.setPartUnitLabel(addPart.getUnit());
		    addPart.listUI.setPartNameLabel(addPart.getPartName());
			//Adds all to necessary fields to the  main frame
			//container.add(addPart.listUI.getPartQuantityLabel());
			container.add(addPart.listUI.getPartUnitLabel());
			container.add(addPart.listUI.getPartNameLabel());
			container.add(addPart.listUI.getDetailsButton());
			container.add(addPart.listUI.getDeleteButton());
			
			//Lsiteners on buttons
			
		    addPart.listUI.getDetailsButton().addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 /*
		        	if(partFrame.partFrame.isActive())
		        		partFrame.partFrame.dispose();*/
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
		    productFrame.setVisible(true);
		   
		}
		
		public void refresh(){
			container.removeAll();
			productFrame.dispose();
			prepareGUI();
			ArrayList<Part> partsList = PartDB.fetchAll();
			for(int i=0; i< partsList.size(); i++){
				addPart(partsList.get(i));
			}
		
	
	}

}
