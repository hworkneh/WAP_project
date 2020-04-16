package edu.miu.goldendomemarket.domain;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
public class Order {

	private Integer orderId;
	private Account account;
	private Item item;
	private String orderDate;
	private String status;
	private Integer amount;//how many of the items
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Integer orderId, Account account, Item item, String orderDate, String status, Integer amount) {
		super();
		this.orderId = orderId;
		this.account = account;
		this.item = item;
		this.orderDate = orderDate;
		this.status = status;
		this.amount = amount;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", account=" + account + ", item=" + item + ", orderDate=" + orderDate
				+ ", status=" + status + ", amount=" + amount + "]";
	}
		
}
