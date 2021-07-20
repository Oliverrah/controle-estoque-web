package com.made4you.controle.web.dao;

import java.util.List;

import com.made4you.controle.web.entities.Product;

public interface ProductDAO {
	
	public List<Product> findAll(int userId);
	public List<Product> searchProducts(String productName, int userId);
	public Product findByName(String productName, int userId);
	public Product findyById(Long productId);
	public void save(Product product);
	public void delete(Product product);
}
