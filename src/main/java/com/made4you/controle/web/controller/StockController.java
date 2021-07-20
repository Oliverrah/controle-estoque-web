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

import com.made4you.controle.web.entities.Product;
import com.made4you.controle.web.entities.Stock;
import com.made4you.controle.web.entities.StockMovimentation;
import com.made4you.controle.web.entities.StoragePlace;
import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.entities.enums.StockOperation;
import com.made4you.controle.web.exceptions.InsufficienteBalanceException;
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
										@RequestParam StockOperation StockOperation, 
										@RequestParam int quantity, 
										@RequestParam("product.id") Long productId, 
										@RequestParam("storagePlace.id") Long storagePlaceId,
										Model model) {
		
				
		Stock stockAlreadyExist = stockService.findByForeignKeyId(productId, storagePlaceId);
		
		if(stockAlreadyExist != null) {
			stock = stockAlreadyExist;
		}
		
		StockMovimentation stockMovimentation = new StockMovimentation(quantity, StockOperation);

		switch(StockOperation) {
		
			case PLACEMENT:						
				stock.addBalance(stockMovimentation);				
				break;
				
			case REMOVAL:				
				try {
					stock.removeBalance(stockMovimentation);					
				}
				catch(InsufficienteBalanceException exc) {
															
					List<StoragePlace> storagePlaces = storagePlaceService.findAll(10);		
					model.addAttribute("storagePlaces", storagePlaces);
					
					Product product = productService.findyById(productId);
					model.addAttribute("product", product);
					
					model.addAttribute("stock", stock);
					
					model.addAttribute("quantity", quantity);
					
					boolean selectRaddioButton = true;
					model.addAttribute("selectRaddioButton", selectRaddioButton);
					
					model.addAttribute("exception", exc);
					
					return "stock_movimentation";						
				}
				break;
		}
		
		stockService.save(stock);
							
		return "stock";
	}
}
