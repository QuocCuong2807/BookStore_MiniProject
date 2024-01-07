package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.Customer;

public class JDBCUtil {
	
	public static Connection getConnection() {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			String userName = "root";
			String passWord = "Quoccuong2807@";
			String url = "jdbc:mysql://127.0.0.1:3306/bookstore_servlet";
			
			Connection c = DriverManager.getConnection(url, userName, passWord);
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void closeConnection(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Customer cus = new Customer(null, "thangnam235", null, null, false, null, null);
		Customer c = CustomerDAO.getInstance().selectCustomerByUserName(cus);
		System.out.println(c.getCusFullName());
	}

}
