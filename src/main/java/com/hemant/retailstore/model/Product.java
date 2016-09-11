package com.hemant.retailstore.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product", catalog = "hemant")
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = -7933946653849710872L;

	private Long productid;
	private ProductCategory productcategory;
	private String code;
	private String name;
	private Double price;
	private Set<LineItem> lineitems = new HashSet<LineItem>(0);

	public Product() {
	}

	public Product(Long productid) {
		this.productid = productid;
	}

	public Product(Long productid, ProductCategory productcategory, String code, String name, Double price,
			Set<LineItem> lineitems) {
		this.productid = productid;
		this.productcategory = productcategory;
		this.code = code;
		this.name = name;
		this.price = price;
		this.lineitems = lineitems;
	}

	@Id
	@Column(name = "PRODUCTID", unique = true, nullable = false)
	public Long getProductid() {
		return this.productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORYID")
	public ProductCategory getProductcategory() {
		return this.productcategory;
	}

	public void setProductcategory(ProductCategory productcategory) {
		this.productcategory = productcategory;
	}

	@Column(name = "CODE")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<LineItem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(Set<LineItem> lineitems) {
		this.lineitems = lineitems;
	}

}
