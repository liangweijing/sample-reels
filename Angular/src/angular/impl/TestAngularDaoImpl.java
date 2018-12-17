package angular.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import angular.dao.TestAngularDao;
import angular.pojo.User;
import qust.stumis.utils.JDBCUtils;




public class TestAngularDaoImpl implements TestAngularDao {

	@Override
	public int AddNameAngular(String name) {
		// TODO Auto-generated method stub
		int result=0;
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		String sql="insert into testangular (name) values(?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, name);
			result=pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(null, pstat, conn);
		}
		
		return result;
	}

	@Override
	public List<User> findUser() {
		// TODO Auto-generated method stub
		String sql="select * from testAngular";
		Connection conn=JDBCUtils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		List<User> users=new ArrayList<User>();
		
		
		try {
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

}
