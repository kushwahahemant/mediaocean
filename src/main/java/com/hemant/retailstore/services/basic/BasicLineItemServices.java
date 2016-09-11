package com.hemant.retailstore.services.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.LineItemDao;
import com.hemant.retailstore.model.LineItem;
import com.hemant.retailstore.services.LineItemServices;

public class BasicLineItemServices implements LineItemServices {

	@Autowired
	LineItemDao lineItemDao;

	@Override
	public boolean addLineItem(LineItem lineItem) throws Exception {
		return lineItemDao.addLineItem(lineItem);
	}

	@Override
	public LineItem getLineItemById(long id) throws Exception {
		return lineItemDao.getLineItemById(id);
	}

	@Override
	public List<LineItem> getLineItemList() throws Exception {
		return lineItemDao.getLineItemList();
	}

	@Override
	public boolean deleteLineItem(long id) throws Exception {
		return lineItemDao.deleteLineItem(id);
	}

}
