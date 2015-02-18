package controller;

import view.MainFrame;
import model.Part;
import model.PartsList;

public class MainController {
	private static Part newPart;
	private static PartsList list = new PartsList();
	private static MainFrame listPartsFrame;
	
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		listPartsFrame = new MainFrame();
		addExamples();
		
	}
	public static void addExamples(){
		String[] ex1 = {"Adapter","8K09195656PS","","3", "Pieces", "", "Facility 2"};
		String[] ex2 = {"Adjust Knob","8T0919070A","parts.com","1", "Pieces", "", "Facility 2"};
		String[] ex3 = {"Braket","8K0614235B","parts.com","2", "Pieces" ,"", "Facility 2"};
		String[] ex4 = {"Screw","8T09190702","","20", "Pieces", "", "Facility 2"};
		String[] ex5 = {"Isolator","8T21919070A","parts.com","1", "Pieces", "", "Facility 2"};
		
		Part part1 = addPart(ex1, new Part());
		Part part2 = addPart(ex2, new Part());
		Part part3 = addPart(ex3, new Part());
		Part part4 = addPart(ex4, new Part());
		Part part5 = addPart(ex5, new Part());
		/*for(int i=0; i < list.getAmount(); i++)
		System.out.println(list.list.get(i).getPartName());
		deletePart(part5);
		for(int i=0; i < list.getAmount(); i++)
			System.out.println(list.list.get(i).getPartName());*/
	}
	public static void deletePart(Part deletePart){
		
		//Remove from array list
		list.removePart(list.findPart(deletePart.getPartName()));
		
		//Remove from MainFrame
		 listPartsFrame.container.remove(deletePart.listUI.getPartQuantityLabel());
		 listPartsFrame.container.remove(deletePart.listUI.getPartUnitLabel());
    	 listPartsFrame.container.remove(deletePart.listUI.getPartNameLabel());
    	 listPartsFrame.container.remove(deletePart.listUI.getDeleteButton());
    	 listPartsFrame.container.remove(deletePart.listUI.getDetailsButton());    	
    	 listPartsFrame.container.revalidate();
    	 
	}
	
	public static Part addPart(String[] stringArray, Part addPart){
		newPart = addPart;
		//Error check with model 
		newPart.setPartName(stringArray[0]);
		newPart.setPartName(stringArray[1]);
		newPart.setVendorName(stringArray[2]);
		newPart.setQuantity(stringArray[3]);
		newPart.setUnit(stringArray[4]);
		newPart.setExternalNum(stringArray[5]);
		newPart.setLocation(stringArray[6]);
		
		//Error check for for duplicates locally
		if(errorCheckPName(stringArray[0]))
			newPart.setPartName(stringArray[0]);
		if(errorCheckpNum(stringArray[1]))
			newPart.setPartNum(stringArray[1]);
		
		
		
		if(newPart.getErrorCount() == 0){
			//Add part to mainFrame
			listPartsFrame.addPart(newPart);
			//Add part to list
			list.addPart(newPart);
			
			newPart.listUI.setPartQuantityLabel((Integer.toString(newPart.getQuantity())));
			newPart.listUI.setPartUnitLabel(newPart.getUnit());
			newPart.listUI.setPartNameLabel(newPart.getPartName());
		}
		
		return newPart;
			
	}
	public static Part updatePart(String[] stringArray, Part updatePart ){
		//Initialize error to check again in this method
		
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
			
			//Add to list part & refresh
			updatePart.listUI.setPartQuantityLabel(stringArray[3]);
			updatePart.listUI.setPartUnitLabel(stringArray[4]);
			updatePart.listUI.setPartNameLabel(stringArray[0]);
			listPartsFrame.refresh(list);
			
		}
		
		return updatePart;
			
	}
	//Might not need this anymore
	/*
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
	}*/
	
	//Error check is part number already exists
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
	
	//Error check if name already exists
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
	public static Part updateInventory(Part mainPart, String[] info) {
		// Create function that will update database
		return null;
	}
}