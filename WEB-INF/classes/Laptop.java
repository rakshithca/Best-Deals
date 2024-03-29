import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Laptop")

/* 
	Laptop class contains class variables name,price,image,retailer,condition,discount.

	Laptop class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Laptop class contains getters and setters for name,price,image,retailer,condition,discount.
*/

public class Laptop extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private int qunatity;
	private String rebate;
	public Laptop(String name, double price, String image, String retailer, String condition,double discount, int qunatity, String rebate){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
		this.qunatity = qunatity;
		this.rebate = rebate;
	}
	
	public Laptop(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getQuantity() {
		return qunatity;
	}

	public void setQuantity(int qunatity) {
		this.qunatity = qunatity;
	}


	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

}
