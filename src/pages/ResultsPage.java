package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {


    private WebDriver driver;

    private final static String RESULT_COUNT_LOCATOR = "//span[@class='pagination-text']";
    private final static String EXPECTED_RESULTS_PAGE_TITLE  = "Search | Vancouver Public Library | BiblioCommons";
    private final static String EXPECTED_RESULTS_PAGE_URL = "https://vpl.bibliocommons.com/v2/search";

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

        WebElement label = driver.findElement(
            By.xpath(RESULT_COUNT_LOCATOR));

        String resultCountText = label.getText();
        return extractNumberFromResultCountText(resultCountText);
    }


    private int extractNumberFromResultCountText(String resultCountText) {
        int startIndex = resultCountText.indexOf("of") + 3;
        int endIndex = resultCountText.indexOf(" results");
        return Integer.parseInt(resultCountText
                .substring(startIndex, endIndex));
    }


}
