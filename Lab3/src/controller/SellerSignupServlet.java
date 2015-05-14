package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthDAO;

/**
 * Servlet implementation class SignupServlet
 */
public class SellerSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String firstName = (String) request.getParameter("firstName");
		String middleName = (String) request.getParameter("middleName");
		String lastName = (String) request.getParameter("lastName");
		String email = (String) request.getParameter("email");
		String routingNum = (String) request.getParameter("routingNum");
		String accountNum = (String) request.getParameter("accountNum");
		String password = (String) request.getParameter("password");
		String password2 = (String) request.getParameter("password2");
		
		String msg = "";
		String url = "/signup.jsp";

		if (username == null) {username = "";} //Avoids null pointer exception
		if (firstName == null) {firstName = "";}
		if (middleName == null) {middleName = "";} 
		if (lastName == null) {lastName = "";} 
		if (email == null) {email = "";} 
		if (routingNum == null) {routingNum = "";} 
		if (accountNum == null) {accountNum = "";} 
		if (password == null) {password = "";}
		if (password2 == null) {password2 = "";}

		
		/** The proceeding block correlates to the first button */

		if (request.getParameter("sub1") != null){
					if ( username.length() == 0 ) {
						msg = "<font color=\"red\"> Username not filled in</font><br/>";
						url = "/signup.jsp";
					}
					else {
						try {
							if ( AuthDAO.isUserNameAvailable(username) ) {
								msg = "<font color=\"red\"> Username is available!</font><br/>";
								url = "/signup.jsp";
							} else {
								msg = "<font color=\"red\"> Username is not available!</font><br/>";
								url = "/signup.jsp";
							}
							AuthDAO.DB_Close();
						} catch (SQLException e) { e.printStackTrace(); } catch (Throwable e) {	e.printStackTrace(); }
					}
			
		} // End -- if (request.getParameter("sub1") != null)

		
		/** The proceeding code block correlates to the second button */
		
		else if (request.getParameter("sub2") != null){
			
			if ( username.length() == 0 ) {
				msg = "<font color=\"red\"> Username not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( firstName.length() == 0 ) {
				msg += "<font color=\"red\"> First name not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( middleName.length() == 0 ) {
				msg += "<font color=\"red\"> Middle name not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( lastName.length() == 0 ) {
				msg += "<font color=\"red\"> Last name not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( email.length() == 0 ) {
				msg += "<font color=\"red\"> Email not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( routingNum.length() == 0 ) {
				msg += "<font color=\"red\"> Bank Number not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( accountNum.length() == 0 ) {
				msg += "<font color=\"red\"> Routing Number not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( password.length() == 0 ) {
				msg += "<font color=\"red\"> Password not filled in</font><br/>";
				url = "/signup.jsp";
			}
			else if ( password2.length() == 0 ) {
				msg += "<font color=\"red\"> Please confirm password</font><br/>";
				url = "/signup.jsp";
			}
			else if ( !password.equals(password2) ) {
				msg += "<font color=\"red\"> Password Does Not Match!</font><br/>";
				url = "/signup.jsp";
			}
			
			else if (password.equals(password2)) {
				try {
					if ( AuthDAO.isUserNameAvailable(username) ) {
						int signupStatus = AuthDAO.enterNewUser(username, password, 2);
						if (signupStatus > 0) {
							AuthDAO.enterUserData(signupStatus, firstName, middleName, lastName, email);
							AuthDAO.enterSellerData(signupStatus, Integer.parseInt(routingNum), Integer.parseInt(accountNum));
							msg = "<font color=\"red\"> Account Created! You are now an authorized Seller.</font><br/>";
							url = "/login.jsp";
						}
						else if (signupStatus == 0) {
							msg = "<font color=\"red\"> User Name Insert Failed</font><br/>";
							url = "/signup.jsp";
						}
						else if (signupStatus == -1) {
							msg = "<font color=\"red\"> Create Account Failed, Please Try Again</font><br/>";
							url = "/signup.jsp";
						}
						
						AuthDAO.DB_Close();
					} else {
						msg = "<font color=\"red\"> Username is not available.</font><br/>";
						url = "/signup.jsp";
					}
				} catch (SQLException e) { e.printStackTrace(); } catch (Throwable e) { e.printStackTrace(); }
			}
			
		} // End -- if (request.getParameter("sub2") != null)

		
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);


		try { AuthDAO.DB_Close(); } catch (Throwable e) { e.printStackTrace(); }
	}// End doPost

}
