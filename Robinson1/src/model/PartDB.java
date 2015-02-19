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
            rs.first();//Just to make sure the cursor is at the first row.
            
            //Add the first row to the list of parts
            result.add(new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            		rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit")));
            
            //Add the rest of the rows to the list of parts.
            while(rs.next()){
            	result.add(new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
                		rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit")));
            }
            
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
			stmt = conn.prepareStatement("INSERT INTO parts (partName, partNumber, externalNumber, vendorName, unit)"+
										"VALUES (?, ?, ?, ?, ?);");
			stmt.setString(1, p.getPartName());
			stmt.setString(2, p.getPartNum());
			stmt.setString(3, p.getExternalNum());
			stmt.setString(4, p.getVendorName());
			stmt.setString(5, p.getUnit());
			if(stmt.execute())
				throw new SQLException();
			
			stmt = conn.prepareStatement("SELECT * FROM parts WHERE partNumber = ?");
			stmt.setString(1, p.getPartNum());
			rs = stmt.executeQuery();
			rs.first();
			addedPart = new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            					 rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return addedPart;
	}
	
	public static void deletePart(Part p){
		// TODO finish deletePart
	}
	
	public static Part updatePart(Part p){
		// TODO finish updatePart
		return p;
	}
}
