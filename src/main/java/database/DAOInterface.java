package database;

import java.sql.ResultSet;
import java.util.List;

public interface DAOInterface<T> {
	
	public T selectById(T t);
	
	public List<T> selectAll();
	
	public int insert(T t);
	
	public int deleteById(T t);
	
	public int updateById(T t); 
}
