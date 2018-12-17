package com.hpe.service;

import java.util.List;

import com.hpe.dao.MenuTypeDao;
import com.hpe.entity.Types;
import com.hpe.impl.MenuTypeImpl;

public class MenuTypeService {
	MenuTypeImpl mti=new MenuTypeDao();
	public List<Types> queryTypes() {
		// TODO Auto-generated method stub
		List<Types> type=mti.queryTypes();
		return type;
	}

}
