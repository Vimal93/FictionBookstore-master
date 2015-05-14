package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteUserDAO {
	
	private static Connection conn;
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	static ResultSet result = null,result1=null;
	static PreparedStatement ps1 = null;
	static PreparedStatement ps2 = null;
	static PreparedStatement ps3 = null;
	static PreparedStatement ps4 = null;
	static PreparedStatement ps5 = null;
	static String sql1=null;
	static String sql2=null;
	static String sql3=null;
	static String sql4=null;
	static String sql5=null;

	
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
		//if (conn != null) { System.out.println("Connected to the database"); }
	}
	
	
	public static boolean deleteBuyer(int user_id) {
		start();
		//System.out.println("deleteBuyer- user_id: "+ user_id);
		
		sql1 = "DELETE FROM se_project.user WHERE user_id = ?";
		sql2 = "DELETE FROM se_project.user_data WHERE user_id = ?";
		sql3 = "DELETE FROM se_project.wishlist WHERE user_id = ?";
		sql4 = "DELETE FROM se_project.reviews WHERE user_id = ?";
		sql5 = "DELETE FROM se_project.order WHERE buyer_id = ?";

		try{
			
			ps5=conn.prepareStatement(sql5);
			ps5.setInt(1, user_id);
			ps5.executeUpdate();
			
			ps4=conn.prepareStatement(sql4);
			ps4.setInt(1, user_id);
			ps4.executeUpdate();
			
			ps3=conn.prepareStatement(sql3);
			ps3.setInt(1, user_id);
			ps3.executeUpdate();
			
			ps2=conn.prepareStatement(sql2);
			ps2.setInt(1, user_id);
			ps2.executeUpdate();
			
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, user_id);
			ps1.executeUpdate();
			
			return true;
		} catch (SQLException e) { 
			System.out.println("Deleting buyer user has failed.");
			e.printStackTrace();
		}
		
		return false;
	} //End deleteBuyer(int user_id)
	
	
	public static boolean deleteSeller(int user_id) {
		start();
		//System.out.println("deleteSeller- user_id: "+ user_id);
		
		sql1 = "DELETE FROM se_project.user WHERE user_id = ?";
		sql2 = "DELETE FROM se_project.user_data WHERE user_id = ?";
		sql3 = "DELETE FROM se_project.selleruserdata WHERE user_id = ?";
		sql4 = "DELETE FROM se_project.sellerlist WHERE user_id = ?";

		try{
			ps4=conn.prepareStatement(sql4);
			ps4.setInt(1, user_id);
			ps4.executeUpdate();
			
			ps3=conn.prepareStatement(sql3);
			ps3.setInt(1, user_id);
			ps3.executeUpdate();
			
			ps2=conn.prepareStatement(sql2);
			ps2.setInt(1, user_id);
			ps2.executeUpdate();
			
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, user_id);
			ps1.executeUpdate();
			
			return true;
		} catch (SQLException e) { 
			System.out.println("Deleting seller user has failed.");
			e.printStackTrace();
		}
		return false;
	} //End deleteSeller(int user_id)

}
