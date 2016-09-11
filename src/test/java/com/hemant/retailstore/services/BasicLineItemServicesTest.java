package com.hemant.retailstore.services;

import static com.hemant.retailstore.fixtures.TestServicesFixtures.createLineItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hemant.retailstore.dao.LineItemDao;
import com.hemant.retailstore.model.LineItem;
import com.hemant.retailstore.services.basic.BasicLineItemServices;

@RunWith(MockitoJUnitRunner.class)
public class BasicLineItemServicesTest {

	@Mock
	private LineItemDao lineItemDao;
	@Mock
	private LineItem resultLineItem;

	@InjectMocks
	private BasicLineItemServices srv;

	@Test
	public void shouldAddLineItem() throws Exception {
		srv.addLineItem(createLineItem());
		Mockito.verify(lineItemDao, only()).addLineItem(Mockito.any(LineItem.class));
	}

	@Test
	public void shouldSearchLineItemById() throws Exception {
		when(lineItemDao.getLineItemById(Mockito.anyLong())).thenReturn(resultLineItem);
		assertSearchResult(srv.getLineItemById(1L));
		Mockito.verify(lineItemDao, only()).getLineItemById(Mockito.anyLong());
	}

	@Test
	public void shouldSearchLineItem() throws Exception {
		when(lineItemDao.getLineItemList()).thenReturn(Collections.singletonList(resultLineItem));
		assertSearchAllResult(srv.getLineItemList());
		Mockito.verify(lineItemDao, only()).getLineItemList();
	}

	@Test
	public void shouldDeleteLineItemById() throws Exception {
		srv.deleteLineItem(Mockito.anyLong());
		Mockito.verify(lineItemDao, only()).deleteLineItem(Mockito.anyLong());
	}

	@Test(expected = Exception.class)
	public void shouldNotDeleteLineItemById() throws Exception {
		Mockito.doThrow(ObjectNotFoundException.class).when(lineItemDao).deleteLineItem(Mockito.anyLong());
		srv.deleteLineItem(Mockito.anyLong());
		Mockito.verify(lineItemDao, only()).deleteLineItem(Mockito.anyLong());
	}

	private void assertSearchResult(LineItem productCategory) {
		assertThat(productCategory, notNullValue());
		assertThat(productCategory, sameInstance(resultLineItem));
	}

	private void assertSearchAllResult(List<LineItem> productCategoryList) {
		assertThat(productCategoryList, notNullValue());
		assertThat(productCategoryList, hasSize(1));
		assertThat(productCategoryList.get(0), sameInstance(resultLineItem));
	}


	
	
}
