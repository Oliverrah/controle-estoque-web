package com.made4you.controle.web.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.made4you.controle.web.entities.enums.StockOperation;
import com.made4you.controle.web.exceptions.InsufficienteBalanceException;

@Entity
@Table(name="stock")
public class Stock{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="balance")
	private int balance;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="storage_place_id")
	private StoragePlace storagePlace;
	
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
	private List<StockMovimentation> stockMovimentations;
	
	public Stock() {
		
	}

	public Stock(int id, int balance, Product product, StoragePlace storagePlace) {
		this.id = id;
		this.balance = balance;
		this.product = product;
		this.storagePlace = storagePlace;
	}

	public Stock(int balance, Product product, StoragePlace storagePlace) {
		this.balance = balance;
		this.product = product;
		this.storagePlace = storagePlace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public StoragePlace getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(StoragePlace storagePlace) {
		this.storagePlace = storagePlace;
	}
	
	public List<StockMovimentation> getStockMovimentations() {
		return stockMovimentations;
	}

	public void add(StockMovimentation stockMovimentation) {
		if(stockMovimentations == null) {
			stockMovimentations = new ArrayList<>();
		}
		
		stockMovimentations.add(stockMovimentation);
		
		stockMovimentation.setStock(this);
	}

	public void addBalance(StockMovimentation stockMovimentation) {		
				
		if(stockMovimentation.getQuantity() > 0 && stockMovimentation.getOperation() == StockOperation.PLACEMENT) {
			this.balance += stockMovimentation.getQuantity();
			this.add(stockMovimentation);
		}
	}
	
	public void removeBalance(StockMovimentation stockMovimentation) {
		
		int quantity = stockMovimentation.getQuantity();
		
		if(stockMovimentation.getQuantity() > 0 && hasEnoughtBalance(quantity) && stockMovimentation.getOperation() == StockOperation.REMOVAL) {			
				this.balance -= stockMovimentation.getQuantity();
				this.add(stockMovimentation);
		}
		
		else {
			throw new InsufficienteBalanceException("N??o h?? saldo suficiente dispon??vel para esse produto");
		}
	}
	
	public boolean hasEnoughtBalance(int quantity) {
		if(this.balance - quantity >= 0) {
			return true;
		}		
		return false;
	}
	
	public void transferProducts(Product product, StoragePlace target) {
		if(this.balance > 0) {
			this.product = product;
			this.storagePlace = target;
		}
	}
		
	@Override
	public String toString() {
		return "Stock [id=" + id + ", balance=" + balance + ", product=" + product + ", storagePlace=" + storagePlace
				+ "]";
	}	
}
