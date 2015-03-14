package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTemplatePartDetailDB {

	public static ArrayList<ProductTemplatePartDetail> fetchAllByProductId(String id){
		ArrayList<ProductTemplatePartDetail> result = new ArrayList<ProductTemplatePartDetail>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	stmt = conn.prepareStatement(
        			"SELECT *"+ 
        			"FROM productTemplatePartDetails"+
        			"INNER JOIN productTemplates, parts"+
        			"WHERE ? = productTemplates.productId"+
        			"AND productTemplatePartDetails.partId = parts.partId");
        	stmt.setString(1, id);
            rs = stmt.executeQuery();
            if(!rs.first()){
            	return result;	
            }
            result.add(new ProductTemplatePartDetail(
            		   new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")),
            		   new Part(rs.getString("partId"), rs.getString("partName"), rs.getString("partNumber"), rs.getString("externalNumber"),
            				    rs.getString("vendorName"), rs.getString("unit")),
            		   rs.getString("quantity")));
            
            while(rs.next()){
            	result.add(new ProductTemplatePartDetail(
             		   new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")),
             		   new Part(rs.getString("partId"), rs.getString("partName"), rs.getString("partNumber"), rs.getString("externalNumber"),
             				    rs.getString("vendorName"), rs.getString("unit")),
             		   rs.getString("quantity")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	//Throws an SQLException if the part/product combo already exists.
	public static ProductTemplatePartDetail addProductTemplate(ProductTemplatePartDetail p){
		ProductTemplatePartDetail addedProduct = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if(p.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			
			stmt = conn.prepareStatement("INSERT INTO productTemplatePartDetails (productId, partId, quantity)"+
										"VALUES (?, ?, ?);");
			stmt.setString(1, p.getProductTemplateid());
			stmt.setString(2, p.getPartId());
			stmt.setString(3, p.getQuantity());
			
			if(stmt.execute())//If an error occurs throw exception.
				throw new SQLException();
			
			stmt = conn.prepareStatement(
        			"SELECT *"+ 
        			"FROM productTemplatePartDetails"+
        			"INNER JOIN productTemplates, parts"+
        			"WHERE ? = productTemplates.productId"+
        			"AND ? = parts.partId");
			stmt.setString(1, p.getProductTemplateid());
			stmt.setString(2, p.getPartId());
			rs = stmt.executeQuery();
			if(!rs.first()){
				return addedProduct;
				
			}
			addedProduct = new ProductTemplatePartDetail(
         		   		   new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")),
         		   		   new Part(rs.getString("partId"), rs.getString("partName"), rs.getString("partNumber"), rs.getString("externalNumber"),
         		   				   	rs.getString("vendorName"), rs.getString("unit")),
         		   		   rs.getString("quantity"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
			p.setErrorListAtIndex(1, "Product/Part combination already exists");
			return p;
		}
		return addedProduct;
	}
	
	public static void deleteProductTemplatePartDetail(ProductTemplatePartDetail p){
		PreparedStatement stmt = null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("DELETE FROM productTemplatePartDetails WHERE productId = ? AND partId = ?;");
			stmt.setString(1, p.getProductTemplateid());
			stmt.setString(2, p.getPartId());
			stmt.execute();
			
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static ProductTemplatePartDetail updateProductTemplate(ProductTemplatePartDetail old, ProductTemplatePartDetail p){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProductTemplatePartDetail result = null;
		if(p.getErrorCount() > 0){
			return null;
		}
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("UPDATE productTemplatePartDetails SET productId=?, partId=?, quantity=? WHERE productId=? AND partId=?");
			stmt.setString(1, p.getProductTemplateid());
			stmt.setString(2, p.getPartId());
			stmt.setString(3, p.getQuantity());
			stmt.setString(4, old.getProductTemplateid());
			stmt.setString(5, old.getPartId());

			stmt.execute();
			
			stmt = conn.prepareStatement(
        			"SELECT *"+ 
        			"FROM productTemplatePartDetails"+
        			"INNER JOIN productTemplates, parts"+
        			"WHERE ? = productTemplates.productId"+
        			"AND ? = parts.partId");
			stmt.setString(1, p.getProductTemplateid());
			stmt.setString(2, p.getPartId());
			rs = stmt.executeQuery();
			if(!rs.first()){
				return result;
			}
			result = new ProductTemplatePartDetail(
  		   		     new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")),
  		   		     new Part(rs.getString("partId"), rs.getString("partName"), rs.getString("partNumber"), rs.getString("externalNumber"),
  		   				   	rs.getString("vendorName"), rs.getString("unit")),
  		   		     rs.getString("quantity"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
			p.setErrorListAtIndex(1, "Product/Part combo already exists");
			return p;
		}
		return result;
	}
}
