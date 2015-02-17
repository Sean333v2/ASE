package model;
import view.listPart;

 
public class Part{
	
	private String partNum;
	private String partName;
	private String vendorName;
	private int quantity;
	private String unit;
	private String location;
	protected int errorCount;
	private int arguments = 8;
	private boolean isNew = true;
	protected String[] errorList = new String[arguments];
	public listPart lp;
	private static long id = 0;
	private String personalId;
	private String externalNum;

	
	public Part(){
		partNum = "";
		partName = "";
		vendorName = "";
		quantity = 0;
		unit = "Unknown";
		//location = "Unknown";
		errorCount = 0;
		lp = new listPart();
		id++;
		personalId = ""+id;
		setExternalNum("");
		//errorList = null;
	}
	public String getPersonalId(){
		return personalId;
	}
	public void setPersonalId(int id){
		this.personalId = ""+id;
			
	}
	public void setErrorList(int i, String s){
		if(s == null )
			errorList[i] = "";
		else
			errorList[i] = s;
	}
	
	public Part(String name, String num, String vendor, String quantity, String unit, String location){
		setPartName(name);
		setPartNum(num);
		setVendorName(vendor);
		setQuantity(quantity);
		setUnit(unit);
		setLocation(location);
		lp = new listPart();
		isNew = false;
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
			else{
				this.location = l;
			}
		}
		catch(Exception e){
			errorList[6]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	
	public String getUnit(){
		return unit;
	}
	
	public void setUnit(String u){
		try{
			if(u == null){
				throw new IllegalArgumentException("'" + u + "' cannot be null");
			}
			else if("Unknown".equals(u) || "".equals(u)){
				throw new IllegalArgumentException("Cannot be 'Unknown'");
			}
			else{
				this.unit = u;
			}
		}
		catch(Exception e){
			errorList[4]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	
	//Getter and Setter for ErrorList
	public String[] getErrorList() {
		//Last field is for error count which is initialized to 0 in the controller
		for(int i=0; i< arguments; i++){
			if(errorList[i] == null)
				errorList[i] = "";
		}
		return errorList;
	}
	public String getErrorListIndex(int i) {
		//Last field is for error count which is initialized to 0 in the controller
		
			if(errorList[i] == null)
				errorList[i] = "";
		
		return errorList[i];
	}

	public void setErrorName(){
		errorList[5] = "Name already exists in file";
	}
	
	//Getter and Setter for Error Count
	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	//Getter and Setter for Part Number
	public String getPartNum() {
		return partNum;
	}
	
	
	public void setPartNum(String partNum) {
		try{
			if( partNum.length() < 20  && partNum.length() > 0)
				this.partNum = partNum;
			else{
				if( partNum.length() > 20)
					throw new IllegalArgumentException("'" + partNum + "' is longer than 20");
				else
					throw new IllegalArgumentException("This field may not be left blank");
			}
		}
		catch(IllegalArgumentException e){
			errorList[1]= ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	//Getter and Setter for Part Name
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		try{
			if( partName.length() < 255 && partName.length() > 0 )
				this.partName = partName;
			else{
				if(partName.length() > 255)
					throw new IllegalArgumentException("'" + partName + "' is longer than 255");
				else 
					throw new IllegalArgumentException("This field may not be left blank");
			}
		}
		catch(IllegalArgumentException e){
			errorList[0] = ("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
		
	}

	//Getter and Setter for Vendor Name
	public String getVendorName() {
		return vendorName;
	}
	
	public void setVendorName(String vendorName) {
		try{
			if( vendorName.length() < 255  )
				this.vendorName = vendorName;
			else{
				throw new IllegalArgumentException("'" + vendorName + "' is longer than 255");
			}
		}
		catch(IllegalArgumentException e){
			errorList[2] =("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	
	//Getter and Setter for Quantity
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(String squantity) {
		try{
			quantity = Integer.parseInt(squantity);
		}
		catch(NumberFormatException e){
			errorList[3] = ("ERROR: Not a number");
			setErrorCount(getErrorCount() + 1);
			return;
		}
		try{
			if( quantity > 0  )
				this.quantity = quantity;
			else if(isNew == false && quantity == 0){
				this.quantity = quantity;
			}
			else{
				throw new IllegalArgumentException("This variable should be greater than 0");
			}
		}
		catch(IllegalArgumentException e){
			errorList[3] =("ERROR: Must be greater");
			setErrorCount(getErrorCount() + 1);
		}	
		isNew = false;
	}
	public String getExternalNum() {
		return externalNum;
	}
	public void setExternalNum(String externalNum) {
		try{
			if( vendorName.length() <= 50  )
				this.externalNum = externalNum;
			else{
				throw new IllegalArgumentException("'" + externalNum + "' is longer than 50");
			}
		}
		catch(IllegalArgumentException e){
			errorList[5] =("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	

	

}

