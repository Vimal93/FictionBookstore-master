package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Seller extends User{
	private static Connection conn;
	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	static String username =null;
	static String first_name =null;
	static String last_name =null;
	static int account_type = 0;
	
	static ResultSet result = null;
	static PreparedStatement ps = null;
	static String sql=null;	

	//return new Seller(userId, userName, 2, firstName, middleName, lastName, email, routingNumber, accountNumber);
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String email;
	protected int routingNum; 
	protected int accountNum;
	
    public static void start() {
		
		conn = null;
		/** Load DB Driver */
		try{ Class.forName(DB_DRIVER); } catch (ClassNotFoundException ex) {
			System.out.println("An error occurred. Driver not loaded.");
			Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		/** Connect to DB */
		try { conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW); } catch (SQLException e) {
			System.out.println("An error occurred. Maybe user/password is invalid");
			e.printStackTrace(); }
		if (conn != null) { System.out.println("Connected to the database"); }
	}
	
	public Seller() {
		this.userId= -1;
		this.userName = "";
		this.accountType = -1;
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.email = "";
		this.routingNum= -1;
		this.accountNum= -1;
	}
	
	public Seller(int userId, String userName, int accountType, String firstName, String middleName, String lastName, String email, String routingNumber, String accountNumber)
	{
		this.userId=userId;
		this.userName = userName;
		this.accountType = accountType;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.routingNum=Integer.parseInt(routingNumber);
		this.accountNum=Integer.parseInt(accountNumber);
	}

	public static boolean update_seller(int user_id, String first_name, String middle_name, String last_name, String email, String address, int phone_num, int routing_num, int bank_account_num){
		start();
		//ResultSet result = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE (user_data NATURAL JOIN selleruserdata) SET firstname =?, middlename=?, lastname=?, email=?, address=?, phonenumber=?, routingnum=?, accountnum=? WHERE user_id = ?;";
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setString(1,first_name);
			ps.setString(2,middle_name);
			ps.setString(3,last_name);
			ps.setString(4,email);
			ps.setString(5,address);
			ps.setInt(6, phone_num);
			ps.setInt(7,routing_num);
			ps.setInt(8,bank_account_num);
			ps.setInt(9,user_id);
			
			/*result = */ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("SQL Error! Check statement.");
			e.printStackTrace(); 
			return false;
		}
		
	}
	
	public void addNewProduct(Product p)
	{
		/*Create a database connection and add the new product to inventory*/
	}
	
	public void deleteProduct(String name)
	{
		/*Create a database connection and delete the product*/
	}
	
	public void deleteProduct(int productId)
	{
		
	}
	
	public void updateProduct(Product p)
	{
		
	}
	
	
	/**
	 * Getters and Setters:
	 */
	
	
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the email
	 */
	public String getemail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setemail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the routingNum
	 */
	public int getroutingNum() {
		return routingNum;
	}
	/**
	 * @param email the email to set
	 */
	public void setroutingNum(int routingNum) {
		this.routingNum = routingNum;
	}
	
	/**
	 * @return the email
	 */
	public int getAccountNum() {
		return accountNum;
	}
	
	public int getUserId() {
		return userId;
	}
	/**
	 * @param email the email to set
	 */
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
	
}