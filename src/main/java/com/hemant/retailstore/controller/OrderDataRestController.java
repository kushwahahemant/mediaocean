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

import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.Status;
import com.hemant.retailstore.services.OrderDataServices;

/**
 * @author Hemant Kushwaha
 */
@Controller
@RequestMapping("/api/orderdata")
public class OrderDataRestController {
	@Autowired
	OrderDataServices orderDataServices;

	static final Logger logger = Logger.getLogger(OrderDataRestController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addProduct(@RequestBody OrderData orderData) {
		try {
			orderDataServices.addOrderData(orderData);
			return new Status(1, "OrderData added Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody OrderData getOrderData(@PathVariable("id") long id) {
		OrderData orderData = null;
		try {
			orderData = orderDataServices.getOrderDataById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return orderData;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<OrderData> getOrderData() {
		List<OrderData> orderDataList = null;
		try {
			orderDataList = orderDataServices.getOrderDataList();
		} catch (Exception e) {
			logger.error(e);
		}
		return orderDataList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteOrderData(@PathVariable("id") long id) {

		try {
			orderDataServices.deleteOrderData(id);
			return new Status(1, "OrderData deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
	
	@RequestMapping(value = "/summary/{id}", method = RequestMethod.GET)
	public @ResponseBody Status getOrderDataSummary(@PathVariable("id") long id) {

		try {
			orderDataServices.getOrderDataSummary(id);
			return new Status(1, "OrderData deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
