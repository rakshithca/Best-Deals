import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

@WebServlet("/modifyproduct")

public class modifyproduct extends HttpServlet {
	TV TV;
    SoundSystem soundsystem;
    Phone phone;
	Accessory accessory;
	Laptop laptop;
	VoiceAssistant voiceassistant;
	FitnessWatch fitnesswatch;
	SmartWatch smartwatch;
	Headphone headphone;
	Wirelessplan wirelessplan;
		

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{			
			String product_id = request.getParameter("product_id");
			String product_name = request.getParameter("product_name");
			String product_description = request.getParameter("product_description");
			Double price_amt = Double.parseDouble(request.getParameter("price_amt"));	
			Double discount_amt = Double.parseDouble(request.getParameter("discount_amt"));
			String condition_prod = "new";
			String manufacturer_prod = request.getParameter("manufacturer_prod");	
            String display_under = request.getParameter("display_under");	
            String image = request.getParameter("image");	
			if (display_under.equalsIgnoreCase("TV")) 
			{
				TV = new TV();
				TV.setId(product_id);
				TV.setDiscount(discount_amt);
				TV.setCondition(condition_prod);
				TV.setRetailer(manufacturer_prod);
				TV.setName(product_name);
				TV.setPrice(price_amt);
				TV.setImage(image);
				SaxParserDataStore.TVs.put(TV.getId(),TV);
			}
			if (display_under.equalsIgnoreCase("phone"))
			{
				phone = new Phone();
				phone.setId(product_id);
				phone.setDiscount(discount_amt);
				phone.setCondition(condition_prod);
				phone.setRetailer(manufacturer_prod);
				phone.setName(product_name);
				phone.setPrice(price_amt);
				phone.setImage(image);
				SaxParserDataStore.phones.put(phone.getId(),phone);
			}
			if (display_under.equalsIgnoreCase("soundsystem"))
			{
				soundsystem= new SoundSystem();
				soundsystem.setId(product_id);
				soundsystem.setDiscount(discount_amt);
				soundsystem.setCondition(condition_prod);
				soundsystem.setRetailer(manufacturer_prod);
				soundsystem.setName(product_name);
				soundsystem.setPrice(price_amt);
				soundsystem.setImage(image);
				SaxParserDataStore.soundsystems.put(soundsystem.getId(),soundsystem);
			}
			if (display_under.equalsIgnoreCase("laptop"))
			{
				
				laptop= new Laptop();
				laptop.setId(product_id);
				laptop.setDiscount(discount_amt);
				laptop.setCondition(condition_prod);
				laptop.setRetailer(manufacturer_prod);
				laptop.setName(product_name);
				laptop.setPrice(price_amt);
				laptop.setImage(image);
				SaxParserDataStore.laptops.put(laptop.getId(),laptop);
			}
	
			if (display_under.equalsIgnoreCase("voiceassistant"))
			{
				
				voiceassistant= new VoiceAssistant();
				voiceassistant.setId(product_id);
				voiceassistant.setDiscount(discount_amt);
				voiceassistant.setCondition(condition_prod);
				voiceassistant.setRetailer(manufacturer_prod);
				voiceassistant.setName(product_name);
				voiceassistant.setPrice(price_amt);
				voiceassistant.setImage(image);
				SaxParserDataStore.voiceassistants.put(voiceassistant.getId(),voiceassistant);
			}
	
			if (display_under.equalsIgnoreCase("fitnesswatch"))
			{
				
				fitnesswatch= new FitnessWatch();
				fitnesswatch.setId(product_id);
				fitnesswatch.setDiscount(discount_amt);
				fitnesswatch.setCondition(condition_prod);
				fitnesswatch.setRetailer(manufacturer_prod);
				fitnesswatch.setName(product_name);
				fitnesswatch.setPrice(price_amt);
				fitnesswatch.setImage(image);
				SaxParserDataStore.fitnesswatches.put(fitnesswatch.getId(),fitnesswatch);
			}
			
	
			if (display_under.equalsIgnoreCase("smartwatch"))
			{
				//currentElement="smartwatch";
				smartwatch= new SmartWatch();
				smartwatch.setId(product_id);
				smartwatch.setDiscount(discount_amt);
				smartwatch.setCondition(condition_prod);
				smartwatch.setRetailer(manufacturer_prod);
				smartwatch.setName(product_name);
				smartwatch.setPrice(price_amt);
				smartwatch.setImage(image);
				SaxParserDataStore.smartwatches.put(smartwatch.getId(),smartwatch);
			}
	
			if (display_under.equalsIgnoreCase("headphone"))
			{
				//currentElement="headphone";
				headphone = new Headphone();
				headphone.setId(product_id);
				headphone.setDiscount(discount_amt);
				headphone.setCondition(condition_prod);
				headphone.setRetailer(manufacturer_prod);
				headphone.setName(product_name);
				headphone.setPrice(price_amt);
				headphone.setImage(image);
				SaxParserDataStore.headphones.put(headphone.getId(),headphone);
			}
			
			if (display_under.equalsIgnoreCase("wirelessplan"))
			{
				//currentElement="wirelessplan";
				wirelessplan= new Wirelessplan();
				wirelessplan.setId(product_id);
				wirelessplan.setDiscount(discount_amt);
				wirelessplan.setCondition(condition_prod);
				wirelessplan.setRetailer(manufacturer_prod);
				wirelessplan.setName(product_name);
				wirelessplan.setPrice(price_amt);
				wirelessplan.setImage(image);
				SaxParserDataStore.wirelessplans.put(wirelessplan.getId(),wirelessplan);
			}
			PrintWriter pw = response.getWriter();
            pw.print("<script>var r = confirm('Product is modified successfully');if(r==true || r==false){window.location.href ='Home';}</script>");
			response.sendRedirect("Home"); 
			return;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String product_id = request.getParameter("product_id");
        String manufacturer_prod = request.getParameter("maker");
        String display_under = request.getParameter("type");
        String product_name = "";
        // String product_description = request.getParameter("product_description");
        Double price_amt =0.0;	
        Double discount_amt = 0.0;
        String condition_prod = "new";
        String image = "";
        PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
        if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login access this page");
			response.sendRedirect("Login");
		    return;
        }
        if (!utility.usertype().equals("retailer"))
		{
            pw.print("<h4 style='color:red'>You can not perform this action</h4>");
            response.sendRedirect("Home");
		    return;
		} else {
            switch(display_under){
                case "TV" : for(Map.Entry<String,TV> entry : SaxParserDataStore.TVs.entrySet())
                            {
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                                
                            }
                            SaxParserDataStore.TVs.remove(product_id);
                            break;
                case "fitnesswatch" : for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fitnesswatches.entrySet())
                            {
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.fitnesswatches.remove(product_id);
							break;
				case "smartwatch" : for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartwatches.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.smartwatches.remove(product_id);
							break;
				

				case "headphone" : for(Map.Entry<String,Headphone> entry : SaxParserDataStore.headphones.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.headphones.remove(product_id);
							break;
				
				case "phone" : for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.phones.remove(product_id);
							break;
				case "soundsystem" : for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.phones.remove(product_id);
							break;
				
				case "laptop" : for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.laptops.remove(product_id);
							break;
				case "voiceassistant" : for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voiceassistants.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.voiceassistants.remove(product_id);
							break;
				case "wirelessplan" : for(Map.Entry<String,Wirelessplan> entry : SaxParserDataStore.wirelessplans.entrySet())
                        	{
                            if(entry.getValue().getRetailer().equals(manufacturer_prod) && entry.getValue().getId().equals(product_id))
                                {
                                    product_name = entry.getValue().getName();
                                    discount_amt = entry.getValue().getDiscount();
                                    condition_prod = entry.getValue().getCondition();
                                    price_amt = entry.getValue().getPrice();
                                    image=entry.getValue().getImage();
                                }
                            }
                            SaxParserDataStore.wirelessplans.remove(product_id);
							break;
            }
            displayModifyproduct(request,response,product_id,product_name,manufacturer_prod,price_amt,display_under,discount_amt,image);
        }
		// response.setContentType("text/html");
		// PrintWriter pw = response.getWriter();
		// displayAddProduct(request, response);
	}
	
