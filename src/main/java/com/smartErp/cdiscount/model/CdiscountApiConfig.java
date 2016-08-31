package com.smartErp.cdiscount.model;

public class CdiscountApiConfig {
	private Integer id;
	private String shopName;
	private String email;
	private String apiAccount;
	private String apiPassword;
	private String receivablesEmail;
	private Integer closeStatus;
	private Integer creator;
	private String systemLog;
	private String lastUpdateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApiAccount() {
		return apiAccount;
	}
	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}
	public String getApiPassword() {
		return apiPassword;
	}
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}
	public String getReceivablesEmail() {
		return receivablesEmail;
	}
	public void setReceivablesEmail(String receivablesEmail) {
		this.receivablesEmail = receivablesEmail;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getSystemLog() {
		return systemLog;
	}
	public void setSystemLog(String systemLog) {
		this.systemLog = systemLog;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getCloseStatus() {
		return closeStatus;
	}
	public void setCloseStatus(Integer closeStatus) {
		this.closeStatus = closeStatus;
	}
}
