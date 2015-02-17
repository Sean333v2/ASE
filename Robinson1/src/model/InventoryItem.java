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
	
	public InventoryItem(){
		location = "Unknown";
		quantity = "0";
		
		
	}
	
	
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String l){
		try{
			if(l == null){
				throw new IllegalArgumentException("'" + l + "' cannot be null");
			}
			else if("Unknown".equals(l) || "".equals(l)){
				throw new IllegalArgumentException("Cannot be 'Unknown'");
			}
			//Check that no other part has same location from database
			else{
				this.location = l;
			}
		}
		catch(Exception e){
		/*	errorList[6]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);*/
		}
	}

	
}
