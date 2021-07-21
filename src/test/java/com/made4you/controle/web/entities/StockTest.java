package com.made4you.controle.web.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.made4you.controle.web.entities.enums.StockOperation;
import com.made4you.controle.web.exceptions.InsufficienteBalanceException;

class StockTest {
			
	@Test
	void should_ReturnTrue_When_Balance_Minus_Quantity_GreaterThanZero() {
		
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		int quantity = -50;
		
		//when
		boolean enoughtBalance = stock.hasEnoughtBalance(quantity);
		
		//then
		assertTrue(enoughtBalance);
	}
	
	@Test
	void should_ReturnFalse_When_Balance_Minus_Quantity_LessThanZero() {
		
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		int quantity = 200;
		
		//when
		boolean enoughtBalance = stock.hasEnoughtBalance(quantity);
		
		//then
		assertFalse(enoughtBalance);
	}
	
	@Test
	void should_AddBalance_When_StockMovimentation_Quantity_GreaterThanZero_And_StockOperation_Equals_Placement() {
				
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		Integer quantity = 25;
							
		//when		
		stock.addBalance(new StockMovimentation(quantity, StockOperation.PLACEMENT));
		
		//then
		assertEquals(125, stock.getBalance());
	}
	
	@Test
	void should_Not_AddBalance_When_StockMovimentation_Quantity_LessThanZero_And_StockOperation_Equals_Placement() {
		
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		Integer quantity = -25;
				
		//when
		stock.addBalance(new StockMovimentation(quantity, StockOperation.PLACEMENT));
			
		//then
		assertEquals(100, stock.getBalance());
	}
	
	
	
	@Test
	void should_RemoveBalance_When_StockMovimentation_Quantity_GreaterThanZero_And_HasEnoughtBalance_And_StockOperation_Equals_Removal() {
		
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		Integer quantity = 50;
		
		//when		
		stock.removeBalance(new StockMovimentation(quantity, StockOperation.REMOVAL));
		
		//then
		assertEquals(50, stock.getBalance());
		
	}
	
	@Test
	void should_ThrowInsufficienteBalanceException_When_HasNotEnoughtBalance() {
		
		//given
		Stock stock = new Stock();
		stock.setBalance(100);
		Integer quantity = 500;
		
		//when		
		Executable executable = () -> stock.removeBalance(new StockMovimentation(quantity, StockOperation.REMOVAL));
		
		//then
		assertThrows(InsufficienteBalanceException.class, executable);
	}
}
