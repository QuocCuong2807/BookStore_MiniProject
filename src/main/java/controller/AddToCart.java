package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BookDAO;
import database.CategoryDAO;
import model.Book;
import model.Cart;
import model.Category;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");

		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			cart.addBookToCart(bookId);
			session.setAttribute("cart", cart);
		} else {
			cart.addBookToCart(bookId);
			session.setAttribute("cart", cart);
		}
		// GET CATEGORY
		ArrayList<Category> categoryList = CategoryDAO.getInstance().selectAll();

		// send category to view
		request.setAttribute("categoryList", categoryList);

		// GET ALL PRODUCT
		ArrayList<Book> bookList = BookDAO.getInstance().selectAll();

		// send book to view
		request.setAttribute("bookList", bookList);

		// FORWARD
		request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
