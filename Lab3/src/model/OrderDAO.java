package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
	
	private static Connection conn;
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	static ResultSet result = null,result1=null;
	static PreparedStatement ps1 = null;
	static PreparedStatement ps2 = null;
	static String sql=null;
	
	static int order_id=0;
	static int buyerId=0;
	static int quantity=0;
	static int status=0;
	static double tax=0.0;
	static double totalPrice=0.0;
	static Date date=null;
	static ArrayList<Product> products=new ArrayList<Product>();
	static int productId=0,isbn=0,sellerId=0;
	static double unitPrice=0;
	static String title="";
	static String desc="";
	static String image="";
	static String author="";
	static String genre="";
	static String sellername="";
	static String buyername="";
	
	
	
	
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
	
	public static int placeOrder(Buyer b, String s,String ph)
	{
		
    	if(b.getOrder()==null) 
    		b.setOrder(new Order(b.getCart()));
		b.getOrder().setShippingAddress(s);
		
		try{
			//GET SELLER ID
			//SELECT user_id FROM se_project.sellerlist WHERE product_id=? AND unitprice=?
			
			
			if((conn==null||conn.isClosed())==true) start();
			//String sql1="INSERT INTO se_project.order (order_id,buyer_id, seller_id, tax, shippingcost, totalprice, status,time) VALUES (NULL,?,NULL,?, NULL, ?, 0, now())";
			String sql1="INSERT INTO se_project.order (order_id,buyer_id,tax,totalprice,time,status,address,phonenumber) VALUES (NULL,?,?,?,NOW(),1,?,?)";	
			//			 INSERT INTO se_project.order (order_id,buyer_id,tax,totalprice,time) VALUES (NULL,?,?,?,NOW())
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, b.getUserId());
			ps1.setDouble(2, b.getOrder().getC().getTax());
			ps1.setDouble(3, b.getOrder().getC().getTotalCost());
			ps1.setString(4,s);
			ps1.setString(5, ph);
			ps1.executeUpdate();
			
			sql1="SELECT LAST_INSERT_ID() FROM se_project.order";
			ps1=conn.prepareStatement(sql1);
			result=ps1.executeQuery();
			result.next();
			int i=result.getInt("LAST_INSERT_ID()");
			
			
			b.getOrder().setOrderId(i);
			b.getOrder().getC().setUserName(b.getUserName());
			b.getOrder().setStatus(1);
			
			b.getOrder().setDateTime(new Date());
			
			for(Product p : b.getOrder().getC().getProducts())
			{
				String sql2="INSERT INTO se_project.productlist (productid,seller_id,unitprice,quantity,order_id) VALUES (?,?,?,?,?)";
				ps2=conn.prepareStatement(sql2);
				ps2.setInt(1, p.getItemId());
				ps2.setInt(2, p.getSellerId());
				ps2.setDouble(3, p.getUnitPrice());
				ps2.setInt(4, p.getQuantity());
				ps2.setInt(5, i);
				ps2.executeUpdate();
			}			
		}
	    catch(Exception e)
	    {
		System.out.println("Unable to update order.");
		e.printStackTrace();
		return -1;
	    }
		b.getOrder().sendNotifications();
		return 0;
	}
	
	public static ArrayList<Order> retrieveOrders(User u, String t)
	{
		ArrayList<Order> orders=new ArrayList<Order>();
		if(Integer.parseInt(t)==0)
		{
			sql="SELECT * FROM se_project.order NATURAL JOIN se_project.productlist NATURAL JOIN se_project.product "
					+ "WHERE order.order_id=productlist.order_id "
					+ "AND productlist.productid=product.product_id "
					+ "ORDER BY order.order_id";			
		}		
		if(Integer.parseInt(t)==1)
		{
			sql="SELECT * FROM se_project.order NATURAL JOIN se_project.productlist NATURAL JOIN se_project.product "
					+ "WHERE order.order_id=productlist.order_id AND order.buyer_id = ? "
					+ "AND productlist.productid=product.product_id "
					+ "ORDER BY order.order_id";
		}
		if(Integer.parseInt(t)==2)
		{
			sql="SELECT * FROM se_project.order NATURAL JOIN se_project.productlist NATURAL JOIN se_project.product "
					+ "WHERE order.order_id=productlist.order_id AND productlist.seller_id = ? "
					+ "AND productlist.productid=product.product_id "
					+ "ORDER BY order.order_id";
		}
		try
		{
			if((conn==null||conn.isClosed())==true) start();
		
			ps1=conn.prepareStatement(sql);
			
			if(Integer.parseInt(t)==1||Integer.parseInt(t)==2) ps1.setInt(1, u.getUserId());
			
			result=ps1.executeQuery();
						
			sql="SELECT user_name FROM se_project.user WHERE user_id= ?";
			
			while(result.next())
			{
				int i;
				order_id=result.getInt("order_id");
				i=order_id;
				buyerId=result.getInt("buyer_id");
				tax=result.getDouble("tax");
				totalPrice=result.getDouble("totalprice");
				date=result.getDate("time");
				do
				{
				if(i!=result.getInt("order_id"))
				{
					result.previous();
					break;
				}
				productId=result.getInt("productid");
				title=result.getString("name");
				desc=result.getString("description");
				image=result.getString("image");
				isbn=result.getInt("isbn");
				author=result.getString("author");
				genre=result.getString("genre");
				unitPrice=result.getDouble("unitprice");
				sellerId=result.getInt("seller_id");
				quantity=result.getInt("quantity");
				status=result.getInt("status");
				ps2=conn.prepareStatement(sql);
				ps2.setInt(1,sellerId);
				result1=ps2.executeQuery();
				if(result1.next()) sellername=result1.getString("user_name");
				ps2.setInt(1,buyerId);
				result1=ps2.executeQuery();
				if(result1.next()) buyername=result1.getString("user_name");
				products.add(new Product(title,unitPrice,productId,sellerId,quantity,sellername,desc,image,isbn,author,genre));
				}while(result.next());
				orders.add(new Order(order_id,buyerId,buyername,date,products,tax,0.0,totalPrice,"",status));
				products=new ArrayList<Product>();
			}
		}
		catch(Exception e)
	    {
			System.out.println("Unable to retrieve order.");
			e.printStackTrace();
			return null;
	    }
		return orders;
		
	}
	
	public static int deleteOrder(int orderId, int sellerId)
	{
		try
		{
			if((conn==null||conn.isClosed())==true) start();
		if(sellerId==-1)
		{
		String sql1="DELETE FROM se_project.order WHERE order_id = ?";
		String sql2="DELETE FROM se_project.productlist WHERE order_id = ?";
		ps1=conn.prepareStatement(sql1);
		ps2=conn.prepareStatement(sql2);
		ps1.setInt(1, orderId);
		ps2.setInt(1, orderId);
		ps1.executeUpdate();
		ps2.executeUpdate();
		}
		else
		{
			String sql1="DELETE FROM se_project.productlist WHERE order_id= ? AND seller_id=?";
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, orderId);
			ps1.setInt(2, sellerId);
			ps1.executeUpdate();
		}	
						
		}
		catch(Exception e)
	    {
			System.out.println("Unable to cancel order.");
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	public static int updateOrder(Buyer b, String s, String ph)
	{
		if(b.getOrder()==null) 
    		b.setOrder(new Order(b.getCart()));
		b.getOrder().setShippingAddress(s);
		
		try{
			if((conn==null||conn.isClosed())==true) start();
			
			String sql1="UPDATE se_project.order SET buyer_id=?,tax=?,totalPrice=?,time=NOW(),address=?,phonenumber=? WHERE order_id=?";
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, b.getUserId());
			ps1.setDouble(2, b.getOrder().getC().getTax());
			ps1.setDouble(3, b.getOrder().getC().getTotalCost());
			ps1.setString(4, s);
			ps1.setString(5, ph);
			ps1.setInt(6, b.getOrder().getOrderId());
			ps1.executeUpdate();
			
			sql1="DELETE FROM se_project.productlist WHERE order_id = ?";
			ps1=conn.prepareStatement(sql1);
			ps1.setInt(1, b.getOrder().getOrderId());
			ps1.executeUpdate();
			
			for(Product p : b.getOrder().getC().getProducts())
			{
				String sql2="INSERT INTO se_project.productlist (productid,seller_id,unitprice,quantity,order_id) VALUES (?,?,?,?,?)";
				ps2=conn.prepareStatement(sql2);
				ps2.setInt(1, p.getItemId());
				ps2.setInt(2, p.getSellerId());
				ps2.setDouble(3, p.getUnitPrice());
				ps2.setInt(4, p.getQuantity());
				ps2.setInt(5, b.getOrder().getOrderId());
				ps2.executeUpdate();
			}
		}
		catch(Exception e)
	    {
		System.out.println("Unable to update order.");
		e.printStackTrace();
		return -1;
	    }
		b.getOrder().sendNotifications();
		return 0;
	}
	
	public static int changeStatus(int orderId)
	{
		try{
			if((conn==null||conn.isClosed())==true) start();
			
			String sql="UPDATE se_project.order SET status=0 WHERE order_id=?";
			ps1=conn.prepareStatement(sql);
			ps1.setInt(1, orderId);
			ps1.executeUpdate();
		}
		catch(Exception e)
	    {
		System.out.println("Unable to update order.");
		e.printStackTrace();
		return 0;
	    }
		
		return 1;
	}
	
}
