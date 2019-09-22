package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class ResultsPage {


    private WebDriver driver;

    private final static By RESULT_COUNT_LOCATOR = By.xpath("//span[@class='pagination-text']");
    private final static String EXPECTED_RESULTS_PAGE_TITLE  = "Search | Vancouver Public Library | BiblioCommons";
    private final static String EXPECTED_RESULTS_PAGE_URL = "https://vpl.bibliocommons.com/v2/search";

    private final static By SECOND_RESULT_LINK = By.xpath ("(//a[@data-key='bib-title']) [2]");
    private final static By SECOND_RESULT_TITLE = By.xpath ("(//a[@data-key='bib-title']) [2]/span[@class='title-content']");

    private final static By SECOND_SUBTITLE =By.xpath("(//span[@class='cp-subtitle'])[2]");
    private final static By AUTHOR = By.xpath("(//a[@href='/v2/search?query=Naughton, Patrick&searchType=author'])[1]");

    private final static By NEXTBTN = By.xpath(("//a[@data-key='next-btn']"));



    public ResultsPage(WebDriver driver) {  this.driver = driver;}


    public boolean isOpen() {
        boolean isTitleCorrect = driver.getTitle().equalsIgnoreCase(EXPECTED_RESULTS_PAGE_TITLE);
        boolean isUrlCorrect = driver.getCurrentUrl().contains(EXPECTED_RESULTS_PAGE_URL);
        return isTitleCorrect && isUrlCorrect;
    }

    public int resultCount() {
        WebElement label = driver.findElement(RESULT_COUNT_LOCATOR);
        String resultCountText = label.getText();
        return extractNumberFromResultCountText(resultCountText);
    }

    private int extractNumberFromResultCountText(String resultCountText) {
        int startIndex = resultCountText.indexOf("of") + 3;
        int endIndex = resultCountText.indexOf(" results");
        return Integer.parseInt(resultCountText.substring(startIndex, endIndex));
    }

    public String startNumber() {
        WebElement label = driver.findElement(RESULT_COUNT_LOCATOR);
        String resultCountText = label.getText();
        int startIndex = resultCountText.indexOf("11 ");
        int endIndex = resultCountText.indexOf(" to");
        String firstNum = resultCountText.substring(startIndex, endIndex);
        // convert to Integer
        return firstNum;
    }

    public String nextPageStartNum() {
        WebElement label = driver.findElement(RESULT_COUNT_LOCATOR);
        String startNum = label.getText();
        int startIndex = startNum.indexOf("11 ");
        int endIndex = startNum.indexOf(" ");
        String firstNum = startNum;
        // convert to Integer
        return firstNum;
    }

    public int endNumber() {
        WebElement label = driver.findElement(RESULT_COUNT_LOCATOR);
        String resultCountText = label.getText();
        int startIndex = resultCountText.indexOf("to") + 3;
        int endIndex = resultCountText.indexOf(" of");
        return Integer.parseInt(resultCountText.substring(startIndex,endIndex));
    }
    public String resultTitle () {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement titleElement  = wait.until(ExpectedConditions.visibilityOfElementLocated(SECOND_RESULT_TITLE));
        return  titleElement.getText();
        }

    public void selectSecondResult() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement titleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(SECOND_RESULT_LINK));
        titleLink.click();
    }

    public void selectNextBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement next = wait.until(ExpectedConditions.visibilityOfElementLocated(NEXTBTN));
        next.click();
    }

    public String resultSubTitle () {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement resultSubTitle  = wait.until(ExpectedConditions.visibilityOfElementLocated(SECOND_SUBTITLE));
        return  resultSubTitle.getText();
    }


    public String resultsAuthor() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement resultsAuthor  = wait.until(ExpectedConditions.visibilityOfElementLocated(AUTHOR));
        return  resultsAuthor.getText();

    }






}
