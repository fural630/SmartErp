package com.smartErp.system.model;

public class ScriptConfig {
	
	private Integer id;
	private String crontab;
	private String scriptUrl;
	private Integer scriptType;
	private Integer randomRange;
	private Integer isOpened;
	private Integer creatorId;
	private String scriptName;
	private String remark;
	private String createTime;
	private String updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCrontab() {
		return crontab;
	}
	public void setCrontab(String crontab) {
		this.crontab = crontab;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	public Integer getScriptType() {
		return scriptType;
	}
	public void setScriptType(Integer scriptType) {
		this.scriptType = scriptType;
	}
	public Integer getRandomRange() {
		return randomRange;
	}
	public void setRandomRange(Integer randomRange) {
		this.randomRange = randomRange;
	}
	public Integer getIsOpened() {
		return isOpened;
	}
	public void setIsOpened(Integer isOpened) {
		this.isOpened = isOpened;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
}
