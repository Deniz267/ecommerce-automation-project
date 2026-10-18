package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    By backpackItem = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");
    By removeButton = By.id("remove-sauce-labs-backpack");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isBackpackDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(backpackItem)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void removeBackpack() {
        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)
        ).click();
    }

    public boolean isBackpackRemoved() {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(backpackItem)
        );
    }

    public void clickCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}