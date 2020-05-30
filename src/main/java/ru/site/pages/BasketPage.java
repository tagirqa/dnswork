package ru.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.site.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {


    final String itemTemplateBasket = "//a[@class='cart-items__product-name-link' and contains(text(), '%s')]/../../following-sibling::div//span[@class='price__current']";

    @FindBy(xpath = "//span[@class='price__current']")
    WebElement priceText;



    public  int checkItemPrice(String name) {
        By locator = By.xpath(String.format(itemTemplateBasket, name));
        String price;
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        price = driver.findElement(locator).getText().replace(" ", "");
        return Integer.parseInt(price);
    }


}

