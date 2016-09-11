package com.hemant.retailstore.services;

import static com.hemant.retailstore.fixtures.TestServicesFixtures.createOrderData;
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

import com.hemant.retailstore.dao.OrderDataDao;
import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.OrderDataSummary;
import com.hemant.retailstore.services.basic.BasicOrderDataServices;

@RunWith(MockitoJUnitRunner.class)
public class BasicOrderDataServicesTest {

	@Mock
	private OrderDataDao orderDataDao;
	@Mock
	private OrderData resultOrderData;
	@Mock
	private OrderDataSummary resultOrderDataSummary;

	@InjectMocks
	private BasicOrderDataServices srv;

	@Test
	public void shouldAddOrderData() throws Exception {
		srv.addOrderData(createOrderData());
		Mockito.verify(orderDataDao, only()).addOrderData(Mockito.any(OrderData.class));
	}

	@Test
	public void shouldSearchOrderDataById() throws Exception {
		when(orderDataDao.getOrderDataById(Mockito.anyLong())).thenReturn(resultOrderData);
		assertSearchResult(srv.getOrderDataById(1L));
		Mockito.verify(orderDataDao, only()).getOrderDataById(Mockito.anyLong());
	}

	@Test
	public void shouldSearchOrderData() throws Exception {
		when(orderDataDao.getOrderDataList()).thenReturn(Collections.singletonList(resultOrderData));
		assertSearchAllResult(srv.getOrderDataList());
		Mockito.verify(orderDataDao, only()).getOrderDataList();
	}

	@Test
	public void shouldDeleteOrderDataById() throws Exception {
		srv.deleteOrderData(Mockito.anyLong());
		Mockito.verify(orderDataDao, only()).deleteOrderData(Mockito.anyLong());
	}

	@Test(expected = Exception.class)
	public void shouldNotDeleteOrderDataById() throws Exception {
		Mockito.doThrow(ObjectNotFoundException.class).when(orderDataDao).deleteOrderData(Mockito.anyLong());
		srv.deleteOrderData(Mockito.anyLong());
		Mockito.verify(orderDataDao, only()).deleteOrderData(Mockito.anyLong());
	}

	@Test
	public void shouldGetOrderDataSummary() throws Exception {
		when(orderDataDao.getOrderDataSummary(Mockito.anyLong())).thenReturn(resultOrderDataSummary);
		assertOrderDataSummaryResult(srv.getOrderDataSummary(Mockito.anyLong()));
		Mockito.verify(orderDataDao, only()).getOrderDataSummary(Mockito.anyLong());
	}
	
	
	private void assertOrderDataSummaryResult(OrderDataSummary orderDataSummary) {
		assertThat(orderDataSummary, notNullValue());
		assertThat(orderDataSummary, sameInstance(resultOrderDataSummary));
	}

	private void assertSearchResult(OrderData orderData) {
		assertThat(orderData, notNullValue());
		assertThat(orderData, sameInstance(resultOrderData));
	}

	private void assertSearchAllResult(List<OrderData> orderDataList) {
		assertThat(orderDataList, notNullValue());
		assertThat(orderDataList, hasSize(1));
		assertThat(orderDataList.get(0), sameInstance(resultOrderData));
	}


	
	
}
