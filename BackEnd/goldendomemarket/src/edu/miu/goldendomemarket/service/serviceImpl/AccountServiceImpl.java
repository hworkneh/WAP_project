package edu.miu.goldendomemarket.service.serviceImpl;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.util.List;

import edu.miu.goldendomemarket.domain.Account;
import edu.miu.goldendomemarket.repository.AccountRepository;
import edu.miu.goldendomemarket.service.AccountService;
import edu.miu.goldendomemarket.util.Database;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(Database db) {
		// TODO Auto-generated constructor stub
		accountRepository=new AccountRepository(db);
	}
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account findById(Integer accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findById(accountId);
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public Account update(Account account, Integer accountId) {
		// TODO Auto-generated method stub
		return accountRepository.update(account, accountId);
	}

	@Override
	public void delete(Integer accountId) {
		// TODO Auto-generated method stub
		accountRepository.delete(accountId);
		
	}

	@Override
	public Account findUser(String email, String password) {
		// TODO Auto-generated method stub
		return accountRepository.findUser(email,password);
	}

}
