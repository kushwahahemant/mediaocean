package com.hemant.retailstore.services.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.OrderDataDao;
import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.OrderDataSummary;
import com.hemant.retailstore.services.OrderDataServices;

/**
 * @author Hemant Kushwaha
 */
public class BasicOrderDataServices implements OrderDataServices {
	@Autowired
	OrderDataDao orderDataDao;
	
	@Override
	public boolean addOrderData(OrderData orderData) throws Exception {
		return orderDataDao.addOrderData(orderData);
	}

	@Override
	public OrderData getOrderDataById(long id) throws Exception {
		return orderDataDao.getOrderDataById(id);
	}

	@Override
	public List<OrderData> getOrderDataList() throws Exception {
		return orderDataDao.getOrderDataList();
	}

	@Override
	public boolean deleteOrderData(long id) throws Exception {
		return orderDataDao.deleteOrderData(id);
	}

	@Override
	public OrderDataSummary getOrderDataSummary(long id) throws Exception {
		return orderDataDao.getOrderDataSummary(id);
	}

}
