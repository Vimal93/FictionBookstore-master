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

import model.Product;
import model.Review;
import model.ReviewDAO;

/**
 * Servlet implementation class ViewReviewServlet
 */
public class ViewReviewServlet extends HttpServlet {
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
		
		ArrayList<Review> reviews=new ArrayList<Review>();
		reviews=null;
		HttpSession session=request.getSession();
		
		String msg = "";
		String url = "/view_reviews.jsp";
		
		String product_id_string = (String) request.getParameter("product_id");
		String name = (String) request.getParameter("name");  //Product name
		String author = (String) request.getParameter("author");  //Product author
		if(product_id_string == null) product_id_string = "-1";
		int product_id = Integer.parseInt(product_id_string);
		if(name == null) name = "";
		
		System.out.println("product_id: "+product_id);
		System.out.println(name);
		
		ArrayList<Product> products=(ArrayList<Product>)session.getAttribute("search");
		for(int i=0;i<products.size();i++)
		{
			if(product_id==products.get(i).getItemId())
			{
				session.setAttribute("currentReviewProduct", products.get(i));
				break;
			}
		}
		
		
		try { reviews=ReviewDAO.viewReview(product_id); } catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		msg="We have found "+reviews.size()+" reviews";
		url="/view_reviews.jsp";	        
	        
	    session.setAttribute("reviews",reviews);
	    session.setAttribute("name",name);
	    session.setAttribute("author",author);
	        
        if(reviews.size()==0)
        {
        	msg="<font color=\"red\">We have found no reviews matching '"+product_id+"'</font>";
        	url="/index.jsp";
        }
        
        request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
	
	}// End doPost() servlet

}
