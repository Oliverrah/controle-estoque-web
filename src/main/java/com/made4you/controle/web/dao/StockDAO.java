package com.made4you.controle.web.dao;

import com.made4you.controle.web.entities.Stock;

public interface StockDAO {

	public Stock findByForeignKeyId(long productId, long storagePlaceId);
	public void addBalance(int quantity);
	public void save(Stock stock);
}
