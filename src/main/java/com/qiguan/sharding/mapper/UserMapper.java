package com.qiguan.sharding.mapper;

import java.util.List;

import com.qiguan.sharding.entity.User;

/**
 * @author Administrator
 *
 */
public interface UserMapper {
	
	Integer insert(User u);
	
	Integer update(User u);

	List<User> findAll();
	
	List<User> page();
	
	Long count();

	List<User> findByUserIds(List<Integer> userIds);
}
