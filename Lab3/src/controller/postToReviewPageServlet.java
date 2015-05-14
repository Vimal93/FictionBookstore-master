package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class postToReviewPage
 */
public class postToReviewPageServlet extends HttpServlet {
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
		response.setContentType("text/html");
		String productTitle = (String) request.getParameter("productTitle");
		String id = (String) request.getParameter("itemId");
		
		if (productTitle == null) {productTitle = "";} //Avoids null pointer exception
		if (id == null) {id = "";}
		
		String url = "/review.jsp";
		
		//HttpSession session = request.getSession(true); //Start a session.session.setAttribute ("loggedIn", "True");
		//session.setAttribute ("productTitle", productTitle);
		//session.setAttribute ("itemId", id);
		
		HttpSession session = request.getSession();
		session.setAttribute("productTitle", productTitle);
		session.setAttribute("itemId", id);
		
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		
	} //End doPost

}
