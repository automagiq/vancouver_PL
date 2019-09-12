package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.Test;

public class SearchTest {

    String homePageTitle = "Vancouver Public Library |";
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/zafar/Documents/chromedriver");
       // ChromeOptions options = new ChromeOptions();

       // ChromeDriver drv = new ChromeDriver(ChromeDriverService.createDefaultService(), options);

        driver = new ChromeDriver();
        //System.setProperty("webdriver.gecko.driver", "/Users/zafar/Documents/Alex Siminiuc/POM/drivers/geckodriver/geckodriver");
        //driver = new FirefoxDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchReturnsResults() {

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertEquals(homePageTitle, homePage.title());
        homePage.searchFor("java");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertTrue(resultsPage.isOpen() == true);
        Assert.assertTrue(resultsPage.resultCount() > 0);

    }




}