	protected void displayModifyproduct(HttpServletRequest request, HttpServletResponse response, String product_id, String product_name, String manufacturer_prod,Double price_amt, String display_under,Double discount_amt, String image) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login access this page");
				response.sendRedirect("Login");
				return;
			}
		}
		catch(Exception e)
			{
		
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Add Product</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table class='gridtable'>");
			if (!utility.usertype().equals("retailer"))
			{
				pw.print("<h4 style='color:red'>You are not storage manager, you can not access this page</h4>");
			}

            pw.print("<form action='modifyproduct'  method='post'>"
            +"<input type='hidden' name='image' value='"+image+"'>"
			+"<table style='width:60%;margin-left: 49px '>"
			+"<tr>"
			+"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Name of Product:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='product_name' value='"+product_name+"' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			+"<tr>"
		   
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Product ID:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='product_id' value='"+product_id+"' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			
			 +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Manufacturer:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='manufacturer_prod' value="+manufacturer_prod+" class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			 +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Cost:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='price_amt' value="+price_amt+" class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
		   
			  +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Display in:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<select name='display_under' style='width:249px; height:35px' required>" 
			//  +"<option value=''>-----Select----</option>" 
			 +"<option value='TV'>TV</option>"
			 +"<option value='soundsystem'>Sound System</option>"
			 +"<option value='phone'>Phone</option>"
			 +"<option value='laptop'>Laptop</option>"
			 +"<option value='voiceassistant'>Voice Assistant</option>" 
			 +"<option value='voiceassistant'>Voice Assistant</option>" 
			 +"<option value='fitnesswatch'>Fitness Watch</option>" 
			 +"<option value='smartwatch'>Smart Watch</option>" 
			 +"<option value='headphone'>Head Phone</option>" 
			 +"<option value='wirelessplan'>Wireless Plan</option>" 
		   +"</select>" 
			+"</div>"
			+"</td>"
			+"</tr>"
		   
			 +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Discount Amount :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='discount_amt' value="+discount_amt+" class='login-input' style='width:249px; height:24px' />"
			+"</div>"
			+"</td>"
			+"</tr>"
			  +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Rebate Amount :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='rebate_amt' value='0.0' class='login-input' style='width:249px; height:24px' />"
			+"</div>"
			+"</td>"
			+"</tr>"
		   
			+"<tr>"
			+"<td colspan='3' align='center'>"
			+"<div>"
			+"<button class='sign-in-up-button' type='submit'>Update Product</button>"
			+"</div>"
			+"</td>"
			+"</tr>"
			+"</table>"
			+"</form>" 
			+"</div></div></div><script> document.getElementsByName('display_under')[0].value = '"+display_under+"' </script>");
		utility.printHtml("Footer.html");
		 }
	
}

