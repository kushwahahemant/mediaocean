package com.hemant.retailstore.services;

import java.util.List;

import com.hemant.retailstore.model.LineItem;
/**
 * @author Hemant Kushwaha
 */
public interface LineItemServices {
	public boolean addLineItem(LineItem lineItem) throws Exception;
	public LineItem getLineItemById(long id) throws Exception;
	public List<LineItem> getLineItemList() throws Exception;
	public boolean deleteLineItem(long id) throws Exception;
}
