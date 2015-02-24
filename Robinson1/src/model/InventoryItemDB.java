package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryItemDB {

	public static ArrayList<InventoryItem> fetchAll(){
		ArrayList<InventoryItem> result = new ArrayList<InventoryItem>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	stmt = conn.prepareStatement("SELECT * FROM inventoryItems ORDER BY inventoryItems.itemId");//This says to get all rows in inventoryItems table.
            rs = stmt.executeQuery();
            rs.first();//Just to make sure the cursor is at the first row.
            
            //Add the first row to the list of items
            result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
            		rs.getString("quantity")));
            
            //Add the rest of the rows to the list of items.
            while(rs.next()){
            	result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
                		rs.getString("quantity")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public static ArrayList<InventoryItem> fetchByLocation(String location){
		ArrayList<InventoryItem> result = new ArrayList<InventoryItem>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	Connection conn = Database.getConnection();
        	stmt = conn.prepareStatement("SELECT * FROM inventoryItems ORDER BY inventoryItems.itemId WHERE location=?");//This says to get all rows in inventoryItems table.
        	stmt.setString(1,location);
            rs = stmt.executeQuery();
            rs.first();//Just to make sure the cursor is at the first row.
            
            //Add the first row to the list of items
            result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
            		rs.getString("quantity")));
            
            //Add the rest of the rows to the list of items.
            while(rs.next()){
            	result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
                		rs.getString("quantity")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	//Returns null if something went wrong with the add
	//Check for errors before calling this!!!
	public static Part addInventoryItem(InventoryItem i){
		InventoryItem result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if(i.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			
			//Added a new row into the parts table with the data from p.
			stmt = conn.prepareStatement("INSERT INTO inventoryItems (partId, location, quantity)"+
										 "VALUES (?, ?, ?);");
			//Sets the variables from p into the query.
			stmt.setString(1, i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.setString(3, i.getQuantity());
			stmt.execute();
			
			//The rest of the code gets back the information from the new row that was just added.
			//This will give us an ID to put into the part object.
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? AND location = ?");
			stmt.setString(1, i.getPartId());
			stmt.setString(2, i.getLocation());
			rs = stmt.executeQuery();
			rs.first();
			result = new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"), rs.getString("quantity"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void deleteInventoryItem(InventoryItem i){
		PreparedStatement stmt = null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("DELETE FROM parts WHERE partId = ? and location=? LIMIT 1");
			stmt.setString(1, i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Part updatePart(InventoryItem i){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Part result = null;
		if(i.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("UPDATE inventoryItems SET partId=?, location=?, quantity=?");
			stmt.setString(1, i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.setString(3, i.getQuantity());
			stmt.execute();
			
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? and location=?");
			stmt.setString(1, i.getPartId());
			stmt.setString(2, i.getLocation());
			rs = stmt.executeQuery();
			rs.first();
			result = new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"), rs.getString("quantity"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
