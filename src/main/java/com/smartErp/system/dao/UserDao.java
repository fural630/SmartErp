package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.system.model.User;
import com.smartErp.util.frame.Page;

public interface UserDao {
	List<User> findAll();
	List<Map<String, Object>> getUserPage(Page page);  
}
