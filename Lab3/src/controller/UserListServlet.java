package controller;

import java.io.IOException;
//import java.sql.SQLException;


//import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String loggedIn = (String) session.getAttribute("loggedIn"); if (loggedIn == null) loggedIn= "false";
		String msg = (String) request.getAttribute("msg");  if (msg == null) msg = "";
		String url = "/user_list.jsp";
		String user_id = (String) request.getParameter("user_id"); if (user_id == null) user_id = "";
		String edit_user = (String) request.getParameter("edit_user");
		
		if(edit_user!=null){
			Seller user_seller=(Seller)session.getAttribute("userObj");
			session.setAttribute("currentUser", user_seller);
			
			/*String first_name = user_seller.getFirstName();
			String middle_name = user_seller.getMiddleName();
			String last_name = user_seller.getLastName();
			String email = user_seller.getemail();
			String address = user_seller.get;
			String phone_num = user_seller.get ;
			int routing_num = user_seller.getroutingNum();
			int bank_account_num = user_seller.getAccountNum();*/
			
			String first_name = (String)request.getParameter("first");
			String middle_name = (String)request.getParameter("middle");
			String last_name = (String)request.getParameter("last");
			String email = (String)request.getParameter("email");
			String address = (String)request.getParameter("address");
			String phone_num = (String)request.getParameter("phone");
			int phone_num_int = Integer.parseInt(phone_num);
			String routing_num = (String)request.getParameter("routing");
			int routing_num_int = Integer.parseInt(routing_num);
			String bank_account_num = (String)request.getParameter("account");
			int bank_account_num_int = Integer.parseInt(bank_account_num);
			
			int user_id_int = user_seller.getUserId();
			url = "/index.jsp";
			request.setAttribute("url",url);
			try{
				/*int phone_num_int = Integer.parseInt(phone_num);*/
				System.out.println(user_id_int + first_name + middle_name + last_name + email + address + phone_num_int+ routing_num_int + bank_account_num_int);
				
				boolean done = Seller.update_seller(user_id_int, first_name, middle_name,  last_name, email, address, phone_num_int, routing_num_int, bank_account_num_int);
				
				if(done == true) msg = "User edited.";
				else msg = "Error in editing.";
				
			    request.setAttribute("msg",msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
			} catch (Exception NumberFormatException){
				msg = "User not found due to number exception.";
			    request.setAttribute("msg",msg);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
			}
		}
		else{
			User user=(Administrator)session.getAttribute("userObj");
			ArrayList<User> users=new ArrayList<User>(); users=null;
			/*If not logged in*/
			if(loggedIn.equals("false")){
				url = "/login.jsp";
				msg = "You need admin priviledges to see userlist";
				session.setAttribute("msg", msg);
				session.setAttribute("url", url);
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		        rd.forward(request, response);
			} else if(user.getaccountType()!=0){
					url = "/index.jsp";
					msg = "You need admin privileges to see userlist";
					session.setAttribute("msg", msg);
					session.setAttribute("url", url);
					RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			        rd.forward(request, response);
			} else{
				users = Administrator.viewUserAccounts();
			    url="/user_list.jsp";
			
			    if(users.size()==0){
			    	msg = "No users to display.";
			    }
			}
		    request.setAttribute("msg",msg);
			request.setAttribute("url",url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
		}
	}
	
}
