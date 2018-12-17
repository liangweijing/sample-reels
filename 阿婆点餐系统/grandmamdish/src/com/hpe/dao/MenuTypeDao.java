package com.hpe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hpe.entity.Types;
import com.hpe.impl.MenuTypeImpl;
import com.hpe.utils.JDBCUtils;

public class MenuTypeDao implements MenuTypeImpl {

	@Override
	public List<Types> queryTypes() {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		Types t=null;
		List<Types> type = new ArrayList();
		String sql="select * from types";
		try {
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			while(rs.next()) {
				t=new Types(rs.getInt(1), rs.getString(2));
				type.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return type;
	}

}
