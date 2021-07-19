package com.made4you.controle.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.CategoryDAO;
import com.made4you.controle.web.entity.Category;

@Service
public class CategoryServiceImplementation implements CategoryService{

	@Autowired
	CategoryDAO categoryDAO;
		
	@Override
	@Transactional
	public List<Category> findAll(int userId) {
		
		return categoryDAO.findAll(userId);			
	}

	@Override
	@Transactional
	public Category findByName(String name, int userId) {
		
		return categoryDAO.findByName(name, userId);
	}

	@Override
	@Transactional
	public Category findById(Long id) {
		return categoryDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Category category) {
		
		categoryDAO.save(category);		
	}

	@Override
	@Transactional
	public List<Category> searchCategories(String categoryName, int userId) {
		return categoryDAO.searchCategories(categoryName, userId);
	}

	@Override
	@Transactional
	public void delete(Category category) {
		categoryDAO.delete(category);	
	}
}
