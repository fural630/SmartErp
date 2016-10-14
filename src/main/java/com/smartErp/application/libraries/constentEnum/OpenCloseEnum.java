package com.smartErp.application.libraries.constentEnum;

public enum OpenCloseEnum {
	OPEN(1),
	CLOSE(0);
	
	Integer value;
	
	private OpenCloseEnum(Integer value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
}
