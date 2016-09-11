package com.hemant.retailstore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orderdata", catalog = "hemant")
public class OrderData implements java.io.Serializable {
	private static final long serialVersionUID = 7238121004084857119L;

	private Long orderid;
	private Date orderdate;
	private Set<LineItem> lineitems = new HashSet<LineItem>(0);

	public OrderData() {
	}

	public OrderData(Long orderid) {
		this.orderid = orderid;
	}

	public OrderData(Long orderid, Date orderdate, Set<LineItem> lineitems) {
		this.orderid = orderid;
		this.orderdate = orderdate;
		this.lineitems = lineitems;
	}

	@Id
	@Column(name = "ORDERID", unique = true, nullable = false)
	public Long getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ORDERDATE", length = 10)
	public Date getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderdata")
	public Set<LineItem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(Set<LineItem> lineitems) {
		this.lineitems = lineitems;
	}

}
