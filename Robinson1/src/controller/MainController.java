package controller;

import java.util.ArrayList;

import view.InventoryAtLocationFrame;
import view.MainFrame;
import model.InventoryItem;
import model.InventoryItemDB;
import model.Part;
import model.PartDB;
import model.PartsList;

public class MainController {
	private static Part newPart;
	private static InventoryItem item;
	public static PartsList list = new PartsList();
	private static MainFrame listPartsFrame;
	private static InventoryAtLocationFrame inventoryLocationFrame;
	private static InventoryItemDB inventoryDB;
	
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		listPartsFrame = new MainFrame();
		initializeList();
		
	}
	//This is to set the inventory frame the current frame is working with and be able to control
	public static void setInventoryLocation(InventoryAtLocationFrame frame){
		inventoryLocationFrame = frame;
	}
	//Returns to inventoryatlocationframe the list to display 
	public static ArrayList<InventoryItem> getInventoryAtLocation(String location){
		return(inventoryDB.fetchByLocation(location));
		
		
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
		}
		
	}
	public static void deletePart(Part deleteItem){
		
		//Remove from array list
		list.removePart(list.findPart(deleteItem.getPartName()));
		PartDB.deletePart(newPart);
		
		//Remove from MainFrame
		 listPartsFrame.container.remove(deleteItem.listUI.getPartQuantityLabel());
		 listPartsFrame.container.remove(deleteItem.listUI.getPartUnitLabel());
    	 listPartsFrame.container.remove(deleteItem.listUI.getPartNameLabel());
    	 listPartsFrame.container.remove(deleteItem.listUI.getDeleteButton());
    	 listPartsFrame.container.remove(deleteItem.listUI.getDetailsButton()); 
    	 listPartsFrame.container.revalidate();
    	 
	}
	
	//Deletes the inventory item from that frame
	public static void deleteInventoryItem(InventoryItem deleteInventoryPart){
		
		//Remove from array list
		InventoryItemDB.deleteInventoryItem(deleteInventoryPart); 
		
		//Remove from MainFrame
		 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getPartQuantityLabel());
		 //listPartsFrame.container.remove(deletePart.partUI.getPartUnitLabel());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getPartNameLabel());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getDeleteButton());
    	 inventoryLocationFrame.container.remove(deleteInventoryPart.partUI.getDetailsButton()); 
    	 inventoryLocationFrame.container.revalidate();
    	 
	}
	/*
public static Part addInventoryItem(String[] stringArray, Part addInventoryItem){
	newPart = addInventoryItem;
	
	//Error check for for duplicates locally first before sending to model
	//to avoid loosing original part name and num
	if(errorCheckPName(stringArray[0], "0")){
		newPart.setPartName(stringArray[0]);
	}
	
	if(errorCheckpNum(stringArray[1], "0")){
		newPart.setPartNum(stringArray[1]);
	}
		
	//Error check with model 
	newPart.setPartName(stringArray[0]);
	newPart.setPartNum(stringArray[1]);
	newPart.setVendorName(stringArray[2]);
	newPart.setQuantity(stringArray[3]);
	newPart.setUnit(stringArray[4]);
	newPart.setExternalNum(stringArray[5]);
	newPart.setLocation(stringArray[6]);
	
	
	newPart.listUI.setPartQuantityLabel((Integer.toString(newPart.getQuantity())));
	newPart.listUI.setPartUnitLabel(newPart.getUnit());
	newPart.listUI.setPartNameLabel(newPart.getPartName());
	
	newPart = PartDB.addPart(newPart);
	
	if(newPart.getErrorCount() == 0 && newPart.getIsNew() == true){
		//Add part to mainFrame
		listPartsFrame.addPart(newPart);
		//Add part to list
		list.addPart(newPart);		
	}
	
	return newPart;
		
}*/
	
	public static Part addPart(String[] stringArray, Part addPart){
		newPart = addPart;
		//Error check for for duplicates locally first before sending to model
		//to avoid loosing original part name and num
		if(errorCheckPName(stringArray[0], "0")){
			newPart.setPartName(stringArray[0]);
		}
		
		if(errorCheckpNum(stringArray[1], "0")){
			newPart.setPartNum(stringArray[1]);
		}
			
		//Error check with model 
		newPart.setPartName(stringArray[0]);
		newPart.setPartNum(stringArray[1]);
		newPart.setVendorName(stringArray[2]);
		newPart.setQuantity(stringArray[3]);
		newPart.setUnit(stringArray[4]);
		newPart.setExternalNum(stringArray[5]);
		newPart.setLocation(stringArray[6]);
		
		
		newPart.listUI.setPartQuantityLabel((Integer.toString(newPart.getQuantity())));
		newPart.listUI.setPartUnitLabel(newPart.getUnit());
		newPart.listUI.setPartNameLabel(newPart.getPartName());
		
		newPart = PartDB.addPart(newPart);
		
		if(newPart.getErrorCount() == 0 && newPart.getIsNew() == true){
			//Add part to mainFrame
			listPartsFrame.addPart(newPart);
			//Add part to list
			list.addPart(newPart);		
		}
		
		return newPart;
			
	}
	public static Part updatePart(String[] stringArray, Part updatePart ){
		//Initialize error to check again in this method
		updatePart.setErrorCount(0);
		/*newPart.setPartName(stringArray[0]);
		newPart.setPartNum(stringArray[1]);
		newPart.setVendorName(stringArray[2]);
		newPart.setUnit(stringArray[4]);
		newPart.setExternalNum(stringArray[5]);
		newPart.setLocation(stringArray[6]);
		*/
		
		//Set flag to know this is an update instance
		updatePart.setIsNew(false);
		updatePart = addPart(stringArray, updatePart);
		
		if(newPart.getErrorCount() == 0){
			listPartsFrame.refresh(list);	
		}
		
		return updatePart;
			
	}

	
	//Error check is part number already exists
	private static boolean errorCheckpNum(String partNum, String id){
		try{
			for(int i=0; i < list.getAmount(); i++){
				if(list.list.get(i).getPartNum().equals(partNum) && !list.list.get(i).getPersonalId().equals(id)){
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
	private static boolean errorCheckPName(String partName, String id){
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
	public static InventoryItem updateInventory(InventoryItem item, String[] info) {
		// Create function that will update database
		return null;
	}
}