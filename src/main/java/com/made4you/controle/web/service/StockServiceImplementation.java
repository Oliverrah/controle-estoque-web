package com.made4you.controle.web.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.StockDAO;
import com.made4you.controle.web.entity.Stock;

@Service
public class StockServiceImplementation implements StockService {

	@Autowired
	StockDAO stockDAO;
	
	@Override
	public void addBalance(int quantity) {
		

	}

	@Override
	@Transactional
	public void save(Stock stock) {
		stockDAO.save(stock);

	}

	@Override
	public Stock findByForeignKeyId(long productId, long storagePlaceId) {
		return stockDAO.findByForeignKeyId(productId, storagePlaceId);
	}
}
