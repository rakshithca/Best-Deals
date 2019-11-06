import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/salesdaily")
public class Salesdaily extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Sales Daily Report</a>");
			pw.print("</h2><div class='entry' style='overflow: auto;height: 64em;'>");
			pw.print("<table class='gridtable' >");
			pw.print("<tr>");
			pw.print("<td>Delivery Date</td><td>Total Sales</td>");
			pw.print("</tr>");

			HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
			hm = MySqlDataStoreUtilities.getDateSaleCount();
			for(Map.Entry<String,List<String>> entry : hm.entrySet()){
				List<String> a = entry.getValue();
				pw.print("<tr>");
				pw.print("<td>"+ entry.getKey()+"</td><td>"+a.get(0)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
			
		} catch(Exception e){

		}
	
	}
	
	
}