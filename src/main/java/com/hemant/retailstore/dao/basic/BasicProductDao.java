package com.hemant.retailstore.dao.basic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.ProductDao;
import com.hemant.retailstore.model.Product;

/**
 * @author Hemant Kushwaha
 */
public class BasicProductDao implements ProductDao {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProduct(Product product) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();

		return true;
	}

	@Override
	public Product getProductById(long id) throws Exception {
		session = sessionFactory.openSession();
		Product product = (Product) session.load(Product.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Product> productList = session.createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session.close();
		return productList;
	}

	@Override
	public boolean deleteProduct(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Product.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return true;
	}
}
