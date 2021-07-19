package com.made4you.controle.web.dao;

import java.util.List;

import com.made4you.controle.web.entity.StoragePlace;

public interface StoragePlaceDAO {
	
	public List<StoragePlace> findAll(int userId);
	public List<StoragePlace> searchStoragePlaces(String storagaPlaceName, int userId);
	public StoragePlace findByName(String locationName, int userId);
	public StoragePlace findyById(Long storagePlaceId);
	public void save(StoragePlace storagePlace);
	public void delete(StoragePlace storagePlace);	
}
