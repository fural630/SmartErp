package com.smartErp.application.libraries.constentEnum;

public enum RandomRangeEnum {
	ONE_TO_FIVE(5),
	ONE_TO_TEN(10),
	ONE_TO_FIFTEEN(15),
	SIXTEEN_TO_THIRTY(20),
	THIRTYONE_TO_FORTYFIVE(25),
	FORTYSIX_TO_FIFTYNINE(30),
	ZERO_TO_FIFTYNINE(45);
	
	Integer value;
	private RandomRangeEnum(Integer value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	
//	public static void main(String[] args) {
//		String string[] = {"one.to.five", "one.to.ten", "one.to.fifteen", "sixteen.to.thirty", "thirtyone.to.fortyfive", "fortysix.to.fiftynine", "zero.to.fiftynine"};
//		for (String string2 : string) {
//			System.out.println(string2.toUpperCase().replace(".", "_") + "(),");
//		}
//	}
	
	
}
