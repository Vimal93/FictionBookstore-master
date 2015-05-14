package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrator;
//import model.Administrator;
import model.AuthDAO;
//import model.Buyer;
import model.Order;
import model.OrderDAO;
import model.Product;
//import model.Seller;
import model.User;
import model.WishlistDAO;
import model.ProductDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//use this method for get requests
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*  • Start Session
		• Add Code to get form parameter values
		• Validate form
		• Check values against stored data (“hard coded for now in a variable”)
		• Set values to session attributes
		• Forward (request, response)
		• *Handle for errors within all of these steps*/
		
		response.setContentType("text/html");
		String name = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		if (name == null) {name = "";} //Avoids null pointer exception
		if (password == null) {password = "";}
		
		String loggedIn = "false";
		String msg = "";
		String url = "/login.jsp";
		
		
		if ( name.length() == 0 ) {
			msg = "<font color=\"red\"> Username not filled in <h2>&#9785</h2></font>";
			url = "/login.jsp";
		}
		
		else if( password.length() == 0 ) {
			msg = "<font color=\"red\"> Password not filled in <h2>&#9785</h2></font>";
			url = "/login.jsp";
		}
		
		else
		{
			/***
			 * Check name and password against the items in db
			 * Use checkUsePass
			 */
			int userId = -1;
			int accountType = -1;
			User user = null;
			HttpSession session = request.getSession(true); //Start a session.session.setAttribute ("loggedIn", "True");
			//if (name.equals("Jd123") && password.equals("UAlbany456")) {
			try { userId = AuthDAO.checkUserPass(name, password); } catch (SQLException e) {e.printStackTrace(); }
			if ( userId > 0) { 
				//true flag will create the session if it doesn't already exist, else it gets the existing session.
				try { 
					accountType = AuthDAO.getAccountType(userId);
					//user = AuthDAO.getBuyerById(userId);
					//check user type here and send to different AuthDAO methods
					if (accountType==0){ 
						System.out.println("You are in admin.");
						user = AuthDAO.getAdminById(userId);
						
						ArrayList<User> users = new ArrayList<User>(); users=null;
						ArrayList<Product> products=new ArrayList<Product>(); products = null;
						int adminId = user.getUserId();
						
						products = ProductDAO.viewProducts(adminId, accountType);
						session.setAttribute("products", products);
						
						users = Administrator.viewUserAccounts();
						session.setAttribute("users", users);

						System.out.println("You are in admin 2.");
						
					}
					else if (accountType==1){ 
						user = AuthDAO.getBuyerById(userId); 
						ArrayList<Product> wishlistproducts=WishlistDAO.selectWishlist(userId);
						session.setAttribute("wishlistproducts", wishlistproducts);
						}
					else if (accountType==2){ 
						user = AuthDAO.getSellerById(userId); 
						
						ArrayList<Product> products=new ArrayList<Product>(); products = null;
						int sellerId = user.getUserId();
						
						products = ProductDAO.viewProducts(sellerId, accountType);
						session.setAttribute("products", products);
					}
					else {user = null;}
					
				} catch (SQLException e) { e.printStackTrace(); }
				
				loggedIn = "true";
				session.setAttribute ("userObj", user);
				session.setAttribute ("loggedIn", loggedIn);
				String test = Integer.toString(accountType);
				session.setAttribute ("accountType", test);
				ArrayList<Order> orders=OrderDAO.retrieveOrders(user, test);
				session.setAttribute("orders", orders);
				msg = "Login Successful! Welcome to the bookstore!";
				url = "/index.jsp";
			}
			else {
				msg = "<font color=\"red\"> Username/Password Not Valid <h2>&#9785</h2></font>";
				url = "/login.jsp";		 
			}
		} // End outer Else
		
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		 
        
        
        try { AuthDAO.DB_Close(); } catch (Throwable e) { e.printStackTrace(); }
	}// End doPost

}// End class
