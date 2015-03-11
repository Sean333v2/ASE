package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTemplateDB {
	
	public static ArrayList<ProductTemplate> fetchAll(){
		ArrayList<ProductTemplate> result = new ArrayList<ProductTemplate>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	stmt = conn.prepareStatement("SELECT * FROM productTemplates ORDER BY productTemplates.productId");
            rs = stmt.executeQuery();
            if(!rs.first()){
            	return result;	
            }
            result.add(new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")));
            
            while(rs.next()){
            	result.add(new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public static ProductTemplate addProductTemplate(ProductTemplate p){
		ProductTemplate addedProduct = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if(p.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			
			//Added a new row into the parts table with the data from p.
			stmt = conn.prepareStatement("INSERT INTO productTemplate (partNumber, description)"+
										"VALUES (?, ?);");
			//Sets the variables from p into the query.
			stmt.setString(1, p.getProductNum());
			stmt.setString(2, p.getDescription());
			
			if(stmt.execute())//If an error occurs throw exception.
				throw new SQLException();
			
			//The rest of the code gets back the information from the new row that was just added.
			//This will give us an ID to put into the part object.
			stmt = conn.prepareStatement("SELECT * FROM productTemplate WHERE description = ?");
			stmt.setString(1, p.getDescription());
			rs = stmt.executeQuery();
			if(!rs.first()){
				return addedProduct;
				
			}
			addedProduct = new ProductTemplate(rs.getString("productId"), rs.getString("productNumber"), rs.getString("description"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
			p.setErrorListAtIndex(1, "Description is not unique");
			return p;
		}
		return addedProduct;
	}
	
	public static void deleteProductTemplate(ProductTemplate p){
		PreparedStatement stmt = null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("DELETE FROM productTemplatePartDetails WHERE productId = ?;"+ 
										 "DELETE FROM productTemplates WHERE productId = ?;");
			stmt.setString(1, p.getProductId());
			stmt.setString(2, p.getProductId());
			stmt.execute();
			
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static ProductTemplate updateProductTemplate(ProductTemplate p){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProductTemplate result = null;
		if(p.getErrorCount() > 0){
			return null;
		}
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("UPDATE productTemplates SET partNumber=?, description=? WHERE productId=?");
			stmt.setString(1, p.getProductNum());
			stmt.setString(2, p.getDescription());
			stmt.setString(3, p.getProductId());

			stmt.execute();
			
			stmt = conn.prepareStatement("SELECT * FROM productTemplates WHERE productId = ?");
			stmt.setString(1, p.getProductId());
			rs = stmt.executeQuery();
			if(!rs.first()){
				return result;
			}
			result = new ProductTemplate(rs.getString("productId"), rs.getString("productNum"), rs.getString("description"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
			p.setErrorListAtIndex(1, "Description is not unique");
			return p;
		}
		return result;
	}
}
