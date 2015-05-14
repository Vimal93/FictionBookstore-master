package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;
import model.WishlistDAO;

/**
 * Servlet implementation class WishlistServlet
 */
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//receive some variable from buttons that determine if it's 'add' or 'remove'
		//send user_id and product_id to WishListDAO
		
		//System.out.println("WISHLIST!!!!!");
		String msg = "";
		String url = "/search_result.jsp";
		Boolean success = false;
		
		HttpSession session = request.getSession();
		User user; user = (User)session.getAttribute("userObj");		if(user == null) user = new User();
		String product_id_string = (String) request.getParameter("productId"); 	if(product_id_string == null) product_id_string = "false";
		String action = (String) request.getParameter("action"); 		if(action == null) action = "false";
		String count = (String) request.getParameter("count");
		System.out.println(count);
		
		int product_id = Integer.parseInt(product_id_string);
		
		//System.out.println("product_id_string: "+product_id_string);
		System.out.println("Wishlist Action:"+action);
		
		int user_id = user.getUserId();
		System.out.println("user_id: "+user_id);
		
		
		if ( (user_id > 0) && (action.equals("add"))) {
			
			success = WishlistDAO.addWishlistItem(product_id, user_id);
			if (success) { 
				msg = "Book successfully added to wishlist!";
				Product p=((ArrayList<Product>)session.getAttribute("search")).get(Integer.parseInt(count));
				((ArrayList<Product>)session.getAttribute("wishlistproducts")).add(p);
				} 
			else { msg = "Book already in wishlist!"; }
			url = "/search_result.jsp";
						
		}
		
		if ( (user_id > 0) && action.equals("remove")) {
			success = WishlistDAO.removeWishListItem(product_id, user_id);
			if (success) { msg = "Book successfully removed from wishlist!"; } else { msg = "Book not removed from wishlist!"; }
			url = "/wishlist.jsp";
			((ArrayList<Product>)session.getAttribute("wishlistproducts")).remove(Integer.parseInt(count));
		}
		
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		
	} //End doPost

}
