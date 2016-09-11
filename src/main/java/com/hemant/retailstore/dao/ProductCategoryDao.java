package com.hemant.retailstore.dao;

import java.util.List;

import com.hemant.retailstore.model.ProductCategory;

/**
 * @author Hemant Kushwaha
 */
public interface ProductCategoryDao {
	public boolean addProductCategory(ProductCategory category) throws Exception;
	public ProductCategory getProductCategoryById(long id) throws Exception;
	public List<ProductCategory> getProductCategoryList() throws Exception;
	public boolean deleteProductCategory(long id) throws Exception;
}
