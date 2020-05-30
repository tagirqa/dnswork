package ru.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultPage extends BasePage {


    public ResultPage() throws InterruptedException {
        Thread.sleep(2000);
        waitElementClickable(titlesElements.get(0));
    }

    //
//    @FindBy(xpath = "top-filter__selected")
//    WebElement lowLoad;
    final String resultBaseXpath = "(//div[@class='catalog-items-list view-simple'])[1]";

    @FindBy(xpath = "//a[@data-role='clamped-link']")
    List<WebElement> titlesElements;

    final String itemTitleTemplate = "//a[@data-role='clamped-link' and contains(text(), '%s')]";

    public void chooseByPartialNameFast(String name) {
        By locator = By.xpath(String.format(itemTitleTemplate, name));
        driver.findElement(locator).click();
    }
}
