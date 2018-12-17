package com.hpe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hpe.entity.Admin;
import com.hpe.entity.MenusOrder;
import com.hpe.impl.BackStageImpl;
import com.hpe.utils.JDBCUtils;
import com.hpe.utils.Page;

public class BackStageDao implements BackStageImpl {
	
	
	@Override
	public Admin AdminLogin(String adminName, String adminpwd) {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		Connection conn=JDBCUtils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		try {
			String sql="SELECT id, name,authority from admin where name="+"\'"+adminName+"\'"+"and pwd="+"\'"+adminpwd+"\'";
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next()) {
				admin.setAid(String.valueOf(rs.getInt(1)));
				admin.setAdminName(rs.getString(2));
				admin.setAuthority(rs.getString(3));
				admin.setPwd(adminpwd);
				
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stat, conn);
		}
		return null;
	}

	@Override
	public Admin AdminUpdateInfo(String adminName, String adminpwd ,Admin admin) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
	
		String sql="update admin set name=?, pwd=? where name=?;";
		Admin newadmin=admin;
		try {
			 pstat=conn.prepareStatement(sql);
			pstat.setString(1, adminName);
			pstat.setString(2, adminpwd);
			pstat.setString(3, admin.getAdminName());
			int result=pstat.executeUpdate();
			if(result>0) {
				newadmin.setAdminName(adminName);
				newadmin.setPwd(adminpwd);
				return newadmin;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(null, pstat, conn);
		}
		return newadmin;
	}

	
	public Page queryOrderById(int curPage,int pageNum) {
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		Page<MenusOrder> page=new Page<MenusOrder>();
		MenusOrder order=new MenusOrder();
		List<MenusOrder> orders=new ArrayList<MenusOrder>();
		//sql长语句不要加+连接，不要加；
		String sql="select  userid,realname,phone,adress,menus.`name`,sum(menunum) as sum,price,times,delivery from menus,orders,users where users.id=orders.userid and menus.id=orders.menuid GROUP BY times, menus.`name` limit ?,?";
		//视图查询和表一样
		String sql2="SELECT count(*) from order_v";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, (curPage-1)*6);
			pstat.setInt(2, pageNum);
			rs=pstat.executeQuery();
			while(rs.next()) {
				
				order=new MenusOrder(0,0, rs.getInt(1), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(7));
				orders.add(order);
			}
			pstat=conn.prepareStatement(sql2);
			rs=pstat.executeQuery();
			if(rs.next()) {
				page.setRows(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstat, conn);
		}
		page.setCurPage(curPage);
		page.setPageNumber(pageNum);
		page.setData(orders);
		if(page.getRows()%pageNum==0) {
			page.setTotalPage(page.getRows()/pageNum);
		}else {
			page.setTotalPage(page.getRows()/pageNum+1);
		}
		
		return page;

	}

	@Override
	public List<MenusOrder> queryOrderById(String userid, String menuname, String date) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		
		MenusOrder order=new MenusOrder();
		List<MenusOrder> list=new ArrayList<MenusOrder>();
		String sql="SELECT userid,realname,phone,adress,menus.`name`,sum(menunum) as sum,price,times,delivery from menus,orders,users where users.id=orders.userid and menus.id=orders.menuid and users.id=? and menus.`name`=? and times like ? GROUP BY times";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(userid));
			pstat.setString(2, menuname);
			pstat.setString(3, date+"%");//like模糊查询可以在传入参数时设置，不必放到sql中太长了
			rs=pstat.executeQuery();
			while(rs.next()) {
				
				order=new MenusOrder(0, 0, rs.getInt(1), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@SuppressWarnings("resource")
	@Override
	public Page<MenusOrder> statistcOrder(int curPage, int pageNum) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		Page<MenusOrder> page=new Page<MenusOrder>();
		MenusOrder order=new MenusOrder();
		List<MenusOrder> orders=new ArrayList<MenusOrder>();
		//sql长语句不要加+连接，不要加；
		//当日的订单查询，在sql语句中加入了获取当日日期的函数
		String sql="SELECT menus.`name`,sum(menunum) as menusum,price from menus,orders where menus.id=orders.menuid and times like CONCAT((select DATE(CURDATE())),'%') GROUP BY menus.`name` limit ?,?";
		//视图查询和表一样
		String sql2="select count(*) from statistic";
		
		try {
			pstat=conn.prepareStatement(sql);
			
			pstat.setInt(1, (curPage-1)*6);
			pstat.setInt(2, pageNum);
			rs=pstat.executeQuery();
			while(rs.next()) {
				order=new MenusOrder(0,0, 0,String.valueOf(rs.getInt(2)) , null, null, rs.getString(1), null, null, null,rs.getString(3));
				orders.add(order);
			}
			pstat=conn.prepareStatement(sql2);
			rs=pstat.executeQuery();
			if(rs.next()) {
				page.setRows(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstat, conn);
		}
		page.setCurPage(curPage);
		page.setPageNumber(pageNum);
		page.setData(orders);
		if(page.getRows()%pageNum==0||page.getRows()<pageNum) {
			page.setTotalPage(page.getRows()/pageNum);
		}else {
			page.setTotalPage(page.getRows()/pageNum+1);
		}
		
		return page;
	}

	@Override
	public int getTotalSales() {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		int sales=0;
		String sql="SELECT SUM(menusum*price) as sales from statistic";
		try {
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			if(rs.next()) {
				sales=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}

	@Override
	public int logintest(String adminName) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql="select * from admin where name="+"'"+adminName+"'";
		int result=0;
		try {
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			if(rs.next()) {
				result=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ahhah"+result);
		return result;
	}
}
