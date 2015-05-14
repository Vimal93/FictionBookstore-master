package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Administrator extends User{
	
	private static final long serialVersionUID = 1L;

	private static Connection conn;
	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	
	static ResultSet result = null;
	static PreparedStatement ps = null;
	static String sql=null;	

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
	
	public Administrator(){
		super();
	}
	
	public Administrator(int userId, String userName, int accountType) { 
		this.userId = userId;
		this.userName = userName;
		this.accountType = accountType;
	}
	
	public static ArrayList<User> viewUserAccounts(){
		int user_id = -1;
		String username =null;
		String first_name =null;
		String last_name =null;
		int account_type = -1;
		ArrayList<User> user =new ArrayList<User>();
		
		try{
    		if((conn==null||conn.isClosed())==true) start();
    		
    		sql = "SELECT user_id, user_name, account_type, firstname, lastname FROM se_project.user NATURAL JOIN se_project.user_data;";
    		//sql = "SELECT user_id, user_name, account_type, firstname, lastname FROM se_project.user, se_project.user_data;";
    		ps=conn.prepareStatement(sql);
					
			result=ps.executeQuery();	
			
			while(result.next()){	
				user_id = result.getInt("user_id");
				username = result.getString("user_name");
				first_name =result.getString("firstname");
				last_name =result.getString("lastname");
				account_type = result.getInt("account_type");
				
				/*if(account_type == 0){
					ArrayList<Administrator> user =new ArrayList<Administrator>();
				}
				else if(account_type == 1){
					ArrayList<Buyer> user =new ArrayList<Buyer>();					
				}
				else if(account_type == 2){
					ArrayList<Seller> user =new ArrayList<Seller>();					
				}
				else{
					System.out.println("user error.");
					ArrayList<Seller> user =new ArrayList<Seller>();
				}*/
				user.add(new User(user_id, username, first_name, last_name, account_type));
			}
			conn.close();
		}
	    catch(Exception e){
		System.out.println("Unable to retrieve users from database");
		e.printStackTrace();
	    }
		return user;
	}
	
	public void deleteUserAccounts(){
		
	}
	
	public void cancelOrders(){
		
	}
	
	public void validateSellerRegistration(){
		
	}

}
