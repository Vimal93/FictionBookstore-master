package model;

public class Buyer extends User{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private Cart cart;
	private Order order;
	
	
	
	
	public Buyer() {
		this.userId= -1;
		this.userName = "";
		this.accountType = -1;
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.email = "";
		this.cart = null;
		this.order = null;
	}
	
	public Buyer(int userId, String userName, int accountType, String firstName, String middleName, String lastName, String email)
	{
		this.userId=userId;
		this.userName = userName;
		this.accountType = accountType;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.cart = new Cart();
		this.order = new Order(cart);
	}
	
    public void updateOrder(int orderId)
	{
		/*Create a database connection to retrieve the order details*/
		cart=new Cart();
		order=new Order(cart);		
	}
	
	
	/**
	 * Getters and Setters:
	 */
	
	
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the email
	 */
	public String getemail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setemail(String email) {
		this.email = email;
	}
	
	/**
	 * Getters and Setters:
	*/

	/**Getter and Setter of cart**/

	public Cart getCart()
	{
		return cart;
	}

	public void setCart(Cart cart)
	{
		this.cart=cart;
	}

	/**Getter and Setter of order**/

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order=order;
	}

}
