
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

@WebServlet("/modifyorder")

public class modifyorder extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

      
        String order_id = request.getParameter("order_id");
        String userName = request.getParameter("userName");
       
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Product List</a>");
			pw.print("</h2><div class='entry'>");
            if (utility.usertype().equals("manager")){
                showproductList(pw,order_id,userName);
            }else{
                pw.print("<h4 style='color:red'>You are not authorised</h4>");
            }
            pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");	        		
	}

	/* Display Account Details of the Customer (Name and Usertype) */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        String TOMCAT_HOME = System.getProperty("catalina.home");

        Integer order_id = Integer.parseInt(request.getParameter("order_id"));
        String userName = request.getParameter("user_id");
        String order_name = request.getParameter("product_name");
        Double order_price=Double.parseDouble(request.getParameter("product_price"));
        String order_address="";
        String order_credit = "";
        String delivery_date = "";
        String new_product_id = request.getParameter("product_id"); 
        


        //utility.storePayment(orderId,oi.getName(),oi.getPrice(),userName,userAddress,creditCardNo);
		
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
            try
			{
                orderPayments=MySqlDataStoreUtilities.selectOrder();

				// FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
				// ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				// orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
        }catch(Exception e)
        {
        
        }
		
		
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
            for(OrderPayment oi:entry.getValue())
            {
                if(oi.getUserName().equals(userName) && oi.getOrderId() == order_id){
                    order_address = oi.getUserAddress();
                    order_credit = oi.getCreditCardNo();
                    delivery_date = oi.getDeliveryDate();
                }
            }
        }

        HttpSession session=request.getSession(); 	
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Product List</a>");
        pw.print("</h2><div class='entry'>");
        if(order_address!="" && order_credit!=""){
            utility.storePayment(order_id,order_name,order_price,userName,order_address,order_credit,delivery_date);
            pw.print("<h4 style='color:red'>New Product Has been addded to your order "+order_id+" </h4>");
        } else {
            pw.print("<h4 style='color:red'>Something Went Wrong</h4>");
        }

        pw.print("</h2></div></div></div>");		
        utility.printHtml("Footer.html");	    

        








    }
	protected void showproductList(PrintWriter pw,String order_id, String user_id) throws ServletException, IOException {

		pw.print("<br><br><br><br><br><h3>Product List</h3>");
		pw.print("<div style='overflow: auto;height: 500px; class='entry'><table class='gridtable' '>");
		pw.print("<tr>");
		pw.print("<td>Name</td><td>Price</td><td>Manufracturer</td><td>Category</td><td colspan=2></td>");
		pw.print("</tr>");

		HashMap<String, FitnessWatch> hm_fit = new HashMap<String, FitnessWatch>();
		hm_fit.putAll(SaxParserDataStore.fitnesswatches);
		for(Map.Entry<String, FitnessWatch> entry : hm_fit.entrySet()){
            FitnessWatch fitnesswatch = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
            pw.print("<tr>");
			pw.print("<td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getRetailer()+"</td><td>Fitness Watch</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+fitnesswatch.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+fitnesswatch.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+fitnesswatch.getPrice()+"'></td>");
			pw.print("</tr></form>");
        }

		HashMap<String, SmartWatch> hm_smart = new HashMap<String, SmartWatch>();
		hm_smart.putAll(SaxParserDataStore.smartwatches);
		for(Map.Entry<String, SmartWatch> entry : hm_smart.entrySet()){
            SmartWatch smartwatch = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+smartwatch.getName()+"</td><td>"+smartwatch.getPrice()+"</td><td>"+smartwatch.getRetailer()+"</td><td>Smart Watch</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+smartwatch.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+smartwatch.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+smartwatch.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}

		HashMap<String, TV> hm_tv = new HashMap<String, TV>();
		hm_tv.putAll(SaxParserDataStore.TVs);
		for(Map.Entry<String,TV> entry : hm_tv.entrySet()){
            TV TV = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getRetailer()+"</td><td>TV</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+TV.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+TV.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+TV.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}

		HashMap<String, Headphone> hm_head = new HashMap<String, Headphone>();
		hm_head.putAll(SaxParserDataStore.headphones);
		for(Map.Entry<String, Headphone> entry : hm_head.entrySet()){
            Headphone headphone = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+headphone.getName()+"</td><td>"+headphone.getPrice()+"</td><td>"+headphone.getRetailer()+"</td><td>Head Phones</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+headphone.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+headphone.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+headphone.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}

		HashMap<String, Phone> hm_phones = new HashMap<String, Phone>();
		hm_phones.putAll(SaxParserDataStore.phones);
		for(Map.Entry<String, Phone> entry : hm_phones.entrySet()){
            Phone phone = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+phone.getName()+"</td><td>"+phone.getPrice()+"</td><td>"+phone.getRetailer()+"</td><td>Phones</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+phone.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+phone.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+phone.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}
		HashMap<String, SoundSystem> hm_sound = new HashMap<String, SoundSystem>();
		hm_sound.putAll(SaxParserDataStore.soundsystems);
		for(Map.Entry<String, SoundSystem> entry : hm_sound.entrySet()){
            SoundSystem soundsystem = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+soundsystem.getName()+"</td><td>"+soundsystem.getPrice()+"</td><td>"+soundsystem.getRetailer()+"</td><td>Sound Systems</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+soundsystem.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+soundsystem.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+soundsystem.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}

		HashMap<String, Laptop> hm_laptop = new HashMap<String, Laptop>();
		hm_laptop.putAll(SaxParserDataStore.laptops);
		for(Map.Entry<String, Laptop> entry : hm_laptop.entrySet()){
            Laptop laptop = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getRetailer()+"</td><td>Laptop</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+laptop.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+laptop.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+laptop.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}
		HashMap<String, VoiceAssistant> hm_voice = new HashMap<String, VoiceAssistant>();
		hm_voice.putAll(SaxParserDataStore.voiceassistants);
		for(Map.Entry<String, VoiceAssistant> entry : hm_voice.entrySet()){
            VoiceAssistant voiceassistant = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getRetailer()+"</td><td>Voice Assistant</td>");
		    pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+voiceassistant.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+voiceassistant.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+voiceassistant.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}

		HashMap<String, Wirelessplan> hm_wireless = new HashMap<String, Wirelessplan>();
		hm_wireless.putAll(SaxParserDataStore.wirelessplans);
		for(Map.Entry<String, Wirelessplan> entry : hm_wireless.entrySet()){
            Wirelessplan wirelessplan = entry.getValue();
            pw.print("<form method='post' action='modifyorder'>");
			pw.print("<tr>");
			pw.print("<td>"+wirelessplan.getName()+"</td><td>"+wirelessplan.getPrice()+"</td><td>"+wirelessplan.getRetailer()+"</td><td>Wireless Plans</td>");
            pw.print("<td><input type='submit' value='add this product to order'>");
            pw.print("<input type='hidden' name='order_id' value='"+order_id+"'><input type='hidden' name='user_id' value='"+user_id+"'>");
            pw.print("<input type='hidden' name='product_id' value='"+wirelessplan.getId()+"'>");
            pw.print("<input type='hidden' name='product_name' value='"+wirelessplan.getName()+"'>");
            pw.print("<input type='hidden' name='product_price' value='"+wirelessplan.getPrice()+"'></td>");
			pw.print("</tr></form>");
		}
		pw.print("</table></div>");
	
	}


}




