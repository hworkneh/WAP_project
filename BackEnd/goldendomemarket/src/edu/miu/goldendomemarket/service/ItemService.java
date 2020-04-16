package edu.miu.goldendomemarket.service;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.util.List;

import edu.miu.goldendomemarket.domain.Item;

public interface ItemService {
	
	public List<Item> findAll();
	public Item findById(Integer itemId);
	public Item save(Item item);
	public Item update(Item item, Integer itemId);
	public void delete(Integer itemId);
}
