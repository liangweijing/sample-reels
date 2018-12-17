package com.hpe.service;

import java.util.List;
import java.util.Map;

import com.hpe.dao.MenuDao;
import com.hpe.entity.Menus;
import com.hpe.entity.MenusOrder;
import com.hpe.impl.MenuImpl;
import com.hpe.utils.Page;

public class MenuService {
	MenuImpl mi=new MenuDao();
	public Page queryMenusByPage(int curPage,int pageNumber) {
		Page p=mi.queryMenusByPage(curPage, pageNumber);
//		List<Menus> data = p.getData();
//		for(Menu m: data) {
//			m.setTypeName();
//		}
		//mi.queryMenusTypeNameById
		return p;
	}
	public Menus addMenu(String menuname, int typeid,String burden, String price, String pricel, String brief, 
			String imgpath) {
		// TODO Auto-generated method stub
		Menus newMenu=mi.addMenu(menuname,typeid,burden,price,pricel,brief,imgpath);
		return newMenu;
	}
	public Menus findMenuById(int menuid) {
		// TODO Auto-generated method stub
		Menus curMenu=mi.findMenuById(menuid);
		return curMenu;
	}
	public int addOrders(List<MenusOrder> orders) {
		// TODO Auto-generated method stub
		int result=mi.addOrders(orders);
		return result;
	}
	public Map<String, String> getRanking() {
		// TODO Auto-generated method stub
		Map<String, String> rankOrder=mi.getRanking();
		return rankOrder;
	}
}
