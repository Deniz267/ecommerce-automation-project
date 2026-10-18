package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By firstNameInput = By.id("first-name");
    By lastNameInput = By.id("last-name");
    By postalCodeInput = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By successHeader = By.className("complete-header");
    By errorMessage = By.cssSelector("[data-test='error']");

    public void enterCustomerInfo(String firstName, String lastName, String zipCode) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement postalField = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput));
        postalField.clear();
        postalField.sendKeys(zipCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isOrderSuccessful() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(successHeader, "Thank you for your order!"));
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return error.getText();
    }
}