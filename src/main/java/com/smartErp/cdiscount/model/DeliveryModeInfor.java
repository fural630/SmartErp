package com.smartErp.cdiscount.model;

public class DeliveryModeInfor {
	private Integer id;
	private String code;
	private Integer deliveryModeType;
	private String modeNameFR;
	private String modeNameEN;
	private Integer apiId;
	private String updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDeliveryModeType() {
		return deliveryModeType;
	}
	public void setDeliveryModeType(Integer deliveryModeType) {
		this.deliveryModeType = deliveryModeType;
	}
	public String getModeNameFR() {
		return modeNameFR;
	}
	public void setModeNameFR(String modeNameFR) {
		this.modeNameFR = modeNameFR;
	}
	public String getModeNameEN() {
		return modeNameEN;
	}
	public void setModeNameEN(String modeNameEN) {
		this.modeNameEN = modeNameEN;
	}
	public Integer getApiId() {
		return apiId;
	}
	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
