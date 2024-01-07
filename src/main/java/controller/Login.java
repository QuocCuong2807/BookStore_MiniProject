package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Common.Common;
import database.CustomerDAO;
import model.Customer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/do-login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set utf-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");

		// Get parameter from request
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		// validate data
		boolean checkError = false;
		if (userName == null ) {
			checkError = true;
			request.setAttribute("e_username", "Ten dang nhap khong duoc de trong");
		}
		if (password == null ) {
			checkError = true;
			request.setAttribute("e_password", "Mat khau khong duoc de trong");
		}

		// Endcoding password
		String enCodePassWordString = "";
		try {
			enCodePassWordString = Common.getSha256(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// validate customer
		Customer customer = new Customer(userName, enCodePassWordString); // customer from login form
		Customer v_customer = CustomerDAO.getInstance().selectCustomerByPassword(customer);// customer after query by
																							// password
		if (v_customer == null) {
			checkError = true;
			request.setAttribute("e_invalidUser", "Thong tin dang nhap khong chinh xac");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("customer", v_customer);
		}

		String url = "/Shop";
		if (checkError)
			url = "/Login.jsp";

		RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
		rq.forward(request, response);

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
