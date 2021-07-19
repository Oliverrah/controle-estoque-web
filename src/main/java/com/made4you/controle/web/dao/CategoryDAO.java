package com.made4you.controle.web.dao;

import java.util.List;

import com.made4you.controle.web.entity.Category;

public interface CategoryDAO {

	public List<Category> findAll(int userId);
	public List<Category> searchCategories(String categoryName, int userId);
	public Category findByName(String name, int userId);
	public Category findById(Long id);
	public void save(Category category);
	public void delete(Category category);
}
