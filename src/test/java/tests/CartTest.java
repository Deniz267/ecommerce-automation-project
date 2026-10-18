package tests;

import driver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

import java.time.Duration;

public class CartTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUpTest() {

        loginPage = new LoginPage(DriverManager.getDriver());
        productPage = new ProductPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10)
        );

        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    @Test
    public void productIsDisplayedInCartTest() {

        productPage.addBackpackToCart();
        productPage.openCart();

        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Məhsul səbətdə görünmür!"
        );
    }

    @Test
    public void removeProductFromCartTest() {

        productPage.addBackpackToCart();
        productPage.openCart();

        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Məhsul səbətə əlavə olunmadı!"
        );

        cartPage.removeBackpack();

        Assert.assertTrue(
                cartPage.isBackpackRemoved(),
                "Məhsul səbətdən silinmədi!"
        );
    }
}