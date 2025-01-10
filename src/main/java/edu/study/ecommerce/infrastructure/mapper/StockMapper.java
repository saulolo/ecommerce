package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.Stock;
import edu.study.ecommerce.infrastructure.entity.StockEntity;

import java.util.List;
import java.util.stream.Collectors;

public class StockMapper {

    private final ProductMapper productMapper;

    public StockMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * Convert StockEntity to Stock
     *
     * @param stockEntity
     * @return
     */
    public Stock toStock(StockEntity stockEntity) {
        if (stockEntity == null) {
            return null;
        }

        return Stock.builder()
                .id(stockEntity.getId())
                .dateCreated(stockEntity.getDateCreated())
                .unitIn(stockEntity.getUnitIn())
                .unitOut(stockEntity.getUnitOut())
                .description(stockEntity.getDescription())
                .balance(stockEntity.getBalance())
                .product(stockEntity.getProductEntity() != null
                        ? productMapper.toProduct(stockEntity.getProductEntity())
                        : null)
                .build();
    }

    /**
     * Convert List of StockEntity to List of Stock
     *
     * @param stockEntities
     * @return
     */
    public List<Stock> toStocks(List<StockEntity> stockEntities) {
        if (stockEntities == null || stockEntities.isEmpty()) {
            return List.of();
        }

        return stockEntities.stream()
                .map(this::toStock)
                .collect(Collectors.toList());
    }

    /**
     * Convert Stock to StockEntity
     *
     * @param stock
     * @return
     */
    public StockEntity toStockEntity(Stock stock) {
        if (stock == null) {
            return null;
        }

        return StockEntity.builder()
                .id(stock.getId())
                .dateCreated(stock.getDateCreated())
                .unitIn(stock.getUnitIn())
                .unitOut(stock.getUnitOut())
                .description(stock.getDescription())
                .balance(stock.getBalance())
                .productEntity(stock.getProduct() != null
                        ? productMapper.toProductEntity(stock.getProduct())
                        : null)
                .build();
    }
}
