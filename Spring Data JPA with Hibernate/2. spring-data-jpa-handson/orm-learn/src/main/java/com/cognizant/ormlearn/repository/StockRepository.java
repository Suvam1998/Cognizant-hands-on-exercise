package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Hands-on 2a: all rows for a code between two dates (e.g. FB in Sep 2019).
    List<Stock> findByCodeAndDateBetween(String code, Date start, Date end);

    // Hands-on 2b: rows for a code whose closing price is greater than a value.
    List<Stock> findByCodeAndCloseGreaterThan(String code, double price);

    // Hands-on 2c: top 3 dates by highest volume.
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Hands-on 2d: 3 lowest closing prices for a code (e.g. NFLX lows).
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
