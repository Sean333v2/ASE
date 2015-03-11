package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            if(!rs.first())//Just to make sure the cursor is at the first row.
            	return result;
            //Add the first row to the list of items
            result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
            		rs.getString("quantity"), rs.getTimestamp("time")));
            
            //Add the rest of the rows to the list of items.
            while(rs.next()){
            	result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
                		rs.getString("quantity"), rs.getTimestamp("time")));
            }
            conn.close();
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
        	stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE location=? ORDER BY itemId");//This says to get all rows in inventoryItems table.
        	stmt.setString(1,location);
            rs = stmt.executeQuery();
            if(!rs.first())//Just to make sure the cursor is at the first row.
            	return result;
            
            //Add the first row to the list of items
            result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
            		rs.getString("quantity"), rs.getTimestamp("time")));
            
            //Add the rest of the rows to the list of items.
            while(rs.next()){
            	result.add(new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"),
                		rs.getString("quantity"), rs.getTimestamp("time")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	//Returns null if something went wrong with the add
	//Check for errors before calling this!!!
	public static InventoryItem addInventoryItem(InventoryItem i){
		InventoryItem result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if(i.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			
			//Added a new row into the parts table with the data from p.
			stmt = conn.prepareStatement("INSERT INTO inventoryItems (partId, location, quantity, time)"+
										 "VALUES (?, ?, ?, ?);");
			//Sets the variables from p into the query.
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.setString(3, i.getQuantity());
			stmt.setTimestamp(4, i.getTime());
			stmt.execute();
			
			//The rest of the code gets back the information from the new row that was just added.
			//This will give us an ID to put into the part object.
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? AND location = ?");
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			rs = stmt.executeQuery();
			rs.first();
			result = new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"), rs.getString("quantity"), rs.getTimestamp("time"));
            conn.close();

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
			stmt = conn.prepareStatement("DELETE FROM inventoryItems WHERE partId = ? AND location=? LIMIT 1");
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.execute();
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static InventoryItem updateInventoryItem(InventoryItem i){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		InventoryItem result = null;
		if(i.getErrorCount() > 0)
			return null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("UPDATE inventoryItems SET partId=?, location=?, quantity=? time=? WHERE itemId=?");
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			stmt.setString(3, i.getQuantity());
			stmt.setString(4, ""+i.getItemId());
			stmt.setTimestamp(5, i.getTime());
			stmt.execute();
			
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? and location=?");
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			rs = stmt.executeQuery();
			rs.first();
			result = new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"), rs.getString("quantity"), rs.getTimestamp("time"));
            conn.close();

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean findInventoryItemByPartIdAndLocation(int partId, String location){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? and location=?");
			stmt.setString(1, ""+partId);
			stmt.setString(2, location);
			rs = stmt.executeQuery();
            conn.close();
			if(rs.first())
				return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static InventoryItem getInventoryItemByPartIdAndLocation(int partId, String location){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		InventoryItem result = null;
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM inventoryItems WHERE partId = ? and location=?");
			stmt.setString(1, ""+partId);
			stmt.setString(2, location);
			rs = stmt.executeQuery();
            conn.close();
			rs.first();
			result = new InventoryItem(rs.getInt("itemId"), rs.getInt("partId"), rs.getString("location"), rs.getString("quantity"), rs.getTimestamp("time"));
            conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static Timestamp getTimestamp(InventoryItem i){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Timestamp time = null;
		
		try{
			Connection conn = Database.getConnection();
			stmt = conn.prepareStatement("SELECT inventoryItems.time FROM inventoryItems WHERE partId=? AND location=?");
			stmt.setString(1, ""+i.getPartId());
			stmt.setString(2, i.getLocation());
			rs = stmt.executeQuery();
			conn.close();
			if(!rs.first())
				return time;
			time = rs.getTimestamp("time");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
}
