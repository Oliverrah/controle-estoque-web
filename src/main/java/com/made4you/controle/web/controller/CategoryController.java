package com.made4you.controle.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.made4you.controle.web.entities.Category;
import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.service.CategoryService;
import com.made4you.controle.web.service.UserService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showCategoryForm")
	public String showCategoryForm(Model model) {
		
		model.addAttribute("category", new Category());
				
		
		return "category-registration";
	}
	
	
	@RequestMapping("/showCategoryFormForUpdate")
	public String showCategoryFormForUpdate(@RequestParam("categoryId") Long id, Model model) {
		
		Category category = categoryService.findById(id);
		
				
		model.addAttribute("category", category);
				
		
		return "category-registration";
	}
	
	@RequestMapping("/addCategory")
	public String addProduct(@Valid @ModelAttribute Category category, BindingResult bindingResult, @RequestParam("userId") int userId, Model model) {
		
		User user = userService.findById(userId);
		user.add(category);
				
		Category categoryExists = categoryService.findByName(category.getName(), userId);
		
		if(bindingResult.hasErrors()) {
			return "category-registration";
		}
		
		if(categoryExists == null) {
			categoryService.save(category);
			
			return "redirect:/categories";
		}
		else if(categoryExists.getId() != category.getId()) {
			model.addAttribute("registrationError", "Já existe categoria com esse nome");
			
			return "category-registration";
		}
		else {
			categoryService.save(category);		
			
			return "redirect:/categories";
		}
	
	}
	
	@RequestMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("categoryId") Long categoryId) {
		
		Category category = categoryService.findById(categoryId);
		
		categoryService.delete(category);
		
		return "redirect:/categories";
	}
	
	@RequestMapping("/categories")
	public String showCategories(Model model, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");	
		int userId = user.getId();
					
		List<Category> categories = categoryService.findAll(userId);
		model.addAttribute("categories", categories);
		
		return "categories";
	}
	
	@RequestMapping("/searchCategory")
	public String searchCategory(Model model, @RequestParam String categoryName, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		int userId = user.getId();		
		
		List<Category> categories = categoryService.searchCategories(categoryName, userId);

		model.addAttribute("categories", categories);
		model.addAttribute("categoryName", categoryName);
		
		
		return "categories";
	}
}
