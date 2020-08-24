package pages;

import org.openqa.selenium.WebElement;

public interface GeneralSearchPage {

    public void search(WebElement input, WebElement button, String text);
}
