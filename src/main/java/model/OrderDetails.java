package model;

public class OrderDetails {
	private int orderDetailsId; 
	private Order order;
	private Book book;
	private int quantity;
	private int bookPice;
	private int discount;
	
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(int orderDetailsId, Order order, Book book, int quantity, int bookPice, int discount) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.order = order;
		this.book = book;
		this.quantity = quantity;
		this.bookPice = bookPice;
		this.discount = discount;
	}

	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBookPice() {
		return bookPice;
	}

	public void setBookPice(int bookPice) {
		this.bookPice = bookPice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	
	public void plusQuantity() {
		this.quantity++;
	}
	
	public void minusQuantity(){
		this.quantity--;
	}
	
}
