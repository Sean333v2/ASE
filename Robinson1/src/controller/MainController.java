package controller;

import view.MainFrame;

import model.Part;
import model.PartsList;

public class MainController {
	private static Part newPart;
	private static PartsList list = new PartsList();
	private static MainFrame mf;
	
	public static void main(String args[]){
		System.out.println("CS 4743 Assignment 1 by Barbara Davila and Sean Gallagher");
		mf = new MainFrame();
		addExamples();
		
	}
	public static void addExamples(){
		String[] ex1 = {"Adapter","8K09195656PS","","3", "unit1"};
		String[] ex2 = {"Adjust Knob","8T0919070A","parts.com","1", "unit2"};
		String[] ex3 = {"Braket","8K0614235B","parts.com","2", "unit3"};
		String[] ex4 = {"Screw","8T09190702","","20", "unit4"};
		String[] ex5 = {"Isolator","8T21919070A","parts.com","1", "unit5"};
		
		Part part1 = addPart(ex1);
		Part part2 = addPart(ex2);
		Part part3 = addPart(ex3);
		Part part4 = addPart(ex4);
		Part part5 = addPart(ex5);
		/*for(int i=0; i < list.getAmount(); i++)
		System.out.println(list.list.get(i).getPartName());
		deletePart(part5);
		for(int i=0; i < list.getAmount(); i++)
			System.out.println(list.list.get(i).getPartName());*/
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
			newPart.lp.setPn(stringArray[0]);
			mf.refresh(list);
			//Add part to list
			//list.addPart(newPart);
		}
		//Set flag to know this is an update instance
		newPart.setErrorList(5, "1");
		
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