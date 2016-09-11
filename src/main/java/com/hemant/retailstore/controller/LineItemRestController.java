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

import com.hemant.retailstore.model.LineItem;
import com.hemant.retailstore.model.Status;
import com.hemant.retailstore.services.LineItemServices;

/**
 * @author Hemant Kushwaha
 */
@Controller
@RequestMapping("/api/lineitem")
public class LineItemRestController {
	@Autowired
	LineItemServices lineItemServices;

	static final Logger logger = Logger.getLogger(LineItemRestController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addProduct(@RequestBody LineItem lineItem) {
		try {
			lineItemServices.addLineItem(lineItem);
			return new Status(1, "LineItem added Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody LineItem getLineItem(@PathVariable("id") long id) {
		LineItem lineItem = null;
		try {
			lineItem = lineItemServices.getLineItemById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return lineItem;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<LineItem> getLineItem() {
		List<LineItem> lineItemList = null;
		try {
			lineItemList = lineItemServices.getLineItemList();
		} catch (Exception e) {
			logger.error(e);
		}
		return lineItemList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteLineItem(@PathVariable("id") long id) {

		try {
			lineItemServices.deleteLineItem(id);
			return new Status(1, "LineItem deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
