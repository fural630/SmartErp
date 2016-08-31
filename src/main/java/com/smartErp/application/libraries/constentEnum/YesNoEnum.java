package com.smartErp.application.libraries.constentEnum;

public enum YesNoEnum {
	YES(1),
	NO(0);
	
	Integer value;
	
	private YesNoEnum(Integer value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
}
