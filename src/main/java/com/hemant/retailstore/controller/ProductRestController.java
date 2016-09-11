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

import com.hemant.retailstore.model.Product;
import com.hemant.retailstore.model.Status;
import com.hemant.retailstore.services.ProductServices;

/**
 * @author Hemant Kushwaha
 */
@Controller
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductServices productServices;

	static final Logger logger = Logger.getLogger(ProductRestController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addProduct(@RequestBody Product product) {
		try {
			productServices.addProduct(product);
			return new Status(1, "Product added Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable("id") long id) {
		Product product = null;
		try {
			product = productServices.getProductById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return product;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProduct() {
		List<Product> productList = null;
		try {
			productList = productServices.getProductList();
		} catch (Exception e) {
			logger.error(e);
		}
		return productList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteProduct(@PathVariable("id") long id) {

		try {
			productServices.deleteProduct(id);
			return new Status(1, "Product deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
