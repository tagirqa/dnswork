package ru.site;

import org.junit.Test;
import ru.site.pages.*;

public class DnsTest extends BaseTest{

    @Test
    public void dnsTest() throws InterruptedException {

        // Товары
        String productOne = "Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры";
        String productTwo = "Игра Detroit: Стать человеком (PS4)";


        new SearchBar().searchAndEnter("playstation"); // поиск playstation
        BasePage basePage = new BasePage();
        ResultPage resultsPage = new ResultPage();
        resultsPage.chooseByPartialNameFast(productOne); // кликнуть по playstation 4 slim black

        ProductPage productPage = new ProductPage();
        productPage.setPrice(productPage.savePrice());  // запомнить цену

        productPage.addToBasket();                     // добавить в корзину


        new SearchBar().searchAndEnterOneProduct(productTwo);  // поиск Detroit
        productPage.setPrice(productPage.savePrice()); // запомнить цену
        productPage.addToBasket();                      // добавить в корзину

        VirtualBasket virtualBasket = new VirtualBasket();


        basePage.clickOnBasket();                   // перейти в корзину

        BasketPage basketPage = new BasketPage();

        virtualBasket.allProductCheckPriceTest();  // проверить цену каждого из товаров и сумму

        basketPage
                .clickOnGuarantee24(VirtualBasket.findObject(productOne)) // В корзине для playstation Доп.гарантия - выбрать 24 месяца + запомнить цену с гарантией
                .removeBasket(productTwo)                // удалить из корзины Detroit
                .checkProductOnRealBasket(productTwo) // проверить что Detroit нет больше в корзине
                .equalsBasketAndVirtualBasket()      //  проверить что сумма уменьшилась на цену Detroit
                .addProductPlus(productOne, 2)   //  добавить еще 2 playstation (кнопкой +)
                .equalsBasketAndVirtualBasket()   // проверить что сумма верна (равна 3*(цена playstation+гарантия))
                .removeBasket(productOne)        //  удалить (кнопка "удалить") Playstation из корзины
                .recoveryProduct()    // нажать вернуть удаленный товаров
                .equalsBasketAndVirtualBasket();     // проверить что 3 playstation снова в корзине и выбрана гарантия 24 месяца





    }
}
