package controller;

import java.util.ArrayList;

import view.MainFrame;
import model.Part;
import model.PartDB;
import model.PartsList;

public class MainController {
	private static Part newPart;
	private static PartsList list = new PartsList();
	private static MainFrame mf;
	
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		mf = new MainFrame();
		initializeList();
		
	}
	public static void initializeList(){
		ArrayList<Part> allParts = PartDB.fetchAll();
		
		for(int i = 0; i < allParts.size(); i++){
			Part newPart = allParts.get(i);
			
			if(newPart.getErrorCount() == 0){
				//Add part to mainFrame
				mf.addPart(newPart);
				//Add part to list
				list.addPart(newPart);
			}
		}
		
	}
	public static void deletePart(Part p){
		
		//Remove from array list
		newPart = list.findPart(p.getPartName());
		list.removePart(newPart);
		
		//Remove from MainFrame
		 mf.container.remove(p.lp.getPq());
		 mf.container.remove(p.lp.getPu());
    	 mf.container.remove(p.lp.getPn());
    	 mf.container.remove(p.lp.getDelete());
    	 mf.container.remove(p.lp.getDetails());    	
    	 mf.container.revalidate();
    	 
	}
	
	public static Part addPart(String[] stringArray){
		
		newPart = new Part();
		
		//Error check for part Name, if none add
		if(errorCheckPName(stringArray[0]))
			newPart.setPartName(stringArray[0]);
		
		//Error check part number
		if(errorCheckpNum(stringArray[1]))
			newPart.setPartNum(stringArray[1]);
		
		newPart.setVendorName(stringArray[2]);
		newPart.setQuantity(stringArray[3]);
		newPart.setUnit(stringArray[4]);
		newPart.setExternalNum(stringArray[5]);
		newPart.setLocation(stringArray[6]);
		
		newPart = PartDB.addPart(newPart);
		
		if(newPart.getErrorCount() == 0){
			//Add part to mainFrame
			mf.addPart(newPart);
			//Add part to list
			list.addPart(newPart);
		}
		
		return newPart;
			
	}
	public static Part updatePart(Part newPart, String[] stringArray){
		//Initialize error to check again in this method
		newPart.setErrorCount(0);
		
		//newPart = new Part(stringArray[0], stringArray[1], stringArray[2], "1", stringArray[4]);
		newPart.setPartName(stringArray[0]);
		newPart.setPartNum(stringArray[1]);
		newPart.setVendorName(stringArray[2]);
		newPart.setUnit(stringArray[4]);
		newPart.setExternalNum(stringArray[5]);
		newPart.setLocation(stringArray[6]);
		//Error check for part Name, if none add
		if(errorCheckPName(stringArray[0]))
			newPart.setPartName(stringArray[0]);
		
		//Error check part number
		if(errorCheckpNum(stringArray[1]))
			newPart.setPartNum(stringArray[1]);
		
		newPart.setVendorName(stringArray[2]);
		
		//Error check quantity
		if(errorCheckUpdateQuantity(stringArray[3])){
			newPart.setQuantity(stringArray[3]);
		}
		if(newPart.getErrorCount() == 0){
			//Add part to mainFrame
			//mf.addPart(newPart);
			
			//Add to list part 
			newPart.lp.setPq(stringArray[3]);
			newPart.lp.setPu(stringArray[4]);
			newPart.lp.setPn(stringArray[0]);
			mf.refresh(list);
			//Add part to list
			//list.addPart(newPart);
		}
		//Set flag to know this is an update instance
		newPart.setErrorList(7, "1");
		
		return newPart;
			
	}
	private static boolean errorCheckUpdateQuantity(String squantity){
		int quantity;
		try{
			quantity = Integer.parseInt(squantity);
		}
		catch(NumberFormatException e){
			newPart.setErrorList(3, "ERROR: Not a number");
			newPart.setErrorCount(newPart.getErrorCount() + 1);
			return false;
		}
		try{
			if( quantity < 0  )
				throw new IllegalArgumentException("This variable should be greater than or equal to 0");
			
		}
		catch(IllegalArgumentException e){
			newPart.setErrorList(3,"ERROR: Must be greater");
			newPart.setErrorCount(newPart.getErrorCount() + 1);
			return false;
		}
		return true;
	}
	private static boolean errorCheckpNum(String partNum){
		try{
			for(int i=0; i < list.getAmount(); i++){
				if(list.list.get(i).getPartNum().equals(partNum)){
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
	private static boolean errorCheckPName(String partName){
		try{
			for(int i=0; i < list.getAmount(); i++){
				if(list.list.get(i).getPartName().equals(partName)){
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
}