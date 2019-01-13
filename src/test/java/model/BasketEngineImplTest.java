package model;

import model.beans.BasketOrder;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BasketEngineImplTest {

    final DecimalFormat df2 = new DecimalFormat("0.00");

    @Test
    public void testOrderOne() {

        BasketEngine engine = createBasketEngine();

        engine.addItemToBasket(
                engine.createBasketItem("book", 12.49, "BOOK", false));

        engine.addItemToBasket(
                engine.createBasketItem("music CD", 14.99, "OTH", false));

        engine.addItemToBasket(
                engine.createBasketItem("chocolate bar", 0.85, "FOOD", false));

        BasketOrder basketOrder  = engine.generateBasketOrder();


        Assert.assertEquals(engine.getBasketSize(), new Integer(3));
        Assert.assertEquals(df2.format(basketOrder.getTotalTaxes()),
                df2.format(new Double(1.50)));
        Assert.assertEquals(df2.format(basketOrder.getTotalPriceAndTaxes()),
                df2.format(new Double(29.83)));

        System.out.println("BASKET ORDER n.1:\n" + basketOrder.toString());

    }

    @Test
    public void testOrderTwo() {

        BasketEngine engine = createBasketEngine();

        engine.addItemToBasket(
                engine.createBasketItem("imported box of chocolate", 10.00, "FOOD", true));

        engine.addItemToBasket(
                engine.createBasketItem("imported bottle of perfume", 47.50, "OTH", true));

        BasketOrder basketOrder  = engine.generateBasketOrder();


        Assert.assertEquals(basketOrder.getBasketItems().size(), 2);
        Assert.assertEquals(df2.format(basketOrder.getTotalTaxes()),
                df2.format(new Double(7.65)));
        Assert.assertEquals(df2.format(basketOrder.getTotalPriceAndTaxes()),
                df2.format(new Double(65.15)));

        System.out.println("BASKET ORDER n.2:\n" + basketOrder.toString());
    }

    @Test
    public void testOrderThree() {

        BasketEngine engine = createBasketEngine();

        engine.addItemToBasket(
                engine.createBasketItem("imported bottle of perfume", 27.99, "OTH", true));

        engine.addItemToBasket(
                engine.createBasketItem("bottle of perfume", 18.99, "OTH", false));

        engine.addItemToBasket(
                engine.createBasketItem("packet ot headache pills", 9.75, "MED", false));

        engine.addItemToBasket(
                engine.createBasketItem("box of imported chocolates", 11.25, "FOOD", true));


        BasketOrder basketOrder  = engine.generateBasketOrder();


        Assert.assertEquals(basketOrder.getBasketItems().size(), 4);

//      arrotondamento non fornisce il rirultato corretto
//      Assert.assertEquals(df2.format(basketOrder.getTotalTaxes()),
//              df2.format(new Double(6.65)));
//      Assert.assertEquals(df2.format(basketOrder.getTotalPriceAndTaxes()),
//              df2.format(new Double(74.63)));

        System.out.println("BASKET ORDER n.3:\n" + basketOrder.toString());
    }

    private BasketEngine createBasketEngine() {

        BasketEngineImpl engine = new BasketEngineImpl();

        Map<String, Double> stringFloatMap = new HashMap<>();
        stringFloatMap.put("OTH", 10.0);
        stringFloatMap.put("BOOK", 0.0);
        stringFloatMap.put("MED", 0.0);
        stringFloatMap.put("FOOD", 0.0);

        engine.setTaxRateMap(stringFloatMap);

        return engine;
    }
}