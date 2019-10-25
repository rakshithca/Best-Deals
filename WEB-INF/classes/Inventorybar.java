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

@WebServlet("/inventorybar")

public class Inventorybar extends HttpServlet {
	
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
			pw.print("<a style='font-size: 24px;'>Inventory List for all Products</a>");
			pw.print("</h2><div class='entry' style='overflow: auto;height: 64em;'>");
			pw.print("<table class='gridtable' >");
		
			
			pw.println("<section id=\"content\">");
            pw.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
                    pw.println("<script type=\"text/javascript\">");
                    pw.println("google.charts.load('current', {'packages':['bar']});");
                    pw.println("google.charts.setOnLoadCallback(drawStuff);");
                    pw.println("function drawStuff() {");
                    pw.println("var data = new google.visualization.arrayToDataTable([");
                    pw.println("['Category', 'Items available'],");
					
					HashMap<String, TV> hm_tv = new HashMap<String, TV>();
					hm_tv.putAll(MySqlDataStoreUtilities.getTVs());
					for(Map.Entry<String,TV> entry : hm_tv.entrySet()){
							TV TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, SoundSystem> hm_ss = new HashMap<String, SoundSystem>();
					hm_ss.putAll(MySqlDataStoreUtilities.getSoundSystems());
					for(Map.Entry<String,SoundSystem> entry : hm_ss.entrySet()){
							SoundSystem TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, Phone> hm_ph = new HashMap<String, Phone>();
					hm_ph.putAll(MySqlDataStoreUtilities.getPhones());
					for(Map.Entry<String,Phone> entry : hm_ph.entrySet()){
							Phone TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, Laptop> hm_lp = new HashMap<String, Laptop>();
					hm_lp.putAll(MySqlDataStoreUtilities.getLaptops());
					for(Map.Entry<String,Laptop> entry : hm_lp.entrySet()){
							Laptop TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, VoiceAssistant> hm_va = new HashMap<String, VoiceAssistant>();
					hm_va.putAll(MySqlDataStoreUtilities.getVoiceAssistants());
					for(Map.Entry<String,VoiceAssistant> entry : hm_va.entrySet()){
							VoiceAssistant TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, FitnessWatch> hm_fw = new HashMap<String, FitnessWatch>();
					hm_fw.putAll(MySqlDataStoreUtilities.getFitnessWatches());
					for(Map.Entry<String,FitnessWatch> entry : hm_fw.entrySet()){
						FitnessWatch TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, SmartWatch> hm_sw = new HashMap<String, SmartWatch>();
					hm_sw.putAll(MySqlDataStoreUtilities.getSmartWatches());
					for(Map.Entry<String,SmartWatch> entry : hm_sw.entrySet()){
							SmartWatch TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, Headphone> hm_hp = new HashMap<String, Headphone>();
					hm_hp.putAll(MySqlDataStoreUtilities.getHeadPhones());
					for(Map.Entry<String,Headphone> entry : hm_hp.entrySet()){
						Headphone TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}

					HashMap<String, Wirelessplan> hm_wp = new HashMap<String, Wirelessplan>();
					hm_wp.putAll(MySqlDataStoreUtilities.getWirelessPlans());
					for(Map.Entry<String,Wirelessplan> entry : hm_wp.entrySet()){
						Wirelessplan TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}
					
					HashMap<String, Accessory> hm_aa = new HashMap<String, Accessory>();
					hm_aa.putAll(MySqlDataStoreUtilities.getAccessories());
					for(Map.Entry<String,Accessory> entry : hm_aa.entrySet()){
							Accessory TV = entry.getValue();
							pw.println("[\""+TV.getName()+"\","+TV.getQuantity()+"],");
					}
					



                    
                    pw.println("]);"); 
                                                                                                        
                    pw.println("var options = { ");
                    pw.println("width: 1000,");
                    pw.println("chart: {");
                    pw.println("title: 'Inventory Details',");
                    pw.println("},");
                    pw.println("bars: 'horizontal',");
                    pw.println("series: {");
                    pw.println("0: { axis: 'distance' }, ");
                    pw.println("//1: { axis: 'brightness' } ");
                    pw.println("},");
                    pw.println("axes: {");
                    pw.println("x: { ");
                    pw.println("distance: {label: 'No. of Items'},");
                    pw.println("} ");
                    pw.println("} ");
                    pw.println("};");
                    pw.println("var chart = new google.charts.Bar(document.getElementById('dual_x_div'));");
                    pw.println("chart.draw(data, options);");
                    pw.println("};");
                    pw.println("</script>");
                    pw.println("<div id=\"dual_x_div\" style=\"height:60em;margin-left: 4em;\"></div>");
			        pw.println("</section>");        

			// HashMap<String, TV> hm_tv = new HashMap<String, TV>();
			// hm_tv.putAll(MySqlDataStoreUtilities.getTVs());
			// for(Map.Entry<String,TV> entry : hm_tv.entrySet()){
			// 	TV TV = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>TV</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }




			// HashMap<String, SoundSystem> hm_s = new HashMap<String, SoundSystem>();
			// hm_s.putAll(MySqlDataStoreUtilities.getSoundSystems());
			// for(Map.Entry<String,SoundSystem> entry : hm_s.entrySet()){
			// 	SoundSystem TV = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>TV</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }

			// HashMap<String, Phone> hm_phone = new HashMap<String, Phone>();
			// hm_phone.putAll(MySqlDataStoreUtilities.getPhones());
			// for(Map.Entry<String,Phone> entry : hm_phone.entrySet()){
			// 	Phone PHONE = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>PHONE</td><td>"+PHONE.getName()+"</td><td>"+PHONE.getPrice()+"</td><td>"+PHONE.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }

			// HashMap<String, Laptop> hm_laptop = new HashMap<String, Laptop>();
			// hm_laptop.putAll(MySqlDataStoreUtilities.getLaptops());
			// for(Map.Entry<String,Laptop> entry : hm_laptop.entrySet()){
			// 	Laptop laptop = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>laptop</td><td>"+laptop.getName()+"</td><td>"+laptop.getPrice()+"</td><td>"+laptop.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }

			// HashMap<String, VoiceAssistant> hm_voiceassistant = new HashMap<String, VoiceAssistant>();
			// hm_voiceassistant.putAll(MySqlDataStoreUtilities.getVoiceAssistants());
			// for(Map.Entry<String,VoiceAssistant> entry : hm_voiceassistant.entrySet()){
			// 	VoiceAssistant voiceassistant = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>voiceassistant</td><td>"+voiceassistant.getName()+"</td><td>"+voiceassistant.getPrice()+"</td><td>"+voiceassistant.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }

			// HashMap<String, FitnessWatch> hm_fitnesswatch = new HashMap<String, FitnessWatch>();
			// hm_fitnesswatch.putAll(MySqlDataStoreUtilities.getFitnessWatches());
			// for(Map.Entry<String,FitnessWatch> entry : hm_fitnesswatch.entrySet()){
			// 	FitnessWatch fitnesswatch = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>fitnesswatch</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }

			
			// HashMap<String, SmartWatch> hm_sw = new HashMap<String, SmartWatch>();
			// hm_sw.putAll(MySqlDataStoreUtilities.getSmartWatches());
			// for(Map.Entry<String,SmartWatch> entry : hm_sw.entrySet()){
			// 	SmartWatch TV = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>SmartWatch</td><td>"+TV.getName()+"</td><td>"+TV.getPrice()+"</td><td>"+TV.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }


			// HashMap<String, Headphone> hm_hp = new HashMap<String, Headphone>();
			// hm_hp.putAll(MySqlDataStoreUtilities.getHeadPhones());
			// for(Map.Entry<String,Headphone> entry : hm_hp.entrySet()){
			// 	Headphone fitnesswatch = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>HeadPhone</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }


			// HashMap<String, Wirelessplan> hm_wp = new HashMap<String, Wirelessplan>();
			// hm_wp.putAll(MySqlDataStoreUtilities.getWirelessPlans());
			// for(Map.Entry<String,Wirelessplan> entry : hm_wp.entrySet()){
			// 	Wirelessplan fitnesswatch = entry.getValue();
			// 	pw.print("<tr>");
			// 	pw.print("<td>WirelessPlan</td><td>"+fitnesswatch.getName()+"</td><td>"+fitnesswatch.getPrice()+"</td><td>"+fitnesswatch.getQuantity()+"</td>");
			// 	pw.print("</tr>");
			// }


		} catch(Exception e){

		}
		// try{
		// 	HttpSession session = request.getSession(false);
			
		// 	Map<String,Product> pMap = MySQLDataStoreUtilities.getProductsinventorygraph();
			
		// 			PrintWriter out = response.getWriter();
		// 			pw.println("<!doctype html>");
		// 			pw.println("<html>");
		// 			pw.println("<head>");
		// 			pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		// 			pw.println("<title>Smart Portables</title>");    
		// 			pw.println("<link rel=\"stylesheet\" href=\"megha.css\" type=\"text/css\" />");				
		// 			pw.println("</head>");
		// 			pw.println("<body>");
		// 			pw.println("<div id=\"screen\">");
		// 			pw.println("<header>");
		// 			pw.println("<h1 align=\"left\">");
                    
		// 			pw.println("<img src=\"images/logo.png\" alt=\"SmartPortables\" style=\"width:170px;height:70px;\" align = \"right\">");
        //             pw.println("<a href=\"Index.html\"><p style=\"margin-left: 7.45em\"><font color= \"9CB814\" ><b style=\"font-family: Verdana\">SMART PORTABLES</font></b>");
        //             pw.println("</p></a>");
        //             pw.println("</h1>");
        //             pw.println("<br>");
        //             pw.println("<h2 align=\"left\"><p style=\"margin-left: 16em\"><font  color=\"051703\" style =\"font-family: Helvetica\" ><b>Smart Portables company</b></font></p><br></h2>");
        //             pw.println("<br>");
		// 			pw.println("<div style=\"width: 960px; height:120px\">");
		// 			 if(session==null){
		// 				pw.println("<h1><a href=\"index.html\">Smart <span> Portables</span></a></h1><br>");
		// 			}
					 
		// 			else{
		// 					String userTypeInfo = (String)session.getAttribute("userTypeInfo");
		// 					pw.println("<h1><a href=\"storemanager.html\">Smart <span> Portables</span></a></h1><br>");
		// 				}
					
		// 			pw.println("<form id=\"searchbox\" action=\"#\">");
		// 			pw.println("<input class=\"search_input\" id=\"search\" type=\"text\" placeholder=\"Search items..\" >");
		// 			pw.println("<input id=\"submit\" type=\"submit\" value=\"Search\" class=\"searchbutton\">");
		// 			pw.println("<ul class=\"navigation\">");
		// 			if(session==null){
		// 				pw.println("<li >");
		// 				pw.println("<a href=\"#\" style=\"width: 90px; text-align: center;\">Sign In&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
		// 				pw.println("<ul>");
		// 				pw.println("<li><a href=\"login.html\">Sign In</a></li>");
		// 				pw.println("<li><a href=\"login.html\">Current Orders</a></li>");				    
		// 				pw.println("</ul>");
		// 				pw.println("</li>");
		// 			}
		// 			else{
		// 				pw.println("<li >");
		// 				pw.println("<a href=\"#\" style=\"width: 90px; text-align: center;\">Sign Out&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
		// 				pw.println("<ul>");
		// 				pw.println("<li><a href=\"logout\">Sign Out</a></li>");
		// 				pw.println("<li><a href=\"displaycurrentorders\">Current Orders</a></li>");					       
		// 				pw.println("</ul>");
		// 				pw.println("</li>");
		// 			}
		// 			pw.println("<li >");
		// 			pw.println("<a href=\"showshoppingcartinfo\" style=\"width: 60px; text-align: center;\">Shopping Cart</a>");
		// 			pw.println("</li>");
		// 			pw.println("</ul>");
		// 			pw.println("</form>");
		// 			pw.println("</div>");
		// 			pw.println("</header>");				
		// 			pw.println("<nav>");
		// 			pw.println("<ul>");
		// 			pw.println("<li class=\"mainmenu\"><a href=\"addnewproduct.html\">Add Product</a></li>");
		// 		    pw.println(" <li class=\"mainmenu\"><a href=\"addnewwarranty.html\">Add Warranty</a></li>");
		// 		    pw.println(" <li class=\"mainmenu\"><a href=\"addnewaccessory.html\">Add New Accessory</a></li>");
		// 			pw.println("</ul>");
		// 			pw.println("</nav>");
		// 	        pw.println("<section id=\"content\">");
        //             pw.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        //             pw.println("<script type=\"text/javascript\">");
        //             pw.println("google.charts.load('current', {'packages':['bar']});");
        //             pw.println("google.charts.setOnLoadCallback(drawStuff);");
        //             pw.println("function drawStuff() {");
        //             pw.println("var data = new google.visualization.arrayToDataTable([");
        //             pw.println("['Category', 'Items available'],");
                    
        //             for (Map.Entry<String, Product> entry : pMap.entrySet()) 
        //             {
        //             	Product product = entry.getValue();
        //             	pw.println("['"+product.getDisplay_under()+"', "+product.getProdcount()+"],");       
        //              } 
        //             pw.println("]);"); 
                                                                                                        
        //             pw.println("var options = { ");
        //             pw.println("width: 600,");
        //             pw.println("chart: {");
        //             pw.println("title: 'Inventory Details',");
        //             pw.println("},");
        //             pw.println("bars: 'horizontal',");
        //             pw.println("series: {");
        //             pw.println("0: { axis: 'distance' }, ");
        //             pw.println("//1: { axis: 'brightness' } ");
        //             pw.println("},");
        //             pw.println("axes: {");
        //             pw.println("x: { ");
        //             pw.println("distance: {label: 'No. of Items'},");
        //             pw.println("} ");
        //             pw.println("} ");
        //             pw.println("};");
        //             pw.println("var chart = new google.charts.Bar(document.getElementById('dual_x_div'));");
        //             pw.println("chart.draw(data, options);");
        //             pw.println("};");
        //             pw.println("</script>");
        //             pw.println("<div id=\"dual_x_div\" style=\"width: 600px; height: 500px;\"></div>");
		// 	        pw.println("</section>");
		// 			pw.println("<aside class=\"sidebar\">");
		// 			pw.println("<ul>	");
		// 			pw.println("<li>");
		// 			pw.println("<h4>Manage Products</h4>");
		// 			pw.println("<ul>");
		// 			pw.println("<li><a href=\"modifyproduct.html\">Modify Products</a></li>");
		// 			pw.println("<li><a href=\"deleteproduct.html\">Delete Products</a></li>");
		// 			pw.println("<li><a href=\"deletewarranty.html\">Delete Warranty</a></li>");
		// 			pw.println("</ul>");
		// 			pw.println("</li>");
		// 			pw.println("<li>");
		// 			pw.println("<h4>Inventory Information</h4>");
		// 			pw.println("<ul>");
		// 			pw.println("<li><a href=\"inventory\">Inventory Report</a></li>");
		// 			pw.println("<li><a href=\"inventorybar\">Inventory Graph Report</a></li>");
		// 			pw.println("<li><a href=\"sales\">Products on Sale</a></li>");
		// 			pw.println("<li><a href=\"prodrebate\">Products with Rebate Amount</a></li>");
		// 			pw.println("</ul>");
		// 			pw.println("</li>");
		// 			pw.println("<li>");
		// 			pw.println("<h4>Sales Information</h4>");
		// 			pw.println("<ul>");
		// 			pw.println("<li><a href=\"sales\">Total Sales Report</a></li>");
		// 			pw.println("<li><a href=\"salesgraph\">Sales Graph Report</a></li>");
		// 			pw.println("<li><a href=\"salesdaily\">Daily Sales Report</a></li>");
		// 			pw.println("</ul>");
		// 			pw.println("</li>");
		// 			pw.println("</ul>");
		// 			pw.println("</aside>");
		// 			pw.println("<div class=\"clear\"></div>");				    
		// 			pw.println("<footer>");	
		// 			pw.println("<div class=\"footer-bottom\"  style=\"font-family: Helvetica\">");
		// 			pw.println("<p>Prices and Offers are subject to change. SmartPortables..All rights reserved!!</p>");
		// 			pw.println("</div>");
		// 			pw.println("</footer>");
		// 			pw.println("</div>");
		// 			pw.println("</body>");
		// 			pw.println("</html>");
		// }
		// catch(Exception e){
		// 	e.printStackTrace();
		// }
	}
	
	
}