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
import model.ProductTemplate;
import model.ProductTemplateDB;
import model.ProductTemplatePartDetail;
import model.ProductTemplatePartDetailDB;
import controller.MainController;
import controller.WindowManager;

public class ProductTemplatePartDetailFrame extends JFrame {
		public static JFrame productFrame;
		public JPanel container;
		public ProductTemplate mainProduct;
		public ArrayList<ProductTemplatePartDetail> productTemplatePartDetailList;
		//public static PartFrame part;
		
		public ProductTemplatePartDetailFrame(ProductTemplate mainProduct){
			this.mainProduct = mainProduct;
			WindowManager.windows.add(this);
			prepareGUI();
			
		}

		private void prepareGUI(){
			//Allocate memory
			productFrame = new JFrame("Cabinetron Product: "+mainProduct.getProductNum());
			productFrame.setLocation(0, 0);
			container = new JPanel();
			JScrollPane scrPane = new JScrollPane(container);
			container.setLayout(new GridLayout(0, 4));
			JButton addButton = new JButton("Add Part");
			
			
			//Construct mainframe
			productFrame.setSize(450,600);
			productFrame.add(scrPane);
			
			//Add buttons and labels to UI
			
			container.add(new JLabel("Part Name"));
			container.add(new JLabel("Quantity"));
			container.add(addButton);
			container.add(new JLabel(""));
			
			//Listeners
			/*productFrame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });*/
			
			addButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 //add part
		        	 addButtonListener();
		         }
		      });
			
			//mainFrame.setVisible(true);	
		}
		
		public void addButtonListener(){
			ProductTemplatePartDetail p = new ProductTemplatePartDetail();
       	 	p.setProductTemplateid(mainProduct.getProductId());
		    final ProductTemplatePartDetailEditFrame productDetailFrame = new ProductTemplatePartDetailEditFrame(p, "Add",this);
       	 	//productDetailFrame.refresh();
	         productDetailFrame.productTemplatePartDetailFrame.setVisible(true);
		}
		public void addProductTemplatePartDetail(ProductTemplatePartDetail addPart){
			//Generating a part frame for this part
		    final ProductTemplatePartDetailEditFrame productDetailFrame = new ProductTemplatePartDetailEditFrame(addPart, "Update",this);
		    
		    /*addPart.listUI.setEditButton("Edit");
		    addPart.listUI.setDetailsButton("Details");
		    addPart.listUI.setProductIdLabel(addPart.getProductId());
		    addPart.listUI.setProductNumLabel(addProduct.getProductNum());
		    addPart.listUI.setDeleteButton("Delete");*/
			//Adds all to necessary fields to the  main frame
			//container.add(addPart.listUI.getPartQuantityLabel());
		    JButton deleteButton = new JButton("Delete");
		    JButton detailsButton = new JButton("Details");
			container.add(new JLabel(addPart.getPart().getPartName()));
			container.add(new JLabel(addPart.getQuantity()));
			container.add(detailsButton);
			container.add(deleteButton);
			
			//Lsiteners on buttons
			
		    detailsButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	productDetailFrame.refresh();
		            productDetailFrame.productTemplatePartDetailFrame.setVisible(true);
		         }
		      });
		    
		    deleteButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 MainController.deleteProductTemplatePartDetail(addPart);
		        	 refresh();
		         }
		      });
		   
		    
		    productFrame.setVisible(true);
		   
		}
		
		public void refresh(){
			container.removeAll();
			WindowManager.windows.remove(this);
			productFrame.dispose();
			prepareGUI();
			productTemplatePartDetailList = ProductTemplatePartDetailDB.fetchAllByProductId(mainProduct.getProductId());
			for(int i=0; i< productTemplatePartDetailList.size(); i++){
				addProductTemplatePartDetail(productTemplatePartDetailList.get(i));
			}
		
	
	}

}

