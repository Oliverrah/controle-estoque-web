package com.made4you.controle.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entities.Provider;

@Repository
public class ProviderDAOHibernateImplementation implements ProviderDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Provider> findAll(int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Provider> query = currentSession.createQuery("from Provider where user_id=:theId", Provider.class);
		query.setParameter("theId", userId);
		
		return query.getResultList();		
	}

	@Override
	public Provider findByName(String name) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Provider provider = currentSession.get(Provider.class, name);
		
		return provider;
	}

	@Override
	public Provider findById(Long id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Provider> query = currentSession.createQuery("from Provider where id=:theId", Provider.class);
		
		query.setParameter("theId", id);
		
		Provider provider = null;
		
		try {
			provider = query.getSingleResult();
		}
		
		catch(Exception exp) {
			provider = null;
		}
		return provider;
	}

	@Override
	public void save(Provider provider) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(provider);					
	}

	@Override
	public List<Provider> searchProviders(String providerName, int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Provider> query = null;
		
		if(providerName != null && providerName.trim().length() > 0) {
			
			query = currentSession.createQuery("from Provider where lower(name) like :theName and user_id=:theId", Provider.class);
			query.setParameter("theName","%" + providerName.toLowerCase() +"%");
			query.setParameter("theId", userId);
		}
		else {
			query = currentSession.createQuery("from Provider where user_id=:theId", Provider.class);
			query.setParameter("theId", userId);
		}
		
		List<Provider> providers = query.getResultList();
		
		if(providers.size() <= 0) {
			return null;
		}
		
		return providers;
	}

	@Override
	public Provider findByCnpj(String cnpj, int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Provider> query = currentSession.createQuery("from Provider where cnpj=:theCnpj and user_id =:theId", Provider.class);
		query.setParameter("theCnpj", cnpj);
		query.setParameter("theId", userId);
		
		Provider provider = null;
		
		try {
			provider = query.getSingleResult();
		}
		
		catch(NoResultException exc) {
			
		}
		
		currentSession.clear();
		
		return provider;
	}

	@Override
	public void delete(Provider provider) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.delete(provider);	
	}
}
