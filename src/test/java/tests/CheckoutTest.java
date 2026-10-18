package tests;

import driver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProductPage productPage = new ProductPage(DriverManager.getDriver());
        CartPage cartPage = new CartPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        productPage.addBackpackToCart();
        productPage.openCart();
        cartPage.clickCheckout();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.enterCustomerInfo("Deniz", "Test", "5000");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderSuccessful(),
                "Sifariş uğurla tamamlanmadı!"
        );
    }

    @Test
    public void checkoutWithEmptyFieldsTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProductPage productPage = new ProductPage(DriverManager.getDriver());
        CartPage cartPage = new CartPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        productPage.addBackpackToCart();
        productPage.openCart();

        cartPage.clickCheckout();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.clickContinue();

        Assert.assertTrue(
                checkoutPage.getErrorMessage().contains("Error: First Name is required"),
                "Xəta mesajı görünmədi!"
        );
    }
}