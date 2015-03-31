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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LoginController;

public class LoginView extends JFrame{
	private JComboBox loginBox;
	private JFrame LoginFrame;
	private JPanel container;
	private String[] loginStrings;
	private String login;
	
	
	public LoginView(){
		prepareGUI();
	}
	
private void prepareGUI(){
		
		LoginFrame = new JFrame("Login");
		LoginFrame.setSize(900,150);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		LoginFrame.add(scrPane);
		container.setLayout(new GridLayout(0, 5));
		LoginFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 System.exit(0);
	         }        
	      });
		loginStrings = LoginController.getlogins();
		loginBox = new JComboBox(loginStrings);
			
		
		JButton submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	
	        	login = (String)loginBox.getSelectedItem();
	        	if(login.equals(LoginController.getlogins()[3])){
	        		JOptionPane.showMessageDialog(LoginFrame,
       					    "Bad Credentials",
       					    "badCredentialsError",
       					    JOptionPane.ERROR_MESSAGE);
	        		LoginFrame.dispose();
	        		LoginView lf = new LoginView();
	        		
	        		
	        	}
	        	else{
	        	LoginFrame.dispose();
	        	OptionFrame optionFrame = new OptionFrame(login);
	        	}
  	 
	         }
	      });
		//Add all 'widgets' to frame and set visibility
		container.add(new JLabel("Login: "));
	    container.add(loginBox);
	    container.add(new JLabel(""));
	    container.add(submitButton);
		container.add(new JLabel(""));
		LoginFrame.setVisible(true);
		
	}

}
