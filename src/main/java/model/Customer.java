package model;

public class Customer {
	private String customerId;
	private String userName;
	private String userPassword;
	private String cusFullName;
	private boolean gender;
	private String phoneNumber;
	private String email;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//customer with id
	public Customer(String customerId) {
		super();
		this.customerId = customerId;
	}

	
	//customer with user name, password
	public Customer(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	//customer with full field
	public Customer(String customerId, String userName, String userPassword, String cusFullName, boolean gender,
			String phoneNumber, String email) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.cusFullName = cusFullName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCusFullName() {
		return cusFullName;
	}

	public void setCusFullName(String cusFullName) {
		this.cusFullName = cusFullName;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
