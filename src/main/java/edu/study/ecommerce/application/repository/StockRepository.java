package edu.study.ecommerce.application.repository;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.Stock;

import java.util.List;

public interface StockRepository {


    /**
     * Save stock
     *
     * @param stock
     * @return
     */
    Stock saveStock(Stock stock);

    /**
     * Get stock by product
     *
     * @param product
     * @return
     */
    List<Stock> getStockByProduct(Product product);

}