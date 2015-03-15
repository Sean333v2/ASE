package controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import view.InventoryAtLocationFrame;
import view.MainFrame;
import view.ProductDetailFrame;
import view.ProductFrame;
import model.InventoryItem;
import model.InventoryItemDB;
import model.Part;
import model.PartDB;
import model.PartsList;
import model.ProductTemplate;
import model.ProductTemplateDB;
import model.ProductTemplatePartDetail;
import model.ProductTemplatePartDetailDB;

public class MainController {
	private static Part newPart;
	private static InventoryItem item;
	public static PartsList list = new PartsList();
	private static MainFrame listPartsFrame;
	private static ProductFrame productFrame;
	private static InventoryAtLocationFrame inventoryLocationFrame;
	public static boolean timeFlag = false;
	
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 4 by Barbara Davila and Sean Gallagher");
		//Show mainFrame
		listPartsFrame = new MainFrame();
		initializeList();
		listPartsFrame.mainFrame.setVisible(true);
		//Show partFram
		productFrame = new ProductFrame();
		//Get products from db
		initializeProductTemplates();
		productFrame.productFrame.setVisible(true);
		
	}
	//This is to set the inventory frame the current frame is working with and be able to control
	public static void setInventoryLocation(InventoryAtLocationFrame frame){
		inventoryLocationFrame = frame;
	}
	//Returns to inventoryatlocationframe the list to display 
	public static ArrayList<InventoryItem> getInventoryAtLocation(String location){
		return(InventoryItemDB.fetchByLocation(location));
		
		
	}
	public static void initializeList(){
		ArrayList<Part> allParts = PartDB.fetchAll();
		
		for(int i = 0; i < allParts.size(); i++){
			Part newPart = allParts.get(i);
			
			if(newPart.getErrorCount() == 0){
				//Add part to mainFrame
				listPartsFrame.addPart(newPart);
				//Add part to list
				list.addPart(newPart);
			}
			else{
				System.out.println("Part with errors was not loaded: "+newPart.getPartName());
			}
		}
		
	}
	public static void initializeProductTemplates(){
		productFrame.productList = ProductTemplateDB.fetchAll();
		
		for(int i = 0; i < productFrame.productList.size(); i++){
			ProductTemplate newProduct = productFrame.productList.get(i);
			
			if(newProduct.getErrorCount() == 0){
				//Add part to product frame.
				productFrame.addProduct(newProduct);
			}
			else{
				System.out.println("Product with errors was not loaded: "+newProduct.getProductId());
				for(int j = 0; j < 3; j++)
					System.out.println("Errors: "+newProduct.getErrorList()[j]);
			}
		}
	}
	public static void deletePart(Part deleteItem){
		boolean isQuantityZero = false;
		//Remove from array list		
		 
		 //Check if part exists in item
		 ArrayList<InventoryItem> inventoryList = InventoryItemDB.fetchAll();
		 for(int i=0; i< inventoryList.size(); i++){
			 int k = Integer.parseInt(deleteItem.getPersonalId());
			 if((inventoryList.get(i).getPartId()) == k){
				 if(inventoryList.get(i).getQuantity().equals("0")){
					 System.out.println("Delete");
					 isQuantityZero = true;
					 /*if(InventoryAtLocationFrame.mainFrame.isActive())
						 InventoryAtLocationFrame.mainFrame.dispose();*/
					 deleteInventoryItem(inventoryList.get(i));
				 }
			 }
		 }
		 if(isQuantityZero){
			 PartDB.deletePart(deleteItem);
			list.removePart(list.findPart(deleteItem.getPartName()));

			//Remove from MainFrame
			 listPartsFrame.container.remove(deleteItem.listUI.getPartUnitLabel());
	    	 listPartsFrame.container.remove(deleteItem.listUI.getPartNameLabel());
	    	 listPartsFrame.container.remove(deleteItem.listUI.getDeleteButton());
	    	 listPartsFrame.container.remove(deleteItem.listUI.getDetailsButton()); 
	    	 listPartsFrame.container.revalidate();
		 } 
	}
	
	//Deletes the inventory item from that frame
	public static void deleteInventoryItem(InventoryItem deleteInventoryPart){
		
		//Remove from array list
		if(deleteInventoryPart.getQuantity().equals("0"))
			InventoryItemDB.deleteInventoryItem(deleteInventoryPart); 
		
		//Remove from MainFrame
		 /*inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getPartQuantityLabel());
		 //listPartsFrame.container.remove(deletePart.partUI.getPartUnitLabel());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getPartNameLabel());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getDeleteButton());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getDetailsButton()); */
    	 inventoryLocationFrame.container.revalidate();
    	 
	}
	
	public static void deleteProductTemplate(ProductTemplate product){
		ProductTemplateDB.deleteProductTemplate(product);
		productFrame.refresh();
	}
	
	public static void deleteProductTemplatePartDetail(ProductTemplatePartDetail template){
		ProductTemplatePartDetailDB.deleteProductTemplatePartDetail(template);
	}
	
	public static ProductTemplatePartDetail addProductTemplatePartDetail(String[] stringArray, ProductTemplatePartDetail addTemplate){
		addTemplate.setErrorCount(0);
		addTemplate.setProductTemplateid(stringArray[0]);
		addTemplate.setPartId(stringArray[1]);
		addTemplate.setQuantity(stringArray[2]);
		
		if(addTemplate.getErrorCount() != 0){
			return addTemplate;
		}
		
		addTemplate = ProductTemplatePartDetailDB.addProductTemplatePartDetail(addTemplate);
				
		return addTemplate;
	}
	
	public static ProductTemplate addProductTemplate(String[] stringArray, ProductTemplate addProduct){
		addProduct.setErrorCount(0);
		addProduct.setProductNum(stringArray[0]);
		addProduct.setProductDescription(stringArray[1]);
		
		if(addProduct.getErrorCount() != 0){
			return addProduct;
		}
		
		if(!uniqueProductTemplate(addProduct)){
			addProduct.setErrorListAtIndex(2, "Description already exists");
			return addProduct;
		}
		addProduct = ProductTemplateDB.addProductTemplate(addProduct);
		
		if(addProduct.getErrorCount() == 0)
			productFrame.refresh();
		
		return addProduct;
	}
	
	public static InventoryItem addInventoryItem(String[] stringArray, InventoryItem addInventoryItem){
		item = addInventoryItem;
		
	//Error check with model 
		item.setPartId(Integer.parseInt(stringArray[0]));
		item.setQuantity(stringArray[1]);
		item.setLocation(stringArray[2]);
	
	
		if(InventoryItemDB.findInventoryItemByPartIdAndLocation(item.getPartId(), item.getLocation()))
		{
			item.setErrorCount(item.getErrorCount() + 1);
			item.setErrorListAtIndex(0, "Part and Location combination already exits!");
		}
		else
			item = InventoryItemDB.addInventoryItem(item);
	
		if(item.getErrorCount() == 0 && item.getItemId() > 0){
			item.partUI.setPartQuantityLabel(item.getQuantity());
			item.partUI.setPartNameLabel(item.getPart().getPartName());
			inventoryLocationFrame.refresh();
			//System.out.println("Something");
	}
	
		return item;
		
	}
	
	public static Part addPart(String[] stringArray, Part addPart){
		newPart = addPart;
		//Error check for for duplicates locally first before sending to model
		//to avoid loosing original part name and num
		//if(errorCheckPName(stringArray[0], "0")){
			//newPart.setPartName(stringArray[0]);
	//	}
		
		if(errorCheckpNum(stringArray[1], "0")){
			newPart.setPartNum(stringArray[1]);
		}
			
		//Error check with model 
		newPart.setPartName(stringArray[0]);
		newPart.setPartNum(stringArray[1]);
		newPart.setVendorName(stringArray[2]);
		newPart.setUnit(stringArray[3]);
		newPart.setExternalNum(stringArray[4]);
		
		
		newPart.listUI.setPartQuantityLabel((Integer.toString(newPart.getQuantity())));
		newPart.listUI.setPartUnitLabel(newPart.getUnit());
		newPart.listUI.setPartNameLabel(newPart.getPartName());
		
		Part tester = PartDB.addPart(newPart);
		if(tester == null)
			return newPart;
		else
			newPart = tester;
		
		
		
		if(newPart.getErrorCount() == 0){
			//newPart = PartDB.addPart(newPart);
			//Add part to mainFrame
			listPartsFrame.addPart(newPart);
			//Add part to list
			list.addPart(newPart);	
			//listPartsFrame.refresh();
		}
		
		return newPart;
			
	}
	
	public static InventoryItem updateInventoryItem(String[] stringArray, InventoryItem updateItem){
		//Initialize error to check again in this method.
		updateItem.setErrorCount(0);
		updateItem.setPartId(Integer.parseInt(stringArray[0]));
		updateItem.setQuantity(stringArray[1]);
		updateItem.setLocation(stringArray[2]);
		
		Timestamp oldTime = InventoryItemDB.getTimestamp(updateItem);
		LocalDateTime dateTime = LocalDateTime.now();
		Timestamp newTime = Timestamp.valueOf(dateTime);
		if(updateItem.getTime().equals(oldTime)){
			updateItem.setTime(newTime);
		}
		else{
			timeFlag = true;
			return updateItem;
		}
		
		updateItem = InventoryItemDB.updateInventoryItem(updateItem);
		
		if(updateItem.getErrorCount() == 0)
			inventoryLocationFrame.refresh();
		
		
		return updateItem;
	}
	
	public static Part updatePart(String[] stringArray, Part updatePart ){
		updatePart.setIsNew(false);
		newPart = updatePart;
		
		//Initialize error to check again in this method
		updatePart.setErrorCount(0);
		updatePart.setPartName(stringArray[0]);
		updatePart.setPartNum(stringArray[1]);
		updatePart.setVendorName(stringArray[2]);
		updatePart.setUnit(stringArray[3]);
		updatePart.setExternalNum(stringArray[4]);
		
		if(errorCheckpNum(stringArray[1], updatePart.getPersonalId())){
			updatePart.setPartNum(stringArray[1]);
		}
		Part tester = PartDB.updatePart(updatePart);
		if(tester == null ){
			return updatePart;
		}
		else
			updatePart = tester;
		
		if(updatePart.getErrorCount() == 0){
			listPartsFrame.refresh();	
		}
		
		return updatePart;
			
	}
	
	public static ProductTemplate updateProductTemplate(String[] stringArray, ProductTemplate updateProduct){
		updateProduct.setErrorCount(0);
		updateProduct.setProductNum(stringArray[0]);
		updateProduct.setProductDescription(stringArray[1]);
		
		if(updateProduct.getErrorCount() != 0){
			return updateProduct;
		}
		
		if(!uniqueProductTemplate(updateProduct)){
			updateProduct.setErrorListAtIndex(2, "Description already exists");
			return updateProduct;
		}
		
		updateProduct = ProductTemplateDB.updateProductTemplate(updateProduct);
		
		if(updateProduct.getErrorCount() == 0){
			productFrame.refresh();
		}
		
		return updateProduct;
	}
	
	public static ProductTemplatePartDetail updateProductTemplatePartDetail(String[] stringArray, ProductTemplatePartDetail addTemplate){
		ProductTemplatePartDetail newTemplate = new ProductTemplatePartDetail(addTemplate.getProductTemplate(), addTemplate.getPart(), addTemplate.getQuantity());
		newTemplate.setErrorCount(0);
		newTemplate.setProductTemplateid(stringArray[0]);
		newTemplate.setPartId(stringArray[1]);
		newTemplate.setQuantity(stringArray[2]);
		
		if(newTemplate.getErrorCount() != 0){
			return newTemplate;
		}
		
		newTemplate = ProductTemplatePartDetailDB.updateProductTemplatePartDetail(addTemplate, newTemplate);
				
		return newTemplate;
	}
	
	//Error check is part number already exists
	private static boolean errorCheckpNum(String partNum, String id){
		try{
			for(int i=0; i < list.getAmount(); i++){
				if(list.list.get(i).getPartNum().equals(partNum) && !list.list.get(i).getPersonalId().equals(id)){
					//System.out.println("HERE");
					throw new IllegalArgumentException("'"+ partNum+"' already exists" );
				}
			}
		}
		catch(IllegalArgumentException e){
			newPart.setErrorList(1 ,"ERROR: "+e.getMessage());
			newPart.setErrorCount(newPart.getErrorCount() + 1);
			return false;
		}
		return true;
	}
	
	//Error check if name already exists
	public static boolean errorCheckPName(String partName, String id){
		try{
			for(int i=0; i < list.getAmount(); i++){
				if(list.list.get(i).getPartName().equals(partName) && !list.list.get(i).getPersonalId().equals(id)){
					throw new IllegalArgumentException("'"+ partName+"' already exists" );
				}
			}
		}
		catch(IllegalArgumentException e){
			newPart.setErrorList(0,"ERROR: "+e.getMessage());
			newPart.setErrorCount(newPart.getErrorCount() + 1);
			return false;
		}
		return true;
	}
	public static boolean nameExists(String name){
		if(list.findPart(name) == null)
			return false;
		return true;
		
	}
	
	public static boolean uniqueProductTemplate(ProductTemplate p){
		for(int i = 0; i < productFrame.productList.size(); i++){
			if(productFrame.productList.get(i).getProductDescription().equals(p.getProductDescription()) && productFrame.productList.get(i).getProductId() != p.getProductId())
				return false;
		}
		return true;
	}

}
