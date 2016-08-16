package com.smartErp.system.dao;

import java.util.List;

import com.smartErp.system.model.User;
import com.smartErp.util.frame.Page;

public interface UserDao {
	List<User> findAll();
	List<User> getUserPage(Page<User> page);  
}
