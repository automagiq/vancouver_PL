package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {


    private WebDriver driver;

    private final static By RESULT_COUNT_LOCATOR = By.xpath("//span[@class='pagination-text']");
    private final static String EXPECTED_RESULTS_PAGE_TITLE  = "Search | Vancouver Public Library | BiblioCommons";
    private final static String EXPECTED_RESULTS_PAGE_URL = "https://vpl.bibliocommons.com/v2/search";

    private final static By SECOND_RESULT_LINK = By.xpath ("(//a[@data-key='bib-title']) [2]");
    private final static By SECOND_RESULT_TITLE = By.xpath ("(//a[@data-key='bib-title']) [2]/span[@class='title-content']");


    public ResultsPage(WebDriver driver) {  this.driver = driver;}


    public boolean isOpen() {
        boolean isTitleCorrect = driver.getTitle()
                .equalsIgnoreCase(
                        EXPECTED_RESULTS_PAGE_TITLE);
        boolean isUrlCorrect = driver.getCurrentUrl()
                .contains(
                        EXPECTED_RESULTS_PAGE_URL);
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
        return Integer.parseInt(resultCountText
                .substring(startIndex, endIndex));
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

}
