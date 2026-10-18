package tests;

import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(DriverManager.getDriver());
        productPage = new ProductPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Test
    public void productsHeaderIsDisplayedTest() {
        Assert.assertTrue(
                DriverManager.getDriver().getPageSource().contains("Products"),
                "Products başlığı tapilmadi!"
        );
    }

    @Test
    public void backpackIsDisplayedTest() {
        Assert.assertTrue(
                DriverManager.getDriver().getPageSource().contains("Sauce Labs Backpack"),
                "Mehsul sehifede görünmür!"
        );
    }

    @Test
    public void cartCountTest() {
        productPage.addBackpackToCart();

        Assert.assertEquals(productPage.getCartCount(), "1", "Sepetdeki mehsul sayısı xetali!");
    }
}