package com.smartErp.application.libraries.constentEnum;

public enum CdiscountPublishStatusEnum {
	
 	WAIT_PENDING(5),						//待处理
	WAIT_SYSTEM_UPLOAD_BASIC_INFO(10),		//待上传基本信息
	PLATFORM_SYSTEM_VALIDATE_ING(15),		//基本信息系统审核中
	PLATFORM_MANUAL_VALIDATE_ING(20),       //基本信息人工审核中
	WAIT_SYSTEM_UPLOAD_OFFERS(25),          //待上传Offers
 	PLATFORM_OFFERS_VALIDATE_ING(30),		//Offers审核中
 	PUBLISHED_FAIL(100),					//刊登失败
 	PUBLISHED_SUCCESS(200);					//刊登成功
 	
    int value;
    private CdiscountPublishStatusEnum(int value) {
	    this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
