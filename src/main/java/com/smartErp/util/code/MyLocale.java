package com.smartErp.util.code;

import java.util.Locale;
import java.util.ResourceBundle;

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
