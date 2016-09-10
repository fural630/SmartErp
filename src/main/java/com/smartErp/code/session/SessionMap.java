package com.smartErp.code.session;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionMap {

	private HttpSession httpSession;

	public SessionMap() {
		this.httpSession = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
	}

	public Object get(String key) {
		return httpSession.getAttribute(key);
	}

	public void put(String key, Object object) {
		this.httpSession.setAttribute(key, object);
	}

	public void destory(String key) {
		this.httpSession.removeAttribute(key);
	}

	public void destoryAll() {
		this.httpSession.invalidate();
	}

}
