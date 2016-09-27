package com.smartErp.application.libraries.constentEnum;

public enum DeliveryModeTypeEnum {
	SMALL_PARCEL(5),
	BIG_PARCEL(10);
	
	int value;
	private DeliveryModeTypeEnum (int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
