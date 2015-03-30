package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LoginController;
import controller.MainController;
import controller.WindowManager;

public class OptionFrame {
	
	JFrame optionFrame;
	private JPanel container;
	private String login;
	private Boolean[] permissions;
	
	
	public OptionFrame(String login){
		this.login = login;
		WindowManager.windows.add(this);
		permissions = LoginController.getPermissions(login);
		prepareGUI();
	}
private void prepareGUI(){
		
		optionFrame = new JFrame(login + " Frames");
		optionFrame.setSize(600,200);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		optionFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		optionFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 WindowManager.windows.remove(this);
	            optionFrame.dispose();
	         }        
	      });
		//permissions = LoginController.getPermissions(login);	
		
		JButton partsButton = new JButton("Parts");
		JButton inventoryButton = new JButton ("Inventory");
		JButton productTemplates = new JButton("Products");
		
		partsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.setPartsFrame();	
			}
		});
		inventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InventoryFrame InvFrame = new InventoryFrame();
	            InventoryFrame.InventoryFrame.setVisible(true);
			}
		});
		productTemplates.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.setProductsFrame();	
			}
		});
		
		
		//Add all 'widgets' to frame and set visibility
		
	    container.add(partsButton);
	    container.add(new JLabel(""));
	    container.add(inventoryButton);
		container.add(new JLabel(""));
		if(permissions[0] == true){
			container.add(productTemplates);
		}
		optionFrame.setVisible(true);
		
	}

}
