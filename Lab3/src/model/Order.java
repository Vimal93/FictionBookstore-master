package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Order {
	private Cart c;
	private int orderId;
	private Date datetime; 
	private int status;
	private String shippingAddress;
	
	public Order()
	{
		this.c=null;
		this.orderId=0;
		this.status=0;
		this.shippingAddress="";		
	}
        
    public Order(Cart c) 
    {
    	this.c=c;
    	this.orderId=0;
    	this.datetime=null;
    	this.status=1;
    	this.shippingAddress="";
    }
    
    public Order(int orderId, int buyerId, String buyerName,Date datetime,ArrayList<Product> p, double tax, double shippingCost, double totalCost,String s, int status)
    {
    	this.orderId=orderId;
    	this.c=new Cart(buyerId,buyerName,p,tax,shippingCost,totalCost);
    	this.shippingAddress=s;
    	this.datetime=datetime;
    	this.status=status;
    }
    
   
    public void sendNotifications()
    {
    	
    }
    
    public void addProduct(Product p, int q)
    {
    	c.addBooks(p, q);   
    }
    
    public void removeProduct(Product p)
    {
    	c.removeBooks(p);
    }
    
    public void updateQuant(Product p, int q)
    {
    	c.updateQuantity(p, q);
    }
    
    //Getter and Setter for cart
    
    public Cart getC()
    {
    	return c;
    }
    
    public void setC(Cart c)
    {
    	this.c=c;
    }
    
    //Getter and setter for orderId
    
    public int getOrderId()
    {
    	return orderId;
    }
    
    public void setOrderId(int orderId)
    {
    	this.orderId=orderId;
    }
    
    //Getter and Setter for status
    
    public int getStatus()
    {
    	return status;
    }
    
    public void setStatus(int status)
    {
    	this.status=status;
    }
    
    //Getter and Setter for shippingAddress
    
    public String getShippingAddress()
    {
    	return shippingAddress;
    }
    
    public void setShippingAddress(String shippingAddress)
    {
    	this.shippingAddress=shippingAddress;
    }
    
    //Getter and Setter for datetime
    
    public Date getDateTime()
    {
    	return datetime;
    }
    
    public void setDateTime(Date datetime)
    {
    	this.datetime=datetime;
    }   
}
