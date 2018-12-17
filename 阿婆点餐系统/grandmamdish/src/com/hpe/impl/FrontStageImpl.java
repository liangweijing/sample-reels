package com.hpe.impl;

import java.util.List;

import com.hpe.entity.MenusOrder;
import com.hpe.entity.Users;

public interface FrontStageImpl {

	Users userLogin(String userName, String userpwd);

	Users userRegist(Users u);

	List<MenusOrder> getUserOrder(int userid);

}
