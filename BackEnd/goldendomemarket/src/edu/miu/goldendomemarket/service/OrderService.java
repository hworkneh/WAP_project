package edu.miu.goldendomemarket.service;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.util.List;

import edu.miu.goldendomemarket.domain.Order;

public interface OrderService {

	public List<Order> findAll();
	public Order findById(Integer orderId);
	public Order save(Order account);
	public Order update(Order account, Integer orderId);
	public void delete(Integer orderId);
	public List<Order> findByUser(Integer accountId);
}
