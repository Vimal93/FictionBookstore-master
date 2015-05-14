package model;

import java.io.Serializable;

public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int itemId; 
	private int sellerId;
	private int isbn;
	private String name;
	private String sellerName;
	private String description; 
	private String author;
	private String genre;
	private String image; 
	private double unitPrice;
	private int quantity;
	
	public Product()
	{
		this.itemId=0000;
		this.sellerId=0000;
		this.isbn=0;
		this.name="";
		this.description="";
		this.author="";
		this.genre="";
		this.image="";
		this.unitPrice=0.00;
		this.quantity=0;
	}
	
	public Product(String name, double unitPrice, int itemId, int sellerId, String sellerName,String description, String image, int isbn,String author, String genre)
	{
		this.itemId=itemId;
		this.sellerId=sellerId;
		this.sellerName=sellerName;
		this.isbn=isbn;
		this.name=name;
		this.description=description;
		this.author=author;
		this.genre=genre;
		this.image=image;
		this.unitPrice= unitPrice;
		this.quantity=0;	//Fix this?
		
	}
	
	public Product(String name, int isbn, String author, double price, String description, String genre)
	{
		this.name= name;
		this.isbn= isbn;
		this.author=author;
		this.unitPrice= price;
		this.description=description;
		this.genre=genre;
	}
	
	public Product(String name, double unitPrice, int itemId, int sellerId,int quantity, String sellerName,String description,String image,int isbn,String author,String genre)
	{
		this.itemId=itemId;
		this.sellerId=sellerId;
		this.sellerName=sellerName;
		this.isbn=isbn;
		this.quantity=quantity;
		this.name=name;
		this.description=description;
		this.author=author;
		this.genre=genre;
		this.image=image;
		this.unitPrice= unitPrice;				
	}
	
	public Product(String name, int itemId, int isbn,String author,double unitPrice, int sellerId)
	{
		this.name=name;
		this.itemId=itemId;
		this.isbn=isbn;
		this.unitPrice=unitPrice;
		this.author=author;
		this.sellerId=sellerId;
				
	}

	/**Getter and Setter of item id**/
	
	public int getItemId()
	{
		return itemId;
	}
	
	public void setItemId(int itemId)
	{
		this.itemId=itemId;
	}

	/**Getter and Setter of seller id**/
		
	public int getSellerId()
	{
		return sellerId;
	}
	
	public void setSellerId(int sellerId)
	{
		this.sellerId=sellerId;
	}
	
	/**Getter and Setter of name**/
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	/**Getter and Setter of isbn**/
	
	public int getIsbn()
	{
		return isbn;
	}
	
	public void setIsbn(int isbn)
	{
		this.isbn=isbn;
	}
	
	/**Getter and Setter of author**/
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author=author;
	}	
	
	/**Getter and Setter of Description**/
    
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	/**Getter and Setter of image**/
	
	public String getImage()
	{
		return image;
	}
	
	public void setImage(String image)
	{
		this.image=image;
	}
	
	/**Getter ansd Setter of unit price**/
	
	public double getUnitPrice()
	{
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice)
	{
		this.unitPrice=unitPrice;
	}
	
	/**Getter and Setter of quantity**/
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	//Getter and Setter for genre
	
	public String getGenre()
	{
		return genre;
	}
	
	public void setGenre(String genre)
	{
		this.genre=genre;
	}
	
	//Getter and Setter for sellerName
	
	public String getSellerName()
	{
		return sellerName;
	}
	
	public void setSellerName(String sellerName)
	{
		this.sellerName=sellerName;
	}
}
