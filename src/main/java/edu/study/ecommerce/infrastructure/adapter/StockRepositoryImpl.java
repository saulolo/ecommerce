package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.StockRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.Stock;
import edu.study.ecommerce.infrastructure.mapper.ProductMapper;
import edu.study.ecommerce.infrastructure.mapper.StockMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepositoryImpl implements StockRepository {

    private final StockCrudRepository stockCrudRepository;
    private final StockMapper stockMapper;
    private final ProductMapper productMapper;

    public StockRepositoryImpl(StockCrudRepository stockCrudRepository, StockMapper stockMapper, ProductMapper productMapper) {
        this.stockCrudRepository = stockCrudRepository;
        this.stockMapper = stockMapper;
        this.productMapper = productMapper;
    }

    /**
     * Save stock
     * @param stock
     * @return Stock
     */
    @Override
    public Stock saveStock(Stock stock) {
        return stockMapper.toStock(stockCrudRepository.save(stockMapper.toStockEntity(stock)));
    }

    /**
     * Get stock by product
     * @param product
     * @return List<Stock>
     */
    @Override
    public List<Stock> getStockByProduct(Product product) {
        return stockMapper.toStocks(stockCrudRepository.findByProductEntity(productMapper.toProductEntity(product)));
    }
}
