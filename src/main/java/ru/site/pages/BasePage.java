package ru.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.site.Init;

import java.util.function.Function;


public class BasePage {
    WebDriver driver;
    WebDriverWait wait;



    public BasePage() {
        driver = Init.getDriver();
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitElementClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }



    public WebElement waitElementVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = "//span[@class='cart-link__price']")
    WebElement mainBasket;

    @FindBy(xpath = "//a[@class='cart-modal__button button-ui button-ui_brand']")
    WebElement popupWindowBasket;

    public int priceBasketOnMainPageAfterAddBuy() {
        waitElementClickable(popupWindowBasket);
        String price = mainBasket.getText().replace(" ", "");
        return Integer.parseInt(price);
    }

    public int priceBasketOnMainPage() {

        String price = mainBasket.getText().replace(" ", "");
        return Integer.parseInt(price);
    }

    public void waitElementRefreshing(long oldBasketPrice) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(webDriver -> {
            long totalPrice = getTotalBasketPrice();
            return totalPrice != oldBasketPrice;
        });
    }

    public long getTotalBasketPrice() {
        return Long.parseLong(waitElementClickable(mainBasket).getText().replaceAll(" ", ""));
    }


    public void clickOnBasket(){
        String basket = "//span[@class='cart-link__lbl']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(basket)));
        driver.findElement(By.xpath(basket)).click();
    }

}
