package model;
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
	private int itemId;
	
	public InventoryItem(){
		location = "Unknown";
		setQuantity("0");
	}
	public InventoryItem( int itemId, int partId, String location, String quantity){
		this.itemId = itemId;
		this.quantity = quantity;
		this.location = location;
		this.quantity = quantity;
	}
	
	public void setQuantity(String quantity){
		this.quantity = quantity;
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
		/*	errorList[6]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);*/
		}
	}


	public String getQuantity() {
		return "" + part.getQuantity();
	}


	public int getErrorCount(){
		return part.getErrorCount();
	}


	

	
}
