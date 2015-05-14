package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Buyer;
import model.Product;
import model.User;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		String sub1=request.getParameter("Add");
				
		String sub2=request.getParameter("Remove");
		
		String sub3=request.getParameter("Update");
		
		String update=request.getParameter("updatequantity");
		if(update==null) update="1";
		
		String quantity=request.getParameter("quantity");
		if(quantity==null) quantity="1";
		
		int cart_quantity;
		String msg="";
		String url="";
		
		int q,u;
		
		int productId=Integer.parseInt(request.getParameter("productId"));
		
		int sellerId=Integer.parseInt(request.getParameter("sellerId"));
		
		HttpSession session=request.getSession();
		
		Buyer user=(Buyer)session.getAttribute("userObj");
		
		if(sub1!=null)
		{
			if(session.getAttribute("updateOrder")==null)
			{
				url="/search_result.jsp";
			}
			else
			{
				url="/update_order.jsp";
			}
			try
			{
		     q=Integer.parseInt(quantity);
			}
			catch(NumberFormatException e)
			{
				msg="Please enter a valid quantity before adding to your cart";
				request.setAttribute("url",url);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;
			}	
			
			if(q==0)
			{	
				msg="Please enter a value more than zero";
				request.setAttribute("url",url);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;			
			}
			
		ArrayList<Product> products=(ArrayList<Product>)session.getAttribute("search");
		
		for(int i=0;i<products.size();i++)
		{
			if((productId==products.get(i).getItemId())&&(sellerId==products.get(i).getSellerId()))
			{
				msg="Added "+products.get(i).getName()+" to Cart";
				((Buyer)user).getCart().addBooks(products.get(i), q);
			    ((ArrayList<Product>)session.getAttribute("search")).remove(products.get(i));
			 }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		}
		if(sub2!=null)
		{
			if(session.getAttribute("updateOrder")==null)
			{
				url="/cart.jsp";
			}
			else
			{
				url="/update_order.jsp";
			}
						
			ArrayList<Product> products=((Buyer)session.getAttribute("userObj")).getCart().getProducts();
			
			for(int i=0;i<products.size();i++)
			{	
				if((productId==products.get(i).getItemId())&&(sellerId==products.get(i).getSellerId()))
				{
					msg="Removed "+products.get(i).getName()+" from Cart";
					((Buyer)user).getCart().removeBooks(products.get(i));
				    
				 }
			}
			request.setAttribute("msg",msg);
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
		}
		if(sub3!=null)
		{
			if(session.getAttribute("updateOrder")==null)
			{
				url="/cart.jsp";
			}
			else
			{
				url="/update_order.jsp";
			}			
			
			try
			{
		     u=Integer.parseInt(update);
			}
			catch(NumberFormatException e)
			{
				msg="Please enter a valid quantity before updating your cart";
				request.setAttribute("url",url);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;
			}
			if(u==0)
			{	
				msg="Please enter a value more than zero";
				request.setAttribute("url",url);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;			
			}
				
			ArrayList<Product> products=((Buyer)session.getAttribute("userObj")).getCart().getProducts();
			
			for(int i=0;i<products.size();i++)
			{
				if((productId==products.get(i).getItemId())&&(sellerId==products.get(i).getSellerId()))
				{
					msg="Updated qunatity of "+products.get(i).getName()+" to Cart";
					((Buyer)user).getCart().updateQuantity(products.get(i), u);
				    
				 }
			}
			request.setAttribute("msg",msg);
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
		}		
	}
}
