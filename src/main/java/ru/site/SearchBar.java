package ru.site;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.site.pages.BasePage;

public class SearchBar extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchInput;

    public void searchAndEnter(String text){
        waitElementClickable(searchInput).sendKeys(text + "\n");

    }
}
