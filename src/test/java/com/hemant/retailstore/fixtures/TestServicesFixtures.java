package com.hemant.retailstore.fixtures;

import static com.hemant.retailstore.constants.TestConstants.TEST;
import static com.hemant.retailstore.constants.TestConstants.TEST_DOUBLE;
import static com.hemant.retailstore.constants.TestConstants.TEST_ID;
import static com.hemant.retailstore.constants.TestConstants.TEST_LONG_ID;
import static java.util.Collections.EMPTY_SET;

import com.hemant.retailstore.model.LineItem;
import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.Product;
import com.hemant.retailstore.model.ProductCategory;

public class TestServicesFixtures {

	private TestServicesFixtures() {
		super();
	}

	public static Product createProduct() {
		return new Product(TEST_LONG_ID, createProductCategory(), TEST, TEST, TEST_DOUBLE, EMPTY_SET);
	}

	public static ProductCategory createProductCategory() {
		return new ProductCategory(TEST_LONG_ID, TEST, TEST_DOUBLE, EMPTY_SET);
	}

	public static LineItem createLineItem() {
		return new LineItem(TEST_LONG_ID, createOrderData(), createProduct(), TEST_ID, TEST_DOUBLE, TEST_DOUBLE,
				TEST_DOUBLE);

	}

	public static OrderData createOrderData() {
		return new OrderData(TEST_LONG_ID, new java.util.Date(), EMPTY_SET);
	}
}
