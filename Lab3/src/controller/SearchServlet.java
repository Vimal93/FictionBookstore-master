package controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.SearchDAO;

/**
 * Servlet implementation class search
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Product> products=new ArrayList<Product>();
		products=null;
		
		HttpSession session=request.getSession();
		
		String msg = "";
		String url = "/index.jsp";
		
		String searchtext = (String) request.getParameter("searchbox");
	    String cat = (String) request.getParameter("srcCat");
	    
	    if(searchtext == null) searchtext = "";
	    if(cat == null) cat = "";
	    
	    if(searchtext.length()==0)
	    {
	    	msg = "Please type something in the search box";
			url = "/index.jsp";
	    }
	    else if(cat.length()==0)
	    {
	    	msg = "Please select a category";
			url = "/index.jsp";	    	
	    }
	    else
	    {
	         products=SearchDAO.search(searchtext,cat);
	        
	        if(products.size()==0)
	        {
	        	products=SearchDAO.advancedSearch(searchtext,cat);
	        }
	        
	        msg="We have found "+products.size()+" results";
		    url="/search_result.jsp";	        
	        
	        session.setAttribute("search",products);
	        
	        if(products.size()==0)
	        {
	        	msg="We have found no products matching "+searchtext;
	        	url="/index.jsp";
	        }
		    
		    
	    }
	    
	    request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		
		
		
	}

}
