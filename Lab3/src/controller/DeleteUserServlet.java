package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteUserDAO;
import model.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
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
		String msg = "Delete failed.";
		String url = "/index.jsp";
		Boolean success = false;
		
		HttpSession session = request.getSession();
		//User user; user = (User)session.getAttribute("user_id");		if(user == null) user = new User();
		String user_id_string = (String) request.getParameter("user_id"); 	if(user_id_string == null) user_id_string = "-1";
		String accountType_string = (String) request.getParameter("accountType"); 	if(accountType_string == null) accountType_string = "-1";
		String other_accountType_string = (String) request.getParameter("other_accountType"); 	if(other_accountType_string == null) other_accountType_string = "-1";
		
		int user_id = Integer.parseInt(user_id_string);
		int accountType = Integer.parseInt(accountType_string);
		int other_accountType = Integer.parseInt(other_accountType_string);
		//System.out.println("INTS " + user_id + " " + accountType);
		
		//System.out.println("accountType: "+ accountType);
		//System.out.println("other_accountType: " + other_accountType);
		//System.out.println("user_id: "+ user_id);
		
		ArrayList<User> users = (ArrayList<User>)session.getAttribute("users");
		
		if (accountType == 0) { //Admin is trying to delete someone
			//System.out.println("buyer delete");
			
			if (other_accountType == 1) {
				success = DeleteUserDAO.deleteBuyer(user_id);
				
				if (success) { msg = "Buyer successfully deleted.";
				for(int i=0;i<users.size();i++)
				{
					if(users.get(i).getUserId()==user_id)
					{
						users.remove(users.get(i));	
						break;
					}
					
				}
				} //Admin deletes buyer
				url = "/user_list.jsp";
				
			}
			if (other_accountType == 2) {
				success = DeleteUserDAO.deleteSeller(user_id);
				if(success){
					msg = "Seller successfully deleted.";
					for(int i=0;i<users.size();i++)
					{
						if(users.get(i).getUserId()==user_id)
						{
							users.remove(users.get(i));	
							break;
						}
						
					}
				}
				if (success && accountType == 2) { 
					session.invalidate(); 
					msg = "Seller successfully deleted.";
					} 
				
				//Admin deletes seller
				url = "/user_list.jsp";
			}
				
		}
		if (accountType == 2) { //Seller is trying to delete himself
			//System.out.println("Delete seller");
			success = DeleteUserDAO.deleteSeller(user_id);
			if (success && accountType == 2) { session.invalidate(); msg = "Account successfully deleted.";
			/**for(int i=0;i<=users.size();i++)
			{
				if(users.get(i).getUserId()==user_id)
				{
					users.remove(users.get(i));					}
				
			}**/
			} //Seller deletes himself
			
		}
		
		
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
		
	} //End doPost

}
