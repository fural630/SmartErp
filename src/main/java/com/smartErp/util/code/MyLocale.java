package com.smartErp.util.code;

import java.util.Locale;
import java.util.ResourceBundle;

import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;

public class MyLocale {
	private ResourceBundle resourceBundle;
	private Locale locale;
	private String propertyName = "com/package";
		
	public MyLocale() {
		setLocale(new Locale("cn"));
		setResourceBundle(ResourceBundle.getBundle(this.propertyName, this.locale));
	}
	
	public String getText(String key) {
		key = convert(key);
		String text = key;
		try {
			text = this.resourceBundle.getString(key);
		} catch (Exception e) {
		}
		MyDate mydate = new MyDate();
		text = text.replaceAll("\\{time\\}", mydate.getCurrentDateTime());
		User user = UserSingleton.getInstance().getUser();
		if (null != user) {
			text = text.replaceAll("\\{name\\}", user.getName());
		}
		return text;
	}
	
	public String getText(String key, String arg) {
		key = convert(key);
		String text = key;
		try {
			text = this.resourceBundle.getString(key);
		} catch (Exception e) {
		}
		MyDate mydate = new MyDate();
		text = text.replaceAll("\\{time\\}", mydate.getCurrentDateTime());
		if (null != text) {
			if (null == arg) {
				arg = "";
			}
			text = text.replaceAll("\\{0\\}", arg);
			User user = UserSingleton.getInstance().getUser();
			if (null != user) {
				text = text.replaceAll("\\{name\\}", user.getName());
			}
		}
		return text;
	}
	public String getText(String key, String... args) {
		key = convert(key);
		String text = key;
		try {
			text = this.resourceBundle.getString(key);
		} catch (Exception e) {}
		MyDate mydate = new MyDate();
		text = text.replaceAll("\\{time\\}", mydate.getCurrentDateTime());
		if (null != text) {
			for (int i = 0; i < args.length; i++) {
				String arg = "";
				if (null != arg) {
					arg = args[i].toString();
				}
				text = text.replaceAll("\\{" + i + "\\}", arg);
			}
			User user = UserSingleton.getInstance().getUser();
			if (null != user) {
				text = text.replaceAll("\\{name\\}", user.getName());
			}
		}
		return text;
	}
	
	
	public String convert(String key) {
		return key.replaceAll("_", ".").toLowerCase();
	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
