package ru.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.site.pages.BasePage;

public class SearchBar extends BasePage {


    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchInput;

    @FindBy(xpath = "//div[@class='n-catalog-product__main']")
    WebElement main;

    public void searchAndEnter(String text){
        waitElementClickable(searchInput).sendKeys(text + "\n");
        waitElementVisible(main);

    }
    public void searchAndEnterOneProduct(String text){
        waitElementClickable(searchInput).sendKeys(text + "\n");


    }
}
