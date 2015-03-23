package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Part;
import model.PartDB;
import model.ProductTemplate;
import model.ProductTemplateDB;
import controller.MainController;

public class ProductFrame {
		public static JPanel productFrame;
		public JPanel container;
		public ArrayList<ProductTemplate> productList;
		//public static PartFrame part;
		
		public ProductFrame(){
			prepareGUI();
		}

		public JPanel getFrame(){
			return productFrame;
		}
		
		private void prepareGUI(){
			//Allocate memory
			productFrame = new JPanel();
			productFrame.setLocation(0, 0);
			container = new JPanel();
			JScrollPane scrPane = new JScrollPane(container);
			container.setLayout(new GridLayout(0, 5));
			JButton addButton = new JButton("Add Product");
			
			
			//Construct mainframe
			productFrame.setSize(550,600);
			productFrame.add(scrPane);
			
			//Add buttons and labels to UI
			
			container.add(new JLabel("Id"));
			container.add(new JLabel("Product Number"));
			container.add(addButton);
			container.add(new JLabel(""));
			container.add(new JLabel(""));

			
			//Listeners
			/*productFrame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });*/
			
			addButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 //add product
		        	 final ProductDetailFrame productDetailFrame = new ProductDetailFrame(new ProductTemplate(), "Add");
		        	 productDetailFrame.refresh();
			         productDetailFrame.productTemplateDetailFrame.setVisible(true);
		         }
		      });
			
			//mainFrame.setVisible(true);	
		}
		
		
		public void addProduct(ProductTemplate addProduct){
			//Generating a part frame for this part
		    final ProductDetailFrame productDetailFrame = new ProductDetailFrame(addProduct, "Update");
		    
		    addProduct.listUI.setEditButton("Edit");
		    addProduct.listUI.setDetailsButton("Details");
		    addProduct.listUI.setProductIdLabel(addProduct.getProductId());
		    addProduct.listUI.setProductNumLabel(addProduct.getProductNum());
		    addProduct.listUI.setDeleteButton("Delete");
			//Adds all to necessary fields to the  main frame
			//container.add(addPart.listUI.getPartQuantityLabel());
			container.add(addProduct.listUI.getProductIdLabel());
			container.add(addProduct.listUI.getProductNumLabel());
			container.add(addProduct.listUI.getEditButton());
			container.add(addProduct.listUI.getDetailsButton());
			container.add(addProduct.listUI.getDeleteButton());
			
			//Lsiteners on buttons
			
		    addProduct.listUI.getDetailsButton().addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	//Close this frame
		        	//productFrame.dispose();
		        	productDetailFrame.refresh();
		            productDetailFrame.productTemplateDetailFrame.setVisible(true);
		         }
		      });
		    
		    addProduct.listUI.getEditButton().addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 //Show details
		        	 final ProductTemplatePartDetailFrame productTemplatePartDetailFrame = new ProductTemplatePartDetailFrame(addProduct);
		        	 productTemplatePartDetailFrame.refresh();
		        	 productTemplatePartDetailFrame.productFrame.setVisible(true);
		         }
		      });
		    
		    addProduct.listUI.getDeleteButton().addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 MainController.deleteProductTemplate(addProduct);
		         }
		      });
		   
		    
		    productFrame.setVisible(true);
		   
		}
		
		public void refresh(){
			container.removeAll();
			//productFrame.dispose();
			prepareGUI();
			productList = ProductTemplateDB.fetchAll();
			for(int i=0; i< productList.size(); i++){
				addProduct(productList.get(i));
			}
		
	
	}

}
