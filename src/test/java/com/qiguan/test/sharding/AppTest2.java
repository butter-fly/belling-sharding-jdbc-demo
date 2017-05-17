package com.qiguan.test.sharding;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiguan.sharding.entity.User;
import com.qiguan.sharding.service.UserService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-ctx.xml",
		"classpath:/spring-sharding-newest.xml" })
public class AppTest2 {

	@Resource
	public UserService userService;

	/**
	 * 
	 */
	@Test
	public void testUserInsert() {
		User u = new User();
		for (int i = 1; i <= 10000000; i++) {
			u.setUserId(i);
			u.setAge(i);
			u.setName("test" + i);
			Assert.assertEquals(userService.insert(u), true);
		}
		
	}
	
	@Test
	public void testUserUpdate() {
		User u = new User();
		u.setUserId(10);
		u.setName("test---");
		Assert.assertEquals(userService.update(u), true);
	}
	
	/**
	 * 
	 */
	@Test
	public void testUserPage() {
		long s = System.currentTimeMillis();
		List<User> users = userService.page();
		if (null != users && !users.isEmpty()) {
			for (User u : users) {
				System.out.println("page query :" + u);
			}
		}
		long e = System.currentTimeMillis();
		
		System.out.println("耗时：" + ((e - s) ));
	}
	
	/**
	 * 
	 */
	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		if (null != users && !users.isEmpty()) {
			for (User u : users) {
				System.out.println("select query :" + u);
			}
		}
	}
	
	@Test
	public void testSQLIN() {
		List<User> users = userService.findByUserIds(Arrays.asList(9, 10, 1));
		if (null != users && !users.isEmpty()) {
			for (User u : users) {
				System.out.println("in query :" + u);
			}
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void testTransactionTestSucess() {
		userService.transactionTestSucess();
	}
	
	/**
	 * @throws IllegalAccessException
	 */
	@Test(expected = IllegalAccessException.class)
	public void testTransactionTestFailure() throws IllegalAccessException {
		userService.transactionTestFailure();
	}
	
	/**
	 * 统计
	 */
	@Test
	public void count() {
		System.out.println("总数：" + userService.count());
	}
}
