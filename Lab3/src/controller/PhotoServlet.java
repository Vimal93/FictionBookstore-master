package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import smartupload.*;

public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 
		 String test=(String)session.getAttribute("PhotoUploaderMode");
		 System.out.println(test);
		 
		 String msg = "";
		 //response.setContentType ("text / html; charset = gb2312");
		 //PrintWriter out = response.getWriter ();
		 SmartUpload mySmartUpload = new SmartUpload ();
		 // Long file_size_max = 4000000;
		
		 String ext = "";
		 String url="";
		 if(test.equals("Add"))
		 {
		 url = "/new_listing.jsp";
		 }
		 if(test.equals("Edit"))
		 {
			 url="/edit_listing.jsp";
		 }
		 // Initialise
		 mySmartUpload.initialize (this.getServletConfig (), request, response);
		 // Only allows uploading of such documents
		 try {
			 mySmartUpload.setAllowedFilesList ("jpg");
		 // Upload file
			 mySmartUpload.upload ();
			 msg = "Uploaded the image file";
			 System.out.println (msg);
		 } catch (Exception e) {
			 msg = "Must select jpg.";
			 System.out.println (msg);
		 }
		 try {
			 smartupload.File myFile = mySmartUpload.getFiles().getFile (0);
			 if (myFile.isMissing ()) msg = "Must select one photo.";
			 else {
				 for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {
					 myFile = mySmartUpload.getFiles().getFile (i);
					 
					 session.setAttribute("myFile",myFile);
					 
					 /**if (myFile.isMissing ()) continue;
					 ext = myFile.getFileExt (); // obtain the extension
					 
					 
					 String saveurl = "";
					
					 String filename = "HP222"; //(String)request.getAttribute("isbn");

					 saveurl = request.getRealPath("/images/");
					 saveurl += "/" + filename + "." + ext; // save path of the final document
					 System.out.println (saveurl);
					 myFile.saveAs (saveurl,mySmartUpload.SAVE_PHYSICAL);
					 
					 msg = "Uploaded pic.";	
					 System.out.println (msg);**/
				 }
			 }
		 } catch (Exception e) {
			 msg = "Nope, didn't work";
			 System.out.println (msg);
			 e.printStackTrace();
		 }
		 request.setAttribute("msg",msg);
		 request.setAttribute("url",url);
	 	 RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
         rd.forward(request, response);
	 }
}
