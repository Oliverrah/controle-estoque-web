package com.made4you.controle.web.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entity.User;

@Repository
public class UserDAOHibernateImplementation implements UserDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User findByUserName(String username) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//retrieve from database
		Query<User> query = currentSession.createQuery("from User where username=:uName", User.class);
		
		query.setParameter("uName", username);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		}
		catch(Exception exc) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(user);		
	}



	@Override
	public User findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> query = currentSession.createQuery("from User where id=:theId", User.class);
		
		query.setParameter("theId", id);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		}
		
		catch(Exception exp) {
			user = null;
		}
		return user;
	}	
}
