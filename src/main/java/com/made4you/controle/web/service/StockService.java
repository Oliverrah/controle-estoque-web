package com.made4you.controle.web.service;

import com.made4you.controle.web.entities.Stock;

public interface StockService {

	public void addBalance(int quantity);
	public Stock findByForeignKeyId(long productId, long storagePlaceId);
	public void save(Stock stock);
}
