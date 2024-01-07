package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class CategoryDAO implements DAOInterface<Category>{
	
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}

	@Override
	public Category selectById(Category t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "SELECT * "
				+ "FROM category "
				+ "WHERE categoryId = ? ";
		
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getCategoryId());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				t.setCategoryId(rs.getInt("categoryId"));
				t.setCategoryName(rs.getString("categoryName"));
				
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
	public ArrayList<Category> selectAll() {
		
		Connection c = JDBCUtil.getConnection();
		ArrayList<Category> list = new ArrayList<>();
		
		String sql = "SELECT * FROM category";
		try {
			
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				Category category = new Category(categoryId, categoryName);
				list.add(category);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Category t) {
		//create connection 
		Connection c = JDBCUtil.getConnection();
		
		String sql = "INSERT INTO category(categoryId, categoryName) "
				+ "VALUES(?, ?)";
		
		try {
			//create and set values to prepare sql statment
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getCategoryId());
			st.setString(2, t.getCategoryName());
			
			//excute query
			int numsRow = st.executeUpdate();
			JDBCUtil.closeConnection(c);
			
			//return number of culumn has been changed 
			return numsRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteById(Category t) {
		
		Connection c = JDBCUtil.getConnection();
		
		String sql = "DELETE FROM category WHERE categoryId = ? ";
		try {
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getCategoryId());
			
			BookDAO.getInstance().deleteAllByCategoryId(t);
			
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
	public int updateById(Category t) {
		Connection c = JDBCUtil.getConnection();
		String sql = "UPDATE category Set categoryName = ? "
					+ "WHERE categoryId = ?";
		try {
			PreparedStatement st =c.prepareStatement(sql);
			st.setString(1, t.getCategoryName());
			st.setInt(2, t.getCategoryId());
			
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
