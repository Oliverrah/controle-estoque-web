package com.made4you.controle.web.service;

import java.util.List;

import com.made4you.controle.web.entities.Provider;


public interface ProviderService {

	public List<Provider> findAll(int userId);
	public List<Provider> searchProviders(String providerName, int userId);
	public Provider findByName(String name);
	public Provider findByCnpj(String cnpj, int userId);
	public Provider findById(Long id);
	public void save(Provider provider);
	public void delete(Provider provider);
}
