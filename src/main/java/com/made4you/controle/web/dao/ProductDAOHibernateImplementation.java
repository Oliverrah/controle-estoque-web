package com.made4you.controle.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entity.Product;

@Repository
public class ProductDAOHibernateImplementation implements ProductDAO {

	@Autowired
	private EntityManager entityManager;

	
	@Override
	public List<Product> findAll(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Product> query = currentSession.createQuery("from Product where user_id=:theId", Product.class);
		query.setParameter("theId", userId);
		
		return query.getResultList();
	}
	
	@Override
	public void save(Product product) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(product);		
	}

	@Override
	public List<Product> searchProducts(String productName, int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Product> query = null;
		
		if(productName != null && productName.trim().length() > 0) {
			
			query = currentSession.createQuery("from Product where lower(name) like :theName and user_id=:theId", Product.class);
			query.setParameter("theName","%" + productName.toLowerCase() +"%");
			query.setParameter("theId", userId);
		}
		else {
			query = currentSession.createQuery("from Product where user_id=:theId", Product.class);
			query.setParameter("theId", userId);
		}
		
		List<Product> products = query.getResultList();
		
		if(products.size() <= 0) {
			return null;
		}
		
		return products;
	}


	@Override
	public Product findByName(String productName, int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Product> query = currentSession.createQuery("from Product where name=:theName and user_id=:theId", Product.class);
		query.setParameter("theName", productName);
		query.setParameter("theId", userId);
		
		Product product = null;
		
		try {
			product = query.getSingleResult();
		}		
		catch(NoResultException exc) {
			
		}
		
		//cleaning the session for later update in product controller addProduct() method.
		currentSession.clear();
				
		return product;	
	}


	@Override
	public Product findyById(Long productId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Product> query = currentSession.createQuery("from Product where id=:theId", Product.class);
		query.setParameter("theId", productId);
				
		return query.getSingleResult();
	}


	@Override
	public void delete(Product product) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.delete(product);
	}
}
