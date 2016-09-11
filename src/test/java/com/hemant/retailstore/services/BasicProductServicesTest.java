package com.hemant.retailstore.services;

import static com.hemant.retailstore.fixtures.TestServicesFixtures.createProduct;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hemant.retailstore.dao.ProductDao;
import com.hemant.retailstore.model.Product;
import com.hemant.retailstore.services.basic.BasicProductServices;

@RunWith(MockitoJUnitRunner.class)
public class BasicProductServicesTest {

	@Mock
	private ProductDao productDao;
	@Mock
	private Product resultProduct;

	@InjectMocks
	private BasicProductServices srv;

	@Test
	public void shouldAddProduct() throws Exception {
		srv.addProduct(createProduct());
		Mockito.verify(productDao, only()).addProduct(Mockito.any(Product.class));
	}

	@Test
	public void shouldSearchProductById() throws Exception {
		when(productDao.getProductById(Mockito.anyLong())).thenReturn(resultProduct);
		assertSearchResult(srv.getProductById(1L));
		Mockito.verify(productDao, only()).getProductById(Mockito.anyLong());
	}

	@Test
	public void shouldSearchProduct() throws Exception {
		when(productDao.getProductList()).thenReturn(Collections.singletonList(resultProduct));
		assertSearchAllResult(srv.getProductList());
		Mockito.verify(productDao, only()).getProductList();
	}

	@Test
	public void shouldDeleteProductById() throws Exception {
		srv.deleteProduct(Mockito.anyLong());
		Mockito.verify(productDao, only()).deleteProduct(Mockito.anyLong());
	}

	@Test(expected = Exception.class)
	public void shouldNotDeleteProductById() throws Exception {
		Mockito.doThrow(ObjectNotFoundException.class).when(productDao).deleteProduct(Mockito.anyLong());
		srv.deleteProduct(Mockito.anyLong());
		Mockito.verify(productDao, only()).deleteProduct(Mockito.anyLong());
	}

	private void assertSearchResult(Product product) {
		assertThat(product, CoreMatchers.notNullValue());
		assertThat(product, CoreMatchers.sameInstance(resultProduct));
	}

	private void assertSearchAllResult(List<Product> productList) {
		assertThat(productList, CoreMatchers.notNullValue());
		assertThat(productList, Matchers.hasSize(1));
		assertThat(productList.get(0), CoreMatchers.sameInstance(resultProduct));
	}

}
