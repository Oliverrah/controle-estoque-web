package com.made4you.controle.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.StoragePlaceDAO;
import com.made4you.controle.web.entities.StoragePlace;

@Service
public class StoragePlaceServiceImplementation implements StoragePlaceService {
	
	@Autowired
	StoragePlaceDAO storagePlaceDAO;

	@Override
	@Transactional
	public void save(StoragePlace storagePlace) {
		storagePlaceDAO.save(storagePlace);
	}

	@Override
	@Transactional
	public List<StoragePlace> findAll(int userId) {
		return storagePlaceDAO.findAll(userId);
	}

	@Override
	@Transactional
	public List<StoragePlace> searchStoragePlaces(String storagaPlaceName, int userId) {
		return storagePlaceDAO.searchStoragePlaces(storagaPlaceName, userId);
	}

	@Override
	@Transactional
	public StoragePlace findByName(String locationName, int userId) {
		return storagePlaceDAO.findByName(locationName, userId);
	}

	@Override
	@Transactional
	public StoragePlace findyById(Long storagePlaceId) {
		return storagePlaceDAO.findyById(storagePlaceId);
	}

	@Override
	@Transactional
	public void delete(StoragePlace storagePlace) {
		storagePlaceDAO.delete(storagePlace);
	}

}
