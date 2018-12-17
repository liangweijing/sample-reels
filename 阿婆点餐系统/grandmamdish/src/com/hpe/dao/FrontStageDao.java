package com.hpe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.entity.Users;
import com.hpe.entity.Users;
import com.hpe.impl.FrontStageImpl;
import com.hpe.utils.JDBCUtils;

public class FrontStageDao implements FrontStageImpl {

	@Override
	public Users userLogin(String userName, String userpwd) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		Users u=null;
		ResultSet rs=null;
		Statement stat=null;
		try {
			String sql="SELECT * from users where name="+"\'"+userName+"\'"+"and pwd="+"\'"+userpwd+"\'";
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next()) {
				u=new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			JDBCUtils.release(rs, stat, conn);
		}
		return u;
	}

	@Override
	public Users userRegist(Users u) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		ResultSet rs=null;
		Users newuser=null;
		String sql="insert into users(name,pwd,realname,sex,age,card,adress,phone,email,code,type) values( ?, ?, ?, ?, ?, ?,?, ?, ?, ?, '1')";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstat.setString(1, u.getName());
			pstat.setString(2, u.getPwd());
			pstat.setString(3, u.getRealname());
			pstat.setString(4, u.getSex());
			pstat.setString(6, u.getAge());
			pstat.setString(5, u.getCard());
			pstat.setString(7, u.getAddress());
			pstat.setString(8, u.getPhone());
			pstat.setString(9, u.getEmail());
			pstat.setString(10, u.getCode());
			
			int result=pstat.executeUpdate();
			rs=pstat.getGeneratedKeys();//刚插入的那一条的自增字段
			if(result>0&&rs.next()) {
				newuser=new Users(rs.getInt(1), u.getName(), u.getPwd(), u.getRealname(), u.getSex(), u.getAge(), u.getCard(), u.getAddress(), u.getPhone(), u.getEmail(), u.getCode(), "1");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newuser;
	}

	@Override
	public List<MenusOrder> getUserOrder(int userid) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		List<MenusOrder> myOrder=new ArrayList<>();
		MenusOrder order=null;
		String sql="select * from orders where userid="+userid;
		try {
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next()) {
				order=new MenusOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),null,null,null,null,null);
				myOrder.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myOrder;
	}

}
