package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WishlistDAO {
	
	static final long serialVersionUID = 1L;
	
	private static Connection conn;

	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	static ResultSet result = null,result1=null;
	static PreparedStatement ps = null;
	static String sql=null;
	
	
	static int productId=0,isbn=0,sellerId=0;
	static double unitPrice=0;
	static String title="";
	static String desc="";
	static String image="";
	static String author="";
	static String genre="";
	static String sellername="";
	

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
	}// End start()
	
	public static boolean removeWishListItem(int product_id, int user_id)
	{
		start();
		
		String sql = "DELETE FROM se_project.wishlist WHERE wishlist.product_id = ? AND wishlist.user_id = ?";

		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.setInt(2, user_id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Deletion from wishlist table failed. Could not delete product from wishlist.");
			e.printStackTrace();
		}
		return false;
		
	} //End removeWishListItem(int product_id, int user_id)
	
	
	public static boolean addWishlistItem(int product_id, int user_id)
	{
		start();
		String sql = "INSERT INTO se_project.wishlist (product_id,user_id) VALUES (?,?)";
		
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.setInt(2, user_id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Insertion into wishlist table failed. Could not delete product from sellerlist");
			e.printStackTrace();
		}
		return false;
		
	} //End addWishlistItem(int product_id, int user_id)
	
	
	public static ArrayList<Product> selectWishlist(int user_id)
	{
		ArrayList<Product> products=new ArrayList<Product>();
		
		sql="SELECT * FROM se_project.wishlist NATURAL JOIN se_project.product WHERE user_id = ?";
		
		try{
			if((conn==null||conn.isClosed())==true) start();
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			result=ps.executeQuery();
			
			sql="SELECT user_name FROM se_project.user WHERE user_id= ?";

			
			while(result.next())
			{
				productId=result.getInt("product_id");
				title=result.getString("name");
				desc=result.getString("description");
				image=result.getString("image");
				isbn=result.getInt("isbn");
				author=result.getString("author");
				genre=result.getString("genre");
				products.add(new Product(title,unitPrice,productId,sellerId,sellername,desc,image,isbn,author,genre));
			}
			conn.close();
	        } catch(Exception e) {
	        	System.out.println("Unable to search wishlist DB table.");
	        	e.printStackTrace();
		    }    	
	    	 
		    return products;	
		
	} //End addWishlistItem(int product_id, int user_id)



	public WishlistDAO() {
		// TODO Auto-generated constructor stub
	}

}
