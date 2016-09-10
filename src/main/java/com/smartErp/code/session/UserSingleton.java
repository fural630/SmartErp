package com.smartErp.code.session;

import com.smartErp.system.model.User;

public class UserSingleton {
	private final String USERSESSION = "userSession";

	private UserSingleton() {
	}

	public User getUser() {
		SessionMap sessionMap = new SessionMap();
		return (User) sessionMap.get(USERSESSION);
	}

	public void setUser(User user) {
		SessionMap sessionMap = new SessionMap();
		sessionMap.put(USERSESSION, user);
	}

	public void unsetUser() {
		SessionMap sessionMap = new SessionMap();
		sessionMap.destoryAll();
	}

	public static UserSingleton getInstance() {
		return UserSingletonHolder.INSTANCE;
	}

	private static class UserSingletonHolder {
		private static final UserSingleton INSTANCE = new UserSingleton();
		private UserSingletonHolder() {
		}
	}
}
