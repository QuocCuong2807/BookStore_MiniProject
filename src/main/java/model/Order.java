package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Order {
	private String orderId;
	private Customer customer;
	private String address;
	private String phoneNumber;
	private int orderStatus;
	private String paymentMethod;
	private Timestamp orderDate;
	private String note;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(String orderId) {
		super();
		this.orderId = orderId;
	}

	public Order(String orderId, Customer customer, String address, String phoneNumber, int orderStatus,
			String paymentMethod, Timestamp orderDate, String note) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.orderDate = orderDate;
		this.note = note;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	
}

