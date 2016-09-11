package com.hemant.retailstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lineitem", catalog = "hemant")
public class LineItem implements java.io.Serializable {
	private static final long serialVersionUID = -5246619768992165169L;

	private Long orderline;
	private OrderData orderdata;
	private Product product;
	private Integer quantity;
	private Double salesTaxAmount = 0.0;
	private Double totalAmount = 0.0;
	private Double netAmount = 0.0;

	public LineItem() {
	}

	public LineItem(Long orderline) {
		this.orderline = orderline;
	}

	public LineItem(Long orderline, OrderData orderdata, Product product, Integer quantity, Double salesTaxAmount,
			Double totalAmount, Double netAmount) {
		this.orderline = orderline;
		this.orderdata = orderdata;
		this.product = product;
		this.quantity = quantity;
		this.salesTaxAmount = salesTaxAmount;
		this.totalAmount = totalAmount;
		this.netAmount = netAmount;
	}

	@Id
	@Column(name = "orderline", unique = true, nullable = false)
	public Long getOrderline() {
		return this.orderline;
	}

	public void setOrderline(Long orderline) {
		this.orderline = orderline;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERID")
	public OrderData getOrderdata() {
		return this.orderdata;
	}

	public void setOrderdata(OrderData orderdata) {
		this.orderdata = orderdata;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "SALESTAX")
	public Double getSalesTaxAmount() {
		return salesTaxAmount;
	}

	public void setSalesTaxAmount(Double salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}

	@Column(name = "TOTALAMOUNT")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "NETAMOUNT")
	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
}
