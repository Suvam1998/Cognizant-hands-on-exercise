package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public List<Stock> getStocksBetween(String code, Date start, Date end) {
        return stockRepository.findByCodeAndDateBetween(code, start, end);
    }

    @Transactional
    public List<Stock> getStocksWithCloseGreaterThan(String code, double price) {
        return stockRepository.findByCodeAndCloseGreaterThan(code, price);
    }

    @Transactional
    public List<Stock> getTop3ByVolume() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    @Transactional
    public List<Stock> getLowest3ByClose(String code) {
        return stockRepository.findTop3ByCodeOrderByCloseAsc(code);
    }
}
