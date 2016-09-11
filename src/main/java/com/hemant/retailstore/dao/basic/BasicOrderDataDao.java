package com.hemant.retailstore.dao.basic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.OrderDataDao;
import com.hemant.retailstore.model.LineItem;
import com.hemant.retailstore.model.OrderData;
import com.hemant.retailstore.model.OrderDataSummary;
import com.hemant.retailstore.model.Product;
import com.hemant.retailstore.model.ProductCategory;

public class BasicOrderDataDao implements OrderDataDao {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addOrderData(OrderData orderData) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(orderData);
		tx.commit();
		session.close();

		return true;
	}

	@Override
	public OrderData getOrderDataById(long id) throws Exception {
		session = sessionFactory.openSession();
		OrderData orderData = (OrderData) session.load(OrderData.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return orderData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderData> getOrderDataList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<OrderData> orderDataList = session.createCriteria(OrderData.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session.close();
		return orderDataList;
	}

	@Override
	public boolean deleteOrderData(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(OrderData.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return true;
	}

	@Override
	public OrderDataSummary getOrderDataSummary(long id) throws Exception {
		session = sessionFactory.openSession();
		OrderDataSummary summary = calculateOrderData((OrderData) session.load(OrderData.class, new Long(id)));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return summary;
	}

	private OrderDataSummary calculateOrderData(OrderData orderData) {
		OrderDataSummary summary = new OrderDataSummary();
		OrderData itemizedOrderData = itemizedOrder(orderData);
		Set<LineItem> itemizedLineitems = new HashSet<LineItem>(0);

		Double saleTaxAmount = 0.0;
		Double totalAmount = 0.0;
		Double netAmount = 0.0;

		// Calculate Sale Tax, Total Price, Net Price
		for (LineItem item : orderData.getLineitems()) {

			Product product = item.getProduct();
			ProductCategory productCategory = product.getProductcategory();
			
			item.setTotalAmount(item.getQuantity() * product.getPrice());
			totalAmount += item.getTotalAmount();
			
			item.setSalesTaxAmount((item.getTotalAmount() * productCategory.getSalestax()) / 100);
			saleTaxAmount += item.getSalesTaxAmount();
			
			item.setNetAmount(item.getTotalAmount() + item.getSalesTaxAmount());
			netAmount += totalAmount + saleTaxAmount;

			itemizedLineitems.add(item);
		}
		itemizedOrderData.setLineitems(itemizedLineitems);
		summary.setOrderData(itemizedOrderData);
		summary.setTotalBeforeOrderAmount(totalAmount);
		summary.setTotalTaxAmount(saleTaxAmount);
		summary.setNetOrderAmount(netAmount);

		return summary;
	}

	private OrderData itemizedOrder(OrderData orderData) {
		OrderData itemizedOrder = new OrderData();

		itemizedOrder.setOrderid(orderData.getOrderid());
		itemizedOrder.setOrderdate(orderData.getOrderdate());

		return itemizedOrder;
	}
}
