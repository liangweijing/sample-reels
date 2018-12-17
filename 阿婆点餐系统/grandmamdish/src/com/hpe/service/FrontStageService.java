package com.hpe.service;

import java.util.List;

import com.hpe.dao.FrontStageDao;
import com.hpe.entity.MenusOrder;
import com.hpe.entity.Users;
import com.hpe.impl.FrontStageImpl;

public class FrontStageService {
	FrontStageImpl fsi=new FrontStageDao();
	public Users AdminLogin(String userName, String userpwd) {
		// TODO Auto-generated method stub
		Users u=fsi.userLogin(userName,userpwd);
		return u;
	}
	public Users userRegist(Users u) {
		// TODO Auto-generated method stub
		Users newuser=fsi.userRegist(u);
		return newuser;
	}
	public List<MenusOrder> getUserOrder(int userid) {
		// TODO Auto-generated method stub
		List<MenusOrder> myOrder=fsi.getUserOrder(userid);
		return myOrder;
	}

}
