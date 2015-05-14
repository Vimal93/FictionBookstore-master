package model;
import java.util.ArrayList;
import java.util.List;


public class Cart {
	   private int userId;
	   private String userName;
	   private ArrayList<Product> products;
	   private double tax;
	   private double shippingCost;
	   private double totalPrice;
	   private double totalCost;
	   
	   public Cart()
	   {
		   this.userId = -1;
		   this.products=new ArrayList<Product>();
		   this.tax=0;
		   this.shippingCost=0;
		   this.totalPrice=0;
	   }

	   /*public Cart(int buyerId)
	   {
		   this.userId = buyerId;
		   this.products=new ArrayList<Product>();
		   this.tax=0;
		   this.shippingCost=0;
		   this.totalPrice=0;
	   }*/
	   
	   public Cart(int buyerId, String userName,ArrayList<Product> p, double tax, double shippingCost, double totalCost)
	   {
		   this.userName=userName;
		   this.userId = buyerId;
		   this.products=p;
		   this.tax=tax;
		   this.shippingCost=shippingCost;
		   this.totalCost=totalCost;		   
	   }
	   
	   public void addBooks(Product product, int q)
	   {
		 product.setQuantity(q);
		 products.add(product);
		 totalPrice= totalPrice+(product.getQuantity()*product.getUnitPrice());
		 calculateTotalCost();
		}
	   
	   public void removeBooks(Product product)
	   {
		 totalPrice=totalPrice-(product.getQuantity()*product.getUnitPrice());
		 products.remove(product);
		 calculateTotalCost();		 
	   }
	   
	   public void updateQuantity(Product product, int q)
	   {
		  totalPrice=totalPrice-(product.getQuantity()*product.getUnitPrice());
		  product.setQuantity(q);
		  totalPrice= totalPrice+(product.getQuantity()*product.getUnitPrice());
		  calculateTotalCost();		  
	   }
	   
	   public void calculateTotalCost()
	   {
		   tax=0.08*totalPrice;
		   totalCost=totalPrice+tax;
	   }
	   
	   /**
		 * Getters and Setters:
	   */
	   
	   /** Getter and setter for userId **/
	   
	   public int getUserId()
	   {
		   return userId;
	   }
	   
	   public void setUserId(int userId)
	   {
		   this.userId=userId;
	   }
	   
	   /** Getter and setter for products **/
	   
	   public ArrayList<Product> getProducts()
	   {
		   return products;
	   }
	   
	   public void setProducts(ArrayList<Product> products)
	   {
		   this.products=products;
	   }
	   
	   /**Getter and setter for tax**/
	   
	   public double getTax()
	   {
		   return tax;
	   }
	   
	   public void setTax(double tax)
	   {
		   this.tax=tax;
	   }
	   
	   /**Getter and setter for shipping cost**/
	   
	   public double getShippingCost()
	   {
		  return shippingCost; 
	   }
	   
	   public void setShippingCost(double shippingCost)
	   {
		   this.shippingCost=shippingCost;
	   }
	   
	   /**Getter and setter for total price**/
	   
	   public double getTotalPrice()
	   {
		   return totalPrice;
	   }
	   
	   public void setTotalPrice(double totalPrice)
	   {
		   this.totalPrice=totalPrice;
	   }
	   
	   /**Getter and setter for total price**/
	   
	   public double getTotalCost()
	   {
		   return totalCost;
	   }
	   
	   public void setTotalCost(double totalCost)
	   {
		   this.totalCost=totalCost;
	   }
	   
	   /**Getter and setter for username**/
	   
	   public String getUserName()
	   {
		   return userName;
	   }
	   
	   public void setUserName(String userName)
	   {
		   this.userName=userName;
	   }
}