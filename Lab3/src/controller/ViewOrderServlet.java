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
import model.Order;
import model.OrderDAO;
import model.Product;
import model.Seller;
import model.User;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String sub1=request.getParameter("sub1");
		
		String sub2=request.getParameter("sub2");
		
		String sub3=request.getParameter("sub3");
		
		String sub4=request.getParameter("sub4");
		
		int orderId=Integer.parseInt(request.getParameter("orderId"));
				
		HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("userObj");
		
		int sellerId;
		
		if(user.getaccountType()==2) sellerId=user.getUserId();
		
		else sellerId=-1;
		
		ArrayList<Order> orders=(ArrayList<Order>)session.getAttribute("orders");
		
		if(sub1!=null)
		{
			String url="/order.jsp";
			for(Order o : orders)
			{
				if(orderId==o.getOrderId())
				{
					session.setAttribute("viewOrder",o);
				}
			}
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);			
		}
		
		if(sub2!=null)
		{
			String url="/update_order.jsp";
			Buyer b=(Buyer)session.getAttribute("userObj");
			for(Order o : orders)
			{
				if(orderId==o.getOrderId())
				{
					b.setOrder(o);
					b.setCart(b.getOrder().getC());
					b.getCart().setTotalPrice(b.getCart().getTotalCost()*100/108);
					session.setAttribute("update", "updateIsOn");
					session.setAttribute("updateOrder", o);								
				}
			}
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
		}
		
		if(sub3!=null)
		{
			String url="/orderlist.jsp";
			Seller s=(Seller)session.getAttribute("userObj");
			for(Order o : orders)
			{
				if(orderId==o.getOrderId())
				{
					if(OrderDAO.changeStatus(orderId)==1)
					{
						o.setStatus(orderId);
					}
													
				}
			}
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
			
		}
		
		if(sub4!=null)
		{
			String url="/orderlist.jsp";
			for(int i=0;i<orders.size();i++)
			{
				if(orderId==orders.get(i).getOrderId())
				{
					if(OrderDAO.deleteOrder(orderId,sellerId)==1);
					((ArrayList<Order>)session.getAttribute("orders")).remove(orders.get(i));				    
				}
			}
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);			
		}
	}

}
