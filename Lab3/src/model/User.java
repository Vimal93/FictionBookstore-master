/**
 * 
 */
package model;

import java.io.Serializable;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int userId;
	protected String userName;
	protected int accountType;
	
	protected String first_name;
	protected String last_name;

	/**
	 * 
	 */
	public User() { 
		this.userId = -1;
		this.userName = "null user";
		this.accountType = -1;
	}
	
	public User(int userId, String userName, int accountType) { 
		this.userId = userId;
		this.userName = userName;
		this.accountType = accountType;
	}
	
	public User(int userId, String userName, String first_name, String last_name, int accountType) { 
		this.userId = userId;
		this.userName = userName;
		this.first_name = first_name;
		this.last_name = last_name;
		this.accountType = accountType;
	}
	
	/**
	 * Getters and Setters:
	 */
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the accountType
	 */
	public int getaccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setaccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getFirstName() {
		return first_name;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setFirstName(String first_name) {
		this.first_name= first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}


}
