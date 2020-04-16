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

import edu.miu.goldendomemarket.domain.Account;
import edu.miu.goldendomemarket.domain.Item;
import edu.miu.goldendomemarket.domain.Order;
import edu.miu.goldendomemarket.service.serviceImpl.AccountServiceImpl;
import edu.miu.goldendomemarket.service.serviceImpl.ItemServiceImpl;
import edu.miu.goldendomemarket.util.Database;

public class OrderRepository {

	private Database db;
	private AccountServiceImpl account;
	private ItemServiceImpl item;
	
	public OrderRepository(Database db) {
		this.db=db;
		account=new AccountServiceImpl(db);
		item=new ItemServiceImpl(db);
		
	}
	

	public List<Order> findAll() {
		// TODO Auto-generated method stub
		List<Order> order=new ArrayList<Order>();
		ResultSet rs;
		try {
			rs = db.runSql("SELECT * FROM goldendomemarket.`order`;");
			while(rs.next()) {
				Integer orderId=Integer.parseInt(rs.getString("orderId"));
				Integer accountId=Integer.parseInt(rs.getString("accountId"));
				Integer itemId=Integer.parseInt(rs.getString("itemId"));
				String orderDate=rs.getString("orderDate");
				String status=rs.getString("status");
				Integer amount=Integer.parseInt(rs.getString("amount"));
				Account a=account.findById(accountId);
				Item i=item.findById(itemId);
				order.add(new Order(orderId, a, i, orderDate, status, amount));
				System.out.println(order.toString());
			}
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		//return null;
	}

	public Order findById(Integer orderId) {
		// TODO Auto-generated method stub
		Order order=null;
		ResultSet rs;
		try {
			rs = db.runSql("SELECT * FROM goldendomemarket.`order` WHERE `orderId`="+orderId+";");
			while(rs.next()) {
				Integer rorderId=Integer.parseInt(rs.getString("orderId"));
				Integer accountId=Integer.parseInt(rs.getString("accountId"));
				Integer itemId=Integer.parseInt(rs.getString("itemId"));
				String orderDate=rs.getString("orderDate");
				String status=rs.getString("status");
				Integer amount=Integer.parseInt(rs.getString("amount"));
				Account a=account.findById(accountId);
				Item i=item.findById(itemId);
				order=new Order(rorderId, a, i, orderDate, status, amount);
			}
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Order save(Order order) {
		// TODO Auto-generated method stub
		try {
			String sql="INSERT INTO `goldendomemarket`.`order` (`accountId`, `itemId`, `orderDate`, `status`, `amount`) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, order.getAccount().getAccountId().toString());
			preparedStmt.setString (2, order.getItem().getItemId().toString());
			preparedStmt.setString (3, order.getOrderDate());
			preparedStmt.setString (4, order.getStatus());
			preparedStmt.setString (5, order.getAmount().toString());
		    preparedStmt.execute();
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Order update(Order order, Integer orderId) {
		// TODO Auto-generated method stub
		try {
			String sql="UPDATE `goldendomemarket`.`order` SET `accountId`= ? , `itemId`= ? , `orderDate`= ? , `status`= ? , `amount`= ? WHERE `orderId` = ? ;";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString (1, order.getAccount().getAccountId().toString());
			preparedStmt.setString (2, order.getItem().getItemId().toString());
			preparedStmt.setString (3, order.getOrderDate());
			preparedStmt.setString (4, order.getStatus());
			preparedStmt.setString (5, order.getAmount().toString());
			preparedStmt.setString (6, orderId.toString());
		    preparedStmt.execute();
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void delete(Integer orderId) {
		// TODO Auto-generated method stub
		try {
			String sql= "DELETE FROM `goldendomemarket`.`order` WHERE `orderId` = ?";
			PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
			preparedStmt.setString(1, orderId.toString());
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Order> findByUser(Integer accountId) {
		List<Order> order=new ArrayList<Order>();
		ResultSet rs;
		try {
			rs = db.runSql("SELECT * FROM goldendomemarket.`order` WHERE accountId="+accountId+";");
			while(rs.next()) {
				Integer orderId=Integer.parseInt(rs.getString("orderId"));
				Integer raccountId=Integer.parseInt(rs.getString("accountId"));
				Integer itemId=Integer.parseInt(rs.getString("itemId"));
				String orderDate=rs.getString("orderDate");
				String status=rs.getString("status");
				Integer amount=Integer.parseInt(rs.getString("amount"));
				Account a=account.findById(raccountId);
				Item i=item.findById(itemId);
				order.add(new Order(orderId, a, i, orderDate, status, amount));
			}
			return order;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
