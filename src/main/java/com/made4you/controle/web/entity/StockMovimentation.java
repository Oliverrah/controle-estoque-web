package com.made4you.controle.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock_movimentation")
public class StockMovimentation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	public StockMovimentation() {
		
	}

	public StockMovimentation(Integer quantity) {
		this.quantity = quantity;
		this.date = new Date();
	}

	public StockMovimentation(Long id, Integer quantity) {
		this.id = id;
		this.quantity = quantity;
		this.date = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
