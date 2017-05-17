package com.qiguan.sharding.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiguan.sharding.entity.User;
import com.qiguan.sharding.mapper.UserMapper;
import com.qiguan.sharding.service.UserService;

/**
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource  
    public UserMapper userMapper;

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#insert(com.qiguan.sharding.entity.User)
	 */
	@Override
	public boolean insert(User u) {
		return userMapper.insert(u) > 0 ? true :false;  
	}
	
	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#update(com.qiguan.sharding.entity.User)
	 */
	@Override
	public boolean update(User u) {
		return userMapper.update(u) > 0 ? true :false;  
	}

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return userMapper.findAll();  
	}
	
	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#page()
	 */
	@Override
	public List<User> page() {
		return userMapper.page();
	}

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#findByUserIds(java.util.List)
	 */
	@Override
	public List<User> findByUserIds(List<Integer> ids) {
		return userMapper.findByUserIds(ids);
	}

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#transactionTestSucess()
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void transactionTestSucess() {
		User u = new User();
		u.setUserId(9);
		u.setAge(25);
		u.setName("good");
		userMapper.insert(u);
	}

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#transactionTestFailure()
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void transactionTestFailure() throws IllegalAccessException {
		User u = new User();
		u.setUserId(9);
		u.setAge(25);
		u.setName("good");
		userMapper.insert(u);
		throw new IllegalAccessException();  
	}

	/* (non-Javadoc)
	 * @see com.qiguan.sharding.service.UserService#count()
	 */
	@Override
	public Long count() {
		return userMapper.count();
	}
}
