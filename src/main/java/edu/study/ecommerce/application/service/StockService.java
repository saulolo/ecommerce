package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.StockRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.Stock;
import edu.study.ecommerce.infrastructure.mapper.StockMapper;

import java.util.List;


public class StockService implements StockRepository{

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    /**
     * Save stock
     *
     * @param stock
     * @return
     */
    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.saveStock(stock);
    }

    /**
     * Get stock by product
     *
     * @param product
     * @return
     */
    @Override
    public List<Stock> getStockByProduct(Product product) {
        return stockRepository.getStockByProduct(product);
    }

}
