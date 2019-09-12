package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {


    private WebDriver driver;


    private final static String URL = "http://www.vpl.ca";


    private final static  By SEARCH_TEXT_BOX_ID = By.id("edit-search");
    private final static  By SEARCH_BUTTON_LOCATOR = By.id("edit-submit");

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

       public  void open() {

        this.driver.get(URL);
    }


    public void searchFor(String keyword) {
        typeKeyword(keyword);
       executeSearch();
    }

     private void typeKeyword(String keyword) {
        WebElement searchTextBox = driver.findElement(SEARCH_TEXT_BOX_ID);
        searchTextBox.clear();
        searchTextBox.sendKeys(keyword,Keys.TAB);

    }

      private void executeSearch() {
        WebElement searchTextButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchTextButton.click();
    }

      public String title () {
        return this.driver.getTitle();

    }

}
