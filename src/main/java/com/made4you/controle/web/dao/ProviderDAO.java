package com.made4you.controle.web.dao;

import java.util.List;

import com.made4you.controle.web.entity.Provider;

public interface ProviderDAO {

	public List<Provider> findAll(int userId);
	public List<Provider> searchProviders(String providerName, int userId);
	public Provider findByName(String name);
	public Provider findByCnpj(String cnpj, int userId);
	public Provider findById(Long id);
	public void save(Provider provider);
	public void delete(Provider provider);
}
