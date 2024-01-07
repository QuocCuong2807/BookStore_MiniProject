package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Author;
import model.Book;
import model.Category;

public class BookDAO implements DAOInterface<Book>{
	
	public static BookDAO getInstance() {
		return new BookDAO();
	}

	@Override
	public Book selectById(Book t) {
		
		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book WHERE bookId = ?";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getBookId());
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				t.setBookId(rs.getString("bookId"));
				t.setBookName(rs.getString("bookName"));
				t.setImages(rs.getString("images"));
				t.setPublishDate(rs.getDate("publishDate"));
				t.setBookPrice(rs.getInt("bookPrice"));
				t.setDiscount(rs.getInt("discount"));
				t.setBookDescription(rs.getString("bookDescription"));
				t.setPublishCompany(rs.getString("publishCompany"));
				t.setLanguages(rs.getString("languages"));
				
				//set object values
				String authorId = rs.getString("authorId");
				Author author = new Author(authorId,"",null);
				t.setAuthor(AuthorDAO.getInstance().selectById(author));
				int categoryId = rs.getInt("categoryId");
				Category category = new Category(categoryId, "");
				t.setCategory(CategoryDAO.getInstance().selectById(category));
			}
			JDBCUtil.closeConnection(c);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public ArrayList<Book> selectAll() {
		
		ArrayList<Book> list = new ArrayList<>();
		
		Connection c = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				String images = rs.getString("images");
				Date publishDate = rs.getDate("publishDate");
				int bookPrice = rs.getInt("bookPrice");
				int discount = rs.getInt("discount");
				String bookDescription = rs.getString("bookDescription");
				String publishCompany = rs.getString("publishCompany");
				String languages = rs.getString("languages");
				
				//set obj values
				String authorId = rs.getString("authorId");
				Author author = new Author(authorId,"",null);
				author = AuthorDAO.getInstance().selectById(author);
				int categoryId = rs.getInt("categoryId");
				Category category = new Category(categoryId, "");
				category = CategoryDAO.getInstance().selectById(category);
				
				Book book = new Book(bookId, bookName, images, publishDate, bookPrice, discount, 
									bookDescription, publishCompany, author, category, languages);
				
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Book> getBookByCategoryId(int categoryId) {
		
		Connection c = new JDBCUtil().getConnection();
		
		String sql = "SELECT * FROM BOOK WHERE categoryId = ? ";
		
		ArrayList<Book> list = new ArrayList<>();
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, categoryId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				String images = rs.getString("images");
				Date publishDate = rs.getDate("publishDate");
				int bookPrice = rs.getInt("bookPrice");
				int discount = rs.getInt("discount");
				String bookDescription = rs.getString("bookDescription");
				String publishCompany = rs.getString("publishCompany");
				String languages = rs.getString("languages");
				
				String authorId = rs.getString("authorId");
				Author author = new Author(authorId,"",null);
				author = AuthorDAO.getInstance().selectById(author);
				Category category = new Category(categoryId, "");
				category = CategoryDAO.getInstance().selectById(category);
				
				Book book = new Book(bookId, bookName, images, publishDate, bookPrice, discount, bookDescription, 
										publishCompany, author, category, languages);
				
				list.add(book);
				
				JDBCUtil.closeConnection(c);
				
				return list;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public int insert(Book t) {

		Connection c = JDBCUtil.getConnection();
		
		String sql = "INSERT INTO book(bookId, bookName, images, publishDate, bookPrice, discount, bookDescription, "
				+ "publishCompany, authorId, categoryId, languages) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?. ?, ?, ?)";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getBookId());
			st.setString(2, t.getBookName());
			st.setString(3, t.getImages());
			st.setDate(4, t.getPublishDate());
			st.setInt(5, t.getBookPrice());
			st.setInt(6, t.getDiscount());
			st.setString(7, t.getBookDescription());
			st.setString(8, t.getPublishCompany());
			st.setString(9, t.getAuthor().getAuthorId());
			st.setInt(10, t.getCategory().getCategoryId());
			st.setString(11, t.getLanguages());
			
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
	public int deleteById(Book t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "DELETE FROM book WHERE bookId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getBookId());
			
			int numsRow = st.executeUpdate();
			
			JDBCUtil.closeConnection(c);
			
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteAllByCategoryId(Category category) {
Connection c = JDBCUtil.getConnection();
		
		String sql = "DELETE FROM book WHERE categoryId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, category.getCategoryId());
			
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
	public int updateById(Book t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "UPDATE book SET bookName = ? "
									+ ", images = ? "
									+ ", publishDate = ? "
									+ ", bookPrice = ? "
									+ ", discount = ? "
									+ ", bookDescription = ? "
									+ ", publishCompany = ? "
									+ ", authorId = ? "
									+ ", categoryId = ? "
									+ ", languages = ? "
					+ "WHERE bookId = ? ";
		
		try {
			
			PreparedStatement st = c.prepareStatement(sql);
			
			st.setString(1, t.getBookName());
			st.setString(2, t.getImages());
			st.setDate(3, t.getPublishDate());
			st.setInt(4, t.getBookPrice());
			st.setInt(5, t.getDiscount());
			st.setString(6, t.getBookDescription());
			st.setString(7, t.getPublishCompany());
			st.setString(8, t.getAuthor().getAuthorId());
			st.setInt(9, t.getCategory().getCategoryId());
			st.setString(10, t.getLanguages());
			st.setString(11, t.getBookId());
			
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
