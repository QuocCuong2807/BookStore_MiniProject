package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Customer;
import model.Order;
import model.OrderDetails;

public class OrderDetailsDAO implements DAOInterface<OrderDetails>{

	public static OrderDetailsDAO getInstance() {
		return new OrderDetailsDAO();
	}
	
	@Override
	public OrderDetails selectById(OrderDetails t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM orderdetails WHERE orderDetailsId  = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getOrder().getOrderId());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int orderDetailsId = rs.getInt("orderDetailsId");
				String orderId = rs.getString("orderId");
				Order order = new Order(orderId);
				order = OrderDAO.getInstance().selectById(order);
				String bookId = rs.getString("bookId");
				Book book = new Book(bookId);
				book = BookDAO.getInstance().selectById(book);
				int quantity = rs.getInt("quantity");
				int bookPrice = rs.getInt("bookPrice");
				int discount = rs.getInt("discount");
				OrderDetails orderDetail = new OrderDetails(orderDetailsId, order, book, quantity, bookPrice, discount);
				JDBCUtil.closeConnection(c);
				return orderDetail;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<OrderDetails> selectAll() {
		List<OrderDetails> list = new ArrayList<>();
		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM orderdetails";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int orderDetailsId = rs.getInt("orderDetailsId");
				String orderId = rs.getString("orderId");
				Order order = new Order(orderId);
				order = OrderDAO.getInstance().selectById(order);
				String bookId = rs.getString("bookId");
				Book book = new Book(bookId);
				book = BookDAO.getInstance().selectById(book);
				int quantity = rs.getInt("quantity");
				int bookPrice = rs.getInt("bookPrice");
				int discount = rs.getInt("discount");
				OrderDetails orderDetail = new OrderDetails(orderDetailsId, order, book, quantity, bookPrice, discount);
				list.add(orderDetail);
				JDBCUtil.closeConnection(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public int insert(OrderDetails t) {
		Connection c = JDBCUtil.getConnection();
		String sql = "INSERT INTO orderdetails(orderId, bookId, quantity, bookPrice, discount) "
				+ "VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getOrder().getOrderId());
			st.setString(2, t.getBook().getBookId());
			st.setInt(3, t.getQuantity());
			st.setInt(4, t.getBookPice());
			st.setInt(5, t.getDiscount());
			
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
	public int deleteById(OrderDetails t) {
		
		Connection c = JDBCUtil.getConnection();
		String sql = "DELETE FROM orderdetails WHERE orderDetailsId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getOrderDetailsId());
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteByOrderId(Order order) {
		Connection c = JDBCUtil.getConnection();
		String sql = "DELETE FROM orderdetails WHERE orderId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, order.getOrderId());
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
	public int updateById(OrderDetails t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
