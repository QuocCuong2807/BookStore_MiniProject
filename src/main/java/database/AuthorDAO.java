package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Author;

public class AuthorDAO implements DAOInterface<Author>{
	
	public static AuthorDAO getInstance() {
		return new AuthorDAO();
	}

	@Override
	public Author selectById(Author t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "SELECT * "
				+ "FROM author "
				+ "Where authorId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getAuthorId()); 
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				t.setAuthorId(rs.getString("authorId"));
				t.setAuthorName(rs.getString("authorName"));
				t.setDayOfBirth(rs.getDate("dayOfBirth"));
			}
			
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public List<Author> selectAll() {
		
		List<Author> list = new ArrayList<>();
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM author";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				String authorId = rs.getString("authorId");
				String authorName = rs.getString("authorName");
				Date dateOfBirth = rs.getDate("dayOfBirth");
				
				Author author = new Author(authorId, authorName, dateOfBirth);
				
				list.add(author);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insert(Author t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "INSERT INTO author(authorId ,authorName ,dayOfBirth ) "
				+ "VALUES (?, ?, ?)";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			
			//set values to sql statment
			st.setString(1, t.getAuthorId());
			st.setString(2, t.getAuthorName());
			st.setDate(3, t.getDayOfBirth());
			
			int rowsNum = st.executeUpdate();
			
			JDBCUtil.closeConnection(c);
			
			return rowsNum;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteById(Author t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "DELETE FROM author WHERE authorId = ?";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getAuthorId());
			
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
	public int updateById(Author t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "UPDATE author SET authorName = ? "
									  + ", dayOfBirth = ? "
									  + ", WHERE authorId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getAuthorName());
			st.setDate(2, t.getDayOfBirth());
			st.setString(3, t.getAuthorId());
			
			int rowsNum = st.executeUpdate();
			
			JDBCUtil.closeConnection(c);
			
			return rowsNum;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
