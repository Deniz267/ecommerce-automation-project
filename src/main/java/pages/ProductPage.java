package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By cartIcon = By.className("shopping_cart_link");
    By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    By cartBadge = By.className("shopping_cart_badge");

    public void addBackpackToCart() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addBtn.click();
    }

    public void openCart() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cart.click();

        // Sepet sayfasının (cart.html) yüklenmesini kesin olarak bekliyoruz
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public String getCartCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
            return badge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
}