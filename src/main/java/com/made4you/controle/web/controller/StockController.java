package com.made4you.controle.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.made4you.controle.web.entity.Product;
import com.made4you.controle.web.entity.Stock;
import com.made4you.controle.web.entity.StockMovimentation;
import com.made4you.controle.web.entity.StoragePlace;
import com.made4you.controle.web.entity.User;
import com.made4you.controle.web.service.ProductService;
import com.made4you.controle.web.service.StockService;
import com.made4you.controle.web.service.StoragePlaceService;

@Controller
public class StockController {

	@Autowired
	StockService stockService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	StoragePlaceService storagePlaceService;
	
	@RequestMapping("/stock")
	public String stock(@RequestParam("productId") Long productId, Model model, HttpServletRequest request) {
		
		HttpSession currentSession = request.getSession();		
		User user = (User) currentSession.getAttribute("user");	
		int userId = user.getId();
		
		List<StoragePlace> storagePlaces = storagePlaceService.findAll(userId);		
		model.addAttribute("storagePlaces", storagePlaces);
				
		Product product = productService.findyById(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("stock", new Stock());
		
		return "stock_movimentation";
	}
	
	@RequestMapping("/movimentation")
	public String stockMovimentation(@ModelAttribute("stock") Stock stock, 
										@RequestParam int operation, 
										@RequestParam int quantity, 
										@RequestParam("product.id") Long productId, 
										@RequestParam("storagePlace.id") Long storagePlaceId) {
		
		StockMovimentation stockMovimentation = new StockMovimentation(quantity);
				
		Stock stockAlreadyExist = stockService.findByForeignKeyId(productId, storagePlaceId);
		
		if(stockAlreadyExist != null) {
			stock = stockAlreadyExist;
		}
		
		Product product = productService.findyById(productId);
		
		product.add(stock);
		
		stock.add(stockMovimentation);
					
		if(operation == 1){
			stock.addBalance(stockMovimentation);
		}
		else if(operation == 2) {
			stock.removeBalance(stockMovimentation);
		}
		
		stockService.save(stock);
		
		return "stock";
	}
}
