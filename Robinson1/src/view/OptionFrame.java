package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LoginController;
import controller.MainController;

public class OptionFrame extends JFrame{
	
	JFrame optionFrame;
	private JPanel container;
	private String login;
	private Boolean[] permissions;
	JMenuBar menuBar;
	JMenu menu, currentLogin;
	JMenuItem exit, logout,currentLog;
	
	
	public OptionFrame(String login){
		this.login = login;
		LoginController.currentLogin = login;
		permissions = LoginController.getPermissions(login);
		prepareGUI();
	}
private void prepareGUI(){
		
		optionFrame = new JFrame(login + " Frames");
		optionFrame.setSize(500,100);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		optionFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		optionFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	dispose();
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
	            InvFrame.InventoryFrame.setVisible(true);
			}
		});
		productTemplates.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.setProductsFrame();	
			}
		});
		
		
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
		
		//Add all 'widgets' to frame and set visibility
		
	    container.add(partsButton);
	    container.add(new JLabel(""));
	    container.add(inventoryButton);
		container.add(new JLabel(""));
		if(permissions[0] == true){
			container.add(productTemplates);
		}
		optionFrame.setJMenuBar(menuBar);
		optionFrame.setVisible(true);
		
	}
	@Override
	public void dispose(){
		optionFrame.dispose();
	
	}

}
