package com.hemant.retailstore.services;

import java.util.List;

import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.OrderDataSummary;

/**
 * @author Hemant Kushwaha
 */
public interface OrderDataServices {
	public boolean addOrderData(OrderData orderData) throws Exception;
	public OrderData getOrderDataById(long id) throws Exception;
	public List<OrderData> getOrderDataList() throws Exception;
	public boolean deleteOrderData(long id) throws Exception;
	public OrderDataSummary getOrderDataSummary(long id) throws Exception;
}
