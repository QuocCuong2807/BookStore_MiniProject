package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {

	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}

	@Override
	public Customer selectById(Customer t) {

		Connection c = JDBCUtil.getConnection();

		String sql = "SELECT * FROM customer WHERE customerId = ? ";

		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				t.setCustomerId(rs.getString("customerId"));
				t.setUserName(rs.getString("userName"));
				t.setUserPassword(rs.getString("userPassword"));
				t.setCusFullName(rs.getString("cusFullName"));
				t.setGender(rs.getBoolean("gender"));
				t.setPhoneNumber(rs.getString("phoneNumber"));
				t.setEmail(rs.getString("email"));

				JDBCUtil.closeConnection(c);

				return t;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> selectAll() {

		List<Customer> list = new ArrayList<>();

		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM customer";

		try {
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("userName");
				String password = rs.getString("userPassword");
				String cusFullName = rs.getString("cusFullName");
				boolean gender = rs.getBoolean("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				Customer customer = new Customer(customerId, customerName, password, cusFullName, gender, phoneNumber,
						email);
				list.add(customer);

				JDBCUtil.closeConnection(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// select by username
	public Customer selectCustomerByUserName(Customer cus) {

		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM customer WHERE userName = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, cus.getUserName());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("userName");
				String password = rs.getString("userPassword");
				String cusFullName = rs.getString("cusFullName");
				boolean gender = rs.getBoolean("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				Customer customer = new Customer(customerId, customerName, password, cusFullName, gender, phoneNumber,
						email);
				JDBCUtil.closeConnection(c);
				return customer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// select by password
	public Customer selectCustomerByPassword(Customer cus) {

		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM customer WHERE userName = ? AND userPassword = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, cus.getUserName());
			st.setString(2, cus.getUserPassword());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("userName");
				String password = rs.getString("userPassword");
				String cusFullName = rs.getString("cusFullName");
				boolean gender = rs.getBoolean("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				Customer customer = new Customer(customerId, customerName, password, cusFullName, gender, phoneNumber,
						email);
				JDBCUtil.closeConnection(c);
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// select by email
	public Customer selectCustomerByEmail(Customer cus) {

		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM customer WHERE email = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, cus.getEmail());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("userName");
				String password = rs.getString("userPassword");
				String cusFullName = rs.getString("cusFullName");
				boolean gender = rs.getBoolean("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				Customer customer = new Customer(customerId, customerName, password, cusFullName, gender, phoneNumber,
						email);
				JDBCUtil.closeConnection(c);
				return customer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// select by phone number

	public Customer selectCustomerByphoneNumber(Customer cus) {

		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM customer WHERE phoneNumber = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, cus.getPhoneNumber());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("userName");
				String password = rs.getString("userPassword");
				String cusFullName = rs.getString("cusFullName");
				boolean gender = rs.getBoolean("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				Customer customer = new Customer(customerId, customerName, password, cusFullName, gender, phoneNumber,
						email);
				JDBCUtil.closeConnection(c);
				return customer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insert(Customer t) {

		Connection c = JDBCUtil.getConnection();
		String sql = "INSERT INTO customer(customerId, userName, userPassword, cusFullName, gender, phoneNumber, email) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			st.setString(2, t.getUserName());
			st.setString(3, t.getUserPassword());
			st.setString(4, t.getCusFullName());
			st.setBoolean(5, t.getGender());
			st.setString(6, t.getPhoneNumber());
			st.setString(7, t.getEmail());

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
	public int deleteById(Customer t) {

		Connection c = JDBCUtil.getConnection();
		String sql = "DELETE FROM customer WHERE customerId = ? ";
		try {

			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			OrderDAO.getInstance().deleteAllbyCustomerId(t);
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
	public int updateById(Customer t) {
		Connection c = JDBCUtil.getConnection();
		String sql = "UPDATE customer SET userName = ? " + ", userPassword = ? " + ", cusFullName = ? "
				+ ", gender = ? " + ", phoneNumber = ? " + ", email = ? " + "WHERE customerId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getUserName());
			st.setString(2, t.getUserPassword());
			st.setString(3, t.getCusFullName());
			st.setBoolean(4, t.getGender());
			st.setString(5, t.getPhoneNumber());
			st.setString(6, t.getEmail());
			st.setString(6, t.getCustomerId());

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
