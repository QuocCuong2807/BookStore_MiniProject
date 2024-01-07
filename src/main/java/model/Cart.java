package model;

import java.security.KeyStore.Entry;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.UnknownFormatFlagsException;

import javax.print.attribute.standard.DateTimeAtCompleted;

import database.BookDAO;

public class Cart {
	private String orderId;
	private String customerId;
	private String address;
	private String phoneNumber;
	private int orderStatus;
	private String paymentMethod;
	private Timestamp orderDate;
	private String note;
	public Map<String, OrderDetails> cart ;

	public Cart() {
		super();
		this.orderId = "";
		this.customerId = "";
		this.address = "";
		this.phoneNumber = "";
		this.orderStatus = 0;
		this.paymentMethod = "ShipCod";
		this.orderDate = new Timestamp(System.currentTimeMillis());
		this.note = "";
		this.cart = new HashMap<String, OrderDetails>();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	public boolean isEmptyCart(String bookId) {
		if(cart.containsKey(bookId))
			return true;
		return false;
	}
	
	public void addBookToCart(String bookId) {
		if(cart.containsKey(bookId)) {
			//plus book quantity in OrderDetail
			
			cart.get(bookId).plusQuantity();
		}else {
			//create initial OrderDetail 
			OrderDetails orderDetail = new OrderDetails();
			Book book = BookDAO.getInstance().selectById(new Book(bookId));
			orderDetail.setQuantity(1);
			orderDetail.setBook(book);
			orderDetail.setDiscount(book.getDiscount());
			orderDetail.setBookPice(book.getBookPrice());
			
			//put to cart Map
			cart.put(bookId, orderDetail);
		}
	}
	
	public void deleteBook(String bookId) {
		if(cart.containsKey(bookId))
			cart.remove(bookId);
	}
	
	public void minusQuantity(String bookId) {
		if(cart.containsKey(bookId)) {
			if(cart.get(bookId).getQuantity() > 1)
				cart.get(bookId).minusQuantity();
			else
				deleteBook(bookId);
		}
	}
	
	public int getPrice(String bookId) {
		return cart.get(bookId).getBookPice() * cart.get(bookId).getQuantity() - ((cart.get(bookId).getBookPice() * cart.get(bookId).getQuantity() * cart.get(bookId).getDiscount()/100));
	}
	
	public int getTotalPrice() {
		int total = 0;
		
		for(Map.Entry<String, OrderDetails> entry : cart.entrySet()) {
			total += getPrice(entry.getKey());
		}
		
		return total;
	}
	
}
