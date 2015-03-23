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
	private int arguments = 7;
	private boolean isNew;
	protected String[] errorList = new String[arguments];
	public listPart listUI;
	//private static long id = 0;
	private String personalId;
	private String externalNum;

	
	public Part(){
		partNum = "";
		partName = "";
		vendorName = "";
		quantity = 0;
		unit = "Unknown";
		location = "Unknown";
		errorCount = 0;
		listUI = new listPart();
		personalId = "";
		externalNum ="";
		isNew = true;
		initializeErrorList();
		//errorList = null;
	}
	public void initializeErrorList(){
		for(int i=0; i < arguments;i++){
			errorList[i] = "";
		}
	}
	public void setIsNew(boolean isNew){
		this.isNew = isNew;
	}
	public boolean getIsNew(){
		return isNew;
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
		setErrorCount(getErrorCount() + 1);
	}
	
	public Part(int id, String name, String num, String extern, String vendor, String unit){
		setPersonalId(id);
		setPartName(name);
		setPartNum(num);
		setExternalNum(extern);
		setVendorName(vendor);
		setUnit(unit);
		listUI = new listPart();
		isNew = false;
	}
	
	public Part(String name, String num, String vendor, String quantity, String unit, String location){
		setPartName(name);
		setPartNum(num);
		setVendorName(vendor);
		setQuantity(quantity);
		setUnit(unit);
		setLocation(location);
		listUI = new listPart();
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
				throw new IllegalArgumentException("Location cannot be 'Unknown'");
			}
			else{
				this.location = l;
			}
		}
		catch(Exception e){
			//errorList[6]= ("ERROR: "+e.getMessage());
			errorList[6]= (""+e.getMessage());
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
				throw new IllegalArgumentException("Unit cannot be 'Unknown'");
			}
			else{
				this.unit = u;
			}
		}
		catch(Exception e){
			//errorList[4]= ("ERROR: "+e.getMessage());
			errorList[4]= (""+e.getMessage());
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

	/*public void setErrorName(){
		errorList[5] = "Name already exists in file";
	}
	*/
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
			if( partNum.length() < 20  && partNum.length() > 0 && partNum.charAt(0) == 'P')
				this.partNum = partNum;
			else{
				if( partNum.length() > 20)
					throw new IllegalArgumentException("'" + partNum + "' is longer than 20 characters");
				else if(partNum.length() == 0)
					throw new IllegalArgumentException("Part number may not be left blank");
				else throw new IllegalArgumentException("Part number must start with 'P'");
					
			}
		}
		catch(IllegalArgumentException e){
			//errorList[1]= ("ERROR: "+e.getMessage());
			errorList[1]= (""+e.getMessage());
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
					throw new IllegalArgumentException("Part name may not be left blank");
				
					
			}
			
		}
		catch(IllegalArgumentException e){
			//errorList[0] = ("ERROR: "+e.getMessage());
			errorList[0]= (""+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
		//Check if there is an existing part for updated parts
		
		
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
		int quantityInt;
		try{
			quantityInt = Integer.parseInt(squantity);
		}
		catch(NumberFormatException e){
			errorList[3] = ("ERROR: Not a number");
			setErrorCount(getErrorCount() + 1);
			return;
		}
		try{
			if( quantityInt > 0  )
				quantity = quantityInt;
			else if(isNew == false && quantityInt == 0){
				this.quantity = quantityInt;
			}
			else{
				throw new IllegalArgumentException("Quantity should be greater than 0");
			}
		}
		catch(IllegalArgumentException e){
			//errorList[3] =("ERROR: Must be greater");
			errorList[3]= (""+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}	
		
	}
	public String getExternalNum() {
		return externalNum;
	}
	public void setExternalNum(String externalNum) {
		try{
			if( externalNum.length() <= 50  )
				this.externalNum = externalNum;
			else{
				throw new IllegalArgumentException("'" + externalNum + "' is longer than 50");
			}
		}
		catch(IllegalArgumentException e){
			//errorList[5] =("ERROR: "+e.getMessage());
			errorList[5]= (""+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	

	

}

