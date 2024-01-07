package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Common;
import database.CustomerDAO;
import database.JDBCUtil;
import database.OrderDAO;
import database.OrderDetailsDAO;
import model.Cart;
import model.Customer;
import model.Order;
import model.OrderDetails;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/save-order")
public class SaveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");

		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String note = request.getParameter("note");

		// VALIDATE
		// PARAMETER---------------------------------------------------------------------------------------------------
		boolean check_err = false;

		if (phoneNumber == null || phoneNumber == "") {
			check_err = true;
			request.setAttribute("e_cusPhoneNumber", "Khong duoc de trong so dien thoai");
			
		}
		if (address == null || address == "") {
			check_err = true;
			request.setAttribute("e_cusAddress", "Khong duoc de trong dia chi");
			
		}

		// validate email -
		// phoneNumber------------------------------------------------------------------------------------------
		Pattern phoneNumberPattern = Pattern.compile("\\d{10}");
		Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);

		if (!phoneNumberMatcher.matches()) {
			check_err = true;
			request.setAttribute("e_cusPhoneNumber", "SO DIEN THOAI KHONG DUNG!");
		}

		// STORE ORDER THAT USER DONT
		// LOGIN-----------------------------------------------------------------------------------------------------

		if (customer == null) {

			String cusFullName = request.getParameter("customerName");
			// Validate parameter
			if (cusFullName == null || cusFullName == "") {
				check_err = true;
				request.setAttribute("e_cusFullName", "Khong duoc de trong ho va ten");
			}

			// Create customer
			String userName = "guest".concat(Common.getRanDomId());
			String customerId = Common.getRanDomId();
			String encryptPassword = "";
			try {
				encryptPassword = Common.getSha256("NOBODYKNOWHO12");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Connection con = JDBCUtil.getConnection();

			try {
				con.setAutoCommit(false);
				if (!check_err) {
					int row = CustomerDAO.getInstance()
							.insert(new Customer(customerId, userName, encryptPassword, cusFullName, false, "", ""));

					Order order = new Order();
					order.setOrderId(Common.getRanDomId());
					order.setCustomer(new Customer(customerId, userName, encryptPassword, cusFullName, false, "", ""));
					order.setAddress(address);
					order.setPhoneNumber(phoneNumber);
					order.setOrderStatus(0);
					order.setPaymentMethod("ShipCod");
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setNote(note);

					row += OrderDAO.getInstance().insert(order);

					Cart cart = (Cart) session.getAttribute("cart");

					for (Map.Entry<String, OrderDetails> entry : cart.cart.entrySet()) {
						entry.getValue().setOrder(order);
						row += OrderDetailsDAO.getInstance().insert(entry.getValue());
					}
					request.setAttribute("orderDetail", cart.cart.entrySet());
					request.setAttribute("customer", new Customer(customerId, userName, encryptPassword, cusFullName, false, "", ""));
					request.setAttribute("total", cart.getTotalPrice());
					con.commit();
					session.removeAttribute("cart");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				check_err = true;
				request.setAttribute("e_objects", "Exception with Objects");
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else {

			Connection con = JDBCUtil.getConnection();

			try {
				con.setAutoCommit(false);
				if (!check_err) {
					int row = CustomerDAO.getInstance().insert(customer);

					Order order = new Order();
					order.setOrderId(Common.getRanDomId());
					order.setCustomer(customer);
					order.setAddress(address);
					order.setPhoneNumber(phoneNumber);
					order.setOrderStatus(0);
					order.setPaymentMethod("ShipCod");
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setNote(note);

					row += OrderDAO.getInstance().insert(order);

					Cart cart = (Cart) session.getAttribute("cart");

					for (Map.Entry<String, OrderDetails> entry : cart.cart.entrySet()) {
						entry.getValue().setOrder(order);
						row += OrderDetailsDAO.getInstance().insert(entry.getValue());
					}
					request.setAttribute("orderDetail", cart.cart.entrySet());
					request.setAttribute("customer", customer);
					request.setAttribute("total", cart.getTotalPrice());
					con.commit();
					session.removeAttribute("cart");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				check_err = true;
				request.setAttribute("e_objects", "Exception with Objects");
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		if (!check_err)
			request.getRequestDispatcher("Success.jsp").forward(request, response);
		else
			request.getRequestDispatcher("CheckOut.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
