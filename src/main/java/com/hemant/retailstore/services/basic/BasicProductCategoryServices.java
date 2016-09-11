package com.hemant.retailstore.services.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.ProductCategoryDao;
import com.hemant.retailstore.model.ProductCategory;
import com.hemant.retailstore.services.ProductCategoryServices;

/**
 * @author Hemant Kushwaha
 */
public class BasicProductCategoryServices implements ProductCategoryServices {
	@Autowired
	ProductCategoryDao productCategoryDao;

	@Override
	public boolean addProductCategory(ProductCategory category) throws Exception {
		return productCategoryDao.addProductCategory(category);
	}

	@Override
	public ProductCategory getProductCategoryById(long id) throws Exception {
		return productCategoryDao.getProductCategoryById(id);
	}

	@Override
	public List<ProductCategory> getProductCategoryList() throws Exception {
		return productCategoryDao.getProductCategoryList();
	}

	@Override
	public boolean deleteProductCategory(long id) throws Exception {
		return productCategoryDao.deleteProductCategory(id);
	}

}
