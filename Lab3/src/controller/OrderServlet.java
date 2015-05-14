package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Buyer;
import model.Cart;
import model.Order;
import model.OrderDAO;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		
		String shippingaddr=(String)request.getParameter("shippingaddr");
		if(shippingaddr==null) shippingaddr="";
		request.setAttribute("shippingaddr", shippingaddr);
		String city=(String)request.getParameter("city");
		if(city==null) city="";
		request.setAttribute("city", city);
		String state=(String)request.getParameter("state");
		if(state==null) state="";
		request.setAttribute("state", state);
		String zip=(String)request.getParameter("zip");
		if(zip==null) zip="";
		request.setAttribute("zip", zip);
		String country=(String)request.getParameter("country");
		if(country==null) country="";
		request.setAttribute("country", country);
		String phoneno=(String)request.getParameter("phoneno");
		if(phoneno==null) phoneno="";
		request.setAttribute("phoneno", phoneno);
		String update=(String)request.getParameter("updateorder");
		if(update==null) update="";
		String url="";
		int x=0;
		
		if(shippingaddr.length()==0)
        {
	       request.setAttribute("errorShippingAddr", "Please fill out the Shipping address");
	       x=-1;
        }
	    if(city.length()==0)
		{
			request.setAttribute("errorCity", "Please fill out the City");
			x=-1;
		}
	    if(state.length()==0)
		{
			request.setAttribute("errorState", "Please fill out the State");
			x=-1;
		}
	    if(zip.length()==0)
		{
			request.setAttribute("errorZip", "Please fill out the Zip Code");
			x=-1;
		}
	    if(country.length()==0)
		{
			request.setAttribute("errorCountry", "Please fill out the Country");
			x=-1;
		}
	    if(phoneno.length()==0)
		{
			request.setAttribute("errorPhoneNumber", "Please fill out the Phone Number");
			x=-1;
		}	    
	    if(x==-1)
		{
			request.getRequestDispatcher("/checkout.jsp").forward(request, response);
			return;
		}
	    
	    String s=shippingaddr+" "+city+" "+state+" "+zip+" "+country;
		
		HttpSession session=request.getSession();
		
		url="/purchase.jsp";
		
		Buyer b=(Buyer)session.getAttribute("userObj");
		
		if(update.length()!=0)
		{
			if(OrderDAO.updateOrder(b,s,phoneno)==0) //Calling updateOrder here, checking if it returned 0 (Status OK)
			{
				session.setAttribute("order", b.getOrder());
				//((ArrayList<Order>)session.getAttribute("orders")).add(b.getOrder());
				b.setCart(new Cart());
				b.setOrder(null);
				session.setAttribute("update",null);
				session.setAttribute("updateOrder",null);
				request.setAttribute("url",url);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;
			
			}
		}else
		if(OrderDAO.placeOrder(b,s,phoneno)==0) //Calling placeOrder here, checking if it returned 0 (Status OK)
		{
			session.setAttribute("order", b.getOrder());
			((ArrayList<Order>)session.getAttribute("orders")).add(b.getOrder());
			b.setCart(new Cart());
			b.setOrder(null);
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);	       
	     }				
	}

}
