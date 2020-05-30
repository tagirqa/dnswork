package ru.site;

import org.junit.Assert;
import ru.site.pages.BasketPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualBasket {

    public static List<Product> basket = new ArrayList<>();

    public  void allBasket() {
        basket.forEach(x -> System.out.println("Наименование: " +x.getName() + " Окончательная цена: " + x.getPrice() +
                " Цена до гарантии: " + x.getPriceWithOutGuarantee() + " Лет гарантии: " + x.getGuaranteeYear()));
    }

    public  int allPriceBasket() {
        return basket.stream().mapToInt(Product::getPrice).sum();
    }

    public static Product findObject(String text){
        return basket.stream().filter(x->x.getName().equals(text)).findFirst().orElse(null);
    }

    public void removeItemFromBasket(String name){
        basket.remove(findObject(name));
    }

    public void allProductCheckPriceTest(){
        BasketPage basketPage = new BasketPage();


        for (Product product : basket) {
            Assert.assertEquals("Сумма товара " + product.getName() + " не совпадает с суммой товара при добавлении!",
                    basketPage.checkItemPrice(product.getName()),
                    product.getPrice());
        }

    }
}
