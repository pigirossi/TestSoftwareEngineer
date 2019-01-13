package model;

import model.beans.BasketItem;
import model.beans.BasketOrder;

import java.util.List;

public interface BasketEngine {

    public BasketItem createBasketItem(String description, Double price, String taxCategory, Boolean imported);

    public Boolean addItemToBasket(BasketItem basketItem);

    public Integer getBasketSize();

    public BasketOrder generateBasketOrder();

    public List<BasketItem> getItemsInBasket();

}
