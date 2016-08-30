package com.smartErp.system.model;

import com.smartErp.system.enumerate.ReturnMessageEnum;

public class ReturnMessage {
	
	private Integer status = ReturnMessageEnum.SUCCESS.getValue();
	private String message;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
