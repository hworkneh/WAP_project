package edu.miu.goldendomemarket.domain;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
public class Account {

	private Integer accountId;
	private String fullName;
	private String email;
	private String password;
	private String accountType;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Integer accountId,String fullName,String email, String password, String accountType) {
		super();
		this.accountId = accountId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.accountType = accountType;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", fullName=" + fullName + ", email=" + email + ", password="
				+ password + ", accountType=" + accountType + "]";
	}
	
	
}
