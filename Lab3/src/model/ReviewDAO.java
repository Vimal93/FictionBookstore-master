/**
 * 
 */
package model;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jonathan Farley, 2015
 *
 */
public class ReviewDAO implements Serializable {
	
	 static final long serialVersionUID = 1L;

	private static Connection conn;

	public static final String DB_URL = "jdbc:mysql://localhost:3306/se_project";
	public static final String DB_USER = "root";
	public static final String DB_PW = "root";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";


	
	public ReviewDAO() {
		
	}
	
	public static void start() {
		
		conn = null;
		/** Load DB Driver */
		try{ Class.forName(DB_DRIVER); } catch (ClassNotFoundException ex) {
			System.out.println("An error occurred. Driver not loaded.");
			Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		/** Connect to DB */
		try { conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW); } catch (SQLException e) {
			System.out.println("An error occurred. Maybe user/password is invalid");
			e.printStackTrace(); }
		//if (conn != null) { System.out.println("Connected to the database"); }
	}
	
	
	public static void postReview(int product_id, int user_id, String review, int like) throws SQLException {
		
		start();
		
		//check if username exists in user table, if not return true else return false
		//ResultSet result = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO reviews (product_id, user_id, comments, likedislike) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE comments=VALUES(comments), likedislike=VALUES(likedislike)";
		//System.out.println("Like DAO: "+like);
		try { 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.setInt(2, user_id);
			ps.setString(3, review);
			ps.setInt(4, like);
			//System.out.println(ps.toString());
			ps.executeUpdate();
			
		} catch (SQLException e) { 
			System.out.println("Unable to insert review.");
			e.printStackTrace(); }
		
	} //End postReview method
	
	
	
	public static ArrayList<Review> viewReview(int product_id) throws SQLException {
		
		//Need an arraylist that holds
		ArrayList<Review> reviews=new ArrayList<Review>();

		PreparedStatement ps = null;
		ResultSet result = null;
		String comments = "";
		String reviewer = "";
		int likedislike = -1;
		
		String sql = "SELECT user_name, comments, likedislike FROM reviews NATURAL JOIN user WHERE product_id = ?";
		try { 
			if((conn==null||conn.isClosed())==true) { start(); }
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			//System.out.println(ps.toString());
			result = ps.executeQuery();
			
			while(result.next()) {
				comments = result.getString("comments");
				reviewer = result.getString("user_name");
				likedislike = result.getInt("likedislike");
				reviews.add(new Review(comments, reviewer, likedislike));
			}
			conn.close();

        }
	    catch(Exception e)
	    {
	    	System.out.println("Unable to search products in database");
	    	e.printStackTrace();
	    } 
		
		return reviews;
		
	} //End viewReview() class
	
	
	
	public static void DB_Close() throws Throwable {
		try {
			if(conn!=null) { //c is a variable for a Connection you may have to rename it
				conn.close(); //c is a variable for a Connection you may have to rename it
			} }
		catch(SQLException e) { System.out.println(e);
		}
	}


}
