package org.soft_fx;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MainTest {
    public static BasePage basePage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProperties
                .getProperty("chromeDriver"));
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("basePageUrl"));
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector(".header__auth.text_theme_auth")));
    }

    @Test
    public void baseTest() throws InterruptedException, NoSuchElementException, ElementNotInteractableException {
        basePage.clickControlToChoseLanguage();
        basePage.languageAreaIsVisible();
        try {
            basePage.inDropdownTwoLanguages();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        basePage.textButtonIsCorrect("en");
        try {
            basePage.clickControlToChoseLanguage();
            basePage.dropdownClickRussian();
            basePage.textButtonIsCorrect("ru");
        } catch (NoSuchElementException | ElementNotInteractableException e)  {
            System.out.println(e.getMessage());
        }

        basePage.clickControlCurrency();
        try {
            basePage.selectLTCUSD();
            basePage.LTCUSDIsPresent();
        } catch (ElementNotInteractableException e){
            System.out.println(e.getMessage());
        }
        basePage.clickOnTradingPlatform();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
