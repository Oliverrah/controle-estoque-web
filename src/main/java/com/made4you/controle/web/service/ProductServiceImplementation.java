package com.made4you.controle.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.ProductDAO;
import com.made4you.controle.web.entity.Category;
import com.made4you.controle.web.entity.Product;
import com.made4you.controle.web.entity.Provider;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProviderService providerService;
	

	@Override
	@Transactional
	public void save(Product product) {
		
		productDAO.save(product);	
	}
	
	@Override
	@Transactional
	public List<Category> findAllCategories(int userId){
		
		return categoryService.findAll(userId);
	}

	@Override
	@Transactional
	public List<Provider> findAllProviders(int userId) {

		return providerService.findAll(userId);
	}

	@Override
	@Transactional
	public List<Product> findAll(int userId) {
		
		return productDAO.findAll(userId);
	}

	@Override
	@Transactional
	public List<Product> searchProducts(String productName, int userId) {
		return productDAO.searchProducts(productName, userId);
	}

	@Override
	@Transactional
	public Product findByName(String productName, int userId) {
		return productDAO.findByName(productName, userId);
	}

	@Override
	public Product findyById(Long productId) {
		return productDAO.findyById(productId);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		productDAO.delete(product);
	}	
}
