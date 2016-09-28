package com.smartErp.application.libraries.constentEnum;

public enum TimeQueryEnum {
	CREATE_TIME("createTime"),
	UPDATE_TIME("updateTime");
	
	String value;
	private TimeQueryEnum (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
