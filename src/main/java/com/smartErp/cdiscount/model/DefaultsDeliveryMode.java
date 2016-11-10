package com.smartErp.cdiscount.model;

public class DefaultsDeliveryMode {
	private Integer id;
	private String deliveryMode;
	private Double addShippingCharges;
	private Double shippingCharges;
	private Integer defaultsId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public Double getAddShippingCharges() {
		return addShippingCharges;
	}
	public void setAddShippingCharges(Double addShippingCharges) {
		this.addShippingCharges = addShippingCharges;
	}
	public Double getShippingCharges() {
		return shippingCharges;
	}
	public void setShippingCharges(Double shippingCharges) {
		this.shippingCharges = shippingCharges;
	}
	public Integer getDefaultsId() {
		return defaultsId;
	}
	public void setDefaultsId(Integer defaultsId) {
		this.defaultsId = defaultsId;
	}
}
