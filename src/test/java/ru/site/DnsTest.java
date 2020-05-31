package ru.site;

import org.junit.Assert;
import org.junit.Test;
import ru.site.pages.*;

public class DnsTest extends BaseTest{

    @Test
    public void dnsTest() throws InterruptedException {

        // Товары
        String productOne = "Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры";
        String productTwo = "Игра Detroit: Стать человеком (PS4)";


        new SearchBar().searchAndEnter("playstation"); // поиск playstation
        ResultPage resultsPage = new ResultPage();
        resultsPage.chooseByPartialNameFast(productOne); // кликнуть по playstation 4 slim black

        ProductPage productPage = new ProductPage();
        productPage.setPrice(productPage.savePrice());  // запомнить цену

        productPage.addToBasket();                     // добавить в корзину


        new SearchBar().searchAndEnter(productTwo);  // поиск Detroit
        productPage.setPrice(productPage.savePrice()); // запомнить цену
        productPage.addToBasket();                      // добавить в корзину

        VirtualBasket virtualBasket = new VirtualBasket();
        BasePage basePage = new BasePage();

        basePage.clickOnBasket();                   // перейти в корзину

        BasketPage basketPage = new BasketPage();

        virtualBasket.allProductCheckPriceTest();  // проверить цену каждого из товаров и сумму

        basketPage.clickOnGuarantee24(VirtualBasket.findObject(productOne)); // В корзине для playstation Доп.гарантия - выбрать 24 месяца + запомнить цену с гарантией

        basketPage.removeBasket(productTwo);                // удалить из корзины Detroit

        basketPage.checkProductOnRealBasket(productTwo); // проверить что Detroit нет больше в корзине
        basketPage.equalsBasketAndVirtualBasket();      //  проверить что сумма уменьшилась на цену Detroit

        basketPage.AddProductPlus(productOne, 2);   //  добавить еще 2 playstation (кнопкой +)

        basketPage.equalsBasketAndVirtualBasket();   // проверить что сумма верна (равна 3*(цена playstation+гарантия))

        basketPage.removeBasket(productOne);        //  удалить (кнопка "удалить") Playstation из корзины

        basketPage.recoveryProduct();    // нажать вернуть удаленный товаров

        basketPage.equalsBasketAndVirtualBasket();     // проверить что 3 playstation снова в корзине и выбрана гарантия 24 месяца





    }
}
