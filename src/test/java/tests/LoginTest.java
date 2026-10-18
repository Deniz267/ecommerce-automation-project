package tests;

import driver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        boolean isInventoryLoaded = wait.until(ExpectedConditions.urlContains("inventory.html"));

        Assert.assertTrue(isInventoryLoaded, "Giriş uğurla baş tutmadı!");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Xəta mesajı görünmədi!"
        );
    }

    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Çıxış əməliyyatı
        loginPage.logout();

        // Çıxış edildikdən sonra yenidən login səhifəsinə qayıtdığını yoxlayırıq
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        boolean isLoggedOut = wait.until(ExpectedConditions.urlContains("saucedemo.com"));

        Assert.assertTrue(isLoggedOut, "Çıxış əməliyyatı uğurla yerinə yetirilmədi!");
    }
}