package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;

public class OrderDAO implements DAOInterface<Order>{
	
	public static OrderDAO getInstance() {
		return new OrderDAO();
	}
	
	@Override
	public Order selectById(Order t) {
		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM orders WHERE orderId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getOrderId());
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				t.setOrderId(rs.getString("orderId"));
				t.setAddress(rs.getString("address"));
				t.setPhoneNumber(rs.getString("phoneNumber"));
				t.setOrderStatus(rs.getInt("orderStatus"));
				t.setPaymentMethod(rs.getString("paymentMethod"));
				t.setOrderDate(rs.getTimestamp("orderDate"));
				t.setNote(rs.getString("note"));
				String customerId = rs.getString("customerId");
				Customer customer = new Customer(customerId);
				t.setCustomer(CustomerDAO.getInstance().selectById(customer));
				
				JDBCUtil.closeConnection(c);
				return t;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> selectAll() {
		
		List<Order> list = new ArrayList<>();	
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM orders";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String orderId = rs.getString("orderId");
				
				String customerId = rs.getString("customerId");
				Customer customer = new Customer(customerId);
				customer = CustomerDAO.getInstance().selectById(customer);
				
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				int orderStatus = rs.getInt("orderStatus");
				String paymentMethod = rs.getString("paymentMethod");
				Timestamp orderDate = rs.getTimestamp("orderDate");
				String note = rs.getString("note");
				
				Order order = new Order(orderId, customer, address, phoneNumber, orderStatus, paymentMethod, orderDate, note);
				list.add(order);
				JDBCUtil.closeConnection(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insert(Order t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "INSERT INTO orders(orderId, customerId, address, phoneNumber, orderStatus, paymentMethod, orderDate, note) "
				+ "Values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getOrderId());
			st.setString(2, t.getCustomer().getCustomerId());
			st.setString(3, t.getAddress());
			st.setString(4, t.getPhoneNumber());
			st.setInt(5, t.getOrderStatus());
			st.setString(6, t.getPaymentMethod());
			st.setTimestamp(7, t.getOrderDate());
			st.setString(8, t.getNote());
			
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteById(Order t) {
		
		Connection c = JDBCUtil.getConnection();
		String sql = "DELETE FROM orders WHERE orderId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getOrderId());
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteAllbyCustomerId(Customer customer) {
		
		Connection c = JDBCUtil.getConnection();
		String sql = "DELETE FROM orders WHERE ordersId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, customer.getCustomerId());
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateById(Order t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "UPDATE orders SET address = ? "
									+ "phoneNumber = ? "
									+ "orderStatus = ? "
									+ "paymentMethod = ? "
									+ "orderDate = ? "
									+ "note = ? "
					+ "WHERE orderId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getAddress());
			st.setString(2, t.getPhoneNumber());
			st.setInt(3, t.getOrderStatus());
			st.setString(4, t.getPaymentMethod());
			st.setTimestamp(5, t.getOrderDate());
			st.setString(6, t.getNote());
			st.setString(7, t.getOrderId());
			
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
