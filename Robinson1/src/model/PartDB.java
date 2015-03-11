package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class PartDB {

	public static ArrayList<Part> fetchAll(){
		ArrayList<Part> result = new ArrayList<Part>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	stmt = conn.prepareStatement("SELECT * FROM parts ORDER BY parts.partId");//This says to get all rows in parts table.
            rs = stmt.executeQuery();
            if(!rs.first()){//Just to make sure the cursor is at the first row.
            	//System.out.println("First return");
            	return result;
            	
            }
            //Add the first row to the list of parts
            result.add(new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            		rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit")));
            
            //Add the rest of the rows to the list of parts.
            while(rs.next()){
            	result.add(new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
                		rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	//Returns null if something went wrong with the add
	//Check for errors before calling this!!!
	public static Part addPart(Part p){
		Part addedPart = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if(p.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			
			//Added a new row into the parts table with the data from p.
			stmt = conn.prepareStatement("INSERT INTO parts (partName, partNumber, externalNumber, vendorName, unit)"+
										"VALUES (?, ?, ?, ?, ?);");
			//Sets the variables from p into the query.
			stmt.setString(1, p.getPartName());
			stmt.setString(2, p.getPartNum());
			stmt.setString(3, p.getExternalNum());
			stmt.setString(4, p.getVendorName());
			stmt.setString(5, p.getUnit());
			
			if(stmt.execute())//If an error occurs throw exception.
				throw new SQLException();
			
			//The rest of the code gets back the information from the new row that was just added.
			//This will give us an ID to put into the part object.
			stmt = conn.prepareStatement("SELECT * FROM parts WHERE partNumber = ?");
			stmt.setString(1, p.getPartNum());
			rs = stmt.executeQuery();
			if(!rs.first()){
				//System.out.println("Second return ");
				return addedPart;
				
			}
			addedPart = new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            					 rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return addedPart;
	}
	
	public static void deletePart(Part p){
		PreparedStatement stmt = null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("DELETE FROM parts WHERE partId = ? LIMIT 1");
			stmt.setString(1, p.getPersonalId());
			stmt.execute();
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Part updatePart(Part p){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Part result = null;
		if(p.getErrorCount() > 0){
			return null;
		}
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("UPDATE parts SET partName=?, partNumber=?, externalNumber=?, vendorName=?, unit=? WHERE partId=?");
			stmt.setString(1, p.getPartName());
			stmt.setString(2, p.getPartNum());
			stmt.setString(3, p.getExternalNum());
			stmt.setString(4, p.getVendorName());
			stmt.setString(5, p.getUnit());
			stmt.setString(6, p.getPersonalId());
			stmt.execute();
			
			stmt = conn.prepareStatement("SELECT * FROM parts WHERE partId = ?");
			stmt.setString(1, p.getPersonalId());
			rs = stmt.executeQuery();
			if(!rs.first()){
				return result;
			}
			result = new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            					 rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
