package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import database.CategoryDAO;
import model.Book;
import model.Category;

/**
 * Servlet implementation class DisplayBook
 */
@WebServlet("/category")
public class DisplayBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");

		// GET BOOK FROM CATEGORY ID

		try {
			// GET BOOK BY CATEGORY ID
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			ArrayList<Book> bookListForCategoryId = BookDAO.getInstance().getBookByCategoryId(categoryId);
			request.setAttribute("bookList", bookListForCategoryId);

			// HANDLE EXCEPTION WHILE CONVERT
		} catch (Exception e) {
			String categoryId = request.getParameter("categoryId");
			ArrayList<Book> bookListForAll = BookDAO.getInstance().selectAll();
			request.setAttribute("bookList", bookListForAll);
		}
		// GET CATEGORY
		ArrayList<Category> categoryList = CategoryDAO.getInstance().selectAll();

		// send category to view
		request.setAttribute("categoryList", categoryList);

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
