package com.hemant.retailstore.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemant.retailstore.model.ProductCategory;
import com.hemant.retailstore.model.Status;
import com.hemant.retailstore.services.ProductCategoryServices;

@Controller
@RequestMapping("/api/product")
public class ProductCategoryRestController {
	static final Logger logger = Logger.getLogger(ProductCategoryRestController.class);

	@Autowired
	ProductCategoryServices productCategoryServices;

	@RequestMapping(value = "/category/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addProductCategory(@RequestBody ProductCategory category) {
		try {
			productCategoryServices.addProductCategory(category);
			return new Status(1, "Product Category added Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductCategory getProductCategory(@PathVariable("id") long id) {
		ProductCategory category = null;
		try {
			category = productCategoryServices.getProductCategoryById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return category;
	}

	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public @ResponseBody List<ProductCategory> getProductCategory() {
		List<ProductCategory> categoryList = null;
		try {
			categoryList = productCategoryServices.getProductCategoryList();
		} catch (Exception e) {
			logger.error(e);
		}
		return categoryList;
	}

	@RequestMapping(value = "/category/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteProductCategory(@PathVariable("id") long id) {
		try {
			productCategoryServices.deleteProductCategory(id);
			return new Status(1, "Product Category deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
