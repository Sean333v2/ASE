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
import model.ProductTemplatePartDetail;
import controller.MainController;

public class ProductTemplatePartDetailEditFrame {
	public JFrame productTemplatePartDetailFrame;
	private JTextField partId;
	private JTextField productId;
	private JTextField quantity;
	public ProductTemplatePartDetail mainProduct;
	private Part part;
	private ProductTemplate productTemplate;
	public String[] info = new String[3];
	private String state;
	private String add = "Add";
	private String update = "Update";
	
	public ProductTemplatePartDetailEditFrame(ProductTemplatePartDetail p, String state){
		mainProduct = p;
		if(update.equals(state)){
			productId = new JTextField(mainProduct.getProductTemplate().getProductId());
			partId = new JTextField(mainProduct.getPart().getPersonalId());
			quantity = new JTextField(mainProduct.getQuantity());
			part = mainProduct.getPart();
			productTemplate = mainProduct.getProductTemplate();
		}
		else if(add.equals(state)){
			productId = new JTextField(mainProduct.getProductTemplateid());
			partId = new JTextField("");
			quantity = new JTextField("");
		}
		this.state = state;
		setUpGUI();
	}
	
	private void setUpGUI(){
		productTemplatePartDetailFrame = new JFrame("Cabinetron: Product Template");
		productTemplatePartDetailFrame.setSize(300, 300);
		productTemplatePartDetailFrame.setLayout(new GridLayout(0, 2));       
	    productTemplatePartDetailFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				productTemplatePartDetailFrame.dispose();
	         }        
	      });
	    JButton updateButton = new JButton(state);
	    
	    productTemplatePartDetailFrame.add(new JLabel("Product Template Id:"));
	    productTemplatePartDetailFrame.add(productId);
	    productTemplatePartDetailFrame.add(new JLabel("Part Id:"));
	    productTemplatePartDetailFrame.add(partId);
	    productTemplatePartDetailFrame.add(new JLabel("Quantity: "));
	    productTemplatePartDetailFrame.add(quantity);
	    productTemplatePartDetailFrame.add(updateButton);
	    
	    updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	
	        	info[0] = productId.getText();
	        	info[1] = partId.getText();
	        	info[2] = quantity.getText();
	        	
	        	if(state.equals(update)){
	        		mainProduct = MainController.updateProductTemplatePartDetail(info, mainProduct);
	        	}
	        	else if(state.equals(add)){
	        		mainProduct = MainController.addProductTemplatePartDetail(info, mainProduct);
	        	}
	        	 //The following will check if duplicate Part Name exists when adding a Part, 
	        	 //the user should be warned and allowed to cancel.
	        	if(mainProduct == null){
	        		JOptionPane.showMessageDialog(productTemplatePartDetailFrame,
       					    "Error adding to the database.",
       					    "Error",
       					    JOptionPane.ERROR_MESSAGE);
	        		if(state.equals(update))
	        			mainProduct = new ProductTemplatePartDetail(productTemplate, part, info[2]);
	        	}
	        	
	        	
	        	 if(mainProduct.getErrorCount() > 0){
	        		int i=0;
	        		while(mainProduct.getErrorList()[i].equals("")){i++;}
	        		JOptionPane.showMessageDialog(productTemplatePartDetailFrame,
       					    mainProduct.getErrorList()[i],
       					    "Error",
       					    JOptionPane.ERROR_MESSAGE);
       			 	refresh();
       			 	//mainProduct.listUI.getDetailsButton().doClick();		 
	        	 }
	        	 else{
	        		 //********Update product in main controller
	        		 productTemplatePartDetailFrame.dispose();
	        	 }
	        	
			}
	      });
	    
	   
	}
	
	public void refresh(){
		productId.setText(mainProduct.getProductTemplateid());
		partId.setText(mainProduct.getPartId());
		quantity.setText(mainProduct.getQuantity());
	}
}



