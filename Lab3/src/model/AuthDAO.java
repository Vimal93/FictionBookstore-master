/**
 * 
 */
package model;


import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jonathan Farley, 2015
 *
 */
public class AuthDAO implements Serializable {
	
	 static final long serialVersionUID = 1L;

	private static Connection conn;

	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";


	
	public AuthDAO() {
		
	}
	
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
	
	
	public static int checkUserPass(String userName, String password) throws SQLException {
		//query database to get password for the referencing username check password passed as a parameter
		//if match return userId else return -1
		
		start();
		
		int userId = -1;
		String dbUser = "";
		String dbPass = "";
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT user_id, user_name, password FROM user WHERE user_name = ?";
		
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setString(1, userName);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get password of user " + userName);
			e.printStackTrace(); }
		
		while(result.next()) {
			userId = result.getInt("user_id");
			dbUser = result.getString("user_name");
			dbPass = result.getString("password");
		}
		
		if(password.equals(dbPass) && userName.equals(dbUser) ) { return userId; }
		else {return -1; }
		
	} //End checkUserPass(String userName, String password)
	
	public static int getAccountType(int userId) throws SQLException {
		int accountType = -1;
		
		start();
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT account_type FROM user WHERE user_id = ?";
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get account type for " + userId);
			e.printStackTrace(); }
		
		while(result.next()) { accountType = result.getInt("account_type"); }
		return accountType;
	}
	
	
	/*Nachos*/
	public static User getUserById(int userId) throws SQLException{
		//return a User Object (with all attributes set) of user with userId == userId in
		//database (since you are dealing with data in two tables maybe do a join)
		
		start();
		
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT user_name FROM user WHERE user_id = ?";
		String userName = "";
		
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get user information for " + userId);
			e.printStackTrace(); }
		
		while(result.next()) { userName = result.getString("user_name"); }
		return new User(userId, userName, -1);
	}
	
	public static Administrator getAdminById(int userId) throws SQLException{
		//return a User Object (with all attributes set) of user with userId == userId in
		//database (since you are dealing with data in two tables maybe do a join)
		
		start();
		
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT user_name FROM user WHERE user_id = ?";
		String userName = "";
		
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get user information for " + userId);
			e.printStackTrace(); }
		
		while(result.next()) { userName = result.getString("user_name"); }
		return new Administrator(userId, userName, 0);
	}
	
	public static Buyer getBuyerById(int userId) throws SQLException{
		
		start();
		
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT user_name, firstname, middlename, lastname, email FROM user NATURAL JOIN user_data WHERE user_id = ?";
		String userName = "";  String firstName = "";
		String middleName = "";  String lastName = "";  String email = "";
		
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get seller information for " + userId);
			e.printStackTrace(); }
		
		while(result.next()) {
			userName = result.getString("user_name");
			firstName = result.getString("firstname");
			middleName = result.getString("middlename");
			lastName = result.getString("lastname");
			email = result.getString("email");
		}

		return new Buyer(userId, userName, 1, firstName, middleName, lastName, email);
	}
	
	public static Seller getSellerById(int userId) throws SQLException{
		//return a User Object (with all attributes set) of user with userId == userId in
		//database (since you are dealing with data in two tables maybe do a join)
		
		start();
		
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT user_name, firstname, middlename, lastname, email, routingnum, accountnum FROM user NATURAL JOIN user_data NATURAL JOIN selleruserdata WHERE user_id = ?";
		String userName = "";
		String firstName = "";  String middleName = "";  String lastName = "";
		String email = "";  String routingNumber = "";  String accountNumber = "";
		
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to get seller information for " + userId);
			e.printStackTrace(); }
		
		while(result.next()) {
			userName = result.getString("user_name");
			firstName = result.getString("firstname");  middleName = result.getString("middlename");  lastName = result.getString("lastname");
			email = result.getString("email");  routingNumber = result.getString("routingnum");  accountNumber = result.getString("accountnum");
		}

		return new Seller(userId, userName, 2, firstName, middleName, lastName, email, routingNumber, accountNumber);
	}
	
	
	public static int enterNewUser(String userName, String password, int accountType) throws SQLException { 
		//Enter userName and password in user table
		//if entry successful return MySql generated userId
		//else return -1
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO user (user_id, user_name, password, account_type) VALUES (NULL, ?, ?, ?)";
		//String sql2 = "";

		/** Update DB */
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setInt(3, accountType);
			ps.executeUpdate();
		} catch (SQLException e) { 
			System.out.println("Insert into user table failed.");
			e.printStackTrace(); }

		/** Select id from newly inserted user */
		try { 
			//ps = conn.prepareStatement(sql2); 
			result = ps.executeQuery("SELECT LAST_INSERT_ID()");
		} catch (SQLException e) { 
			System.out.println("Unable to get ID of user " + userName);
			e.printStackTrace(); }

		while(result.next()) {
			int id = result.getInt("LAST_INSERT_ID()");
			return id;
		}
		return -1;
	}
	
	
	public static boolean enterUserData(int userId, String firstName, String middleName, String lastName, String email) {
		//Enter userId (returned from enterNewUser method), firstName, lastName in user_profile table
		//If entry successful return true else false
		
		start();
		
		PreparedStatement ps = null;
		String sql = "INSERT INTO user_data (user_id, firstname, middleName, lastname, email) VALUES (?, ?, ?, ?, ?)";
		
		/** Update DB */
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			ps.setString(2, firstName);
			ps.setString(3, middleName);
			ps.setString(4, lastName);
			ps.setString(5, email);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Update to user table failed. Could not insert user_profile information");
			e.printStackTrace(); }
		
		return false;
	}
	
	
	//Insert extra seller data into DB. If entry successful return true else false
	public static boolean enterSellerData(int userId, int routingNum, int accountNum) {
		
		start();
		
		PreparedStatement ps = null;
		String sql = "INSERT INTO selleruserdata (user_id, routingnum, accountnum) VALUES (?, ?, ?)";
		
		/** Update DB */
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, userId);
			ps.setInt(2, routingNum);
			ps.setInt(3, accountNum);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Update to user table failed. Could not insert selleruserdata information");
			e.printStackTrace(); }
		
		return false;
	}
	
	
	
	public static boolean isUserNameAvailable(String userName) throws SQLException {
		
		start();
		
		//check if username exists in user table, if not return true else return false
		ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "SELECT 1 FROM user WHERE user_name = ?";
		
		try { 
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			result = ps.executeQuery();
		} catch (SQLException e) { 
			System.out.println("Unable to check if username " + userName + " is available.");
			e.printStackTrace(); }

		if (result.next() ) { return false; } //If result isn't empty, the user exists
		else { return true; } //If result is empty, the user does not previously exist
	}
	
	
	public static void DB_Close() throws Throwable {
		try {
			if(conn!=null) { //c is a variable for a Connection you may have to rename it
				conn.close(); //c is a variable for a Connection you may have to rename it
			} }
		catch(SQLException e) { System.out.println(e);
		}
	}


}
