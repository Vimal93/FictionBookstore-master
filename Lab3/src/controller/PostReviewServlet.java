package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AuthDAO;
import model.User;
import model.ReviewDAO;

/**
 * Servlet implementation class PostReviewServlet
 */
public class PostReviewServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String review = (String) request.getParameter("review");
		String like = (String) request.getParameter("radio");
		String itemId = (String) request.getParameter("itemId");
		boolean success = false;
		String msg = "";
		String url = "/order.jsp";
		int like_num = -1;
		
		HttpSession session = request.getSession();
		int user_id = ((User)session.getAttribute("userObj")).getUserId();
		
		//pass user_id as string object, check if null, then parse into int
		//need product_id
		
		if (review == null) {review = "";}
		if (like == null) {like = "";}
		if (itemId == null) {itemId = "-1";}
		
		int product_id = Integer.parseInt(itemId);

		if (like.equals("dislike")) { like_num = 0; }
		if (like.equals("like")) { like_num = 1; }
		
		if ( like_num == -1 ) {
			msg = "<font color=\"red\"> Please select like or dislike before continuing.</font>";
			url = "/review.jsp";
		}
		if ( review.length() < 5 ) {
			msg = "<font color=\"red\"> Please enter at least 5 characters.</font>";
			url = "/review.jsp";
		}
		if ( review.length() > 255 ) {
			msg = "<font color=\"red\"> Too many characters! The limit is 255.</font>";
			url = "/review.jsp";
		}
		else {
			
			try { ReviewDAO.postReview(product_id, user_id, review, like_num); } catch (SQLException e) {
				e.printStackTrace();
			}
			
		} // End else
		
		try { AuthDAO.DB_Close(); } catch (Throwable e) { e.printStackTrace(); }
		
		session.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response); 
        
	} //End doPost() method

}
