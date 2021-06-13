package org.soft_fx;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;
    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//  Locator search Sign-In button
    @FindBy(css = ".header__auth.text_theme_auth")
    private WebElement signInButton;

//    Search dropbox for language selection
    @FindBy(xpath="//select[@id='list-language']")
    private WebElement selectLanguage;

//    Switch to russian
    public void dropdownClickRussian() {
        Select dropdown = new Select(selectLanguage);
        dropdown.selectByVisibleText("Русский");
    }

//    Search dropdown for currency control
    @FindBy(css = ".k-icon.k-i-arrow-60-down._image.icon-drop-down-w")
    private WebElement controlCurrency;

//    Click on dropdown
    public void clickControlCurrency(){
        controlCurrency.click();
    }

    @FindBy(xpath = "//select[@id='list-symbols']")
    private WebElement selectCurrency;

    public void selectLTCUSD(){
        Select dropdown = new Select(selectCurrency);
        dropdown.selectByVisibleText("LTCUSD");
    }

    @FindBy(xpath = "//span[@id='list-language']")
    private WebElement controlToChoseLanguage;

    public void clickControlToChoseLanguage(){
        controlToChoseLanguage.click();
    }

    @FindBy(xpath = "//div[@class='header__language']/span[@aria-expanded='true']")
    private WebElement languageArea;

    public void languageAreaIsVisible(){
        languageArea.isDisplayed();
    }

    @FindBy(id = "open-market-watch")
    private WebElement buttonMarketWatch;

    @FindBy(id = "exchange")
    private WebElement exchangeTab;

    @FindBy(id = "advanced-limit-trade")
    private WebElement buttonPlaceLimit;

    public void textButtonIsCorrect(String language){
        if (language.equals("en")) {
            Assert.assertEquals(buttonPlaceLimit.getText(), "PLACE LIMIT ");
            Assert.assertEquals(linkTutorial.getText(), "Tutorial");
            Assert.assertEquals(exchangeTab.getText(), "EXCHANGE");
            Assert.assertEquals(buttonMarketWatch.getText(), "MARKET WATCH");
        }
        else if (language.equals("ru")) {
            Assert.assertEquals(buttonPlaceLimit.getText(), "РАЗМЕСТИТЬ LIMIT ");
            Assert.assertEquals(linkTutorial.getText(), "Обучение");
            Assert.assertEquals(exchangeTab.getText(), "ОБМЕНЯТЬ");
            Assert.assertEquals(buttonMarketWatch.getText(), "ОБЗОР РЫНКА");
        }
    }
    @FindBy(id = "tutorial-steps")
    private WebElement linkTutorial;


    @FindBy(xpath = "//div[@class = 'title-bcHj6pEn title1st-2at68hKe apply-overflow-tooltip withDot-VrJCTK--'][contains(text(), 'LTCUSD')]")
    private WebElement ltcUsdIsPresent;

    public void LTCUSDIsPresent(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class = 'title-bcHj6pEn title1st-2at68hKe apply-overflow-tooltip withDot-VrJCTK--'][contains(text(), 'LTCUSD')]")));
        Assert.assertEquals(ltcUsdIsPresent.getText(), "LTCUSD");
    }

    @FindBy(xpath = "//li/a[@href='/trading']")
    private WebElement linkTradingPlatform;

    public void clickOnTradingPlatform() throws InterruptedException {
        String currentURL = driver.getCurrentUrl();
        linkTradingPlatform.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            Thread.sleep (3000);
        }
        String url = driver.getCurrentUrl();
        String currentUrl = "https://demo-ticktrader.free2ex.com/trading";
        Assert.assertEquals(currentUrl, url);
    }

    public void inDropdownTwoLanguages(){
        Select dropdown = new Select(selectLanguage);
        for (WebElement option : dropdown.getOptions()){
            if (option.getText().equals("Русский") | option.getText().equals("English")){
                assert true;
            }
        }
    }
}
