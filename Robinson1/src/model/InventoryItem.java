package model;

import controller.MainController;
import view.inventoryUI;
import java.sql.Timestamp;

/*
Inventory Item: an instance of a Part at a Location with a given Quantity. Id: automatically generated unique id
Part: the part at that Location (should be a link to an instance of Part) Location: (same as before)
Quantity: (same as before)
Constraint: No two Inventory Items can have same Part/Location
*/
public class InventoryItem{
	private String location;
	private String quantity;
	private Part part;
	private int itemId = -1;
	private int partId;
	public inventoryUI partUI;
	private String[] locationStrings = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2", "Unknown"};
	private String[] errorList = new String[4];
	private int errorCount = 0;
	private Timestamp time;
	
	public InventoryItem(){
		location = "Unknown";
		this.quantity = null;
		partUI = new inventoryUI();
		errorList[0] = null;
	}
	
	public InventoryItem( int itemId, int partId, String location, String quantity, Timestamp t){
		this.quantity = null;
		this.itemId = itemId;
		setPartId(partId);
		setLocation(location);
		setQuantity(quantity);
		partUI = new inventoryUI();
		errorList[0] = null;
		time = t;
	}
	
	public InventoryItem( int itemId, int partId, String location, String quantity){
		this.quantity = null;
		this.itemId = itemId;
		setPartId(partId);
		setLocation(location);
		setQuantity(quantity);
		partUI = new inventoryUI();
		errorList[0] = null;
	}
	
	public InventoryItem(int partId, String location, String quantity){
		this.quantity = null;
		setPartId(partId);
		setLocation(location);
		setQuantity(quantity);
		partUI = new inventoryUI();
		errorList[0] = null;
	}
	
	public Timestamp getTime(){
		return this.time;
	}
	
	public void setTime(Timestamp t){
		this.time = t;
	}
	
	public int getItemId(){
		return this.itemId;
	}
	
	public void setItemId(int id){
		this.itemId=id;
	}
	
	public int getPartId(){
		return this.partId;
	}
	
	public void setPartId(int partId){
		try{
			if(MainController.list.findPartById(partId)){
				this.partId = partId;
				part = MainController.list.getPartById(this.partId);
			}
			else
				throw new IllegalArgumentException("PartId does not exist");
		}
		catch(IllegalArgumentException e){
			errorList[1] = "ERROR: "+e.getMessage();
			setErrorCount(getErrorCount()+1);
			return;
		}
	}
	public Part getPart(){
		return part;	
	}
	public void setQuantity(String quantity){
		int quantityInt;
		try{
			quantityInt = Integer.parseInt(quantity);
		}
		catch(NumberFormatException e){
			errorList[2] = ("ERROR: Not a number");
			setErrorCount(getErrorCount() + 1);
			e.printStackTrace();
			return;
		}
		try{
			if(quantityInt == 0)
				if(this.quantity != null)
					this.quantity = quantity;
				else
					throw new IllegalArgumentException("Cannot be initialized to 0");
			else if(quantityInt < 0)
				throw new IllegalArgumentException("Cannot be less than 0");
			else
				this.quantity = quantity;
		}
		catch(IllegalArgumentException e){
			errorList[2] = "Error: "+e.getMessage();
			
		}
		
	}
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String location){
		try{
			if(location == null){
				throw new IllegalArgumentException("'" + location + "' cannot be null");
			}
			else if("Unknown".equals(location) || "".equals(location)){
				throw new IllegalArgumentException("Cannot be 'Unknown'");
			}
			//Check that no other part has same location from database
			else{
				this.location = location;
			}
		}
		catch(Exception e){
			errorList[3]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}


	public String getQuantity() {
		return this.quantity;
	}


	public int getErrorCount(){
		return errorCount;
	}
	
	public void setErrorCount(int c){
		errorCount = c;
	}

	public String getErrorListAtIndex(int index){
		return errorList[index];
	}

	public void setErrorListAtIndex(int i, String string) {
		errorList[i] = string;
	}

	

	

	
}
