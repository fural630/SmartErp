package com.smartErp.system.enumerate;

public enum ReturnMessageEnum {
	FAIL(0),
	SUCCESS(1),
	WARRING(2);
    int value;
    private ReturnMessageEnum(int value) {
	    this.value = value;
	}
	public int getValue() {
		return value;
	}
}
