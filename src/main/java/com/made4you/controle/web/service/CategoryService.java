package com.made4you.controle.web.service;

import java.util.List;

import com.made4you.controle.web.entities.Category;

public interface CategoryService {

	public List<Category> findAll(int userId);
	public List<Category> searchCategories(String categoryName, int userId);
	public Category findByName(String name, int userId);
	public Category findById(Long id);
	public void save(Category category);
	public void delete(Category category);
}
