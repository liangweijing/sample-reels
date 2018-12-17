package com.hpe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.impl.MenuImpl;
import com.hpe.utils.JDBCUtils;
import com.hpe.utils.Page;

public class MenuDao implements MenuImpl{

	
	/*
	 * (non-Javadoc)完成两个实体类menu和page查询出所有menu形成集合，并设置page的所有属性
	 * @see com.hpe.impl.MenuImpl#queryMenusByPage(int, int)
	 */
	public Page queryMenusByPage(int curPage, int pageNumber) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		ResultSet rs=null;
		PreparedStatement pstat=null;
		//查询每页显示的记录
		String sql="select menus.*,types.name as typename from menus,types where types.id=menus.typeid order by menus.id limit ?,? ";
		//查询一共有多少条记录
		String sql1="select count(*) from menus;";
		
		Page p=new Page();//menus是Page的属性之一
		Menus m=null;
		List<Menus> data=new ArrayList();
		try {
			 pstat=conn.prepareStatement(sql);
			
			PreparedStatement pstat1=conn.prepareStatement(sql1);
			
			pstat.setInt(1, (curPage-1)*6);//第一页是第0条开始的6条，第二页下标第6条开始，第三页12条开始
			pstat.setInt(2, 6);
			 rs=pstat.executeQuery();
			ResultSet rs1=pstat1.executeQuery();
			while(rs1.next()) {
				p.setRows(rs1.getInt(1));
			}
			while(rs.next()) {
			 m=new Menus(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11) );
			data.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstat, conn);
		}
		p.setData(data);
		p.setCurPage(curPage);
		p.setPageNumber(pageNumber);
		
		//总页数
		int rows=p.getRows();
		if(rows%pageNumber==0) {
			p.setTotalPage(rows/pageNumber);
		}else {
			p.setTotalPage(rows/pageNumber+1);
		}
		return p;
	}

	@Override
	public Menus addMenu(String menuname,int typeid, String burden, String price, String pricel, String brief, 
			String imgpath) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		ResultSet rs=null;
		Menus newMenu = null;
		PreparedStatement pstat=null;
		String sql="INSERT INTO `menus`(name,typeid,burden,brief,price,sums,pricel,sumsl,imgpath) VALUES ( ?, ?, ?, ?, ?, '0', ?, '0',?);";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, menuname);
			pstat.setInt(2, typeid);
			pstat.setString(3, burden);
			pstat.setString(4, brief);
			pstat.setString(5, price);
			pstat.setString(6, pricel);
			pstat.setString(7, imgpath);
			int result=pstat.executeUpdate();
			if(result>0) {
				String sql1="select menus.*,types.name as typename from menus,types where types.id=menus.typeid and menus.name="+"'"+menuname+"'";
				PreparedStatement pstat1=conn.prepareStatement(sql1);
				rs=pstat1.executeQuery();
				if(rs.next()) {
					newMenu=new Menus(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11) );
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstat, conn);
		}
		return newMenu;
	}

	@Override
	public Menus findMenuById(int menuid) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		Menus curMenu=null;
		try {
			stat=conn.createStatement();
			String sql="select menus.*,types.name as typename from menus,types where types.id=menus.typeid and menus.id="+menuid;
			rs=stat.executeQuery(sql);
			if(rs.next()) {
				curMenu=new Menus(menuid, rs.getString(2), rs.getInt(3),  rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11) );
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stat, conn);
		}
		return curMenu;
	}

	@Override
	public int addOrders(List<MenusOrder> orders) {
		// TODO Auto-generated method stub
		int result=0;
		Connection conn=JDBCUtils.getConnection();
		PreparedStatement pstat=null;
		for(int i=0;i<orders.size();i++) {
			MenusOrder m=orders.get(i);
			String sql="INSERT INTO `orders`(menuid,userid,menunum,times,delivery) VALUES ( ?, ?, ?, ?, ?)";
			try {
				//关闭自动提交
				conn.setAutoCommit(false);
				pstat=conn.prepareStatement(sql);
				pstat.setInt(1, m.getMenuid());
				pstat.setInt(2, m.getUserid());
				pstat.setString(3, m.getMenunum());
				pstat.setString(4, m.getTimes());
				pstat.setString(5, m.getDelivery());
				result=pstat.executeUpdate();
						
				String sql2="update menus set sums=sums-"+m.getMenunum()+" where id="+m.getMenuid();
				pstat=conn.prepareStatement(sql2);
				int result2=pstat.executeUpdate();
				
				conn.commit();
				if(result>0&&result2>0) {
					continue;
				}else {
					return 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
//				JDBCUtils.release(null, pstat, conn);
			}
			
		}
		
		return result;
	}

	@Override
	public Map<String, String> getRanking() {
		// TODO Auto-generated method stub
		Map<String,String> rankOrder=new LinkedHashMap();//HashMap无序可以放null
		Connection conn=JDBCUtils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		String sql="select menus.name,SUM(menunum) as totalnum from menus,orders where orders.menuid=menus.id GROUP BY menuid ORDER BY SUM(menunum)desc LIMIT 0,3;";
		try {
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next()) {
				rankOrder.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rankOrder;
	}

}
