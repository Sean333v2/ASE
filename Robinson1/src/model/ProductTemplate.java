package model;

import javax.swing.JButton;
import javax.swing.JLabel;

import view.listPart;
import view.productTemplateListUI;




public class ProductTemplate {
	private String productID;
	private String productNum;
	private String productDescription;
	private int arguments = 3;
	private String[] errorList = new String[arguments];
	private int errorCount =0;
	public productTemplateListUI listUI = new productTemplateListUI();
	
	
	public ProductTemplate(String productID, String productNum, String productDescription){
		initializeErrorList();
		setProductId(productID);
		setProductNum(productNum);
		setProductDescription(productDescription);
	}
	private void initializeErrorList(){
		for(int i=0; i < arguments;i++){
			errorList[i] = "";
		}
	}
	public void setErrorListAtIndex(int index, String error){
			errorList[index] = error;
			setErrorCount(getErrorCount()+1);
	}
	public String[] getErrorList(){
		return errorList;
	}
	public String getProductId() {
		return productID;
	}
	public void setProductId(String productID) {
		this.productID = productID;
		
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		try{
			if( productNum.length() < 20  ){
				this.productNum = productNum;
			
			}
			else{
				throw new IllegalArgumentException("'" + productNum + "' is longer than 20");
			}
	
			
		}
		catch(IllegalArgumentException e){
			errorList[0] =("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
		
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		try{
			if( productDescription.length() < 255  ){
				if( productDescription.substring(0, 1).toLowerCase().equals("a") ){
					this.productDescription = productDescription;
					
				}
					
				else{
					throw new IllegalArgumentException("'" + productDescription + "' needs to begin with \"a\"");
				}
			}
			else{
				throw new IllegalArgumentException("'" + productDescription + "' is longer than 255");
			}
	
			
		}
		catch(IllegalArgumentException e){
			errorList[2] =("ERROR: "+e.getMessage());
			setErrorCount(getErrorCount() + 1);
		}
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	/*public JButton getProductUIField() {
		return productUIField;
	}

	private void setProductUIField(String productDescription) {
		productUIField = new JButton(productDescription);
		productUIField.setBorderPainted(false);
		productUIField.setOpaque(false);
		
		
	}

	public JLabel getProductNumLabel() {
		return productNumLabel;
	}

	public void setProductNumLabel(String productNumLabel) {
		
		this.productNumLabel = new JLabel(productNumLabel);
	}

	public JLabel getProductidLabel() {
		return productidLabel;
	}

	public void setProductidLabel(String productidLabel) {
		this.productidLabel = new JLabel(productidLabel);
	}*/
	
	
}
