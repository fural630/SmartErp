package com.smartErp.application.libraries.constentEnum;

public enum ScriptTypeEnum {
	COSTOMIZE(5),
	EVER_RANDOM_NUMBER(10),
	SPECIFY_RANDOM_NUMBER(15);
	
	Integer value;
	private ScriptTypeEnum(Integer value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	
}
