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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.made4you.controle.web.entity.Category;
import com.made4you.controle.web.entity.Product;
import com.made4you.controle.web.entity.Provider;
import com.made4you.controle.web.entity.User;
import com.made4you.controle.web.service.CategoryService;
import com.made4you.controle.web.service.ProductService;
import com.made4you.controle.web.service.ProviderService;
import com.made4you.controle.web.service.UserService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProviderService providerService;
		
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showProductForm")
	public String showProductForm(Model model, HttpServletRequest request) {
		
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");	
		int userId = user.getId();
				
		model.addAttribute("product", new Product());
		
		List<Category> categories = productService.findAllCategories(userId);
		model.addAttribute("categories", categories);
		
		List<Provider> providers = productService.findAllProviders(userId);
		model.addAttribute("providers", providers);
				
		return "product-registration";
	}
	
	@RequestMapping("/showProductFormForUpdate")
	public String showProductFormForUpdate(@RequestParam("productId") Long productId, Model model, HttpServletRequest request) {
		
		HttpSession currentSession = request.getSession();		
		User user = (User) currentSession.getAttribute("user");	
		int userId = user.getId();
			
		Product product = productService.findyById(productId);
				
		Long categoryId = product.getCategory().getId();
		Long providerId = product.getProvider().getId();
		
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("providerId", providerId);
		
		List<Category> categories = productService.findAllCategories(userId);
		model.addAttribute("categories", categories);
		
		List<Provider> providers = productService.findAllProviders(userId);
		model.addAttribute("providers", providers);
				
		model.addAttribute("product", product);
				
		return "product-registration";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@Valid @ModelAttribute("product") Product product,
								BindingResult bindingResult,
								@RequestParam("category.id") Long categoryId,
								@RequestParam("provider.id") Long providerId,
								@RequestParam("userId") int userId, 
								Model model) {
					
		Category category = categoryService.findById(categoryId);
		Provider provider = providerService.findById(providerId); 
		User user = userService.findById(userId);
				
		category.add(product);
		provider.add(product);
		user.add(product);
						
		Product productExists = productService.findByName(product.getName(), userId);
				
		List<Category> categories = productService.findAllCategories(userId);
		model.addAttribute("categories", categories);
		
		List<Provider> providers = productService.findAllProviders(userId);
		model.addAttribute("providers", providers);
				
		if(bindingResult.hasErrors()) {
			return "product-registration";
		}
		
		if(productExists == null) {
			
			productService.save(product);
			
			return "redirect:/products";	
		}
		
		else if(productExists.getId() != product.getId()){
				model.addAttribute("registrationError", "Já existe produto com esse nome");
				return "product-registration";
			}
		
		else {
			productService.save(product);
			return "redirect:/products";	
		}	
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("productId") Long productId) {
		
		Product product = productService.findyById(productId);
		
		productService.delete(product);
		
		return "redirect:/products";
	}
	
	@RequestMapping("/products")
	public String showProducts(Model model, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		int userId = user.getId();
		
		List<Product> products = productService.findAll(userId);
		model.addAttribute("products", products);
		
		return "products";
	}
	
	@RequestMapping("/searchProduct")
	public String seachProduct(Model model, @RequestParam String productName, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		
		int userId = user.getId();
		
		List<Product> products = productService.searchProducts(productName, userId);
		
		model.addAttribute("products", products);
		model.addAttribute("productName", productName);
			
		return "products";
	}				
}
