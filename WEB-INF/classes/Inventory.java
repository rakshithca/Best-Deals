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

@WebServlet("/inventory")

public class Inventory extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String sale = request.getParameter("sale");
		String rebate = request.getParameter("rebate");
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
			pw.print("<a style='font-size: 24px;'>Inventory List for all Products</a>");
			pw.print("</h2><div class='entry' style='overflow: auto;height: 64em;'>");
			pw.print("<table class='gridtable' >");
			pw.print("<tr>");
			if(sale!=null &&  sale.equals("sale")){
				pw.print("<td>Type</td><td>Name</td><td>Price</td><td>Sale</td>");
			} else if(rebate!=null && rebate.equals("yes")){
				pw.print("<td>Type</td><td>Name</td><td>Price</td><td>Rebate</td>");
			}else{
				pw.print("<td>Type</td><td>Name</td><td>Price</td><td>Count</td>");
			}
			
			pw.print("</tr>");
			

			HashMap<String, TV> hm_tv = new HashMap<String, TV>();
			hm_tv.putAll(MySqlDataStoreUtilities.getTVs());
			for(Map.Entry<String,TV> entry : hm_tv.entrySet()){
				TV TV = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale") ){
					pw.print("<td>TV</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes")){
					if(TV.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>TV</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getRebate()+"</td>");
					}
				} else {
					pw.print("<td>TV</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}




			HashMap<String, SoundSystem> hm_s = new HashMap<String, SoundSystem>();
			hm_s.putAll(MySqlDataStoreUtilities.getSoundSystems());
			for(Map.Entry<String,SoundSystem> entry : hm_s.entrySet()){
				SoundSystem TV = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>SoundSystem</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes") ){
					if(TV.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>SoundSystem</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>SoundSystem</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}

			HashMap<String, Phone> hm_phone = new HashMap<String, Phone>();
			hm_phone.putAll(MySqlDataStoreUtilities.getPhones());
			for(Map.Entry<String,Phone> entry : hm_phone.entrySet()){
				Phone PHONE = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>PHONE</td><td>"+PHONE.getName()+"</td><td>"+PHONE.getPrice()+"</td><td>"+PHONE.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes")){
					if(PHONE.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>PHONE</td><td>"+PHONE.getName()+"</td><td>"+PHONE.getPrice()+"</td><td>"+PHONE.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>PHONE</td><td>"+PHONE.getName()+"</td><td>"+PHONE.getPrice()+"</td><td>"+PHONE.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}

			HashMap<String, Laptop> hm_laptop = new HashMap<String, Laptop>();
			hm_laptop.putAll(MySqlDataStoreUtilities.getLaptops());
			for(Map.Entry<String,Laptop> entry : hm_laptop.entrySet()){
				Laptop laptop = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>laptop</td><td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes")){
					if(laptop.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>laptop</td><td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>laptop</td><td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}

			HashMap<String, VoiceAssistant> hm_voiceassistant = new HashMap<String, VoiceAssistant>();
			hm_voiceassistant.putAll(MySqlDataStoreUtilities.getVoiceAssistants());
			for(Map.Entry<String,VoiceAssistant> entry : hm_voiceassistant.entrySet()){
				VoiceAssistant voiceassistant = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>voiceassistant</td><td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes") ){
					if(voiceassistant.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>voiceassistant</td><td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getRebate()+"</td>");
					}
				}else{
					pw.print("<td>voiceassistant</td><td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}

			HashMap<String, FitnessWatch> hm_fitnesswatch = new HashMap<String, FitnessWatch>();
			hm_fitnesswatch.putAll(MySqlDataStoreUtilities.getFitnessWatches());
			for(Map.Entry<String,FitnessWatch> entry : hm_fitnesswatch.entrySet()){
				FitnessWatch fitnesswatch = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>fitnesswatch</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes")){
					if(fitnesswatch.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>fitnesswatch</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>fitnesswatch</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}

			
			HashMap<String, SmartWatch> hm_sw = new HashMap<String, SmartWatch>();
			hm_sw.putAll(MySqlDataStoreUtilities.getSmartWatches());
			for(Map.Entry<String,SmartWatch> entry : hm_sw.entrySet()){
				SmartWatch TV = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>SmartWatch</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getDiscount()+"</td>");
				}else if(rebate!=null && rebate.equals("yes")){
					if(TV.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>SmartWatch</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>SmartWatch</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}


			HashMap<String, Headphone> hm_hp = new HashMap<String, Headphone>();
			hm_hp.putAll(MySqlDataStoreUtilities.getHeadPhones());
			for(Map.Entry<String,Headphone> entry : hm_hp.entrySet()){
				Headphone fitnesswatch = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>HeadPhone</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equals("yes") ){
					if(fitnesswatch.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>HeadPhone</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>HeadPhone</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}


			HashMap<String, Wirelessplan> hm_wp = new HashMap<String, Wirelessplan>();
			hm_wp.putAll(MySqlDataStoreUtilities.getWirelessPlans());
			for(Map.Entry<String,Wirelessplan> entry : hm_wp.entrySet()){
				Wirelessplan fitnesswatch = entry.getValue();
				pw.print("<tr>");
				if( sale!=null && sale.equals("sale")){
					pw.print("<td>WirelessPlan</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getDiscount()+"</td>");
				} else if(rebate!=null && rebate.equalsIgnoreCase("yes")){
					if(fitnesswatch.getRebate().equalsIgnoreCase("yes")){
						pw.print("<td>WirelessPlan</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getRebate()+"</td>");
					}
				} else{
					pw.print("<td>WirelessPlan</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
				}
				pw.print("</tr>");
			}


		} catch(Exception e){

		}
		
	}
	
	
}