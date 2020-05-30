package ru.site.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.site.Product;
import ru.site.VirtualBasket;

public class ProductPage extends BasePage {
    private int price;
    private int priceBefore;
    private int guaranteeYear;

    public int getGuaranteeYear() {
        return guaranteeYear;
    }

    public void setGuaranteeYear(int guaranteeYear) {
        this.guaranteeYear = guaranteeYear;
    }

    public int getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(int priceBefore) {
        this.priceBefore = priceBefore;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @FindBy(xpath = "//div[@class='hidden-xs hidden-sm price-block-wrap price-block-wrap_view_desktop']//div[@class='price_g']//span[@data-price-value]")
    WebElement productPriceField;

    @FindBy(xpath = "//select[@class='form-control select']")
    WebElement guaranteeField;

    @FindBy(xpath = "//div[@class='hidden-xs hidden-sm price-block-wrap price-block-wrap_view_desktop']//div[@class='price-changed-alert']")
    WebElement changePrice;

    @FindBy(id = "prod-card-buy-btn")
    WebElement buttonBuy;

    @FindBy(xpath = "//h1[@data-product-param='name']")
    WebElement textField;


    public void selectChoice(int year){
        setGuaranteeYear(year);
        if (getGuaranteeYear() == 2 || getGuaranteeYear() == 1) {
            waitElementClickable(guaranteeField);
            Select selectGuaranteeField = new Select(guaranteeField);
            selectGuaranteeField.selectByIndex(getGuaranteeYear());
            waitElementVisible(changePrice);
        }
        setPriceBefore(getPrice());
    }

    public int savePrice(){
        String newPrice = productPriceField.getText().replace(" ", "");
        return Integer.parseInt(newPrice);
    }

    public void addToBasket(){
        waitElementClickable(buttonBuy);
        String productNameText = waitElementVisible(textField).getText();

        VirtualBasket.basket.add(new Product(getPrice(), getPriceBefore(), productNameText, getGuaranteeYear()));
        buttonBuy.click();
        setGuaranteeYear(0);
        setPrice(0);
        setPriceBefore(0);
    }
}
