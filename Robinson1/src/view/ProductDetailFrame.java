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
import javax.swing.JTextField;

import model.Part;
import model.ProductTemplate;
import controller.MainController;
import controller.WindowManager;

public class ProductDetailFrame extends JFrame {
	public JFrame productTemplateDetailFrame;
	private JTextField description;
	private JTextField number;
	private JLabel id;
	public ProductTemplate mainProduct;
	public String[] info = new String[2];
	private String state;
	private String add = "Add";
	private String update = "Update";
	
	public ProductDetailFrame(ProductTemplate p, String state){
		WindowManager.windows.add(this);
		mainProduct = p;
		if(update.equals(state)){
			description = new JTextField(mainProduct.getProductDescription());
			number = new JTextField(mainProduct.getProductNum());
			id = new JLabel("ID: "+mainProduct.getProductId());
		}
		else if(add.equals(state)){
			description = new JTextField("");
			number = new JTextField("");
			id = new JLabel("");
		}
		this.state = state;
		setUpGUI();
	}
	
	private void setUpGUI(){
		productTemplateDetailFrame = new JFrame("Cabinetron: Product Template");
		productTemplateDetailFrame.setSize(300, 300);
		productTemplateDetailFrame.setLayout(new GridLayout(0, 2));       
	    productTemplateDetailFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				WindowManager.windows.remove(this);
				productTemplateDetailFrame.dispose();
	         }        
	      });
	    JButton updateButton = new JButton(state);
	    
	    if(state.equals(update)){
	    	productTemplateDetailFrame.add(new JLabel(""));
	    	productTemplateDetailFrame.add(id);
	    }
	    productTemplateDetailFrame.add(new JLabel("Product Number:"));
	    productTemplateDetailFrame.add(number);
	    productTemplateDetailFrame.add(new JLabel("Product Description:"));
	    productTemplateDetailFrame.add(description);
	    productTemplateDetailFrame.add(updateButton);
	    
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	
	        	info[0] = number.getText();
	        	info[1] = description.getText();
	        	
	        	if(state.equals(update)){
	        		mainProduct = MainController.updateProductTemplate(info, mainProduct);
	        	}
	        	else if(state.equals(add)){
	        		mainProduct = MainController.addProductTemplate(info, mainProduct);
	        	}
	        	 //The following will check if duplicate Part Name exists when adding a Part, 
	        	 //the user should be warned and allowed to cancel.
	        	if(mainProduct == null){
	        		JOptionPane.showMessageDialog(productTemplateDetailFrame,
       					    "Error adding to the database.",
       					    "Error",
       					    JOptionPane.ERROR_MESSAGE);
	        		mainProduct = new ProductTemplate(info[0], info[1]);
	        	}
	        	
	        	
	        	 if(mainProduct.getErrorCount() > 0){
	        		int i=0;
	        		while(mainProduct.getErrorList()[i].equals("")){i++;}
	        		JOptionPane.showMessageDialog(productTemplateDetailFrame,
       					    mainProduct.getErrorList()[i],
       					    "Error",
       					    JOptionPane.ERROR_MESSAGE);
       			 	refresh();
       			 			 
	        	 }
	        	 else{
	        		 //********Update product in main controller
	        		 WindowManager.windows.remove(this);
	        		 productTemplateDetailFrame.dispose();
	        	 }
	        	
			}
	      });
	    
	   
	}
	
	public void refresh(){
		description.setText(mainProduct.getProductDescription());
		number.setText(mainProduct.getProductNum());	
	}
}


