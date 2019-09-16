package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DetailsPage;
import pages.HomePage;
import pages.ResultsPage;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.Test;

public class SearchTest {

    String homePageTitle = "Vancouver Public Library |";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/zafar/Documents/chromedriver");
       // ChromeOptions options = new ChromeOptions();

       // ChromeDriver drv = new ChromeDriver(ChromeDriverService.createDefaultService(), options);

        driver = new ChromeDriver();
        //System.setProperty("webdriver.gecko.driver", "/Users/zafar/Documents/Alex Siminiuc/POM/drivers/geckodriver/geckodriver");
        //driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchReturnsResults() {

        HomePage homePage = new HomePage(driver);
        homePage.open();

        wait.until(ExpectedConditions.titleIs(homePageTitle));
        Assert.assertEquals(homePageTitle, homePage.title());

        homePage.searchFor("java");

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.isOpen() == true);
        Assert.assertTrue(resultsPage.resultCount() > 0);

        String resultsTitle = resultsPage.resultTitle();
        resultsPage.selectSecondResult();

        DetailsPage detailsPage = new DetailsPage(driver);
        Assert.assertTrue(detailsPage.isOpen() == true);

        String detailsTitle = detailsPage.resultTitle();

        Assert.assertEquals(resultsTitle, detailsTitle);

        }

    }






