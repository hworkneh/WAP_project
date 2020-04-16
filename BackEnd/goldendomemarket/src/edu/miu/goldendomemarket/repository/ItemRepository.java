package edu.miu.goldendomemarket.repository;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.miu.goldendomemarket.domain.Item;
import edu.miu.goldendomemarket.util.Database;

public class ItemRepository {

	private Database db;
	
	public ItemRepository(Database db) {
		this.db=db;
	}
	
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		List<Item> item=new ArrayList<Item>();
		ResultSet rs;
		try {
			rs = db.runSql("select * from item");
			while(rs.next()) {
				Integer itemId=Integer.parseInt(rs.getString("itemId"));
				String itemName=rs.getString("itemName");
				Double itemPrice=Double.parseDouble(rs.getString("itemPrice"));
				String itemDescription=rs.getString("itemDescription");
				item.add(new Item(itemId, itemName, itemPrice, itemDescription));
			}
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Item findById(Integer itemId) {
		// TODO Auto-generated method stub
		Item item=null;
		ResultSet rs;
		try {
			rs = db.runSql("select * from item where itemId='"+itemId+"'");
			while(rs.next()) {
				Integer ritemId=Integer.parseInt(rs.getString("itemId"));
				String itemName=rs.getString("itemName");
				Double itemPrice=Double.parseDouble(rs.getString("itemPrice"));
				String itemDescription=rs.getString("itemDescription");
				item=new Item(ritemId, itemName, itemPrice, itemDescription);
			}
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Item save(Item item) {
		// TODO Auto-generated method stub
		try {
			String sql="insert into item (itemName, itemPrice, itemDescription) values (?, ?, ?)";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, item.getItemName());
			preparedStmt.setString (2, item.getItemPrice().toString());
			preparedStmt.setString (3, item.getItemDescription());
		    preparedStmt.execute();
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Item update(Item item, Integer itemId) {
		// TODO Auto-generated method stub
		try {
			String sql= "UPDATE item SET itemName= ? , itemPrice= ? , itemDescription= ? WHERE itemId = ?";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, item.getItemName());
			preparedStmt.setString (2, item.getItemPrice().toString());
			preparedStmt.setString (3, item.getItemDescription());
			preparedStmt.setString (4, itemId.toString());
			preparedStmt.execute();
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void delete(Integer itemId) {
		// TODO Auto-generated method stub
		try {
			String sql= "DELETE FROM item WHERE itemId = ?";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString(1, itemId.toString());
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
