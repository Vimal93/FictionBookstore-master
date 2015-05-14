package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
	
	 static final long serialVersionUID = 1L;

		private static Connection conn;

		public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
		public static final String DB_USER = "root";
		public static final String DB_PW = "root";
		public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
		
		static ResultSet result = null,result1=null;
		static PreparedStatement ps1 = null;
		static PreparedStatement ps2 = null;
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

	public static boolean deleteSellerProduct(int product_id, int user_id)
	{
		start();
		String sql = "DELETE FROM se_project.sellerlist WHERE sellerlist.product_id = ? AND sellerlist.user_id = ?";
		//product_id
		//user_id
		try{
			ps1=conn.prepareStatement(sql);
			ps1.setInt(1, product_id);
			ps1.setInt(2, user_id);
			ps1.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Deletion from seller list table failed. Could not delete product from sellerlist");
			e.printStackTrace();
		}
		return false;
	} //End of deleteSellerProduct method

	
	
	public static boolean addNewProduct(Product p, int userId)
	{
		start();
		int x=0;
		
		try{
		String sql="SELECT isbn,product_id,name FROM se_project.product";
		ps1=conn.prepareStatement(sql);
		result=ps1.executeQuery();
		while(result.next())
		{
			if(p.getIsbn()==result.getInt("isbn"))
			{
				p.setItemId(result.getInt("product_id"));
				p.setName(result.getString("name"));
				addToSellerList(p,userId);
				x=1;
			}
		}
		if(x==0)
			{
				sql = "INSERT INTO se_project.product (`name`,`description`,`isbn`,`author`,`genre`) VALUES (?,?, ?, ?, ?)";
				
				//GET SELLER ID
				//SELECT user_id FROM se_project.sellerlist WHERE product_id=? AND unitprice=?
				
				
				ps1=conn.prepareStatement(sql);
				ps1.setString(1, p.getName());
				ps1.setString(2, p.getDescription());
				ps1.setInt(3, p.getIsbn());
				ps1.setString(4,p.getAuthor());
				ps1.setString(5, p.getGenre());
				ps1.executeUpdate();
				
				sql="SELECT LAST_INSERT_ID() FROM se_project.product";
				ps1=conn.prepareStatement(sql);
				result=ps1.executeQuery();
				result.next();
				int i=result.getInt("LAST_INSERT_ID()");
				System.out.println(i);
				
				p.setItemId(i);
				
				addToSellerList(p,userId);
				
			}		

		} catch (SQLException e) { 
			System.out.println("Update to product table failed. Could not insert product information");
			e.printStackTrace();
			return false;
			}
		
		return true;
		
	}
	
	public static void addToSellerList(Product p, int userId)
	{
		try
		{
		sql="INSERT INTO se_project.sellerlist (product_id,user_id,unitprice) VALUES (?,?,?)";
		ps1=conn.prepareStatement(sql);
		ps1.setInt(1, p.getItemId());
		ps1.setInt(2, userId);
		ps1.setDouble(3, p.getUnitPrice());
		ps1.executeUpdate();
		}
		catch (SQLException e) { 
			System.out.println("Update to product table failed. Could not insert product information");
			e.printStackTrace();
		}
		
	}
	
	public static boolean editPrice(int user_id, int isbn, double price)
	{
		start();
		ResultSet result = null;
		PreparedStatement ps = null;
		int product_id = -1;
		
		String sql = "SELECT product_id FROM sellerlist NATURAL JOIN product WHERE user_id = ? AND ISBN = ?";
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1,user_id);
			ps.setInt(2,isbn);
			result = ps.executeQuery();
			
			while(result.next())
			{
				product_id=result.getInt("product_id");
			}
			
		} catch (SQLException e) { 
			System.out.println("Update to get product_id. Could not change product price");
			e.printStackTrace(); 
			return false;
			}
		
		
		
		//String sql = "UPDATE se_project.sellerlist set unitprice= ? where product_id = (SELECT product_id FROM se_project.product WHERE isbn= ?) and user_id=5";
		//SELECT product_id FROM sellerlist NATURAL JOIN product WHERE user_id = ? AND ISBN = ?
		
		sql = "INSERT INTO se_project.sellerlist (product_id, user_id, unitprice) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE unitprice=VALUES(unitprice)";
		
		/** Update DB */
		try { 
			ps = conn.prepareStatement(sql); 
			ps.setInt(1,product_id);
			ps.setInt(2,user_id);
			ps.setDouble(3,price);
			System.out.println("QUERY 2: " + ps);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) { 
			System.out.println("Update to product table failed. Could not change product price");
			e.printStackTrace(); 
			return false;
			}
		
	}
	
	public static ArrayList<Product> viewProducts(int userId, int accountType){
		start();
		ResultSet result = null;
		PreparedStatement ps = null;
		int product_id = -1;
		
		ArrayList<Product> products = new ArrayList<Product>();
		String title;
		int isbn;
		double unitprice;
		String author;
		int seller_id;
		
		try{
    		if((conn==null||conn.isClosed())==true) start();

    		
    		if (accountType==0){
    			sql = "SELECT name, product_id, isbn,unitprice,author,user_id FROM se_project.product natural join se_project.sellerlist;";
    			ps=conn.prepareStatement(sql);
    		}
    		if (accountType==2){
    			sql = "SELECT name, product_id, isbn,unitprice,author,user_id FROM se_project.product natural join se_project.sellerlist where user_id = ?;";
    			ps=conn.prepareStatement(sql);
    			ps.setInt(1, userId);
    		}	
			result=ps.executeQuery();
			
			
			while(result.next()){	
				title = result.getString("name");
				product_id = result.getInt("product_id");
				isbn = result.getInt("isbn");
				author=result.getString("author");
				unitprice=result.getDouble("unitprice");
				seller_id=result.getInt("user_id");
				products.add(new Product(title, product_id,isbn,author,unitprice,seller_id));
			}
			conn.close();
		}
	    catch(Exception e){
		System.out.println("Unable to retrieve users from database");
		e.printStackTrace();
	    }
		return products;
	}
	

}
