package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchDAO {
	
	private static Connection conn;

	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	static int productId=0,isbn=0,sellerId=0;
	static double unitPrice=0;
	static String title="";
	static String desc="";
	static String image="";
	static String author="";
	static String genre="";
	static String sellername="";
	
	static ResultSet result = null,result1=null;
	static PreparedStatement ps = null,ps1=null;
	static String sql=null,sql1=null;
	
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
	
    public static ArrayList<Product> search(String s, String cat)
    {
    	ArrayList<Product> products=new ArrayList<Product>();
    	
    	try{
		if((conn==null||conn.isClosed())==true) start();
		
		if(cat.equals("1"))
		{
			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND name LIKE ?";			
		}
		else if(cat.equals("2"))
		{
			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND isbn LIKE ?";				
		}
		else if(cat.equals("3"))
		{
			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND author LIKE ?";
		}
		else if(cat.equals("4"))
		{
			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND genre LIKE ?";
		}
		
		ps=conn.prepareStatement(sql);
		ps.setString(1, "%"+s+"%");
		
		result=ps.executeQuery();
		
		sql1="SELECT user_name FROM se_project.user WHERE user_id= ?";
		
		while(result.next())
		{
			productId=result.getInt("product_id");
			title=result.getString("name");
			desc=result.getString("description");
			image=result.getString("image");
			isbn=result.getInt("isbn");
			author=result.getString("author");
			genre=result.getString("genre");
			unitPrice=result.getDouble("unitprice");
			sellerId=result.getInt("user_id");
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1,sellerId);
			result1=ps1.executeQuery();
			if(result1.next()) sellername=result1.getString("user_name");
			products.add(new Product(title,unitPrice,productId,sellerId,sellername,desc,image,isbn,author,genre));
		}
		
		conn.close();

        }
	    catch(Exception e)
	    {
		System.out.println("Unable to search products in database");
		e.printStackTrace();
	    }    	
    	 
	    return products;	    
	}
    
    public static ArrayList<Product> advancedSearch(String s, String cat)
    {
    	ArrayList<Product> products=new ArrayList<Product>();
    	List<String> strings = new ArrayList<String>();
    	int index = 0;
    	while (index < s.length()) {
    	    if(index+3<=s.length()) strings.add(s.substring(index, Math.min(index + 3,s.length())));
    	    index += 1;
    	}
    	    	
    	try
    	{
    		if((conn==null||conn.isClosed())==true) start();
    		
    		if(cat.equals("1"))
    		{
    			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND name REGEXP '";
    			for(String sub:strings)
    			{
    				sql=sql.concat("("+sub+")+|");
    			}
    			sql=sql.concat("'");
    			sql=sql.replace("|'", "'");
    		}
    		else if(cat.equals("2"))
    		{
    			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND isbn REGEXP '";
    			for(String sub:strings)
    			{
    				sql=sql.concat("("+sub+")+|");
    			}
    			sql=sql.concat("'");
    			sql=sql.replace("|'", "'");    			   			
    		}
    		else if(cat.equals("3"))
    		{
    			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND author REGEXP '";
    			for(String sub:strings)
    			{
    				sql=sql.concat("("+sub+")+|");
    			}
    			sql=sql.concat("'");
    			sql=sql.replace("|'", "'");    			
    		}
    		else if(cat.equals("4"))
    		{
    			sql="SELECT * FROM se_project.product INNER JOIN sellerlist ON product.product_id=sellerlist.product_id AND genre REGEXP '";
    			for(String sub:strings)
    			{
    				sql=sql.concat("("+sub+")+|");
    			}
    			sql=sql.concat("'");
    			sql=sql.replace("|'", "'");    			
    		}
    		
    		ps=conn.prepareStatement(sql);
    		
    		result=ps.executeQuery(); 
    		
    		while(result.next())
    		{
    			productId=result.getInt("product_id");
    			title=result.getString("name");
    			desc=result.getString("description");
    			image=result.getString("image");
    			isbn=result.getInt("isbn");
    			author=result.getString("author");
    			genre=result.getString("genre");
    			unitPrice=result.getDouble("unitprice");
    			sellerId=result.getInt("user_id");
    			ps1=conn.prepareStatement(sql1);
    			ps1.setInt(1,sellerId);
    			result1=ps1.executeQuery();
    			if(result1.next()) sellername=result1.getString("user_name");
    			products.add(new Product(title,unitPrice,productId,sellerId,sellername,desc,image,isbn,author,genre));
    		}
    		
    		conn.close();
    		
    	}
    	catch(Exception e)
    	{
    	System.out.println("Unable to search products in database");
    	e.printStackTrace();
    	}
    	
    	return products;
    }

}