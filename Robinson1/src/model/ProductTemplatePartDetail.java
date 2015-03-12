package model;

import view.listPart;

public class ProductTemplatePartDetail{
	private String productTemplateid;
	private String partId;
	private String quantity;
	private int arguments = 2;
	private String[] errorList = new String[arguments];
	private int errorCount = 0;
	public listPart partUI = new listPart();
	public String partName;
	
	public ProductTemplatePartDetail(ProductTemplate productTemplate, Part part, String quantity){
		setProductTemplateid(productTemplate.getProductId());
		setPartId(part.getPersonalId());
		setQuantity(quantity);
		setPartName(part.getPartName());
		
	}
	public void setPartName(String name){
		partName = name;
		partUI.setPartNameLabel(name);
	}
	public String getPartName(){
		return partName;
	}
	public String[] getErrorList(){
		return errorList;
	}
	public void setErrorListAtIndex(int index, String error){
		errorList[index] = error;
		setErrorCount(getErrorCount() + 1);
	}
	public String getProductTemplateid() {
		return productTemplateid;
	}
	public void setProductTemplateid(String productTemplateid) {
		this.productTemplateid = productTemplateid;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
		partUI.setPartUnitLabel(partId);
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		int quantityInt;
		try{
			quantityInt = Integer.parseInt(quantity);
		}
		catch(NumberFormatException e){
			errorList[0] = ("ERROR: Not a number");
			setErrorCount(getErrorCount() + 1);
			return;
		}
		try{
			if( quantityInt > 0  ){
				this.quantity = ""+quantityInt;
				partUI.setPartQuantityLabel(quantity);
			}
				
			
			else{
				throw new IllegalArgumentException("This variable should be greater than 0");
			}
		}
		catch(IllegalArgumentException e){
			errorList[0] =("ERROR: Must be greater");
			setErrorCount(getErrorCount() + 1);
		}	
		
		
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
	
	

}
