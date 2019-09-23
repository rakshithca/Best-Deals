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

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable'>");
			
			
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
			catch(Exception e)
				{
			
				}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	
					if(od.getUserName().equals(user.getName()))
					size= size+1;
				}
				
			if(size>0)
				{	
					
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{
						for(OrderPayment oi:entry.getValue())	
						if(oi.getUserName().equals(user.getName())) 
						{
							pw.print("<form method='get' action='ViewOrder'>");
							pw.print("<tr>");			
							pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
							pw.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
							pw.print("</tr>");
							pw.print("</form>");
						}
					
					}
					
					pw.print("</table>");
					
				}

				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
				
				
				
				
				
			pw.print("</table>");
			if (utility.usertype().equals("retailer"))
					{
							//pw.print("<tr><td colspan=2>");
							pw.print("<a href='addnewproduct' style='font-size: 23px;'>Add Product</a><br>");
							// pw.print("<a href='modifyproduct'>Update Products</a><br>");
							// pw.print("<a href='deleteproduct'>Delete Products</a></td></tr>");
							showproductList(pw);
					}		

			if (utility.usertype().equals("manager"))
					{
							//pw.print("<tr><td colspan=2>");
							pw.print("<a href='addnewcustomer' style='font-size: 23px;'>Add Customer</a><br>");
							// pw.print("<a href='modifyproduct'>Update Products</a><br>");
							// pw.print("<a href='deleteproduct'>Delete Products</a></td></tr>");
							showcustomer(pw);
					}		
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}

	protected void showproductList(PrintWriter pw) throws ServletException, IOException {

		pw.print("<br><br><br><br><br><h3>Product List</h3>");
		pw.print("<div class='entry' style='overflow: auto;height: 500px;'><table class='gridtable' >");
		pw.print("<tr>");
		pw.print("<td>Name</td><td>Price</td><td>Manufracturer</td><td>Category</td><td colspan=2></td>");
		pw.print("</tr>");

		HashMap<String, FitnessWatch> hm_fit = new HashMap<String, FitnessWatch>();
		hm_fit.putAll(SaxParserDataStore.fitnesswatches);
		for(Map.Entry<String, FitnessWatch> entry : hm_fit.entrySet()){
			FitnessWatch fitnesswatch = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getRetailer()+"</td><td>Fitness Watch</td>");
			pw.print("<td><a href='modifyproduct?type=fitnesswatch&maker="+fitnesswatch.getRetailer()+"&product_id=product_id"+fitnesswatch.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=fitnesswatch&product_id="+fitnesswatch.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, SmartWatch> hm_smart = new HashMap<String, SmartWatch>();
		hm_smart.putAll(SaxParserDataStore.smartwatches);
		for(Map.Entry<String, SmartWatch> entry : hm_smart.entrySet()){
			SmartWatch smartwatch = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+smartwatch.getName()+"</td><td>"+smartwatch.getPrice()+"</td><td>"+smartwatch.getRetailer()+"</td><td>Smart Watch</td>");
			pw.print("<td><a href='modifyproduct?type=smartwatch&maker="+smartwatch.getRetailer()+"&product_id="+smartwatch.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=smartwatch&product_id="+smartwatch.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, TV> hm_tv = new HashMap<String, TV>();
		hm_tv.putAll(SaxParserDataStore.TVs);
		for(Map.Entry<String,TV> entry : hm_tv.entrySet()){
			TV TV = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getRetailer()+"</td><td>TV</td>");
			pw.print("<td><a href='modifyproduct?type=TV&maker="+TV.getRetailer()+"&product_id="+TV.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=TV&product_id="+TV.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, Headphone> hm_head = new HashMap<String, Headphone>();
		hm_head.putAll(SaxParserDataStore.headphones);
		for(Map.Entry<String, Headphone> entry : hm_head.entrySet()){
			Headphone headphone = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+headphone.getName()+"</td><td>"+headphone.getPrice()+"</td><td>"+headphone.getRetailer()+"</td><td>Head Phones</td>");
			pw.print("<td><a href='modifyproduct?type=headphone&maker="+headphone.getRetailer()+"&product_id="+headphone.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=headphone&product_id="+headphone.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, Phone> hm_phones = new HashMap<String, Phone>();
		hm_phones.putAll(SaxParserDataStore.phones);
		for(Map.Entry<String, Phone> entry : hm_phones.entrySet()){
			Phone phone = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+phone.getName()+"</td><td>"+phone.getPrice()+"</td><td>"+phone.getRetailer()+"</td><td>Phones</td>");
			pw.print("<td><a href='modifyproduct?type=phone&maker="+phone.getRetailer()+"&product_id="+phone.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=phone&product_id="+phone.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}
		HashMap<String, SoundSystem> hm_sound = new HashMap<String, SoundSystem>();
		hm_sound.putAll(SaxParserDataStore.soundsystems);
		for(Map.Entry<String, SoundSystem> entry : hm_sound.entrySet()){
			SoundSystem soundsystem = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+soundsystem.getName()+"</td><td>"+soundsystem.getPrice()+"</td><td>"+soundsystem.getRetailer()+"</td><td>Sound Systems</td>");
			pw.print("<td><a href='modifyproduct?type=soundsystem&maker="+soundsystem.getRetailer()+"&product_id="+soundsystem.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=soundsystem&product_id="+soundsystem.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, Laptop> hm_laptop = new HashMap<String, Laptop>();
		hm_laptop.putAll(SaxParserDataStore.laptops);
		for(Map.Entry<String, Laptop> entry : hm_laptop.entrySet()){
			Laptop laptop = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getRetailer()+"</td><td>Laptop</td>");
			pw.print("<td><a href='modifyproduct?type=laptop&maker="+laptop.getRetailer()+"&product_id="+laptop.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=laptop&product_id="+laptop.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}
		HashMap<String, VoiceAssistant> hm_voice = new HashMap<String, VoiceAssistant>();
		hm_voice.putAll(SaxParserDataStore.voiceassistants);
		for(Map.Entry<String, VoiceAssistant> entry : hm_voice.entrySet()){
			VoiceAssistant voiceassistant = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getRetailer()+"</td><td>Voice Assistant</td>");
			pw.print("<td><a href='modifyproduct?type=voiceassistant&maker="+voiceassistant.getRetailer()+"&product_id="+voiceassistant.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=voiceassistant&product_id="+voiceassistant.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}

		HashMap<String, Wirelessplan> hm_wireless = new HashMap<String, Wirelessplan>();
		hm_wireless.putAll(SaxParserDataStore.wirelessplans);
		for(Map.Entry<String, Wirelessplan> entry : hm_wireless.entrySet()){
			Wirelessplan wirelessplan = entry.getValue();
			pw.print("<tr>");
			pw.print("<td>"+wirelessplan.getName()+"</td><td>"+wirelessplan.getPrice()+"</td><td>"+wirelessplan.getRetailer()+"</td><td>Wireless Plans</td>");
			pw.print("<td><a href='modifyproduct?type=wirelessplan&maker="+wirelessplan.getRetailer()+"&product_id="+wirelessplan.getId()+"'>Update Products</a><br></td>");
			pw.print("<td><a href='deleteproduct?type=wirelessplan&product_id="+wirelessplan.getId()+"'>Delete Products</a></td>");
			pw.print("</tr>");
		}
		pw.print("</table></div>");
	
	}

	protected void showcustomer(PrintWriter pw) throws ServletException, IOException{
	
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		pw.print("<br><br><br><br><br><h3>Orders List</h3>");
		pw.print("<div class='entry' style='overflow: auto;height: 500px;'><table class='gridtable' >");
		pw.print("<tr>");
		pw.print("<td> Order ID</td><td>Username</td><td>order name </td><td>Order price<td colspan=2></td>");
		pw.print("</tr>");
		
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}

		
		
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
			for(OrderPayment oi:entry.getValue()){
					pw.print("<tr>");			
					pw.print("<td>"+oi.getOrderId()+"</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
					pw.print("<td><a href='modifyorder?type=user&order_id="+oi.getOrderId()+"&userName="+oi.getUserName()+"'>Add to this Order ID</a><br></td>");
					//pw.print("<td><a href='#' onclick=>Add to this Order ID</a><br></td>");
					pw.print("<td><a href='ViewOrder?cust_order=cust&orderName="+oi.getOrderName()+"&orderId="+oi.getOrderId()+"&Order=CancelOrder'>Delete order</a></td>");
					pw.print("</tr>");
			}
		}
		pw.print("</table></div>");
	}
}




