package ru.site;

import org.junit.Assert;
import ru.site.pages.BasketPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualBasket {

    public static List<Product> basket = new ArrayList<>();

    private static List<Product> deleteProduct = new ArrayList<>();

    public static List<Product> getDeleteProduct() {
        return deleteProduct;
    }

    public void allBasket() {
        if (basket.size() == 0) System.out.println("Корзина пуста!");
        basket.forEach(x -> System.out.println("Наименование: " + x.getName() + " Окончательная цена: " + x.getPrice() +
                " Лет гарантии: " + x.getGuaranteeYear()));
    }

    public int allPriceBasket() {
        int sum = 0;
        for (Product product : basket){
            if (product.getPriceWithGuarantee()!=0) {
                sum += product.getPriceWithGuarantee() * product.getCount();
            }
            else sum+= product.getPrice() * product.getCount();
        }
        return sum;
    }

    public static Product findObject(String text) {
        return basket.stream().filter(x -> x.getName().equals(text)).findFirst().orElse(null);
    }

    public static Product findObjectDelete(String text) {
        return deleteProduct.stream().filter(x -> x.getName().equals(text)).findFirst().orElse(null);
    }

    public static void removeItemFromBasket(Product product) {
        basket.remove(product);
    }

    public void allProductCheckPriceTest() {
        BasketPage basketPage = new BasketPage();


        for (Product product : basket) {

            Assert.assertEquals("Сумма товара " + product.getName() + " не совпадает с суммой товара при добавлении!",
                    basketPage.checkItemPrice(product.getName()),
                    product.getPrice());
        }

    }


}

