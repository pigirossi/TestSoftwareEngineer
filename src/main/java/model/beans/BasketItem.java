package model.beans;

import java.io.Serializable;
import java.text.DecimalFormat;

public class BasketItem implements Serializable {

    private String description;

    private Double price;

    private Double taxPercentage;

    private Double salesTaxAmount;

    private Double totalPrice;

    private String taxCategory;

    private Double importTax;

    private boolean imported;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(Double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public Double getImportTax() {
        return importTax;
    }

    public void setImportTax(Double importTax) {
        this.importTax = importTax;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @Override
    public String toString() {

        DecimalFormat df2 = new DecimalFormat("0.00");

        StringBuilder builder = new StringBuilder();

        builder.append("1 " +  description + ": " );
        builder.append(df2.format(totalPrice ));

        return builder.toString();
    }
}
