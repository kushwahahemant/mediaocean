package com.hemant.retailstore.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productcategory", catalog = "hemant")
public class ProductCategory implements java.io.Serializable {
	private static final long serialVersionUID = -410170921389608625L;

	private Long categoryid;
	private String name;
	private Double salestax;
	private Set<Product> products = new HashSet<Product>(0);

	public ProductCategory() {
	}

	public ProductCategory(Long categoryid) {
		this.categoryid = categoryid;
	}

	public ProductCategory(Long categoryid, String name, Double salestax, Set<Product> products) {
		this.categoryid = categoryid;
		this.name = name;
		this.salestax = salestax;
		this.products = products;
	}

	@Id
	@Column(name = "CATEGORYID", unique = true, nullable = false)
	public Long getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "NAME", length = 11)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SALESTAX")
	public Double getSalestax() {
		return this.salestax;
	}

	public void setSalestax(Double salestax) {
		this.salestax = salestax;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productcategory")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
