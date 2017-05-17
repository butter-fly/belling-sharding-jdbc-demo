package com.qiguan.sharding.service;

import java.util.List;

import com.qiguan.sharding.entity.User;

/**
 * @author Administrator
 *
 */
public interface UserService {
	public boolean insert(User u);
	
	public boolean update(User u);

	public List<User> findAll();
	
	public List<User> page();
	
	public Long count();

	public List<User> findByUserIds(List<Integer> ids);

	public void transactionTestSucess();

	public void transactionTestFailure() throws IllegalAccessException;
}
