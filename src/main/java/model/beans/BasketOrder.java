package model.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class BasketOrder implements Serializable {

    private List<BasketItem> basketItems;
    private Double totalSalesTaxes;
    private Double importTaxes;
    private Double totalTaxes;
    private Double totalPrice;
    private Double totalPriceAndTaxes;


    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public Double getTotalSalesTaxes() {
        return totalSalesTaxes;
    }

    public void setTotalSalesTaxes(Double totalSalesTaxes) {
        this.totalSalesTaxes = totalSalesTaxes;
    }

    public Double getImportTaxes() {
        return importTaxes;
    }

    public void setImportTaxes(Double importTaxes) {
        this.importTaxes = importTaxes;
    }

    public Double getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(Double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPriceAndTaxes() {
        return totalPriceAndTaxes;
    }

    public void setTotalPriceAndTaxes(Double totalPriceAndTaxes) {
        this.totalPriceAndTaxes = totalPriceAndTaxes;
    }

    @Override
    public String toString() {

        final DecimalFormat df2 = new DecimalFormat("0.00");

        StringBuilder builder = new StringBuilder();

        for (BasketItem basketItem : basketItems) {
            builder.append(basketItem.toString() + "\n");
        }

        builder.append("Sales Taxes: " + df2.format(totalTaxes) + "\n");
        builder.append("Total: " + df2.format(totalPriceAndTaxes) + "\n");


        return builder.toString();
    }
}
