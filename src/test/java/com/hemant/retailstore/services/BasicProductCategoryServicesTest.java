package com.hemant.retailstore.services;

import static com.hemant.retailstore.fixtures.TestServicesFixtures.createProductCategory;
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

import com.hemant.retailstore.dao.ProductCategoryDao;
import com.hemant.retailstore.model.ProductCategory;
import com.hemant.retailstore.services.basic.BasicProductCategoryServices;

@RunWith(MockitoJUnitRunner.class)
public class BasicProductCategoryServicesTest {

	@Mock
	private ProductCategoryDao productCategoryDao;
	@Mock
	private ProductCategory resultProductCategory;

	@InjectMocks
	private BasicProductCategoryServices srv;

	@Test
	public void shouldAddProductCategory() throws Exception {
		srv.addProductCategory(createProductCategory());
		Mockito.verify(productCategoryDao, only()).addProductCategory(Mockito.any(ProductCategory.class));
	}

	@Test
	public void shouldSearchProductCategoryById() throws Exception {
		when(productCategoryDao.getProductCategoryById(Mockito.anyLong())).thenReturn(resultProductCategory);
		assertSearchResult(srv.getProductCategoryById(1L));
		Mockito.verify(productCategoryDao, only()).getProductCategoryById(Mockito.anyLong());
	}

	@Test
	public void shouldSearchProductCategory() throws Exception {
		when(productCategoryDao.getProductCategoryList()).thenReturn(Collections.singletonList(resultProductCategory));
		assertSearchAllResult(srv.getProductCategoryList());
		Mockito.verify(productCategoryDao, only()).getProductCategoryList();
	}

	@Test
	public void shouldDeleteProductCategoryById() throws Exception {
		srv.deleteProductCategory(Mockito.anyLong());
		Mockito.verify(productCategoryDao, only()).deleteProductCategory(Mockito.anyLong());
	}

	@Test(expected = Exception.class)
	public void shouldNotDeleteProductCategoryById() throws Exception {
		Mockito.doThrow(ObjectNotFoundException.class).when(productCategoryDao)
				.deleteProductCategory(Mockito.anyLong());
		srv.deleteProductCategory(Mockito.anyLong());
		Mockito.verify(productCategoryDao, only()).deleteProductCategory(Mockito.anyLong());
	}

	private void assertSearchResult(ProductCategory productCategory) {
		assertThat(productCategory, CoreMatchers.notNullValue());
		assertThat(productCategory, CoreMatchers.sameInstance(resultProductCategory));
	}

	private void assertSearchAllResult(List<ProductCategory> productCategoryList) {
		assertThat(productCategoryList, CoreMatchers.notNullValue());
		assertThat(productCategoryList, Matchers.hasSize(1));
		assertThat(productCategoryList.get(0), CoreMatchers.sameInstance(resultProductCategory));
	}

}
