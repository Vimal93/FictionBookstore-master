package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import model.Product;
import model.ProductDAO;
import model.User;

/**
 * Servlet implementation class DeleteProductServlet
 */
public class DeleteProductServlet extends HttpServlet {
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
		System.out.println("Delete product");
		//product_id
		//user_id
		
		HttpSession session = request.getSession();
		User user; user = (User)session.getAttribute("userObj");			if(user == null) user = new User();
		String loggedIn = (String)session.getAttribute("loggedIn"); 		if(loggedIn == null) loggedIn = "false";
		String seller_id_string = (String)session.getAttribute("seller_id"); 		if(seller_id_string == null) seller_id_string = "-1";
		String product_id = (String) request.getParameter("product_id");	if(product_id == null) product_id = "-1";
		//String user_id =(String) request.getParameter("user_id");
		
		int product_id_num = Integer.parseInt(product_id);
		
		ArrayList<Product> products=(ArrayList<Product>)session.getAttribute("products");
		
		//System.out.println("SELLER ID STRING:"+ seller_id_string);
		int seller_id = Integer.parseInt(seller_id_string);
		//System.out.println("SELLER ID:"+ seller_id);
		String msg="";
		String url="/productlist.jsp";
		
		for(int i=0;i<products.size();i++)
		{
			if(products.get(i).getItemId()==Integer.parseInt(product_id))
			{
				msg="Deleted "+products.get(i).getName();
				ProductDAO.deleteSellerProduct(product_id_num, seller_id);
				products.remove(products.get(i));		    	
			}
		}
		
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		
		
	}

}
