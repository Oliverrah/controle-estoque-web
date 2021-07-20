package com.made4you.controle.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entities.Category;

@Repository
public class CategoryDAOHibernateImplementation implements CategoryDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Category> findAll(int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Category> query = currentSession.createQuery("from Category where user_id=:theId", Category.class);
		query.setParameter("theId", userId);
		
		return query.getResultList();
		
	}

	@Override
	public Category findByName(String name, int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Category> query = currentSession.createQuery("from Category where name=:theName and user_id=:theId", Category.class);
		query.setParameter("theName", name);
		query.setParameter("theId", userId);
		
		Category category = null;
		
		try {
			category = query.getSingleResult();
		}
		
		catch(NoResultException exc){
			
		}
		
		currentSession.clear();
		
		return category;
	}

	@Override
	public Category findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Category> query = currentSession.createQuery("from Category where id=:theId", Category.class);
		
		query.setParameter("theId", id);
		
		Category category = null;
		
		try {
			category = query.getSingleResult();
		}
		
		catch(Exception exc){
			category = null;
		}
		
		return category;
	}

	@Override
	public void save(Category category) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(category);
	}

	@Override
	public List<Category> searchCategories(String categoryName, int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Category> query = null;
		
		if(categoryName != null && categoryName.trim().length() > 0) {
			
			query = currentSession.createQuery("from Category where lower(name) like :theName and user_id=:theId", Category.class);
			query.setParameter("theName","%" + categoryName.toLowerCase() +"%");
			query.setParameter("theId", userId);
		}
		else {
			query = currentSession.createQuery("from Category where user_id=:theId", Category.class);
			query.setParameter("theId", userId);
		}
		
		List<Category> categories = query.getResultList();
		
		if(categories.size() <= 0) {
			return null;
		}
		
		return categories;
	}

	@Override
	public void delete(Category category) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.delete(category);	
	}
}
