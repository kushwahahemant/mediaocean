package com.hemant.retailstore.services;

import java.util.List;

import com.hemant.retailstore.model.Product;

/**
 * @author Hemant Kushwaha
 */
public interface ProductServices {
	public boolean addProduct(Product product) throws Exception;
	public Product getProductById(long id) throws Exception;
	public List<Product> getProductList() throws Exception;
	public boolean deleteProduct(long id) throws Exception;
}
