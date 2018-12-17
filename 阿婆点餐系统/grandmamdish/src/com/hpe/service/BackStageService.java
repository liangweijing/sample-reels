package com.hpe.service;

import java.util.List;

import com.hpe.dao.BackStageDao;
import com.hpe.entity.Admin;
import com.hpe.entity.MenusOrder;
import com.hpe.impl.BackStageImpl;
import com.hpe.utils.Page;

public class BackStageService {
	BackStageImpl bs=new BackStageDao();
	
	public Admin AdminLogin(String adminName,String adminpwd) {
		Admin admin=bs.AdminLogin(adminName, adminpwd);
		return admin;
	}

	public Admin AdminUpdateInfo(String adminName, String adminpwd, Admin admin2) {
		// TODO Auto-generated method stub
		Admin admin=bs.AdminUpdateInfo(adminName, adminpwd,admin2);
		return admin;
	}
	public Page queryOrderById(int curPage,int pageNum) {
		Page page=bs.queryOrderById(curPage, pageNum);
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<MenusOrder> queryOrder(String userid, String menuname, String date) {
		// TODO Auto-generated method stub
		List<MenusOrder> list=bs.queryOrderById(userid, menuname,date);
		return list;
	}

	public Page<MenusOrder> statisticOrder(int curPage, int pageNum) {
		// TODO Auto-generated method stub
		
		Page<MenusOrder> page=bs.statistcOrder(curPage, pageNum);
		return page;
	}

	public int getTotalSales() {
		// TODO Auto-generated method stub
		int sales=bs.getTotalSales();
		return sales;
	}

	public int logintest(String adminName) {
		// TODO Auto-generated method stub
		int result=bs.logintest(adminName);
		return result;
	}

}
