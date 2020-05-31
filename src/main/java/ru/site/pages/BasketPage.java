package ru.site.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.site.Product;
import ru.site.VirtualBasket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BasketPage extends BasePage {


    final String itemTemplateBasket = "//a[@class='cart-items__product-name-link' and contains(text(), '%s')]/../../following-sibling::div//span[@class='price__current']";

    @FindBy(xpath = "//span[@class='price__current']")
    WebElement priceText;

    @FindBy(xpath = "//div[@class='cart-items']")
    WebElement cartItems;

    final String productCartItemsTemplate = "(//a[contains(text(),'%s')]/../../../../following-sibling::div[@class='cart-items__additionals']//div[@class='slider__elements'])[1]";

    final String productCartDeleteTemplate = "//a[contains(text(),'%s')]/../../div[@class='menu-product menu-product_position-absolute']//button[contains(text(),'Удалить')]";


    @FindBy(xpath = "//div[@class='additional-warranties-row__slider-wrapper']//div[@class='slider']")
    WebElement productGuaranteeBar;


    public void clickOnGuarantee24(Product product) {
        By locator = By.xpath(String.format(productCartItemsTemplate, product.getName()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement productCart = driver.findElement(locator);
        WebElement productGuarantee24 = productCart.findElement(By.xpath("(//span[@class='base-ui-radio-button__icon'])[2]"));
        productGuarantee24.click();
        WebElement sumAfterClickGuarantee = productGuarantee24.findElement(By.xpath("//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']/..//following-sibling::span"));
        String sum = sumAfterClickGuarantee.getText().replaceAll("[^0-9]", "");
        product.setPriceWithGuarantee(Integer.parseInt(sum) + product.getPrice());
        product.setGuaranteeYear(2);
    }

    public int checkItemPrice(String name) {
        By locator = By.xpath(String.format(itemTemplateBasket, name));
        String price;
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        price = driver.findElement(locator).getText().replace(" ", "");
        return Integer.parseInt(price);
    }

    public void removeBasket(String name) {
        VirtualBasket.getDeleteProduct().add(VirtualBasket.findObject(name));
        VirtualBasket.removeItemFromBasket(VirtualBasket.findObject(name));

        By locator = By.xpath(String.format(productCartDeleteTemplate, name));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement deleteButton = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }

    public void equalsBasketAndVirtualBasket() {
        VirtualBasket virtualBasket = new VirtualBasket();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-link__price']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='menu-control-button'][contains(text(),'Удалить')]")));

        Assert.assertEquals("Сумма в корзине не равна ожидаемой сумме!", virtualBasket.allPriceBasket(), priceBasketOnMainPage());
    }

    public void AddProductPlus(String name, int i) throws InterruptedException {

        By locator = By.xpath(String.format("//a[contains(text(),'%s')]/../../following-sibling::div//i[@class='count-buttons__icon-plus']", name));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement button = driver.findElement(locator);
        while (i != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
            i -= 1;
            VirtualBasket.findObject(name).setCount(VirtualBasket.findObject(name).getCount() + 1);
            Thread.sleep(2000);
        }
    }

    public void recoveryProduct() {
        String xpath = "//a[@class='empty-message-restore-btn ui-link_pseudolink']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        WebElement recovery = driver.findElement(By.xpath(xpath));
        recovery.click();
        String xpathRecovery = "//a[@class='cart-items__product-name-link']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRecovery)));
        WebElement productRecovery = driver.findElement(By.xpath(xpathRecovery));
        VirtualBasket.basket.add(VirtualBasket.findObjectDelete(productRecovery.getText()));
    }

    public void checkProductOnRealBasket(String name) {
        String nameProduct = "//a[contains(text(),'%s')][1]";
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nameProduct)));
            WebElement prod = driver.findElement(By.xpath(String.format(nameProduct, name)));
        }
        catch (Exception e) {
            System.out.println("Элемента: " + name + " больше нет в корзине!");
        }
    }

}

