package com.hemant.retailstore.model;

public class OrderDataSummary implements java.io.Serializable {
	private static final long serialVersionUID = -8082832450922761364L;

	private OrderData orderData;
	private Double totalBeforeOrderAmount;
	private Double totalTaxAmount;
	private Double netOrderAmount;

	public OrderData getOrderData() {
		return orderData;
	}

	public void setOrderData(OrderData orderData) {
		this.orderData = orderData;
	}

	public Double getTotalBeforeOrderAmount() {
		return totalBeforeOrderAmount;
	}

	public void setTotalBeforeOrderAmount(Double totalBeforeOrderAmount) {
		this.totalBeforeOrderAmount = totalBeforeOrderAmount;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public Double getNetOrderAmount() {
		return netOrderAmount;
	}

	public void setNetOrderAmount(Double netOrderAmount) {
		this.netOrderAmount = netOrderAmount;
	}

}
