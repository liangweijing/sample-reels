package com.hpe.impl;

import java.util.List;
import java.util.Map;

import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.utils.Page;

public interface MenuImpl {
	//分页查询
	public Page queryMenusByPage(int curPage,int pageNumber);

	public Menus addMenu(String menuname,int typeid, String burden, String price, String pricel, String brief, 
			String imgpath);

	public Menus findMenuById(int menuid);

	public int addOrders(List<MenusOrder> orders);

	public Map<String,String> getRanking();
}
