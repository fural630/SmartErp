package com.smartErp.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartErp.system.dao.UserDao;
import com.smartErp.system.model.User;
import com.smartErp.util.frame.Page;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public List<User> getUserList() {
		return userDao.findAll();
	}
	
	public List<User> getUserPage(Page<User> page) {
		return userDao.getUserPage(page);
	}
}
