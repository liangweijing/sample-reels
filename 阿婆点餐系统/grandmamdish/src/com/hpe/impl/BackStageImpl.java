package com.hpe.impl;

import java.util.List;

import com.hpe.entity.Admin;
import com.hpe.entity.MenusOrder;
import com.hpe.utils.Page;

public interface BackStageImpl {
	public Admin AdminLogin(String adminName,String adminpwd);

	public Admin AdminUpdateInfo(String adminName, String adminpwd, Admin admin2);
	
	public Page queryOrderById(int curPage,int pageNum);


	public List<MenusOrder> queryOrderById(String userid, String menuname, String date);

	public Page<MenusOrder> statistcOrder(int curPage, int pageNum);

	public int getTotalSales();

	public int logintest(String adminName);

}
