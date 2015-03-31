package view;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.LoginController;
import controller.MainController;
import model.Part;
import model.PartDB;
import model.PartsList;


public class MainFrame extends JFrame{
	public static JFrame mainFrame;
	public JPanel container;
	JMenuBar menuBar;
	JMenu menu,currentLogin;
	JMenuItem exit, logout, currentLog;
	//public static PartFrame part;
	
	public MainFrame(){
		prepareGUI();
	}
	

	
	
	private void prepareGUI(){
		
		//Allocate memory
		mainFrame = new JFrame("Cabinetron");
		mainFrame.setLocation(650, 0);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		container.setLayout(new GridLayout(0, 4));
		JButton addButton = new JButton("Add Part");
		//JButton inventoryButton = new JButton("Inventory");
		
		//Construct mainframe
		mainFrame.setSize(550,600);
		mainFrame.add(scrPane);
		
		//Add buttons and labels to UI
		//container.add(new JLabel("Quantity"));
		container.add(new JLabel("Unit"));
		container.add(new JLabel("Name"));
		container.add(addButton);
		container.add(new JLabel(""));
		//container.add(inventoryButton);
		//Menu stuff
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		currentLogin = new JMenu("Current Login");
		menuBar.add(menu);
		menuBar.add(currentLogin);
		exit = new JMenuItem("Exit Program");
		logout = new JMenuItem("Logout");
		menu.add(exit);
		menu.add(logout);
		currentLog = new JMenuItem(LoginController.currentLogin,
                KeyEvent.VK_T);
		currentLogin.add(currentLog);
		
		
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//dispose all frames and show login frame
				MainController.closeAllOpenWindows();
			}
		});
		mainFrame.setJMenuBar(menuBar);
		
		//Listeners
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            //System.exit(0);
	        	 dispose();
	         }        
	      });
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 AddFrame addFrame = new AddFrame();
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		
		/*inventoryButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 InventoryFrame InvFrame = new InventoryFrame();
	             InventoryFrame.InventoryFrame.setVisible(true);
	         }
	      });*/
		
		
		//mainFrame.setVisible(true);
		if(LoginController.getPermissions(LoginController.currentLogin)[5] == false){
			addButton.setEnabled(false); 
		}
		
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
	        	 if(MainController.deletePart(addPart))
	        		 refresh();
	        	 if( partFrame.partFrame.isShowing() )
	        		 partFrame.partFrame.dispose();	 
	         }
	      });
	    mainFrame.setVisible(true);
	    
	    if(LoginController.getPermissions(LoginController.currentLogin)[6] == false){
	    	addPart.listUI.getDeleteButton().setEnabled(false); 
		}
	   
	}
	@Override
	public void dispose(){
		mainFrame.dispose();
		
	}
	public void refresh(){
		container.removeAll();
		dispose();
		prepareGUI();
		ArrayList<Part> partsList = PartDB.fetchAll();
		for(int i=0; i< partsList.size(); i++){
			addPart(partsList.get(i));
		}
	
}
}
