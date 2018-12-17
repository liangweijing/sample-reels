package angular.dao;

import java.util.List;

import angular.pojo.User;

public interface TestAngularDao {
	public int AddNameAngular(String name);
	public List<User> findUser();
}

