package com.made4you.controle.web.service;

import java.util.List;

import com.made4you.controle.web.entity.Category;
import com.made4you.controle.web.entity.Product;
import com.made4you.controle.web.entity.Provider;

public interface ProductService {
	
	public List<Product> findAll(int userId);
	public List<Product> searchProducts(String productName, int userId);
	public Product findByName(String productName, int userId);
	public Product findyById(Long productId);
	public void save(Product product);
	public void delete(Product product);
	
	
	public List<Category> findAllCategories(int userId);
	public List<Provider> findAllProviders(int userId);
}
