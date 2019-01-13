package model;

import model.beans.BasketItem;
import model.beans.BasketOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BasketEngineImpl implements BasketEngine {

    private List<BasketItem> itemsInBasket;

    private Map<String, Double> taxRateMap;


    public BasketEngineImpl() {

        this.itemsInBasket = new ArrayList<BasketItem>();
        this.taxRateMap = new HashMap<>();
    }

    @Override
    public BasketItem createBasketItem(String description, Double price, String taxCategory, Boolean imported) {

        Double taxRate = taxRateMap.get(taxCategory);

        BasketItem basketItem = new BasketItem();

        basketItem.setDescription(description);
        basketItem.setPrice(price);
        basketItem.setTaxPercentage(taxRate);

        basketItem.setImported(imported);
        basketItem.setImportTax(calculateImportTax(price, imported));

        basketItem.setSalesTaxAmount(calculateSalesTax(price, taxRate));

        Double totalPrice = price + basketItem.getSalesTaxAmount() + basketItem.getImportTax();

        basketItem.setTotalPrice(totalPrice);

        basketItem.setTaxCategory(taxCategory);

        return basketItem;
    }

    @Override
    public Boolean addItemToBasket(BasketItem basketItem){

        return itemsInBasket.add(basketItem);
    }

    @Override
    public Integer getBasketSize() {

        return itemsInBasket.size();
    }

    public BasketOrder generateBasketOrder() {

        BasketOrder basketOrder = new BasketOrder();

        Double totalPrice = itemsInBasket.stream()
                .mapToDouble(b -> b.getPrice())
                .sum();

        Double totalSalesTaxes = itemsInBasket.stream()
                .mapToDouble(b -> b.getSalesTaxAmount())
                .sum();

        Double totalImportTaxes = itemsInBasket.stream()
//                .filter(b -> b.isImported())
                .mapToDouble(b -> b.getImportTax())
                .sum();

        basketOrder.setTotalPrice(totalPrice);
        basketOrder.setTotalSalesTaxes(totalSalesTaxes);
        basketOrder.setImportTaxes(totalImportTaxes);
        basketOrder.setTotalTaxes(round05(totalSalesTaxes + totalImportTaxes));
        basketOrder.setTotalPriceAndTaxes(totalPrice + round05(totalSalesTaxes + totalImportTaxes));

        basketOrder.setBasketItems(itemsInBasket);

        return basketOrder;
    }

    public List<BasketItem> getItemsInBasket() {
        return itemsInBasket;
    }

    public void setItemsInBasket(List<BasketItem> itemsInBasket) {
        this.itemsInBasket = itemsInBasket;
    }

    void setTaxRateMap(Map<String, Double> taxRateMap) {
        this.taxRateMap = taxRateMap;
    }

    public Map<String, Double> getTaxRateMap() {
        return taxRateMap;
    }

    private Double calculateSalesTax(Double price, Double taxRate) {

        return round05(taxRate == 0.0 ? taxRate : price * (taxRate / 100));
    }

    private Double calculateImportTax(Double price, boolean imported) {

        return imported ? round05(price * 0.05) : 0.0;
    }

    private  Double round05(double num) {
        
        return Math.round(num * 20) / 20.0;
    }
}