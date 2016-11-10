package com.smartErp.cdiscount.model;

public class CdiscountDefaultsValue {
	private Integer id;
	private String templateName;
	private String brandName;
	private Integer quantity;
	private Double dea;
	private Double vat;
	private Double ecoPart;
	private Integer stockingTime;
	private Integer isDefaults;
	private Integer creator;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getDea() {
		return dea;
	}
	public void setDea(Double dea) {
		this.dea = dea;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getEcoPart() {
		return ecoPart;
	}
	public void setEcoPart(Double ecoPart) {
		this.ecoPart = ecoPart;
	}
	public Integer getStockingTime() {
		return stockingTime;
	}
	public void setStockingTime(Integer stockingTime) {
		this.stockingTime = stockingTime;
	}
	public Integer getIsDefaults() {
		return isDefaults;
	}
	public void setIsDefaults(Integer isDefaults) {
		this.isDefaults = isDefaults;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
}
