package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DetailsPage {

    private WebDriver driver;

    private final static By RESULT_TITLE = By.xpath ("//h1[@testid='text_bibtitle'] ");
    private final static String EXPECTED_URL = "https://vpl.bibliocommons.com/item/show/";

    private final static By RESULT_SUBTITLE = By.xpath("//h2[@class='subTitle']");
    private final static By RESULT_AUTHOR = By.xpath("//a[@testid='author_search']");

    public DetailsPage(WebDriver driver) {  this.driver = driver;}


    public boolean isOpen() {

        return driver.getCurrentUrl()
                .contains(
                        EXPECTED_URL);

    }


    public String resultTitle () {
        WebElement element = driver.findElement(RESULT_TITLE);
        return element.getText();
    }

    public String resultSubTitle () {
        WebElement element = driver.findElement(RESULT_SUBTITLE);
        return element.getText();
    }

    public String resultAuthor () {
        WebElement element = driver.findElement(RESULT_AUTHOR);
        return element.getText();
    }

}
