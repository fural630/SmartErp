package com.smartErp.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.application.libraries.constentEnum.OpenCloseEnum;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.dao.UserDao;
import com.smartErp.system.model.User;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.Page;

@Service
public class UserService {
	
	@Autowired(required=false)
	private UserDao userDao;
	
	public List<User> getUserList() {
		return userDao.findAll();
	}
	
	public List<Map<String, Object>> getUserPage(Page page) {
		return userDao.getUserPage(page);
	}
	
	public User getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}

	public void insertUser(User user) {
		MyDate myDate = new MyDate();
		MyLocale myLocale = new MyLocale();
		user.setCreateTime(myDate.getCurrentDateTime());
		user.setLog(SysRemark.append("", myLocale.getText("at.time.create.user")));
		user.setUpdateTime(myDate.getCurrentDateTime());
		user.setPassword(DESEncrypt.DataEncrypt(user.getPassword()));
		userDao.insertUser(user);
	}
	
	public void updateUser(User user) {
		MyDate myDate = new MyDate();
		MyLocale myLocale = new MyLocale();
		user.setPassword(DESEncrypt.DataEncrypt(user.getPassword()));
		user.setUpdateTime(myDate.getCurrentDateTime());
		User oldUser = userDao.getUserById(user.getId());
		user.setLog(SysRemark.append(oldUser.getLog(), myLocale.getText("at.time.update.user")));
		if (null == user.getStatus()) {
			user.setStatus(OpenCloseEnum.OPEN.getValue());
		} 
		userDao.updateUser(user);
	}

	public boolean checkAccountExist(String username) {
		User user = userDao.getUserByUserName(username);
		if (null == user) {
			return false;
		}
		return true;
	}

	public User getUserByIdAndCreator(Integer id, Integer loginUserId) {
		return userDao.getUserByIdAndCreator(id, loginUserId);
	}
	
	public User getUserById(Integer id){
		return userDao.getUserById(id);
	}

	public void deleteUserById(Integer id) {
		userDao.deleteUserById(id);
	}

}
