package edu.miu.goldendomemarket.service.serviceImpl;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.util.List;

import edu.miu.goldendomemarket.domain.Item;
import edu.miu.goldendomemarket.repository.ItemRepository;
import edu.miu.goldendomemarket.service.ItemService;
import edu.miu.goldendomemarket.util.Database;

public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	
	public ItemServiceImpl(Database db) {
		// TODO Auto-generated constructor stub
		this.itemRepository=new ItemRepository(db);
	}
	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public Item findById(Integer itemId) {
		// TODO Auto-generated method stub
		return itemRepository.findById(itemId);
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public Item update(Item item, Integer itemId) {
		// TODO Auto-generated method stub
		return itemRepository.update(item, itemId);
	}

	@Override
	public void delete(Integer itemId) {
		// TODO Auto-generated method stub
		itemRepository.delete(itemId);
	}

}
