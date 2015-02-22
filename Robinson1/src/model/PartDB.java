package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class PartDB {

	public static ArrayList<Part> fetchAll(){
		ArrayList<Part> result = new ArrayList<Part>();
		PreparedStatement sRS = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	sRS = conn.prepareStatement("SELECT * FROM parts ORDER BY parts.partId");
            rs = sRS.executeQuery();
            rs.first();
            result.add(new Part(rs.getInt("partId"), rs.getString("partName"), rs.getString("partNumber"),
            		rs.getString("externalNumber"), rs.getString("vendorName"), rs.getString("unit")));
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
	public static Part addPart(Part p){
		return p;
	}
	
	public static void deletePart(Part p){
		
	}
	
	public static Part updatePart(Part p){
		return p;
	}
}
