package com.made4you.controle.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entities.Stock;

@Repository
public class StockDAOHibernateImplementation implements StockDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public void save(Stock stock) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(stock);		
	}
	
	@Override
	public void addBalance(int quantity) {
					
	}

	@Override
	public Stock findByForeignKeyId(long productId, long storagePlaceId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Stock> query = currentSession.createQuery("from Stock where product_id=:productId and storage_place_id=:storagePlaceId", Stock.class);
		query.setParameter("productId", productId);
		query.setParameter("storagePlaceId", storagePlaceId);
		
		Stock stock = null;
		
		try {
			stock = query.getSingleResult();
		}
		
		catch(NoResultException exc) {
			
		}
	
		return stock;		
	}

	

}
