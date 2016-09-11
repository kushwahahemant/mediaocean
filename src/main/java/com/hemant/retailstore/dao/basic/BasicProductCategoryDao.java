package com.hemant.retailstore.dao.basic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.hemant.retailstore.dao.ProductCategoryDao;
import com.hemant.retailstore.model.ProductCategory;

/**
 * @author Hemant Kushwaha
 */
public class BasicProductCategoryDao implements ProductCategoryDao {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;


	@Override
	public boolean addProductCategory(ProductCategory category) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(category);
		tx.commit();
		session.close();

		return true;
	}

	@Override
	public ProductCategory getProductCategoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		ProductCategory category = (ProductCategory) session.load(ProductCategory.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> getProductCategoryList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<ProductCategory> categoryList = session.createCriteria(ProductCategory.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		session.close();
		return categoryList;
	}

	@Override
	public boolean deleteProductCategory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(ProductCategory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
}
