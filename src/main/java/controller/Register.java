package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.Common;
import database.CustomerDAO;
import model.Customer;

/**
 * Servlet implementation class Register
 */
@WebServlet("/do-register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set utf-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		
		//Get parameter from request
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");
		String rePassWord = request.getParameter("rePassWord");
		String email = request.getParameter("email");
		String cusFullName = request.getParameter("cusFullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		
		boolean checkErr = false;
		
		//Validate empty or null data
		if(userName == null || userName.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_userName", "thong tin khong duoc de trong");
		}
		if(passWord == null || passWord.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_password", "thong tin khong duoc de trong");
		}
		if(rePassWord == null || rePassWord.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_rePassWord", "thong tin khong duoc de trong");
		}
		if(email == null || email.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_email", "thong tin khong duoc de trong");
		}
		if(cusFullName == null || cusFullName.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_cusFullName", "thong tin khong duoc de trong");
		}
		if(phoneNumber == null || phoneNumber.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_phoneNumber", "thong tin khong duoc de trong");
		}
		if(gender == null || gender.trim().equals("")) {
			checkErr = true;
			request.setAttribute("e_gender", "thong tin khong duoc de trong");
		}
		
		//CONVERT GENDER FROM STRING TO BOOLEAN VALUE
		boolean genderVal = false;
		if(gender.trim().equalsIgnoreCase("male"))
			genderVal = true;
		else if(gender.trim().equalsIgnoreCase("female"));
			genderVal = false;
		
		//validate email - phoneNumber 
		Pattern phoneNumberPattern = Pattern.compile("\\d{10}");
		Pattern emailPattern = Pattern.compile("\\w+@\\w+(\\.\\w+)+(\\.\\w+)*");
		Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
		Matcher emailMatcher = emailPattern.matcher(email);
		
		if(!phoneNumberMatcher.matches()) {
			checkErr = true;
			request.setAttribute("e_phoneNumber", "SO DIEN THOAI KHONG DUNG!");
		}
		if(!emailMatcher.matches()) {
			checkErr = true;
			request.setAttribute("e_email", "email phai nhap chinh xac");
		}
		
		//Validate password - rePassWord
		if(!passWord.equals(rePassWord)) {
			checkErr = true;
			request.setAttribute("e_rePassWord", "mat khau nhap lai khong hop le");
		}
		
		//Get customerId
		String customerId = Common.getRanDomId();
		
		//Endcoding password
		String enCodePassWordString = "";
		try {
			enCodePassWordString = Common.getSha256(passWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Registered customer
		Customer customer = new Customer(customerId, userName, enCodePassWordString, cusFullName, genderVal, phoneNumber, email);
		
		//Validated obj user by phone number, email, user name
		if(CustomerDAO.getInstance().selectCustomerByUserName(customer) != null) {
			checkErr = true;
			request.setAttribute("e_userName", "username da ton tai");
		}
		if(CustomerDAO.getInstance().selectCustomerByEmail(customer) != null) {
			checkErr = true;
			request.setAttribute("e_email", "email da ton tai");
		}
		if(CustomerDAO.getInstance().selectCustomerByphoneNumber(customer) != null) {
			checkErr = true;
			request.setAttribute("e_phoneNumber", "so dien thoai da ton tai");
		}
			
		//
		String url = "/Login.jsp";
		if(!checkErr) {
			int row = CustomerDAO.getInstance().insert(customer);
			request.setAttribute("numsRow", row);
		}else {
			url = "/Register.jsp";
		}
		
		RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
		rq.forward(request, response);
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
