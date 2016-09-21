package com.smartErp.system.enumerate;

public enum ReturnMessageEnum {
	FAIL(-1),
	SUCCESS(1),
	WARRING(0);
    int value;
    private ReturnMessageEnum(int value) {
	    this.value = value;
	}
	public int getValue() {
		return value;
	}
}
