package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.Stock;

import java.util.List;

public class ValidateStock {

    private final StockService stockService;

    public ValidateStock(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Checks if there is any existing balance for the given product.
     *
     * This method retrieves the stock list for the specified product and checks if the list is not empty.
     * If the list is not empty, it means there is an existing balance for the product.
     *
     * @param product the product for which to check the existing balance
     * @return true if there is an existing balance for the product, false otherwise
     */
    private boolean existBalance(Product product) {
        List<Stock> stockList = stockService.getStockByProduct(product);
        return !stockList.isEmpty();
    }

    /**
     * Calculates the balance for the given stock entry.
     *
     * This method calculates the balance based on the units in or units out for the given stock entry.
     * If there are units in, it adds them to the existing balance. If there are units out, it subtracts them from the existing balance.
     * The balance is then set in the stock entry and returned.
     *
     * @param stock the stock entry for which to calculate the balance
     * @return the stock entry with the calculated balance
     */
    public Stock calculateBalance(Stock stock) {
        if (stock.getUnitIn() != 0) {
            if (existBalance(stock.getProduct())) {
                List<Stock> stockList = stockService.getStockByProduct(stock.getProduct());
                Integer balance = stockList.get(stockList.size() - 1).getBalance();
                stock.setBalance(balance + stock.getUnitIn());
            } else {
                stock.setBalance(stock.getUnitIn());
            }
        } else {
            List<Stock> stockList = stockService.getStockByProduct(stock.getProduct());
            Integer balance = stockList.get(stockList.size() - 1).getBalance();
            stock.setBalance(balance - stock.getUnitOut());
        }

        return stock;
    }
}
