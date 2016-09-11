package com.hemant.retailstore.services.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.ProductDao;
import com.hemant.retailstore.model.Product;
import com.hemant.retailstore.services.ProductServices;

/**
 * @author Hemant Kushwaha
 */
public class BasicProductServices implements ProductServices {
	@Autowired
	ProductDao productDao;

	@Override
	public boolean addProduct(Product product) throws Exception {
		return productDao.addProduct(product);
	}

	@Override
	public Product getProductById(long id) throws Exception {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getProductList() throws Exception {
		return productDao.getProductList();
	}

	@Override
	public boolean deleteProduct(long id) throws Exception {
		return productDao.deleteProduct(id);
	}


}
