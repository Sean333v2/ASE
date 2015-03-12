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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.ProductTemplate;
import model.ProductTemplateDB;
import model.ProductTemplatePartDetail;
import model.ProductTemplatePartDetailDB;

public class ProductTemplatePartDetailsFrame {
	public static JFrame productFrame;
	public JPanel container;
	private String productTemplateId;
	
	
	public ProductTemplatePartDetailsFrame(String productTemplateId){
		this.productTemplateId = productTemplateId;
		prepareGUI();
	}

	private void prepareGUI(){
		
		//Allocate memory
		productFrame = new JFrame(productTemplateId);
		productFrame.setLocation(0, 0);
		container = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		container.setLayout(new GridLayout(0, 4));
		JButton addButton = new JButton("Add");
		
		
		//Construct mainframe
		productFrame.setSize(550,600);
		productFrame.add(scrPane);
		
		//Add buttons and labels to UI
		
		container.add(new JLabel("Part Id"));
		container.add(new JLabel("Part Name"));
		container.add(new JLabel("Part Quantity"));
		container.add(addButton);

		
		//Listeners
		productFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		
		addButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //add part in here (scrollparts)
	        	 AddFrame addFrame = new AddFrame();
	             addFrame.addFrame.setVisible(true);
	         }
	      });
		
		productFrame.setVisible(true);	
	}
	
	
	public void addProduct(ProductTemplatePartDetail addProduct){
		//******Generating a part frame for this part
	    final ProductDetailFrame productDetailFrame = new ProductDetailFrame(addProduct);
	    
	    addProduct.partUI.setDeleteButton("Delete");
	    addProduct.partUI.setDetailsButton("Details");
	   
		//Adds all to necessary fields to the  main frame
		//container.add(addPart.listUI.getPartQuantityLabel());
		container.add(addProduct.partUI.getPartUnitLabel());
		container.add(addProduct.partUI.getPartQuantityLabel());
		container.add(addProduct.partUI.getDetailsButton());
		container.add(addProduct.partUI.getDeleteButton());
		
		
		//Lsiteners on buttons
		
	    addProduct.partUI.getDetailsButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	//*******
	        	productDetailFrame.refresh();
	            productDetailFrame.productTemplateDetailFrame.setVisible(true);
	         }
	      });
	    
	    addProduct.partUI.getDeleteButton().addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 //********
	         }
	      });
	   
	    
	    productFrame.setVisible(true);
	   
	}
	
	public void refresh(){
		container.removeAll();
		productFrame.dispose();
		prepareGUI();
		ArrayList<ProductTemplatePartDetail> productPartList;
		productPartList = ProductTemplatePartDetailDB.fetchAllByProductId(productTemplateId);
		for(int i=0; i< productPartList.size(); i++){
			addProduct(productPartList.get(i));
		}
	

}
}