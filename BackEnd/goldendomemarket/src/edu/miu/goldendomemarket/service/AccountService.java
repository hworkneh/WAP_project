package edu.miu.goldendomemarket.service;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.util.List;

import edu.miu.goldendomemarket.domain.Account;

public interface AccountService {

	public List<Account> findAll();
	public Account findById(Integer accountId);
	public Account save(Account account);
	public Account update(Account account, Integer accountId);
	public void delete(Integer accountId);
	public Account findUser(String email, String password);
}
