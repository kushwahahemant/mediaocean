package com.hemant.retailstore.dao.basic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.LineItemDao;
import com.hemant.retailstore.model.LineItem;

public class BasicLineItemDao implements LineItemDao {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addLineItem(LineItem lineItem) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(lineItem);
		tx.commit();
		session.close();

		return true;
	}

	@Override
	public LineItem getLineItemById(long id) throws Exception {
		session = sessionFactory.openSession();
		LineItem lineItem = (LineItem) session.load(LineItem.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return lineItem;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LineItem> getLineItemList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<LineItem> lineItemList = session.createCriteria(LineItem.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session.close();
		return lineItemList;
	}

	@Override
	public boolean deleteLineItem(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(LineItem.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return true;
	}

}
