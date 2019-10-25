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

@WebServlet("/deleteproduct")

public class deleteproduct extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String product_id = request.getParameter("product_id");
        String display_under = request.getParameter("type");
       
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
                case "TV" : 
                            SaxParserDataStore.TVs.remove(product_id);
                            break;
                case "fitnesswatch" : 
                            SaxParserDataStore.fitnesswatches.remove(product_id);
                            break;
                case "soundsystem" : 
                            SaxParserDataStore.soundsystems.remove(product_id);
                            break;
                case "smartwatch" : 
                            SaxParserDataStore.smartwatches.remove(product_id);
                            break;
                case "phone" : 
                            SaxParserDataStore.phones.remove(product_id);
                            break;
                case "laptop" : 
                            SaxParserDataStore.laptops.remove(product_id);
                            break;
                case "voiceassistant" : 
                            SaxParserDataStore.voiceassistants.remove(product_id);
                            break;
                case "wirelessplan" : 
                            SaxParserDataStore.wirelessplans.remove(product_id);
                            break;
                case "headphone" : 
                            SaxParserDataStore.headphones.remove(product_id);
                            break;
            }
            try{
                MySqlDataStoreUtilities.deleteproducts(product_id);
                pw.print("<script>var r = confirm('Product is deleted successfully');if(r==true || r==false){window.location.href ='Home';}</script>");
            } catch(Exception e){
                pw.print("<script>var r = confirm('Product is not deleted');if(r==true || r==false){window.location.href ='Home';}</script>");
            }
           
            
        }
		
	}
	
	
}

