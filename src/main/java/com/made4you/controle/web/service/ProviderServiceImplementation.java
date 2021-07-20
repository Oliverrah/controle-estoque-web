package com.made4you.controle.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.ProviderDAO;
import com.made4you.controle.web.entities.Provider;

@Service
public class ProviderServiceImplementation implements ProviderService {

	@Autowired
	ProviderDAO providerDAO;
	
	@Override
	@Transactional
	public List<Provider> findAll(int userId) {
		
		return providerDAO.findAll(userId);		
	}

	@Override
	public Provider findByName(String name) {
		
		return providerDAO.findByName(name);
	}

	@Override
	@Transactional
	public Provider findById(Long id) {
		
		return providerDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Provider provider) {
		providerDAO.save(provider);		
	}

	@Override
	@Transactional
	public List<Provider> searchProviders(String providerName, int userId) {
		return providerDAO.searchProviders(providerName, userId);		
	}

	@Override
	@Transactional
	public Provider findByCnpj(String cnpj, int userId) {
		return providerDAO.findByCnpj(cnpj, userId);		
	}

	@Override
	@Transactional
	public void delete(Provider provider) {
		providerDAO.delete(provider);
	}
}
