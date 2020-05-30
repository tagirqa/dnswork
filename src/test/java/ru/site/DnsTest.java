package ru.site;

import org.junit.Assert;
import org.junit.Test;
import ru.site.pages.*;

public class DnsTest extends BaseTest{

    @Test
    public void dnsTest() throws InterruptedException {
        new SearchBar().searchAndEnter("Playstation");
        ResultPage resultsPage = new ResultPage();
        resultsPage.chooseByPartialNameFast("Игровая приставка PlayStation 4 Slim Black");
        ProductPage productPage = new ProductPage();
        productPage.setPrice(productPage.savePrice());
        productPage.selectChoice(2);
        productPage.setPrice(productPage.savePrice());
        VirtualBasket virtualBasket = new VirtualBasket();
        productPage.addToBasket();

        new SearchBar().searchAndEnter("Detroit");
        productPage.setPrice(productPage.savePrice());
        productPage.addToBasket();

        virtualBasket.allBasket();

        BasePage basePage = new BasePage();


//        Assert.assertEquals("Сумма в корзине и ожидаемая сумма не совпадают!",
//                basePage.priceBasketOnMainPage(),
//                basketPage.allPriceBasket());

        basePage.clickOnBasket();

        BasketPage basketPage = new BasketPage();
        basketPage.checkItemPrice("Detroit");

//        virtualBasket.allProductCheckPriceTest();
        virtualBasket.removeItemFromBasket("Игра Detroit: Стать человеком (PS4)");
        virtualBasket.allBasket();

        System.out.println(basePage.priceBasketOnMainPage());
        System.out.println(virtualBasket.allPriceBasket());




    }
}
