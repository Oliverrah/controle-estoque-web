package com.made4you.controle.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.made4you.controle.web.entity.StoragePlace;

@Repository
public class StoragePlaceDAOHibernateImplementation implements StoragePlaceDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public void save(StoragePlace storagePlace) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(storagePlace);
	}

	@Override
	public List<StoragePlace> findAll(int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<StoragePlace> query = currentSession.createQuery("from StoragePlace where user_id=:theId", StoragePlace.class);
		query.setParameter("theId", userId);
		
		return query.getResultList();		
	}

	@Override
	public List<StoragePlace> searchStoragePlaces(String storagaPlaceName, int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<StoragePlace> query = null;
		
		if(storagaPlaceName != null && storagaPlaceName.trim().length() > 0) {
			
			query = currentSession.createQuery("from StoragePlace where lower(location_name) like :theName and user_id=:theId", StoragePlace.class);
			query.setParameter("theName","%" + storagaPlaceName.toLowerCase() +"%");
			query.setParameter("theId", userId);
		}
		else {
			query = currentSession.createQuery("from StoragePlace where user_id=:theId", StoragePlace.class);
			query.setParameter("theId", userId);
		}
		
		List<StoragePlace> storagePlaces = query.getResultList();
		
		if(storagePlaces.size() <= 0) {
			return null;
		}
		
		return storagePlaces;
	}

	@Override
	public StoragePlace findByName(String locationName, int userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<StoragePlace> query = currentSession.createQuery("from StoragePlace where location_name =:theName and user_id =:theId", StoragePlace.class);
		query.setParameter("theName", locationName);
		query.setParameter("theId", userId);
		
		StoragePlace storagePlace = null;
		
		try {
			storagePlace = query.getSingleResult();
		}
		
		catch(NoResultException exc) {
			
		}
		
		//cleaning the session for later update in storagePlace controller addStoragePlace() method.
		currentSession.clear();
		
		return storagePlace;
	}

	@Override
	public StoragePlace findyById(Long storagePlaceId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<StoragePlace> query = currentSession.createQuery("from StoragePlace where id=:theId", StoragePlace.class);
		query.setParameter("theId", storagePlaceId);
		
		return query.getSingleResult();
	}

	@Override
	public void delete(StoragePlace storagePlace) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.delete(storagePlace);		
	}
}
