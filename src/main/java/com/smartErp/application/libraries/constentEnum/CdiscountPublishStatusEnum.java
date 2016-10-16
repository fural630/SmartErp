package com.smartErp.application.libraries.constentEnum;

public enum CdiscountPublishStatusEnum {
// 	WAIT_PENDING(1),	           		   //待处理
//	WAIT_PRODUCT_UPLOAD_PACKAGE(5),   	   //待商品打包上传
//	UPLOAD_PRODUCT_PACKAGE_FAIL(10),	   //商品打包上传失败
//	PLATFORM_VALIDATION_PRODUCT_ING(15),   //商品系统验证中
//	PLATFORM_MANUAL_REVIEW_PRODUCT_ING(20),//商品人工审查中
//	WAIT_OFFERS_UPLOAD_PACKAGE(25),		   //待Offers打包上传
//	PLATFORM_VALIDATION_OFFERS_ING(30),    //Offers系统验证中
//	UPLOAD_OFFERS_PACKAGE_FAIL(35),		   //Offers打包上传失败
//	PUBLISHED_SUCCESS(40),                 //刊登成功
//	PUBLISHED_FAIL(45);                    //刊登失败
	
	
 	WAIT_PENDING(5),						//待处理
	WAIT_SYSTEM_UPLOAD(10),					//待系统上传
	PLATFORM_VALIDATE_ING(15),				//平台验证中
	PUBLISHED_SUCCESS(1),                 	//刊登成功
	PUBLISHED_FAIL(0);                    	//刊登失败
	
 	
    int value;
    private CdiscountPublishStatusEnum(int value) {
	    this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
