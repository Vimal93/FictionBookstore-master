package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartupload.SmartUpload;
import model.AuthDAO;
import model.Product;
import model.ProductDAO;
import model.Seller;
import model.User;

/**
 * Servlet implementation class SellerProductListServlet
 */
public class SellerProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerProductListServlet() {
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
		HttpSession session = request.getSession();
		User user; user = (User)session.getAttribute("userObj");	//if(user == null) user = new User();
		String loggedIn = (String)session.getAttribute("loggedIn"); if(loggedIn == null) loggedIn = "false";
		String title = (String) request.getParameter("title"); 		//if (title == null) title = "";
		String genre = (String) request.getParameter("genre");
		String title1=(String) request.getParameter("editedProduct"); //if (title1 == null) title = "";
		String isbn = (String) request.getParameter("isbn");		//if (isbn == null) isbn = "";
		String author = (String) request.getParameter("author");	//if (author == null) author = "";
		String price = (String) request.getParameter("price");		//if (price == null) price = "";
		String description = (String) request.getParameter("desc");	//if (description == null) description = "";
		String sub = (String) request.getParameter("sub");			//if (sub == null) sub = "";
		String sub1=(String) request.getParameter("sub1");			//if (sub1 == null) sub1 = "";
		String sub2=(String) request.getParameter("sub2");			//if (sub2 == null) sub2 = "";
		
		String isbn2 = (String) request.getParameter("isbn5");		//if (isbn2 == null) isbn2 = "";
		String price2 = (String) request.getParameter("price5");	//if (price2 == null) price2 = "";
		
		String msg="";
		String url="";
		
		/**if(loggedIn.equals("false") || (user.getaccountType() != 2)){
			msg = "Please log in as seller.";
			url = "/index.jsp";
			request.setAttribute("msg",msg);
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			request.setAttribute("priceChanged", "The price of your book is changed");
	        rd.forward(request, response);
		}
				
			url="/new_listing.jsp";
			
			if (title == null) {title = "";} //Avoids null pointer exception
			if (isbn == null) {isbn = "";}
			if (author == null) {author = "";} 
			if (price == null) {price = "";} 
			if (description == null) {description = "";} 
			
			if( title.length() == 0 ) 
			{
				msg = "<font color=\"red\"> Title not filled in</font><br/>";
				url = "/new_listing.jsp";
			} 
			else if( isbn.length() == 0 ) 
			{
				msg += "<font color=\"red\"> ISBN not filled in</font><br/>";
				url = "/new_listing.jsp";
			} 
			else if( author.length() == 0 ) {
				msg += "<font color=\"red\"> Author not filled in</font><br/>";
				url = "/new_listing.jsp";
			} 
			else if( price.length() == 0 ) {
				msg += "<font color=\"red\"> Price not filled in</font><br/>";
				url = "/new_listing.jsp";
			} 
			else if( description.length() == 0 ){
				msg += "<font color=\"red\"> Description not filled in</font><br/>";
				url = "/new_listing.jsp";
			} 
			else **/
		if(sub!=null){
				int isbn1 = Integer.parseInt(isbn);
				double price1 = Double.parseDouble(price);
				Product newProduct = new Product(title, isbn1, author, price1, description,genre);
				boolean done=ProductDAO.addNewProduct(newProduct,user.getUserId());
				if(done == true){
					((ArrayList<Product>)session.getAttribute("products")).add(newProduct);
					msg = "Added "+title+" in products!";
					url = "/productlist.jsp";
					
					if(session.getAttribute("myFile")!=null)
					 {
					 try {
						 
						 
						 smartupload.File myFile = (smartupload.File)session.getAttribute("myFile");
						 	 
								 String ext = myFile.getFileExt (); // obtain the extension
								 String saveurl = "";
								
								  //(String)request.getAttribute("isbn");
		                         System.out.println(title);
								 saveurl = request.getRealPath("/images/");
								 saveurl += "/" + newProduct.getName() + "." + ext; // save path of the final document
								 System.out.println(newProduct.getName());
								 myFile.saveAs (saveurl,SmartUpload.SAVE_PHYSICAL);
								 
								 msg = "Saved pic.";	
								 System.out.println (msg);
								 
								 session.removeAttribute("myFile");
							 
						 
					 } catch (Exception e) {
						 msg = "Nope, didn't work";
						 System.out.println (msg);
						 e.printStackTrace();
					 } }
		       } 
				
			}
			else if(sub1!=null){
				if(session.getAttribute("myFile")!=null)
				 {
				 try {					 
					 
					 smartupload.File myFile = (smartupload.File)session.getAttribute("myFile");
					 	 
							 String ext = myFile.getFileExt (); // obtain the extension
							 String saveurl = "";
							
							  //(String)request.getAttribute("isbn");
	                         System.out.println(title);
							 saveurl = request.getRealPath("/images/");
							 saveurl += "/" + title1 + "." + ext; // save path of the final document
							 System.out.println (saveurl);
							 myFile.saveAs (saveurl,SmartUpload.SAVE_PHYSICAL);
							 
							 msg = "Saved the pic.";	
							 System.out.println (msg);
							 
							 session.removeAttribute("myFile");
						 
					 
				 } catch (Exception e) {
					 msg = "Nope, didn't work";
					 System.out.println (msg);
					 e.printStackTrace();
				 } }
				url="/productlisting.jsp";
				int isbn3 = Integer.parseInt(isbn2);
				double price3 = Double.parseDouble(price2);				
				boolean done = ProductDAO.editPrice(user.getUserId(),isbn3,price3 );
				
				if(done == true){
					msg = "Edited "+title1+" in products!";
					url = "/productlist.jsp";
					ArrayList<Product> products=new ArrayList<Product>(); products = null;
					int sellerId = user.getUserId();
					int accountType;
					try {
						accountType = AuthDAO.getAccountType(sellerId);
						products = ProductDAO.viewProducts(sellerId, accountType);
						session.setAttribute("products", products);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		       }
			}
				else if(sub2!=null){
					int id=Integer.parseInt(request.getParameter("currentId"));
					url="/edit_listing.jsp";
					ArrayList<Product> products=(ArrayList<Product>)session.getAttribute("products");
					for(Product p : products)
					{
						if(id==p.getItemId())
						{
							session.setAttribute("currentProduct", p);
						}
						
					}
					
				}
				request.setAttribute("msg",msg);
				request.setAttribute("url",url);
				System.out.println("URL: "+url);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);	
				
		   }
				
		
					
				
	}

